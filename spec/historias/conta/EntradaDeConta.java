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

import br.org.universa.appbank.negocio.comum.UtilHelper;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.negocio.dominio.ContaCorrente;
import br.org.universa.appbank.negocio.dominio.ContaPoupanca;
import br.org.universa.appbank.negocio.dominio.EstadoDaConta;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;
import br.org.universa.appbank.persistencia.map.DaoContaMap;

public class EntradaDeConta {

	private static EntradaDeConta instancia = null;

	private EntradaDeConta() {
		// Construtor privado
	}

	public static EntradaDeConta get() {
		if (instancia == null) {
			instancia = new EntradaDeConta();
		}

		return instancia;
	}

	public void insere(int numero, String cpf, String tipo, double saldo,
			String estado) throws Exception {
		Conta conta = null;
		TipoDaConta tipoDaConta = TipoDaConta.get(tipo);

		if (tipoDaConta.getChave().equals(TipoDaConta.CORRENTE.getChave())) {
			conta = new ContaCorrente();
		} else {
			conta = new ContaPoupanca();
		}

		conta.setTipoDaConta(tipoDaConta);
		conta.setAgencia(1234);
		conta.setNumero(numero);

		if (UtilHelper.isCampoPreenchido(cpf)) {
			Cliente titular = DaoClienteMap.get().consultaPorCpf(cpf);

			if (titular == null) {
				titular = new Cliente(cpf, "", "", "");
			}

			conta.setTitular(titular);
		}

		if (UtilHelper.isCampoPreenchido(saldo)) {
			conta.credita(saldo);
		} else {
			conta.credita(0);
		}

		if (UtilHelper.isCampoPreenchido(estado)) {
			conta.setEstadoDaConta(EstadoDaConta.get(estado));
		}

		DaoContaMap.get().insere(conta);
	}
}