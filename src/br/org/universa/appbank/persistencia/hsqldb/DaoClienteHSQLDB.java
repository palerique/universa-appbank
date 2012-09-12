// package br.org.universa.appbank.persistencia.hsqldb;
//
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import br.org.universa.appbank.negocio.dominio.Cliente;
// import br.org.universa.appbank.persistencia.DaoCliente;
//
// /**
// *
// * @author ph - palerique@gmail.com
// *
// */
// public abstract class DaoClienteHSQLDB implements DaoCliente {
//
// /**
// * Conexao com banco de dados.
// */
// private Connection conexao;
// private static DaoClienteHSQLDB instancia = null;
// private Map<String, Cliente> clientes = new HashMap<String, Cliente>();
//
// private DaoClienteHSQLDB(Connection connection) {
//
// conexao = connection;
//
// }
//
// public static DaoClienteHSQLDB get() {
//
// Connection connection;
//
// if (instancia == null) {
// // instancia = new DaoClienteHSQLDB(connection);
// }
//
// return instancia;
// }
//
// /**
// * SQL para inserir novos ingredientes no banco de dados.
// */
// private static final String SQL_INSERCAO = "INSERT INTO ingredientes " + "(NOME, VALOR_PORCAO) VALUES (?, ?)";
//
// /**
// * SQL para listar todos ingrediente
// */
// private static final String SQL_LISTAR = "SELECT * FROM " + "ingredientes";
//
// /**
// * SQL para buscar um ingrediente por nome
// */
// private static final String SQL_BUSCA_POR_NOME = "SELECT * FROM " + "ingredientes WHERE nome = ?";
//
// /**
// * SQL para buscar um ingrediente por ID
// */
// private static final String SQL_BUSCA_ID = "SELECT * FROM " + "ingredientes WHERE id = ?";
//
// private static final String SQL_REMOCAO = "DELETE FROM ingredientes WHERE id = ?";
//
// public void inserir(Ingrediente ingrediente) throws RuntimeException {
//
// if (!(ingrediente.validar())) {
// throw new IllegalArgumentException("Ingrediente não válido!");
// }
//
// Ingrediente ingredienteBanco = buscarPorNome(ingrediente.getNome());
// if (ingredienteBanco != null) {
// throw new RuntimeException("Ingrediente com esse nome já existe no banco");
// }
//
// try {
// // Realiza inserção e habilita retorno da chave primária
// // criada pelo banco.
// PreparedStatement ps = conexao.prepareStatement(SQL_INSERCAO, Statement.RETURN_GENERATED_KEYS);
// ps.setString(1, ingrediente.getNome());
// ps.setFloat(2, ingrediente.getValorPorPorcao());
// ps.executeUpdate();
//
// ResultSet novasChavesPrimarias = ps.getGeneratedKeys();
// if (novasChavesPrimarias.next()) {
// ingrediente.setId(novasChavesPrimarias.getLong(1));
// }
//
// } catch (SQLException sqle) {
// throw new RuntimeException("Problema ao adicionar novo ingrediente", sqle);
// }
// }
//
// public List<Ingrediente> listar() throws RuntimeException {
//
// try {
// PreparedStatement ps = conexao.prepareStatement(SQL_LISTAR);
//
// ResultSet rs = ps.executeQuery();
// return carregarIngredientes(rs);
//
// } catch (SQLException sqle) {
// throw new RuntimeException("Problema ao realizar consulta", sqle);
// }
// }
//
// public Ingrediente buscar(Long ingredienteID) throws RuntimeException {
//
// try {
// PreparedStatement ps = conexao.prepareStatement(SQL_BUSCA_ID);
// ps.setLong(1, ingredienteID);
//
// ResultSet rs = ps.executeQuery();
// List<Ingrediente> ingredientes = carregarIngredientes(rs);
// if (ingredientes.size() == 0) {
// return null;
// } else {
// return ingredientes.get(0);
// }
// } catch (SQLException sqle) {
// throw new RuntimeException("Problema ao realizar consulta", sqle);
// }
// }
//
// public Ingrediente buscarPorNome(String nomeIngrediente) throws RuntimeException {
//
// try {
// PreparedStatement ps = conexao.prepareStatement(SQL_BUSCA_POR_NOME);
// ps.setString(1, nomeIngrediente);
//
// ResultSet rs = ps.executeQuery();
// List<Ingrediente> ingredientes = carregarIngredientes(rs);
// if (ingredientes.size() == 0) {
// return null;
// } else {
// return ingredientes.get(0);
// }
// } catch (SQLException se) {
// throw new RuntimeException("Não foi possível realizar a pesquisa", se);
// }
// }
//
// /**
// * Carrega ingredientes de um ResultSet em uma lista de {@code Ingredientes}
// *
// * @param rs
// * ResultSet da consulta feita.
// * @return Retorna lista com instâncias de {@code Ingrediente}
// * @throws SQLException
// * Lançada quando algum problema ocorre com o ResultSet.
// */
// private List<Ingrediente> carregarIngredientes(ResultSet rs) throws SQLException {
//
// List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
// while (rs.next()) {
// Ingrediente ingrediente = new Ingrediente();
// ingrediente.setId(rs.getLong("id"));
// ingrediente.setNome(rs.getString("nome"));
// ingrediente.setValorPorPorcao(rs.getFloat("valor_porcao"));
// ingredientes.add(ingrediente);
// }
// return ingredientes;
// }
//
// public Ingrediente remover(String nomeIngrediente) throws RuntimeException {
//
// try {
// Ingrediente ingrediente = buscarPorNome(nomeIngrediente);
// if (ingrediente != null) {
// PreparedStatement ps = conexao.prepareStatement(SQL_REMOCAO);
// ps.setLong(1, ingrediente.getId());
//
// int alteracoes = ps.executeUpdate();
// if (alteracoes == 1) {
// return ingrediente;
// }
// return null;
// }
// return null;
// } catch (SQLException se) {
// throw new RuntimeException("Não foi possível realizar a pesquisa", se);
// }
//
// }
//
// public Connection getConexao() {
//
// return conexao;
// }
//
// public void setConexao(Connection conexao) {
//
// this.conexao = conexao;
// }
//
// }