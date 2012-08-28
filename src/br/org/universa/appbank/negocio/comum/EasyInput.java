/* The class EasyInput provides several static methods that make it
   easier to read from the standard input stream, System.in.  The public
   methods defined in this class are:

      public static char getChar()
         -- Reads and returns the next character from System.in.
            If the next thing in system.in is an end-of-line,
            this returns '\n'.  (Note that '\n' is always returned
            by this method to represent end-of-line -- even though
            some systems use '\r' or '\r' followed by '\n' to
            represent end-of-line internally.)

      public static int getInt()
         -- Reads and returns an integer from System.in.  This method will
            skip white space (spaces, tabs, end-of-lines, etc.) before trying
            to read the integer.  If it doesn't find a legal integer, it
            will print an error message on System.out and ask the user
            to try again.  Note that when the method returns, you will
            always get a valid integer that was input by the user.

      public static double getDouble()
         -- Reads and returns a real number from System.in.  This method will
            skip white space (spaces, tabs, end-of-lines) before trying
            to read the number.  If it doesn't find a legal number, it
            will print an error message on System.out and ask the user
            to try again.  Note that when the method returns, you will
            always get a valid integer that was input by the user.
            (Numbers of type double can include decimal points and
            exponents.  For example:  3.14, 23e7, 123.45E-17)

      public static String getln()
         -- Reads characters up to and including the next end-of-line.
            Returns a string consisting of all the characters read, EXCLUDING
            the end-of-line.  Note that the returned string can be empty,
            Often, you will call getln() without using the returned value;
            this is a simple way to discard the remainder of an input line
            after reading a character, integer, or double number.

      public static void skipWhiteSpace()
         -- Skips over any while space (tabs, spaces, end-of-line, etc.) in System.in.

      public static char lookChar()
         -- Lets you look ahead at the next character in the input, without
            removing it from the input stream.  Like getChar(), this method
            returns '\n' to represent end-of-line.


   WARNING NOTE:  When you use the input methods from class ConsoleInput,
                  you should NOT read directly from System.in.  (This
                  class actually reads data from System.in in full-line
                  chunks.  There is no easy way to synchronize this with
                  System.in.read() or other direct methods.)  Also, you
                  should not use this if System.in has been changed to
                  be something other than the standard console input stream.

 */
package br.org.universa.appbank.negocio.comum;

import java.io.IOException;

public class EasyInput {
	// ------------------ PRIVATE VARIABLES AND METHODS --------------------
	private static StringBuffer buf = new StringBuffer();
	private static int pos = 1;
	private static boolean checkForLineFeed = false;
	private static StringBuffer tempBuf = new StringBuffer();

	public static char lookChar() {
		if (pos > buf.length()) {
			fillBuffer();
		}

		if (pos == buf.length()) {
			return '\n';
		} else {
			return buf.charAt(pos);
		}
	}

	public static char getChar() {
		char ch = lookChar();
		pos++;

		return ch;
	}

	public static void skipWhiteSpace() {
		while (Character.isSpaceChar(lookChar()))
			getChar();
	}

	public static String getln() {
		if (lookChar() == '\n') {
			pos = buf.length() + 1;

			return "";
		}

		if (pos == 0) {
			pos = buf.length() + 1;

			return buf.toString();
		}

		tempBuf.setLength(0);

		for (; pos < buf.length(); pos++)
			tempBuf.append(buf.charAt(pos));

		pos = buf.length() + 1;

		return tempBuf.toString();
	}

