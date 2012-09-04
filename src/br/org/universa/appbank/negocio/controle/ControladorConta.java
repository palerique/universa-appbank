package br.org.universa.appbank.negocio.controle;

import java.util.List;

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;

public class ControladorConta implements GestorConta {

	private static ControladorConta instancia;

	@Override
	public Conta consultaConta(int numero) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conta> consultaTodasContas() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int abreConta(Cliente titular, TipoDaConta tipoDaConta)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void encerraConta(Conta conta) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void depositoEmConta(Conta conta, double valor) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saqueEmConta(Conta conta, double valor) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void transfereEntreContas(Conta contaDeDebito, Conta contaDeCredito,
			double valor) throws Exception {
		// TODO Auto-generated method stub

	}

	public static ControladorConta get() {
		if (instancia == null) {

			instancia = new ControladorConta();
		}
		return instancia;
	}

}
