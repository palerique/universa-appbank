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

import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.ContaCorrente;
import br.org.universa.appbank.negocio.dominio.ContaPoupanca;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;

public class ContaVO {

	private Conta conta;

	public ContaVO(Conta conta) {
		this.conta = conta;
	}

	public int getNumero() {
		return conta.getNumero();
	}

	public int getAgencia() {
		return conta.getAgencia();
	}

	public String getCpfDoTitular() {
		return conta.getTitular().getCpf();
	}

	public String getTipoDaConta() {
		return conta.getTipoDaConta().getValor();
	}

	public double getSaldo() {
		return conta.getSaldo();
	}

	public String getEstadoDaConta() {
		return conta.getEstadoDaConta().getValor();
	}

	public double getLimiteDoChequeEspecial() {
		if (conta.getTipoDaConta().getChave()
				.equals(TipoDaConta.CORRENTE.getChave())) {
			return ((ContaCorrente) conta).getLimiteDoChequeEspecial();
		}

		return 0.0;
	}

	public int getDiaDeAniversario() {
		if (conta.getTipoDaConta().getChave()
				.equals(TipoDaConta.POUPANCA.getChave())) {
			return ((ContaPoupanca) conta).getDiaDeAniversario();
		}

		return 0;
	}
}
