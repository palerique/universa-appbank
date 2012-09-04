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
package br.org.universa.appbank.negocio.controle;

import java.util.List;

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;

/**
 * Fachada da Aplicação Bancária.
 * 
 * @author flavio.roberto
 */
public class FachadaBancaria {
	/** Instância para implementar o padrão singleton */
	private static FachadaBancaria instancia = null;

	private FachadaBancaria() {
	}

	public static FachadaBancaria get() {
		if (instancia == null) {
			instancia = new FachadaBancaria();
		}

		return instancia;
	}

	public Cliente consultaCliente(String cpf) throws Exception {

		return ControladorCliente.get().consultaCliente(cpf);

	}

	public void incluiCliente(Cliente cliente) throws Exception {
		ControladorCliente.get().incluiCliente(cliente);
	}

	public void alteraCliente(Cliente cliente) throws Exception {
		ControladorCliente.get().alteraCliente(cliente);
	}

	public void excluiCliente(Cliente cliente) throws Exception {
		ControladorCliente.get().alteraCliente(cliente);
	}

	public Conta consultaConta(int numero) throws Exception {
		return ControladorConta.get().consultaConta(numero);
	}

	public List<Conta> consultaTodasContas() throws Exception {
		return ControladorConta.get().consultaTodasContas();
	}

	public int abreConta(Cliente titular, TipoDaConta tipoDaConta)
			throws Exception {
		return ControladorConta.get().abreConta(titular, tipoDaConta);
	}

	public void encerraConta(Conta conta) throws Exception {
		ControladorConta.get().encerraConta(conta);
	}

	public void depositoEmConta(Conta conta, double valor) throws Exception {
		ControladorConta.get().depositoEmConta(conta, valor);
	}

	public void saqueEmConta(Conta conta, double valor) throws Exception {
		ControladorConta.get().saqueEmConta(conta, valor);
	}

	public void transfereEntreContas(Conta contaDeDebito, Conta contaDeCredito,
			double valor) throws Exception {
		ControladorConta.get().transfereEntreContas(contaDeDebito,
				contaDeCredito, valor);
	}
}