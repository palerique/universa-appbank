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
 * Domínio para as situações da conta.
 * 
 * @author flavio.roberto
 *
 */
public enum SituacaoDoCliente {

	ATIVO(1, "Ativo"), PENDENTE(2, "Pendente");

	private int chave;
	private String valor;

	private SituacaoDoCliente(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	public static SituacaoDoCliente get(String valor) {
		for (SituacaoDoCliente situacaoDoCliente : SituacaoDoCliente.values()) {
			if (valor.equals(situacaoDoCliente.getValor())) {
				return situacaoDoCliente;
			}
		}

		return null;
	}
}
