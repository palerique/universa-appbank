package br.org.universa.appbank.negocio.controle;

import br.com.phd.cadin.servico.CadinFacade;
import br.com.phd.cadin.servico.MotivoDaAtualizacaoCadastral;
import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.SituacaoDoCliente;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;

public final class ControladorCliente implements GestorCliente {

	private static ControladorCliente	instancia	= null;

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

		// validaDadosCliente(cliente);
		cliente.validaDados();

		validaClienteBanco(cliente);

		validaClienteCadin(cliente);

		DaoClienteMap.get().insere(cliente);
		CadinFacade.get().notificaAtualizacaoCadastral(cliente.getCpf(), MotivoDaAtualizacaoCadastral.CREDENCIAMENTO_DE_CLIENTE);
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

	@Override
	public void alteraCliente(Cliente cliente) throws Exception {

		Cliente clienteAntigo = DaoClienteMap.get().consultaPorCpf(cliente.getCpf());

		if (clienteAntigo == null) {
			throw new RuntimeException(Mensagens.CLIENTE_NAO_ENCONTRADO);
		}
		if (!clienteAntigo.getLogin().equals(cliente.getLogin()) && DaoClienteMap.get().consultaPorLogin(cliente.getLogin()) != null) {
			throw new RuntimeException(Mensagens.LOGIN_JA_UTILIZADO);
		}

		// validaDadosCliente(cliente);
		cliente.validaDados();

		DaoClienteMap.get().atualiza(cliente);
		CadinFacade.get().notificaAtualizacaoCadastral(cliente.getCpf(), MotivoDaAtualizacaoCadastral.ATUALIZACAO_DE_DADOS_CADASTRAIS);
	}

	@Override
	public void excluiCliente(Cliente cliente) throws Exception {

		DaoClienteMap.get().remove(cliente);
		CadinFacade.get().notificaAtualizacaoCadastral(cliente.getCpf(), MotivoDaAtualizacaoCadastral.DESCREDENCIAMENTO_DE_CLIENTE);
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
