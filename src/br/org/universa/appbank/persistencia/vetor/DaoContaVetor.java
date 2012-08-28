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
package br.org.universa.appbank.persistencia.vetor;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.persistencia.DaoConta;

/**
 * Implementação do DAO de Conta usando Vetor.
 * 
 * @author flavio.roberto
 */
public class DaoContaVetor implements DaoConta {

	private static DaoContaVetor instancia = null;

	/** Vetor de contas */
	private Vector<Conta> contas = new Vector<Conta>();

	private DaoContaVetor() {
		// Construtor privado
	}

	public static DaoContaVetor get() {
		if (instancia == null) {
			instancia = new DaoContaVetor();
		}

		return instancia;
	}

	public Conta consulta(int numero) {
		Conta c = null;

		for (int i = 0; i < contas.size(); i++) {
			Conta conta = (Conta) contas.elementAt(i);

			if (conta.getNumero() == numero) {
				c = (Conta) contas.elementAt(i);
			}
		}

		return c;
	}

	public void insere(Conta conta) {
		contas.add(conta);
	}

	public void atualiza(Conta conta) {
		// Não precisa fazer nada por ser um vetor.
	}

	public int geraNumero() {
		int max = 1;

		for (int i = 0; i < contas.size(); i++) {
			Conta conta = (Conta) contas.elementAt(i);

			if (conta.getNumero() >= max) {
				max = conta.getNumero() + 1;
			}
		}

		return max;
	}

	@Override
	public List<Conta> consultaTodas() {
		List<Conta> c = new ArrayList<Conta>();

		for (int i = 0; i < contas.size(); i++) {
			Conta conta = (Conta) contas.elementAt(i);
			c.add(conta);
		}

		return c;
	}

	public void removeTodos() {
		contas.removeAllElements();
	}
}