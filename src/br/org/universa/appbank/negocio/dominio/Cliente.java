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
 * Componente de negócio de Cliente.
 * 
 * @author flavio.roberto
 */
public class Cliente {
	/** CPF do cliente */
	private String cpf;

	/** Nome do cliente */
	private String nome;

	/** Login do cliente */
	private String login;

	/** Senha do cliente */
	private String senha;

	/** Estado do cliente */
	private SituacaoDoCliente situacao;

	public Cliente() {
	}

	public Cliente(String cpf, String nome, String login, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSituacao(SituacaoDoCliente situacao) {
		this.situacao = situacao;
	}

	public SituacaoDoCliente getSituacao() {
		return situacao;
	}

	public void validaDados() throws Exception {
		validaCpf();
		validaNome();
		validaLogin();
		validaSenha();
	}

	private void validaCpf() throws Exception {
		if (!UtilHelper.isCpfValido(cpf))
			throw new Exception(Mensagens.CPF_INVALIDO);
	}

	private void validaNome() throws Exception {
		if (!UtilHelper.isCampoPreenchido(nome))
			throw new Exception(Mensagens.NOME_INVALIDO);
	}

	private void validaLogin() throws Exception {
		if (!UtilHelper.isLoginValido(login))
			throw new Exception(Mensagens.LOGIN_INVALIDO);
	}

	private void validaSenha() throws Exception {
		if (!UtilHelper.isCampoPreenchido(senha))
			throw new Exception(Mensagens.SENHA_INVALIDA);
	}
}