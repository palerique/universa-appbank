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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.phd.cadin.servico.MotivoDaAtualizacaoCadastral;
import br.org.universa.appbank.negocio.dominio.EstadoDaConta;
import br.org.universa.appbank.negocio.dominio.SituacaoDoCliente;
import br.org.universa.appbank.negocio.dominio.TipoDaConta;
import br.org.universa.appbank.negocio.dominio.TipoDoLancamento;

public class EnumerationTest {

	@Test
	public void assertValoresDosEnums() throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		for (Class<?> enumClass : getEnumClasses()) {
			Enum<?> operacao = values(enumClass)[0];
			Assert.assertNotNull(operacao.getClass().getMethod("getChave"));
			Assert.assertNotNull(operacao.getClass().getMethod("getValor"));

			Method methodGetChave = operacao.getClass().getMethod("getChave");
			methodGetChave.invoke(operacao);

			Method methodGetValor = operacao.getClass().getMethod("getValor");
			methodGetValor.invoke(operacao);

			valueOf(enumClass, name(operacao));
		}
	}

	private List<Class<?>> getEnumClasses() {
		return Arrays.<Class<?>> asList(EstadoDaConta.class,
				SituacaoDoCliente.class, TipoDaConta.class,
				TipoDoLancamento.class, MotivoDaAtualizacaoCadastral.class);
	}

	private void valueOf(Class<?> enumClass, String name)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method method = enumClass.getMethod("valueOf", String.class);
		method.invoke(null, name);
	}

	private String name(Enum<?> operacao) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Method method = operacao.getClass().getMethod("name");
		return (String) method.invoke(operacao);
	}

	private Enum<?>[] values(Class<?> enumClass) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Method method = enumClass.getMethod("values");
		return (Enum<?>[]) method.invoke(null);
	}
}
