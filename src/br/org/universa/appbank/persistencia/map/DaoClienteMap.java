/*
 * PhD Software do Brasil / Universa Escola de Gestão.
 * email - professor.flavio.roberto@gmail.com
 * App Bank - Aplicação Bancária.
 * OBS: Todos os códigos estão sendo oferecidos com a intenção única de
 * estimular o aprendizado. Não podem ser usados com fins comerciais sem
 * autorização prévia do autor. Se redistribuídos para outros sites, o autor e
 * a fonte devem ser sempre citados.
 */
package br.org.universa.appbank.persistencia.map;

import java.util.HashMap;
import java.util.Map;

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.persistencia.DaoCliente;

public final class DaoClienteMap implements DaoCliente {

	private static DaoClienteMap	instancia	= null;
	private Map<String, Cliente>	clientes	= new HashMap<String, Cliente>();

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

		clientes.put(cliente.getCpf(), cliente);
	}

	@Override
	public void atualiza(Cliente cliente) {

		clientes.remove(cliente.getCpf());
		clientes.put(cliente.getCpf(), cliente);
	}

	@Override
	public void remove(Cliente cliente) {

		clientes.remove(cliente.getCpf());
	}

	@Override
	public Cliente consultaPorCpf(String cpf) {

		if (clientes.containsKey(cpf)) {
			return clientes.get(cpf);
		}
		return null;

	}

	@Override
	public Cliente consultaPorLogin(String login) {

		for (Map.Entry<String, Cliente> cliente : clientes.entrySet()) {
			if (cliente.getValue().getLogin().equals(login)) {
				return cliente.getValue();
			}
		}
		return null;
	}

	@Override
	public void removeTodos() {

		clientes.clear();
	}
}