package br.org.universa.appbank.negocio.controle;

import java.util.List;

import br.com.phd.cadin.servico.CadinFacade;
import br.com.phd.spb.servico.SPBFacade;
import br.com.phd.spb.servico.TransacaoSPB;
import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.comum.UtilHelper;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.ContaCorrente;
import br.org.universa.appbank.negocio.dominio.ContaPoupanca;
import br.org.universa.appbank.negocio.dominio.EstadoDaConta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

public class ControladorConta implements GestorConta {

	private static ControladorConta instancia;

	@Override
	public Conta consultaConta(int numero) throws Exception {
		Conta conta = DaoContaMap.get().consulta(numero);

		if (conta == null) {
			throw new RuntimeException(Mensagens.CONTA_NAO_ENCONTRADA);
		}

		return conta;
	}

	@Override
	public List<Conta> consultaTodasContas() throws Exception {
		return DaoContaMap.get().consultaTodas();
	}

	@Override
	public int abreConta(Cliente titular, TipoDaConta tipoDaConta)
			throws Exception {

		Conta conta = null;

		if (tipoDaConta == TipoDaConta.CORRENTE) {
			conta = new ContaCorrente();
		} else if (tipoDaConta == TipoDaConta.POUPANCA) {
			conta = new ContaPoupanca();
		}

		conta.setTitular(titular);
		conta.setNumero(DaoContaMap.get().geraNumero());
		conta.setTipoDaConta(tipoDaConta);
		conta.setEstadoDaConta(EstadoDaConta.ATIVA);
		conta.setAgencia(1234);

		// TODO: conta.validaDados();

		if (CadinFacade.get().cpfPresenteNoCadin(conta.getTitular().getCpf()) == 3) {
			throw new RuntimeException(Mensagens.CLIENTE_PENDENTE_NO_CADIN);
		}

		DaoContaMap.get().insere(conta);

		return conta.getNumero();
	}

	@Override
	public void encerraConta(Conta conta) throws Exception {

		System.out.println("SALDO: " + conta.getSaldo());

		if (conta.getSaldo() != Double.valueOf(0)) {
			throw new RuntimeException(Mensagens.CONTA_POSSUI_SALDO);
		}
		if (conta.getEstadoDaConta() == EstadoDaConta.ENCERRADA) {
			throw new RuntimeException(Mensagens.CONTA_JA_ENCERRADA);
		}

		conta.setEstadoDaConta(EstadoDaConta.ENCERRADA);

		DaoContaMap.get().atualiza(conta);

	}

	@Override
	public void depositoEmConta(Conta conta, double valor) throws Exception {
		conta.credita(valor);

		DaoContaMap.get().atualiza(conta);

		if (conta.getTitular().getCpf() == null
				|| !UtilHelper.isCampoPreenchido(conta.getTitular().getCpf())) {
			throw new RuntimeException(Mensagens.DADOS_INSUFICIENTES_SPB);
		} else if (!UtilHelper.isCpfValido(conta.getTitular().getCpf())) {
			throw new RuntimeException(Mensagens.CPF_TITULAR_CONTA_INVALIDO);
		} else {

			TransacaoSPB transacao = SPBFacade.get().criaTransacaoSPB();

			transacao.setAgencia(conta.getAgencia());
			transacao.setConta(conta.getNumero());
			transacao.setCpfDoTitular(conta.getTitular().getCpf());
			transacao.setValor(valor);

			SPBFacade.get().notificaTransacao(transacao);
		}

	}

	@Override
	public void saqueEmConta(Conta conta, double valor) throws Exception {
		conta.debita(valor);

		DaoContaMap.get().atualiza(conta);

	}

	@Override
	public void transfereEntreContas(Conta contaDeOrigem, Conta contaDeDestino,
			double valor) throws Exception {
		contaDeOrigem.debita(valor);
		contaDeDestino.credita(valor);

		DaoContaMap.get().atualiza(contaDeDestino);
		DaoContaMap.get().atualiza(contaDeOrigem);
	}

	public static ControladorConta get() {
		if (instancia == null) {

			instancia = new ControladorConta();
		}
		return instancia;
	}

}
