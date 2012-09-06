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
 * Componente de negócio de Conta.
 * 
 * @author flavio.roberto
 */
public abstract class Conta {
	/** Número da conta */
	private int numero;

	/** Código do cliente */
	private Cliente titular;

	/** Tipo da conta {Corrente ou Poupança} */
	private TipoDaConta tipoDaConta;

	/** Número da agência */
	private int agencia;

	/** Saldo da conta */
	protected double saldo;

	/** Estado da conta {Ativa, Encerrada} */
	private EstadoDaConta estadoDaConta;

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setTipoDaConta(TipoDaConta tipoDaConta) {
		this.tipoDaConta = tipoDaConta;
	}

	public TipoDaConta getTipoDaConta() {
		return tipoDaConta;
	}

	public EstadoDaConta getEstadoDaConta() {
		return estadoDaConta;
	}

	public void setEstadoDaConta(EstadoDaConta estadoDaConta) {
		this.estadoDaConta = estadoDaConta;
	}

	public void credita(double valor) throws Exception {
		saldo += valor;
	}

	public abstract void debita(double valor) throws Exception;

	public void validaDados() throws Exception {
		if (UtilHelper.isCampoPreenchido(titular)
				&& UtilHelper.isCampoPreenchido(agencia)
				&& UtilHelper.isCampoPreenchido(numero)
				&& UtilHelper.isCampoPreenchido(saldo)
				&& UtilHelper.isCampoPreenchido(tipoDaConta) == false) {
			throw new Exception(
					Mensagens.CAMPOS_OBRIGATORIOS_CONTA_NAO_PREENCHIDOS);
		}
	}
}