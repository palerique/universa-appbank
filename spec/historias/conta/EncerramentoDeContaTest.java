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

import historias.cliente.EntradaDeCliente;
import junit.framework.Assert;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.org.universa.appbank.negocio.controle.FachadaBancaria;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

@RunWith(ConcordionRunner.class)
public class EncerramentoDeContaTest extends ContaTest {

	@BeforeClass
	public static void setUp() {
		DaoContaMap.get().removeTodos();
		DaoClienteMap.get().removeTodos();

		try {
			EntradaDeCliente.get()
					.insere("04693433110", null, null, null, null);
			EntradaDeCliente.get()
					.insere("03203175444", null, null, null, null);
			EntradaDeCliente.get()
					.insere("77276469115", null, null, null, null);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public String encerraConta(int numero) {
		Conta conta;
		try {
			conta = FachadaBancaria.get().consultaConta(numero);

			FachadaBancaria.get().encerraConta(conta);
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}
}