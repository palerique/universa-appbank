package br.org.universa.appbank.negocio.controle;

import java.util.List;

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;

public interface GestorConta {

	public abstract Conta consultaConta(int numero) throws Exception;

	public abstract List<Conta> consultaTodasContas() throws Exception;

	public abstract int abreConta(Cliente titular, TipoDaConta tipoDaConta)
			throws Exception;

	public abstract void encerraConta(Conta conta) throws Exception;

	public abstract void depositoEmConta(Conta conta, double valor)
			throws Exception;

	public abstract void saqueEmConta(Conta conta, double valor)
			throws Exception;

	public abstract void transfereEntreContas(Conta contaDeDebito,
			Conta contaDeCredito, double valor) throws Exception;

}