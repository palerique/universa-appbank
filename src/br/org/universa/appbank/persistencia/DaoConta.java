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

import java.util.List;

import br.org.universa.appbank.negocio.dominio.Conta;

/**
 * Interface para o DAO do componente de negócio Conta.
 * 
 * @author flavio.roberto
 */
public interface DaoConta {

	public void insere(Conta conta);

	public void atualiza(Conta conta);

	public Conta consulta(int numero);

	public int geraNumero();

	public List<Conta> consultaTodas();
	
	public void removeTodos();
}
