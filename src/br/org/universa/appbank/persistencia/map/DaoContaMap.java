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
package br.org.universa.appbank.persistencia.map;

import java.util.List;

import br.org.universa.appbank.negocio.dominio.Conta;
import br.org.universa.appbank.persistencia.DaoConta;

public class DaoContaMap implements DaoConta {

	private static DaoContaMap instancia = null;

	private DaoContaMap() {
		// Construtor privado
	}

	public static DaoContaMap get() {
		if (instancia == null) {
			instancia = new DaoContaMap();
		}

		return instancia;
	}

	@Override
	public void insere(Conta conta) {
		// TODO Implementar o método
	}

	@Override
	public void atualiza(Conta conta) {
		// TODO Implementar o método
	}

	@Override
	public Conta consulta(int numero) {
		// TODO Implementar o método
		return null;
	}

	@Override
	public int geraNumero() {
		// TODO Implementar o método
		return 0;
	}

	@Override
	public List<Conta> consultaTodas() {
		// TODO Implementar o método
		return null;
	}

	@Override
	public void removeTodos() {
		// TODO Implementar o método
	}
}