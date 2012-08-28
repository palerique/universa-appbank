/*
 * PhD Software do Brasil / Universa Escola de Gestão.
 * email - professor.flavio.roberto@gmail.com
 *
 * App Bank - Aplicação Bancária.
 *
 * OBS: Todos os códigos estão sendo oferecidos com a intenção única de
 * estimular o aprendizado. Não podem ser usados com fins comerciais sem
 * autorização prévia do autor. Se redistribuídos para outros sites, o autor e
 * a fonte devem ser sempre citados.
 */
package br.org.universa.appbank.negocio.comum;

import java.math.BigDecimal;

public class UtilHelper {

	public static boolean isLoginValido(String login) {
		return (login
				.matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$"));
	}

	public static boolean isCpfValido(String cpf) {
		int i, soma1, soma2, digito1, digito2;

		if (cpf.length() == 10) {
			cpf = "0" + cpf;
		}

		if (cpf.length() != 11)
			return false;

		if ((cpf.equals("00000000000")) || (cpf.equals("11111111111"))
				|| (cpf.equals("22222222222")) || (cpf.equals("33333333333"))
				|| (cpf.equals("44444444444")) || (cpf.equals("55555555555"))
				|| (cpf.equals("66666666666")) || (cpf.equals("77777777777"))
				|| (cpf.equals("88888888888")) || (cpf.equals("99999999999")))
			return false;

		// Calcula o primeiro d�gito
		soma1 = 0;
		for (i = 0; i <= 8; i++)
			soma1 = soma1 + Integer.parseInt(cpf.substring(i, i + 1))
					* (10 - i);

		if (soma1 % 11 < 2)
			digito1 = 0;
		else
			digito1 = 11 - (soma1 % 11);

		// Calcula o segundo d�gito
		soma2 = 0;
		for (i = 0; i <= 9; i++)
			soma2 = soma2 + Integer.parseInt(cpf.substring(i, i + 1))
					* (11 - i);

		if (soma2 % 11 < 2)
			digito2 = 0;
		else
			digito2 = 11 - (soma2 % 11);

		if ((digito1 == Integer.parseInt(cpf.substring(9, 10)))
				&& (digito2 == Integer.parseInt(cpf.substring(10))))
			return true;

		return false;
	}

	public static boolean isCampoPreenchido(String valor) {
		if (valor == null || valor.trim().equals("")) {
			return false;
		}

		return true;
	}

	public static boolean isCampoPreenchido(Object valor) {
		if (valor == null) {
			return false;
		}

		return true;
	}

	public static boolean isCampoPreenchido(double valor) {
		if (valor <= 0) {
			return false;
		}

		return true;
	}

	public static boolean isCampoPreenchido(int valor) {
		if (valor <= 0) {
			return false;
		}

		return true;
	}

	public static double arredonda(double valor, int numeroDeDecimais) {
		BigDecimal bd = new BigDecimal(Double.toString(valor));
		bd = bd.setScale(numeroDeDecimais, BigDecimal.ROUND_HALF_UP);

		return bd.doubleValue();
	}
}