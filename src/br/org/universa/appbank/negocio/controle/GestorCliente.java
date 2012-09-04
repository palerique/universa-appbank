package br.org.universa.appbank.negocio.controle;

import br.org.universa.appbank.negocio.dominio.Cliente;

public interface GestorCliente {

	public abstract Cliente consultaCliente(String cpf) throws Exception;

	public abstract void incluiCliente(Cliente cliente) throws Exception;

	public abstract void alteraCliente(Cliente cliente) throws Exception;

	public abstract void excluiCliente(Cliente cliente) throws Exception;

}