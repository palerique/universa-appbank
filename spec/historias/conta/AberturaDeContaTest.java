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
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

@RunWith(ConcordionRunner.class)
public class AberturaDeContaTest extends ContaTest {

	@BeforeClass
	public static void setUp() {
		DaoContaMap.get().removeTodos();
		DaoClienteMap.get().removeTodos();
	}

	public void insereCliente(String cpf, String nome, String login,
			String senha, String situacao) {

		try {
			EntradaDeCliente.get().insere(cpf, nome, login, senha, situacao);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public String abreConta(String cpf, String tipo) {
		Cliente titular = null;

		try {
			titular = FachadaBancaria.get().consultaCliente(cpf);

			FachadaBancaria.get().abreConta(titular, TipoDaConta.get(tipo));
		} catch (Exception e) {
			return e.getMessage();
		}

		return "";
	}
}