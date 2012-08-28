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

import br.org.universa.appbank.negocio.comum.Mensagens;
import br.org.universa.appbank.negocio.comum.UtilHelper;

/**
 * Componente de negócio de Conta Poupança.
 * 
 * @author flavio.roberto
 * 
 */
public class ContaPoupanca extends Conta {

	private int diaDeAniversario;

	public int getDiaDeAniversario() {
		return diaDeAniversario;
	}

	public void setDiaDeAniversario(int diaDeAniversario) {
		this.diaDeAniversario = diaDeAniversario;
	}

	public void debita(double valor) throws Exception {
		// TODO Implementar o método
	}

	@Override
	public void validaDados() throws Exception {
		super.validaDados();

		if (UtilHelper.isCampoPreenchido(diaDeAniversario) == false) {
			throw new Exception(
					Mensagens.CAMPOS_OBRIGATORIOS_CONTA_NAO_PREENCHIDOS);
		}
	}
}