	public static int getInt() {
		long n = 0;

		while (true) {
			boolean neg;
			char ch = lookChar();

			while (Character.isSpaceChar(ch)) {
				if (ch == '\n') {
					System.out.print("? ");
				}

				getChar();
				ch = lookChar();
			}

			if ((ch != '-') && (ch != '+') && !Character.isDigit(ch)) {
				reportError("Found \"" + ch
						+ "\" while looking for an integer.", "integer");

				continue;
			}

			neg = (ch == '-');

			if ((ch == '+') || (ch == '-')) {
				getChar();
				ch = lookChar();

				if (!Character.isDigit(ch)) {
					reportError(
							"Error while reading an integer; character following \""
									+ ch + "\" is not a digit.", "integer");

					continue;
				}
			}

			n = 0;

			while ((n <= Integer.MAX_VALUE) && Character.isDigit(ch)) {
				n = (10 * n) + Character.digit(ch, 10);
				getChar();
				ch = lookChar();
			}

			if (n > Integer.MAX_VALUE) {
				reportError(
						"Integer value in input is too large; largest legal value is "
								+ Integer.MAX_VALUE, "integer");

				continue;
			}

			if (neg) {
				n = -n;
			}

			break;
		}

		return (int) n;
	}

	public static double getDouble() {
		double d = 0.0;

		while (true) {
			char ch = lookChar();

			while (Character.isSpaceChar(ch)) {
				if (ch == '\n') {
					System.out.print("? ");
				}

				getChar();
				ch = lookChar();
			}

			tempBuf.setLength(0);

			boolean neg = (ch == '-');

			if ((ch == '-') || (ch == '+')) {
				getChar();
				ch = lookChar();
			}

			if ((ch != '.') && !Character.isDigit(ch)) {
				reportError("Found \"" + ch
						+ "\" while looking for a real number.", "real number");

				continue;
			}

			while (Character.isDigit(lookChar()))
				tempBuf.append(getChar());

			if (lookChar() == '.') {
				tempBuf.append(getChar());

				while (Character.isDigit(lookChar()))
					tempBuf.append(getChar());
			}

			if ((tempBuf.length() == 1) && (tempBuf.charAt(0) == '.')) {
				reportError("Found number with decimal point but no digits!",
						"real number");

				continue;
			}

			ch = lookChar();

			if ((ch == 'e') || (ch == 'E')) {
				tempBuf.append(getChar());
				ch = lookChar();

				if ((ch == '-') || (ch == '+')) {
					tempBuf.append(getChar());
					ch = lookChar();
				}

				if (!Character.isDigit(ch)) {
					reportError("No digits found in exponent part of number.",
							"real number");

					continue;
				}

				while (Character.isDigit(lookChar()))
					tempBuf.append(getChar());
			}

			Double D = null;

			try {
				D = new Double(tempBuf.toString());
			} catch (NumberFormatException e) {
				D = null;
			}

			if (D == null) {
				reportError("Illegal number, \"" + tempBuf.toString()
						+ "\", found in input.", "real number");

				continue;
			}

			if (D.isInfinite()) {
				reportError("Input number, " + tempBuf.toString()
						+ ", is outside of legal range for real numbers.",
						"real number");

				continue;
			}

			d = D.doubleValue();

			if (neg) {
				d = -d;
			}

			break;
		}

		return d;
	}

	private static void fillBuffer() {
		buf.setLength(0);
		pos = 0;

		try {
			int ch;
			ch = System.in.read();

			if (checkForLineFeed && (ch == '\n')) {
				ch = System.in.read();
			}

			if (ch == -1) {
				return;
			}

			while ((ch != -1) && (ch != '\r') && (ch != '\n')) {
				buf.append((char) ch);
				ch = System.in.read();
			}

			checkForLineFeed = (ch == '\r');
		} catch (IOException e) {
			throw new RuntimeException(
					"Unexpected IO error while reading from System.in in class ConsoleInput.");
		}
	}

	private static void reportError(String message, String type) {
		System.out.println();
		System.out.println("*** " + message);

		if (pos >= buf.length()) {
			System.out.println("*** Discarding input: (end of line)");
			getln();
		} else {
			System.out.println("*** Discarding input: " + getln());
		}

		System.out.println("*** Please try again with a legal " + type + '.');
		System.out.println();
		System.out.print("? ");
	}
}
