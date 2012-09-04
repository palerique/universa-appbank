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

import historias.cliente.EntradaDeCliente;
import historias.conta.EntradaDeConta;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.EstadoDaConta;
import br.org.universa.appbank.negocio.dominio.SituacaoDoCliente;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

public class FachadaBancariaTest {

	private static final String PENELOPE_CPF = "77276469115";
	private static final String PENELOPE_NOME = "Penelope Cruz";
	private static final String PENELOPE_LOGIN = "penelope@cruz.com";
	private static final String PENELOPE_SENHA = "penelope";

	private static final String NICOLE_CPF = "02728594430";
	private static final String NICOLE_NOME = "Nicole Kidman";
	private static final String NICOLE_LOGIN = "nicole@kidman.com";
	private static final String NICOLE_SENHA = "nicole";

	private static final String JULIA_CPF = "04693433110";
	private static final String JULIA_NOME = "Julia Roberts";
	private static final String JULIA_LOGIN = "julia@roberts.com";
	private static final String JULIA_SENHA = "julia";

	@BeforeClass
	public static void setUp() {
		DaoClienteMap.get().removeTodos();
		DaoContaMap.get().removeTodos();

		try {
			EntradaDeCliente.get().insere(PENELOPE_CPF, PENELOPE_NOME,
					PENELOPE_LOGIN, PENELOPE_SENHA,
					SituacaoDoCliente.ATIVO.getValor());

			EntradaDeConta.get().insere(1, PENELOPE_CPF,
					TipoDaConta.CORRENTE.getValor(), 0,
					EstadoDaConta.ATIVA.getValor());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testConsultaClienteComCpfNaoCadastrado() {
		try {
			FachadaBancaria.get().consultaCliente(NICOLE_CPF);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(),
					Mensagens.CLIENTE_NAO_ENCONTRADO);
		}
	}

	@Test
	public void testConsultaClientePorCpf() {
		try {
			Cliente cliente = FachadaBancaria.get().consultaCliente(
					PENELOPE_CPF);
			Assert.assertEquals(PENELOPE_NOME, cliente.getNome());
			Assert.assertEquals(PENELOPE_LOGIN, cliente.getLogin());
			Assert.assertEquals(PENELOPE_SENHA, cliente.getSenha());
			Assert.assertEquals(SituacaoDoCliente.ATIVO.getChave(), cliente
					.getSituacao().getChave());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComCpfInvalido() {
		Cliente cliente = new Cliente("99999999999", NICOLE_NOME, NICOLE_LOGIN,
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CPF_INVALIDO, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComNomeInvalido() {
		Cliente cliente = new Cliente(NICOLE_CPF, "", NICOLE_LOGIN,
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.NOME_INVALIDO, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComLoginInvalido() {
		Cliente cliente = new Cliente(NICOLE_CPF, NICOLE_NOME, "nicole.com.br",
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.LOGIN_INVALIDO, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComSenhaInvalida() {
		Cliente cliente = new Cliente(NICOLE_CPF, NICOLE_NOME, NICOLE_LOGIN, "");

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.SENHA_INVALIDA, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComCpfJaCadastrado() {
		Cliente cliente = new Cliente(PENELOPE_CPF, NICOLE_NOME, NICOLE_LOGIN,
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CPF_JA_CADASTRADO, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComLoginJaUsadoPorOutroUsuario() {
		Cliente cliente = new Cliente(NICOLE_CPF, NICOLE_NOME, PENELOPE_LOGIN,
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.LOGIN_JA_UTILIZADO, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComPendenciaNoCadin() {
		Cliente titular = new Cliente(NICOLE_CPF, NICOLE_NOME, NICOLE_LOGIN,
				NICOLE_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(titular);

			Cliente cliente = FachadaBancaria.get().consultaCliente(NICOLE_CPF);

			Assert.assertEquals(NICOLE_CPF, cliente.getCpf());
			Assert.assertEquals(NICOLE_NOME, cliente.getNome());
			Assert.assertEquals(NICOLE_LOGIN, cliente.getLogin());
			Assert.assertEquals(NICOLE_SENHA, cliente.getSenha());
			Assert.assertEquals(SituacaoDoCliente.PENDENTE.getChave(), cliente
					.getSituacao().getChave());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteSemPendenciaNoCadin() {
		Cliente titular = new Cliente(JULIA_CPF, JULIA_NOME, JULIA_LOGIN,
				JULIA_SENHA);

		try {
			FachadaBancaria.get().incluiCliente(titular);

			Cliente cliente = FachadaBancaria.get().consultaCliente(JULIA_CPF);

			Assert.assertEquals(JULIA_CPF, cliente.getCpf());
			Assert.assertEquals(JULIA_NOME, cliente.getNome());
			Assert.assertEquals(JULIA_LOGIN, cliente.getLogin());
			Assert.assertEquals(JULIA_SENHA, cliente.getSenha());
			Assert.assertEquals(SituacaoDoCliente.ATIVO.getChave(), cliente
					.getSituacao().getChave());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testAlteraClienteInexistente() {
		try {
			Cliente cliente = new Cliente("02728594431", NICOLE_NOME,
					NICOLE_LOGIN, NICOLE_SENHA);

			FachadaBancaria.get().alteraCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CLIENTE_NAO_ENCONTRADO,
					e.getMessage());
		}
	}

	@Test
	public void testAlteraClienteComLoginJaUtilizado() {
		try {
			Cliente cliente = new Cliente(NICOLE_CPF, NICOLE_NOME,
					PENELOPE_LOGIN, NICOLE_SENHA);

			FachadaBancaria.get().alteraCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.LOGIN_JA_UTILIZADO, e.getMessage());
		}
	}

	@Test
	public void testAlteraCliente() {
		try {
			Cliente cliente = FachadaBancaria.get().consultaCliente(NICOLE_CPF);

			cliente.setNome("Nicole Cruz");
			cliente.setLogin("nicole@cruz.com");
			cliente.setSenha("nicole.cruz");

			FachadaBancaria.get().alteraCliente(cliente);

			Cliente titular = FachadaBancaria.get().consultaCliente(NICOLE_CPF);

			Assert.assertEquals(NICOLE_CPF, titular.getCpf());
			Assert.assertEquals("Nicole Cruz", titular.getNome());
			Assert.assertEquals("nicole@cruz.com", titular.getLogin());
			Assert.assertEquals("nicole.cruz", titular.getSenha());
			Assert.assertEquals(SituacaoDoCliente.PENDENTE.getChave(), titular
					.getSituacao().getChave());
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testExcluiCliente() {
		try {
			Cliente cliente = FachadaBancaria.get().consultaCliente(NICOLE_CPF);

			FachadaBancaria.get().excluiCliente(cliente);

			FachadaBancaria.get().consultaCliente(NICOLE_CPF);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CLIENTE_NAO_ENCONTRADO,
					e.getMessage());
		}
	}

	@Test
	public void testConsultaContaComNumeroInexistente() {
		try {
			FachadaBancaria.get().consultaConta(2);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CONTA_NAO_ENCONTRADA, e.getMessage());
		}
	}

	@Test
	public void testIncluiClienteComCPFInvalidoParaCadin() {
		try {
			EntradaDeCliente.get().insere("04693433111", "", "", "", "");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		try {
			Cliente cliente = FachadaBancaria.get().consultaCliente(
					"04693433111");
			FachadaBancaria.get().alteraCliente(cliente);

			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(Mensagens.CPF_INVALIDO, e.getMessage());
		}
	}
}
