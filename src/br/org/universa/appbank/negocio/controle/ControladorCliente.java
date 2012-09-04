package br.org.universa.appbank.negocio.controle;

import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.comum.UtilHelper;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;

public class ControladorCliente implements GestorCliente {

	private static ControladorCliente instancia = null;

	private ControladorCliente() {
		// Construtor Privado
	}

	public static ControladorCliente get() {
		if (instancia == null) {

			instancia = new ControladorCliente();
		}
		return instancia;
	}

	@Override
	public void incluiCliente(Cliente cliente) throws Exception {
		validaDadosCliente(cliente);

		if (DaoClienteMap.get().consultaPorCpf(cliente.getCpf()) != null) {
			throw new RuntimeException(Mensagens.CPF_JA_CADASTRADO);
		}
		if (DaoClienteMap.get().consultaPorLogin(cliente.getLogin()) != null) {
			throw new RuntimeException(Mensagens.LOGIN_JA_UTILIZADO);
		}

		DaoClienteMap.get().insere(cliente);
	}

	private void validaDadosCliente(Cliente cliente) {
		if (!UtilHelper.isCampoPreenchido(cliente.getSenha())) {
			throw new RuntimeException(Mensagens.SENHA_INVALIDA);
		}
		if (!UtilHelper.isLoginValido(cliente.getLogin())) {
			throw new RuntimeException(Mensagens.LOGIN_INVALIDO);
		}
		if (!UtilHelper.isCampoPreenchido(cliente.getNome())) {
			throw new RuntimeException(Mensagens.NOME_INVALIDO);
		}
		if (!UtilHelper.isCpfValido(cliente.getCpf())) {
			throw new RuntimeException(Mensagens.CPF_INVALIDO);
		}
	}

	@Override
	public void alteraCliente(Cliente cliente) throws Exception {
		DaoClienteMap.get().atualiza(cliente);
	}

	@Override
	public void excluiCliente(Cliente cliente) throws Exception {
		DaoClienteMap.get().remove(cliente);
	}

	@Override
	public Cliente consultaCliente(String cpf) throws Exception {
		Cliente cliente = DaoClienteMap.get().consultaPorCpf(cpf);
		if (cliente == null) {
			throw new Exception(Mensagens.CLIENTE_NAO_ENCONTRADO);
		}
		return cliente;
	}
}
