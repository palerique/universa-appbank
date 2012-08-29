package br.org.universa.appbank.negocio.controle;

import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;

public class ControladorCliente {

	private static ControladorCliente instancia = null;

	private ControladorCliente() {
		// Construtor Privado
	}

	public static ControladorCliente get() {
		if (instancia == null) {

			instancia = new ControladorCliente();
		}
		return instancia;
	}

	public Cliente consultaCliente(String cpf) throws Exception {
		Cliente cliente = DaoClienteMap.get().consultaPorCpf(cpf);
		if (cliente == null) {
			throw new Exception(Mensagens.CLIENTE_NAO_ENCONTRADO);
		}
		return cliente;
	}
}
