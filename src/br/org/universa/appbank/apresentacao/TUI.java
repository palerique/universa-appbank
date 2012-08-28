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
package br.org.universa.appbank.apresentacao;

import br.org.universa.appbank.negocio.comum.EasyInput;
import br.org.universa.appbank.negocio.controle.FachadaBancaria;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;

/**
 * Textual User Interface da Aplicação Bancária.
 * 
 * @author flavio.roberto
 */
public class TUI {

	/**
	 * Menu do Sistema.
	 */
	public static void main(String[] args) {
		try {
			int operacao;
			TUI tui = new TUI();

			while (true) {
				operacao = tui.showMainMenu();

				switch (operacao) {
				case 1:
					tui.incluiCliente();
					break;
				case 2:
					tui.alteraCliente();
					break;
				case 3:
					tui.excluiCliente();
					break;
				case 4:
					tui.consultaCliente();
					break;
				case 5:
					tui.abreConta();
					break;
				case 6:
					tui.encerraConta();
					break;
				case 7:
					tui.consultaSaldo();
					break;
				case 8:
					tui.saca();
					break;
				case 9:
					tui.deposita();
					break;
				case 10:
					tui.transfereEntreContas();
					break;
				case 11:
					System.out.println("\nAplicacao encerrada.");
					System.exit(0);
				default:
					System.out.println("\nOperação inválida.");
					break;
				}

				tui.clearScreen();
			}
		} catch (Exception e) {
			System.out.println("Exceção gerada: \n");
			e.printStackTrace();
		}
	}

	/**
	 * Monta o menu da aplicação.
	 * 
	 * @return int - Opção desejada.
	 */
	private int showMainMenu() {
		int opcao = 0;

		System.out.println("Escolha a opcao desejada: \n");
		System.out.println("1 - Cadastrar cliente ");
		System.out.println("2 - Alterar cliente");
		System.out.println("3 - Excluir cliente");
		System.out.println("4 - Consultar dados do cliente");
		System.out.println("5 - Abrir conta");
		System.out.println("6 - Encerrar conta");
		System.out.println("7 - Saldo da conta");
		System.out.println("8 - Saque em conta");
		System.out.println("9 - Deposito em conta");
		System.out.println("10 - Transferencia entre contas");
		System.out.println("11 - Sair");
		System.out.println("");
		System.out.println("Digite a opção desejada: ");

		try {
			opcao = leOpcaoDesejada();
		} catch (Exception ne) {
			System.out.println(ne.getMessage());
		}

		return opcao;
	}

	private int leOpcaoDesejada() throws Exception {
		String strOpcao = EasyInput.getln();

		try {
			return Integer.parseInt(strOpcao);
		} catch (NumberFormatException nfe) {
			throw new Exception("Opção inválida.");
		}
	}

