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
 * Componente de negócio de Conta Corrente
 * 
 * @author flavio.roberto
 * 
 */
public class ContaCorrente extends Conta {

	private double limiteDoChequeEspecial;

	public void debita(double valor) throws Exception {
		// TODO Implementar o método
	}

	public double getLimiteDoChequeEspecial() {
		return limiteDoChequeEspecial;
	}

	public void setLimiteDoChequeEspecial(double limiteDoChequeEspecial) {
		this.limiteDoChequeEspecial = limiteDoChequeEspecial;
	}

	@Override
	public void validaDados() throws Exception {
		super.validaDados();

		if (UtilHelper.isCampoPreenchido(limiteDoChequeEspecial) == false) {
			throw new Exception(
					Mensagens.CAMPOS_OBRIGATORIOS_CONTA_NAO_PREENCHIDOS);
		}
	}
}
