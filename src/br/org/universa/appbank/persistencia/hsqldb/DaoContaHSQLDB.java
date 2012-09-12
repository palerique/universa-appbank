/*
 * PhD Software do Brasil / Universa Escola de Gestão.
 * email - professor.flavio.roberto@gmail.com
 * App Bank - Aplicação Bancária.
 * OBS: Todos os códigos estão sendo oferecidos com a intenção única de
 * estimular o aprendizado. Não podem ser usados com fins comerciais sem
 * autorização prévia do autor. Se redistribuídos para outros sites, o autor e
 * a fonte devem ser sempre citados.
 */
package br.org.universa.appbank.persistencia.hsqldb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.persistencia.DaoConta;

public final class DaoContaHSQLDB implements DaoConta {

	private static DaoContaHSQLDB	instancia	= null;
	private Map<Integer, Conta>		contas		= new HashMap<Integer, Conta>();

	private DaoContaHSQLDB() {

		// Construtor privado
	}

	public static DaoContaHSQLDB get() {

		if (instancia == null) {
			instancia = new DaoContaHSQLDB();
		}

		return instancia;
	}

	@Override
	public void insere(Conta conta) {

		contas.put(conta.getNumero(), conta);
	}

	@Override
	public void atualiza(Conta conta) {

		contas.remove(conta.getNumero());
		contas.put(conta.getNumero(), conta);
	}

	@Override
	public Conta consulta(int numero) {

		return contas.get(numero);
	}

	@Override
	public int geraNumero() {

		return contas.size() + 1;
	}

	@Override
	public List<Conta> consultaTodas() {

		return new ArrayList<Conta>(contas.values());
	}

	@Override
	public void removeTodos() {

		contas.clear();
	}
}