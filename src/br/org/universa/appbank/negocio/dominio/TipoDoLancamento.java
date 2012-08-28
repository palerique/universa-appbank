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
package br.org.universa.appbank.negocio.dominio;

/**
 * Domínio para os tipos do lançamentos da conta.
 * 
 * @author flavio.roberto.
 *
 */
public enum TipoDoLancamento {

	DEBITO(1, "Débito"), CREDITO(2, "Crédito");

	private Integer chave;
	private String valor;

	private TipoDoLancamento(Integer chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public Integer getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}
}
