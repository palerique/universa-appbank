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

import java.util.Vector;

import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.persistencia.DaoCliente;

/**
 * Implementação do DAO de cliente usando Vetor.
 * 
 * @author flavio.roberto
 */
public class DaoClienteVetor implements DaoCliente {

	private static DaoClienteVetor instancia = null;

	/** Vetor de clientes */
	private Vector<Cliente> clientes = new Vector<Cliente>();

	private DaoClienteVetor() {
		// Construtor privado
	}

	public static DaoClienteVetor get() {
		if (instancia == null) {
			instancia = new DaoClienteVetor();
		}

		return instancia;
	}

	public Cliente consultaPorCpf(String cpf) {
		Cliente c = null;

		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = (Cliente) clientes.elementAt(i);

			if (cliente.getCpf().equals(cpf)) {
				c = cliente;
				i = clientes.size();
			}
		}

		return c;
	}

	public void insere(Cliente cliente) {
		clientes.add(cliente);
	}

	public void atualiza(Cliente cliente) {
		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = (Cliente) clientes.elementAt(i);

			if (cliente.getCpf().equals(c.getCpf())) {
				c.setLogin(cliente.getLogin());
				c.setNome(cliente.getNome());
				c.setSenha(cliente.getSenha());

				break;
			}
		}
	}

	public void remove(Cliente cliente) {
		for (int i = 0; i < clientes.size(); i++) {
			Cliente c = (Cliente) clientes.elementAt(i);

			if ((cliente.getLogin()).equals(c.getLogin())) {
				clientes.removeElementAt(i);
				i = clientes.size();
			}
		}
	}

	public Cliente consultaPorLogin(String login) {
		Cliente c = null;

		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = (Cliente) clientes.elementAt(i);

			if (cliente.getLogin().equals(login)) {
				c = cliente;
				i = clientes.size();
			}
		}

		return c;
	}

	public void removeTodos() {
		clientes.removeAllElements();
	}
}