	/**
	 * Inclui um cliente.
	 */
	public void incluiCliente() {
		Cliente cliente = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Incluir dados do Cliente      ");
			System.out.println("Digite o CPF: ");
			String cpf = leCpfDoCliente();

			System.out.println("Digite o Nome: ");
			String nome = EasyInput.getln();
			System.out.println("Digite o Login: ");
			String login = EasyInput.getln();
			System.out.println("Digite a Senha: ");
			String senha = EasyInput.getln();

			cliente = new Cliente();
			cliente.setCpf(cpf);
			cliente.setNome(nome);
			cliente.setLogin(login);
			cliente.setSenha(senha);

			FachadaBancaria.get().incluiCliente(cliente);
			System.out.println("Cliente cadastrado com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Atualiza o cliente.
	 */
	public void alteraCliente() {
		Cliente cliente = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("        Alterar dados do Cliente        ");
			System.out.println(" Digite o CPF do Cliente :");
			String cpf = leCpfDoCliente();

			cliente = FachadaBancaria.get().consultaCliente(cpf);

			System.out
					.println("Dados atuais:\n--------------------------------");
			exibeDadosDoCliente(cliente);
			System.out
					.println("--------------------------------\nEntre os dados atualizados (enter p/ nao alterar um campo)");
			System.out.println("Nome: ");
			System.out.println("Digite o Nome: ");
			String nome = EasyInput.getln();
			System.out.println("Digite o Login: ");
			String login = EasyInput.getln();
			System.out.println("Digite a Senha: ");
			String senha = EasyInput.getln();

			if ("".equals(nome)) {
				nome = cliente.getNome();
			}

			if ("".equals(login)) {
				login = cliente.getLogin();
			}

			if ("".equals(senha)) {
				senha = cliente.getSenha();
			}

			cliente.setNome(nome);
			cliente.setLogin(login);
			cliente.setSenha(senha);

			FachadaBancaria.get().alteraCliente(cliente);
			System.out.println("Cliente atualizado com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Exclui um cliente.
	 */
	public void excluiCliente() {
		Cliente cliente = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Excluir Cliente      ");
			System.out.println(" Digite o CPF do Cliente :");
			String cpf = leCpfDoCliente();

			cliente = FachadaBancaria.get().consultaCliente(cpf);
			exibeDadosDoCliente(cliente);

			if (confirma()) {
				FachadaBancaria.get().excluiCliente(cliente);
				System.out.println("Cliente excluído com sucesso.");
			} else {
				System.out.println("Operação cancelada.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Apresenta os dados de um cliente.
	 */
	public void consultaCliente() {
		Cliente cliente = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("       Dados do Cliente      ");
			System.out.println(" Digite o CPF do Cliente :");
			String cpf = leCpfDoCliente();

			cliente = FachadaBancaria.get().consultaCliente(cpf);

			System.out.println("-----------------------------------");
			exibeDadosDoCliente(cliente);
			System.out.println("-----------------------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Inclui uma conta.
	 */
	public void abreConta() {
		int numero = 0;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Abertura de conta      ");
			System.out.println(" Digite o CPF do Cliente :");
			String cpf = leCpfDoCliente();

			System.out
					.print(" Digite o tipo da conta (C - Corrente ou P - Poupanca) :");
			TipoDaConta tipoDaConta = leTipoDaConta();

			Cliente cliente = FachadaBancaria.get().consultaCliente(cpf);

			numero = FachadaBancaria.get().abreConta(cliente, tipoDaConta);
			System.out.println(" Numero da conta aberta: " + numero);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Encerra uma conta.
	 */
	public void encerraConta() {
		Conta conta = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Encerrar Conta      ");
			System.out.println(" Digite o numero da conta :");
			int numero = leNumeroDaConta();

			conta = FachadaBancaria.get().consultaConta(numero);

			FachadaBancaria.get().encerraConta(conta);
			System.out.println("Conta encerrada.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Obt�m o saldo da conta.
	 */
	public void consultaSaldo() {
		Conta conta = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Saldo      ");
			System.out.println(" Digite o numero da conta :");
			int numero = leNumeroDaConta();

			conta = FachadaBancaria.get().consultaConta(numero);
			System.out.println("Saldo da conta " + numero + ": "
					+ conta.getSaldo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Efetua um débito em conta.
	 */
	public void saca() {
		Conta conta = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Saque      ");
			System.out.println(" Digite o número da conta :");
			int numero = leNumeroDaConta();

			conta = FachadaBancaria.get().consultaConta(numero);

			System.out.println(" Digite o valor a ser sacado: ");
			double valor = leValorDaTransacao();

			FachadaBancaria.get().saqueEmConta(conta, valor);
			System.out.println("Saque efetivado.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Efetua um crédito em conta.
	 */
	public void deposita() {
		Conta conta = null;

		try {
			System.out.println("------------------------------------------");
			System.out.println("      Depósito      ");
			System.out.println(" Digite o número da conta :");
			int numero = leNumeroDaConta();

			conta = FachadaBancaria.get().consultaConta(numero);

			System.out.println(" Digite o valor depositado: ");
			double valor = leValorDaTransacao();

			FachadaBancaria.get().depositoEmConta(conta, valor);
			System.out.println("Deposito efetivado.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Efetua uma transferência entre contas.
	 */
	public void transfereEntreContas() {
		Conta contaDeDebito = null;
		Conta contaDeCredito = null;

		System.out.println("------------------------------------------");
		System.out.println("      Transferencia      ");
		System.out.println(" Digite o numero da conta sacada :");

		String numeroDaContaDeDebito = EasyInput.getln();
		System.out.println(" Digite o número da conta favorecida :");

		String numeroDaContaDeCredito = EasyInput.getln();

		try {
			contaDeDebito = FachadaBancaria.get().consultaConta(
					Integer.parseInt(numeroDaContaDeDebito));

			contaDeCredito = FachadaBancaria.get().consultaConta(
					Integer.parseInt(numeroDaContaDeCredito));

			System.out.println(" Digite o valor da transferencia: ");
			double valor = leValorDaTransacao();

			FachadaBancaria.get().transfereEntreContas(contaDeDebito,
					contaDeCredito, valor);
			System.out.println("Transferencia efetivada.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String leCpfDoCliente() throws Exception {
		String cpf = EasyInput.getln();

		try {
			Long.parseLong(cpf);
			return cpf;
		} catch (NumberFormatException nfe) {
			throw new Exception("CPF deve conter apenas números");
		}
	}

	private int leNumeroDaConta() throws Exception {
		String strNumero = EasyInput.getln();

		try {
			return Integer.parseInt(strNumero);
		} catch (NumberFormatException nfe) {
			throw new Exception("Número da conta deve conter apenas números");
		}
	}

	private double leValorDaTransacao() throws Exception {
		String strValor = EasyInput.getln();

		try {
			return Double.parseDouble(strValor);
		} catch (NumberFormatException nfe) {
			throw new Exception("Valor da transação deve ser um número");
		}
	}

	private TipoDaConta leTipoDaConta() throws Exception {
		String strTipo = EasyInput.getln();

		try {
			return TipoDaConta.get(strTipo.trim().toUpperCase().charAt(0));
		} catch (Exception e) {
			throw new Exception("Tipo da conta informado é inválido");
		}
	}

	/**
	 * Apresenta os dados de um cliente.
	 * 
	 * @param cliente
	 */
	private void exibeDadosDoCliente(Cliente cliente) {
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Login: " + cliente.getLogin());
		System.out.println("Senha: " + cliente.getSenha());
		System.out.println("Situação: " + cliente.getSituacao().getValor());
	}

	/**
	 * Limpa a tela texto.
	 */
	private void clearScreen() {
		EasyInput.getln();

		for (int i = 0; i < 4; i++) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
		}
	}

	/**
	 * Solicita confirma��o da opera��o.
	 * 
	 * @return boolean - Se houve confirma��o ou n�o.
	 */
	private boolean confirma() {
		boolean retorno = false;
		boolean recebeuResposta = false;

		while (!recebeuResposta) {
			System.out.println("Confirma S/N ? ");

			String resp = EasyInput.getln();

			if ("S".equals(resp) || "s".equals(resp)) {
				recebeuResposta = true;
				retorno = true;
			}

			if ("N".equals(resp) || "n".equals(resp)) {
				recebeuResposta = true;
				retorno = false;
			}

			if (!recebeuResposta) {
				System.out.println("Digite somente S ou N\n");
			}
		}

		return retorno;
	}
}