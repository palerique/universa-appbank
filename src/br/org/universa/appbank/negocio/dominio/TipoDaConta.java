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
 * Domínio para os tipos da conta.
 * 
 * @author flavio.roberto
 *
 */
public enum TipoDaConta {

	CORRENTE('C', "Corrente"), POUPANCA('P', "Poupança");

	private Character chave;
	private String valor;

	private TipoDaConta(Character chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public Character getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	public static TipoDaConta get(Character chave) {
		for (TipoDaConta tipoDaConta : TipoDaConta.values()) {
			if (chave.equals(tipoDaConta.getChave())) {
				return tipoDaConta;
			}
		}

		return null;
	}

	public static TipoDaConta get(String valor) {
		for (TipoDaConta tipoDaConta : TipoDaConta.values()) {
			if (valor.equals(tipoDaConta.getValor())) {
				return tipoDaConta;
			}
		}

		return null;
	}
}
