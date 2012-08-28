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
package historias.conta;

import junit.framework.Assert;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.org.universa.appbank.negocio.controle.FachadaBancaria;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

@RunWith(ConcordionRunner.class)
public class RealizaTransacaoNaContaTest extends ContaTest {

	@BeforeClass
	public static void setUp() {
		DaoContaMap.get().removeTodos();
		DaoClienteMap.get().removeTodos();
	}

	public void depositoEmConta(int numero, double valor) {
		Conta conta = null;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);
			conta.setTitular(new Cliente("77276469115", null, null, null));

			FachadaBancaria.get().depositoEmConta(conta, valor);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public String depositoEmContaSemCPFDoTitular(int numero, double valor) {
		Conta conta = null;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);
			conta.setTitular(new Cliente("", null, null, null));

			FachadaBancaria.get().depositoEmConta(conta, valor);
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}

	public String depositoEmContaComCPFDoTitularInvalido(int numero,
			double valor) {
		Conta conta = null;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);
			conta.setTitular(new Cliente("77276469114", null, null, null));

			FachadaBancaria.get().depositoEmConta(conta, valor);
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}

	public String saqueEmConta(int numero, double valor) {
		Conta conta = null;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);

			FachadaBancaria.get().saqueEmConta(conta, valor);
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}

	public String transferenciaEntreContas(int numeroDeOrigem, double valor,
			int numeroDeDestino) {
		Conta contaDeOrigem = null;
		Conta contaDeDestino = null;

		try {
			contaDeOrigem = FachadaBancaria.get().consultaConta(numeroDeOrigem);
			contaDeDestino = FachadaBancaria.get().consultaConta(
					numeroDeDestino);

			FachadaBancaria.get().transfereEntreContas(contaDeOrigem,
					contaDeDestino, valor);
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}

	public double getSaldo(int numero) {
		Conta conta = null;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);

			return conta.getSaldo();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		return 0.0;
	}
}