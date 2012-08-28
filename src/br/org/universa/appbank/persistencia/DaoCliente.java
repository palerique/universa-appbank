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
package br.org.universa.appbank.persistencia;

import br.org.universa.appbank.negocio.dominio.Cliente;

/**
 * Interface para o DAO do componente de negócio  Cliente.
 * 
 * @author flavio.roberto
 */
public interface DaoCliente {

	public void insere(Cliente cliente);

	public void atualiza(Cliente cliente);

	public void remove(Cliente cliente);

	public Cliente consultaPorCpf(String cpf);

	public Cliente consultaPorLogin(String login);

	public void removeTodos();
}