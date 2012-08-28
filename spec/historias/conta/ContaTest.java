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
package historias.conta;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import br.org.universa.appbank.negocio.controle.FachadaBancaria;
import br.org.universa.appbank.negocio.dominio.Conta;

public class ContaTest {
	
	public void insereConta(int numero, String cpf, String tipo, double saldo,
			String estado) {
		try {
			EntradaDeConta.get().insere(numero, cpf, tipo, saldo, estado);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public List<ContaVO> getContas() {
		List<ContaVO> contasVO = new ArrayList<ContaVO>();

		try {
			List<Conta> contas = FachadaBancaria.get().consultaTodasContas();

			for (Conta conta : contas) {
				contasVO.add(new ContaVO(conta));
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		return contasVO;
	}
}
