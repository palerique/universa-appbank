package br.org.universa.appbank.negocio.controle;

import br.com.phd.cadin.servico.CadinFacade;
import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.comum.UtilHelper;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.SituacaoDoCliente;
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

		validaClienteBanco(cliente);

		validaClienteCadin(cliente);

		DaoClienteMap.get().insere(cliente);
	}

	private void validaClienteCadin(Cliente cliente) {
		if (CadinFacade.get().cpfPresenteNoCadin(cliente.getCpf()) == 3) {
			cliente.setSituacao(SituacaoDoCliente.PENDENTE);
		} else {
			cliente.setSituacao(SituacaoDoCliente.ATIVO);
		}
	}

	private void validaClienteBanco(Cliente cliente) {
		if (DaoClienteMap.get().consultaPorCpf(cliente.getCpf()) != null) {
			throw new RuntimeException(Mensagens.CPF_JA_CADASTRADO);
		}
		if (DaoClienteMap.get().consultaPorLogin(cliente.getLogin()) != null) {
			throw new RuntimeException(Mensagens.LOGIN_JA_UTILIZADO);
		}
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
		if (DaoClienteMap.get().consultaPorCpf(cliente.getCpf()) != null) {
			DaoClienteMap.get().atualiza(cliente);
		} else {
			throw new RuntimeException(Mensagens.CLIENTE_NAO_ENCONTRADO);
		}
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
