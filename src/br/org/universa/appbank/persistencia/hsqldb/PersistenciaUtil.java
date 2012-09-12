package br.org.universa.appbank.persistencia.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Possui algum métodos utilitarios para conexão com banco de dados.
 */
public class PersistenciaUtil {

	/**
	 * Conexao com o banco de dados.
	 */
	private static Connection	conexao;

	private PersistenciaUtil() {

	}

	/**
	 * Recupera uma conexão com o banco de dados, our crie uma caso
	 * ela não exista ainda.
	 * 
	 * @return Conexão com o banco de dados.
	 * @throws RuntimeException
	 */
	public static synchronized Connection getConexao(String dbURL, String dbUser, String dbPassword) throws RuntimeException {

		if (conexao == null) {
			conexao = criarConexao(dbURL, dbUser, dbPassword);
		}
		return conexao;
	}

	/**
	 * Cria conexão com o banco de dados e retorna está conexão.
	 * 
	 * @return Conexão com o banco de dados.
	 * @throws RuntimeException
	 *             Indica que a conexão nao pode ser criada
	 *             devido a algum problema.
	 */
	private static synchronized Connection criarConexao(String dbURL, String dbUser, String dbPassword) throws RuntimeException {

		// Carrega driver do banco de dados e cria conexão com banco
		// em memória.
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conexao = DriverManager.getConnection(dbURL, dbUser, dbPassword);

			return conexao;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Problema ao carregar driver jdbc", e);
		} catch (SQLException e) {
			throw new RuntimeException("Problema ao inicializar banco de dados", e);
		}
	}

	/**
	 * Encerra uma conexão com o banco de dados quando não for mais
	 * utilizada.
	 * 
	 * @throws RuntimeException
	 *             Indica que ocorreu algum problema no
	 *             encerramento da conexão.
	 */
	public static synchronized void encerrarConexao() throws RuntimeException {

		try {
			if (conexao != null) {
				conexao.close();
				conexao = null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Problema o encerrar conexão", e);
		}
	}

	/**
	 * Popular banco de dados com SQL passada como parametro
	 * 
	 * @param sql
	 *            SQL que será executada no banco de dados.
	 * @throws RuntimeException
	 *             Indica que ocorreu algum problema na
	 *             execução da SQL
	 */
	public static synchronized void popularBanco(String sql, String dbURL, String dbUser, String dbPassword) throws RuntimeException {

		if (conexao == null) {
			conexao = criarConexao(dbURL, dbUser, dbPassword);
		}

		// Carrega arquivo com a estrutura das tabelas e executa
		// no banco de dados
		try {
			Statement stmt = conexao.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException("Problema executar sql do arquivo", e);
		}

	}
}
