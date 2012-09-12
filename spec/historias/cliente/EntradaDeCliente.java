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
package historias.cliente;

import br.org.universa.appbank.negocio.comum.UtilHelper;
import br.org.universa.appbank.negocio.dominio.Cliente;
import br.org.universa.appbank.negocio.dominio.SituacaoDoCliente;
import br.org.universa.appbank.persistencia.map.DaoClienteMap;

public class EntradaDeCliente {

	private static EntradaDeCliente instancia = null;

	private EntradaDeCliente() {
		// Construtor privado
	}

	public static EntradaDeCliente get() {
		if (instancia == null) {
			instancia = new EntradaDeCliente();
		}

		return instancia;
	}

	public void insere(String cpf, String nome, String login, String senha,
			String situacao) throws Exception {
		Cliente cliente = new Cliente();

		if (UtilHelper.isCampoPreenchido(cpf)) {
			cliente.setCpf(cpf);
		}

		cliente.setNome(nome);
		cliente.setLogin(login);
		cliente.setSenha(senha);

		if (UtilHelper.isCampoPreenchido(situacao)) {
			cliente.setSituacao(SituacaoDoCliente.get(situacao));
		}

		DaoClienteMap.get().insere(cliente);
	}
}
