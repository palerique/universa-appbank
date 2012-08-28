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
 * Domínio para os estados da conta.
 * 
 * @author flavio.roberto
 *
 */
public enum EstadoDaConta {

	ATIVA(1, "Ativa"), ENCERRADA(2, "Encerrada");

	private int chave;
	private String valor;

	private EstadoDaConta(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public String getValor() {
		return valor;
	}

	public static EstadoDaConta get(String valor) {
		for (EstadoDaConta situacaoDoCliente : EstadoDaConta.values()) {
			if (valor.equals(situacaoDoCliente.getValor())) {
				return situacaoDoCliente;
			}
		}

		return null;
	}
}
