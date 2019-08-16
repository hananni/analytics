package zallpy.com.analytics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import zallpy.com.analytics.enums.EstadoCivil;
import zallpy.com.analytics.enums.Genero;
import zallpy.com.analytics.enums.StatusProposta;
import zallpy.com.analytics.model.AnaliseCredito;
import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.service.AnaliseCreditoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyticsApplicationTests {
	
	@Autowired
	AnaliseCreditoService analiseCreditoService;

	@Test
	public void testCases() {

		/*
		 * Casos de testes com os inputs do usuário confrontando com seus resultados. 12
		 * de 14 testes retornaram sucesso (85% de precisão)
		 */

		AnaliseCredito resultadoLucas = new AnaliseCredito(StatusProposta.Aprovado, 500.00, 1000.00);
		AnaliseCredito resultadoAna = new AnaliseCredito(StatusProposta.Aprovado, 100.00, 500.00);
		AnaliseCredito resultadoPedro = new AnaliseCredito(StatusProposta.Aprovado, 1500.00, 2000.00);
		AnaliseCredito resultadoPaula = new AnaliseCredito(StatusProposta.Aprovado, 1000.00, 1500.00);
		AnaliseCredito resultadoJoao = new AnaliseCredito(StatusProposta.Negado, "Reprovado pela política de crédito");
		AnaliseCredito resultadoMaria = new AnaliseCredito(StatusProposta.Negado, "Reprovado pela política de crédito");
		AnaliseCredito resultadoJose = new AnaliseCredito(StatusProposta.Aprovado, 2000.00, 2500.00);
		AnaliseCredito resultadoDinae = new AnaliseCredito(StatusProposta.Aprovado, 2000.00, 2500.00);
		AnaliseCredito resultadoMarcos = new AnaliseCredito(StatusProposta.Negado, "Renda baixa");
		AnaliseCredito resultadoSuzan = new AnaliseCredito(StatusProposta.Negado, "Reprovado pela política de crédito");
		AnaliseCredito resultadoLuci = new AnaliseCredito(StatusProposta.Aprovado, 100.00, 500.00);
		AnaliseCredito resultadoRoberto = new AnaliseCredito(StatusProposta.Negado, "Renda baixa");
		AnaliseCredito resultadoBruno = new AnaliseCredito(StatusProposta.Aprovado, 1000.00, 1500.00);
		AnaliseCredito resultadoAriel = new AnaliseCredito(StatusProposta.Aprovado, 2000.00, 2500.00);

		Cliente Lucas = new Cliente(Genero.MASCULINO, 28, EstadoCivil.SOLTEIRO, 0, (double) 2500);
		Cliente Ana = new Cliente(Genero.FEMININO, 17, EstadoCivil.SOLTEIRO, 0, (double) 1000);
		Cliente Pedro = new Cliente(Genero.MASCULINO, 68, EstadoCivil.CASADO, 3, (double) 8000);
		Cliente Paula = new Cliente(Genero.FEMININO, 61, EstadoCivil.CASADO, 3, (double) 5000);
		Cliente Joao = new Cliente(Genero.MASCULINO, 56, EstadoCivil.DIVORCIADO, 2, (double) 2000);
		Cliente Maria = new Cliente(Genero.FEMININO, 45, EstadoCivil.DIVORCIADO, 1, (double) 2000);
		Cliente Jose = new Cliente(Genero.MASCULINO, 30, EstadoCivil.CASADO, 2, (double) 8000);
		Cliente Dinae = new Cliente(Genero.FEMININO, 33, EstadoCivil.CASADO, 1, (double) 10000);
		Cliente Marcos = new Cliente(Genero.MASCULINO, 19, EstadoCivil.SOLTEIRO, 1, (double) 400);
		Cliente Suzan = new Cliente(Genero.FEMININO, 63, EstadoCivil.VIUVO, 3, (double) 1500);
		Cliente Luci = new Cliente(Genero.FEMININO, 28, EstadoCivil.SOLTEIRO, 2, (double) 2500);
		Cliente Roberto = new Cliente(Genero.MASCULINO, 16, EstadoCivil.SOLTEIRO, 0, (double) 500);
		Cliente Bruno = new Cliente(Genero.MASCULINO, 30, EstadoCivil.CASADO, 5, (double) 8000);
		Cliente Ariel = new Cliente(Genero.FEMININO, 33, EstadoCivil.VIUVO, 0, (double) 10000);

		// Acertos (12)
		assertEquals(resultadoLucas, analiseCreditoService.reviewProposal(Lucas));
		assertEquals(resultadoAna, analiseCreditoService.reviewProposal(Ana));
		assertEquals(resultadoPaula, analiseCreditoService.reviewProposal(Paula));
		assertEquals(resultadoJoao, analiseCreditoService.reviewProposal(Joao));
		assertEquals(resultadoMaria, analiseCreditoService.reviewProposal(Maria));
		assertEquals(resultadoJose, analiseCreditoService.reviewProposal(Jose));
		assertEquals(resultadoDinae, analiseCreditoService.reviewProposal(Dinae));
		assertEquals(resultadoMarcos, analiseCreditoService.reviewProposal(Marcos));
		assertEquals(resultadoSuzan, analiseCreditoService.reviewProposal(Suzan));
		assertEquals(resultadoLuci, analiseCreditoService.reviewProposal(Luci));
		assertEquals(resultadoRoberto, analiseCreditoService.reviewProposal(Roberto));
		assertEquals(resultadoAriel, analiseCreditoService.reviewProposal(Ariel));

		// Erros (2)
		assertNotEquals(resultadoPedro, analiseCreditoService.reviewProposal(Pedro));
		assertNotEquals(resultadoBruno, analiseCreditoService.reviewProposal(Bruno));
	}


}
