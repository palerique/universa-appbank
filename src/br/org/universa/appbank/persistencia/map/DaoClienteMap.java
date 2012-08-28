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

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.persistencia.DaoCliente;

public class DaoClienteMap implements DaoCliente {

	private static DaoClienteMap instancia = null;

	private DaoClienteMap() {
		// Construtor privado
	}

	public static DaoClienteMap get() {
		if (instancia == null) {
			instancia = new DaoClienteMap();
		}

		return instancia;
	}

	@Override
	public void insere(Cliente cliente) {
		// TODO Implementar o método
	}

	@Override
	public void atualiza(Cliente cliente) {
		// TODO Implementar o método
	}

	@Override
	public void remove(Cliente cliente) {
		// TODO Implementar o método
	}

	@Override
	public Cliente consultaPorCpf(String cpf) {
		// TODO Implementar o método
		return null;
	}

	@Override
	public Cliente consultaPorLogin(String login) {
		// TODO Implementar o método
		return null;
	}

	@Override
	public void removeTodos() {
		// TODO Implementar o método
	}
}