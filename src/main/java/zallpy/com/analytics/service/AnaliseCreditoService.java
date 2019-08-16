package zallpy.com.analytics.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zallpy.com.analytics.enums.EstadoCivil;
import zallpy.com.analytics.enums.Genero;
import zallpy.com.analytics.enums.StatusProposta;
import zallpy.com.analytics.model.AnaliseCredito;
import zallpy.com.analytics.model.Cliente;
import zallpy.com.analytics.repository.AnaliseCreditoRepository;

@Service
public class AnaliseCreditoService {
	
	@Autowired
	AnaliseCreditoRepository analiseCreditoRepository;

	//Método responsável por buscar a análise de proposta através do cpf do cliente
	public AnaliseCredito findCreditAnalysisByClientCpf(String cpf) {
		return analiseCreditoRepository.findCreditAnalysisByClientCpf(cpf).orElseThrow(() -> new EntityNotFoundException("CPF não cadastrado!"));
	}
	
	//Método responsável por analisar a proposta e gerar uma análise de crédito apartir do cliente
	public void reviewAndSaveProposol(Cliente cliente) {
		AnaliseCredito analiseCredito = this.reviewProposal(cliente);
		analiseCredito.setCliente(cliente);
		analiseCreditoRepository.save(analiseCredito);
	}
	
	//Método responsável por analisar a proposta e gerar uma análise de crédito apartir do cliente
	public AnaliseCredito reviewProposal(Cliente cliente) {
		
		//Caso a renda do cliente seja menor que 600, automaticamente não entra no algorítimo de análise de crédito
		if(cliente.getRenda() < 600) {
			AnaliseCredito analiseCredito = new AnaliseCredito(StatusProposta.Negado, "Renda baixa");
			return analiseCredito;
		}
		
		//Inicilizando Variáveis de Pontuação
		int pontuacaoRenda = 0, pontuacaoEstadoCivil = 0, pontuacaoIdade = 0, pontuacaoGenero = 0, pontuacaoDependentes = 0;
		
		//Pesos fixos para cada variável (valores obtidos apartir de análise do código de backtracking (85% de precisão dos casos de teste)
		Integer pesoRenda = 5, pesoEstadoCivil = 2, pesoIdade = 1, pesoGenero = 1, pesoDependentes = 1;
		
		
		/*  Cálculo da Pontuação de Renda.
		*   Utilização de operadores ternários para otimizar o código. 
		*   (Pontuações para cada caso obtidos pelo algoritmo de backtracking);
		*
		*   | RENDA				| PONTUAÇÃO				       		|
		*	| ----------------  | ---------------------------- 		|
		*	| ENTRE 601 A 2200	| 2					       			| 
		*	| ENTRE 2201 A 2800 | 4					       			| 
		*	| ENTRE 2801 A 5000 | 9           				     	|
		*	| MAIOR QUE 5000    | 12 (BONIFICAÇÃO POR RENDA ALTA)	|
		*
		*/
		
		pontuacaoRenda = cliente.getRenda() <= 2200 ? 2 : 
						 cliente.getRenda() <= 2800 ? 4 : 
						 cliente.getRenda() <= 5000 ? 9 : 12;
		

		
		/*  Cálculo da Pontuação do Estado Civil.
		*   Utilização de operadores ternários para otimizar o código. 
		*   (Pontuações para cada caso obtidos pelo algoritmo de backtracking);
		*
		*   | ESTADO CIVIL | PONTUAÇÃO |
		*	|--------------|-----------|
		*	| SEPARADO     | 9         |
		*	| VIUVO        | 5         |
		*	| SOLTEIRO     | 7         |
		*	| CASADO       | 4         |
		*
		*/
		
		pontuacaoEstadoCivil = cliente.getEstadoCivil().equals(EstadoCivil.SEPARADO) ? 9 :
							   cliente.getEstadoCivil().equals(EstadoCivil.VIUVO) ? 5 : 
							   cliente.getEstadoCivil().equals(EstadoCivil.SOLTEIRO) ? 7 :
							   cliente.getEstadoCivil().equals(EstadoCivil.CASADO) ? 4 : 0;
								
		
		/*  Cálculo da Pontuação da Idade.
		*   Utilização de operadores ternários para otimizar o código. 
		*   (Pontuações para cada caso obtidos pelo algoritmo de backtracking);
		*
		*	| IDADE               | PONTUAÇÃO |
		*	|---------------------|-----------|
		*	| MENOR OU IGUAL A 20 | 9         |
		*	| ENTRE 21 A 30       | 3         |
		*	| ENTRE 31 A 40       | 6         |
		*	| ENTRE 41 A 50       | 5         |
		*	| MAIOR QUE 50        | 7         |
		*
		*/
		
		pontuacaoIdade = cliente.getIdade() <= 20 ? 9 : 
						cliente.getIdade() <= 30 ? 3 :
						cliente.getIdade() <= 40 ? 6 :
						cliente.getIdade() <= 50 ? 5 : 7;
			 	

		
		
		/*  Cálculo da Pontuação de Gênero.
		*   Utilização de operadores ternários para otimizar o código. 
		*   (Pontuações para cada caso obtidos pelo algoritmo de backtracking);
		*
		*	| GENERO    | PONTUAÇÃO |
		*	|-----------|-----------|
		*	| FEMININO  | 5         |
		*	| MASCULINO | 7         |
		*
		*/
		
		pontuacaoGenero = cliente.getGenero() == Genero.FEMININO ? 5 : 
						  cliente.getGenero() == Genero.MASCULINO ? 7 : 0;
	
		
		/*  Cálculo da Pontuação de Dependentes.
		*   Utilização de operadores ternários para otimizar o código. 
		*   (Pontuações para cada caso obtidos pelo algoritmo de backtracking);
		*
		*	| DEPENDENTES | PONTUAÇÃO |
		*	|-------------|-----------|
		*	| 0           | 8         |
		*	| 1           | 5         |
		*	| 2           | 5         |
		*	| 3           | 4         |
		*	| MAIOR QUE 3 | 1         |
		*
		*/
		
		pontuacaoDependentes = cliente.getDependentes() == 0 ?  8 :
							   cliente.getDependentes() == 1 ?  5 :
							   cliente.getDependentes() == 2 ?  5 :
 							   cliente.getDependentes() == 3 ?  4 : 1;
		
	
		//Realizando média ponderada com os valores obtidos.
		Integer pontuacaoGeral = this.getWeightedAverage(pontuacaoRenda, pontuacaoEstadoCivil , pontuacaoIdade, pontuacaoGenero, pontuacaoDependentes,
															 pesoRenda, pesoEstadoCivil, pesoIdade , pesoGenero, pesoDependentes);
		
		
		//Descobrir e retornar a analise de crédito através da pontuação geral
		return this.getCreditAnalysisByScore(pontuacaoGeral);

	}
	
	//Método que calcula média ponderada
	public Integer getWeightedAverage(Integer pontuacaoRenda, Integer pontuacaoEstadoCivil , Integer pontuacaoIdade, Integer pontuacaoGenero, Integer pontuacaoDependentes,
			Integer pesoRenda, Integer pesoEstadoCivil, Integer pesoIdade, Integer pesoGenero, Integer pesoDependentes){
			Integer pontuacaoGeral = ((pontuacaoRenda * pesoRenda) + ( pontuacaoEstadoCivil * pesoEstadoCivil) + (pontuacaoIdade * pesoIdade) + (pontuacaoGenero * pesoGenero)  + (pontuacaoDependentes * pesoDependentes)) / (pesoRenda + pesoEstadoCivil + pesoIdade + pesoGenero + pesoDependentes); 
			return pontuacaoGeral;
	}
	
	
	public AnaliseCredito getCreditAnalysisByScore(Integer pontuacaoGeral) {
		
		/*
		 * Gerar Análise de Crédito apartir da pontuação geral
		 * 
		 *  | CONDIÇÕES             | STATUS                                       |
		 *	| ----------------      | -------------------------------------------- |     
		 *	| Renda Menor que 600   | Negado   - renda baixa                       | 
		 *	| Pontuação Menor que 3 | Negado   - reprovado pela política de crédito| 
		 *	| Pontuação = 4	        | Aprovado - Limite entre 100 - 500            | 
		 *	| Pontuação = 5	        | Aprovado - Limite entre 500 - 1000           |
		 *	| Pontuação = 6	        | Aprovado - Limite entre 1000 - 1500          |
		 *	| Pontuação = 7	        | Aprovado - Limite entre 1500 - 2000          | 
		 *	| Pontuação =Maior que 8| Aprovado - Superior a 2000                   |
		 * 
		 * 
		 */
		
		if(pontuacaoGeral<=3) return new AnaliseCredito(StatusProposta.Negado, "Reprovado pela política de crédito");
		else if (pontuacaoGeral==4) return new AnaliseCredito(StatusProposta.Aprovado, 100.00,  500.00);
		else if (pontuacaoGeral==5) return new AnaliseCredito(StatusProposta.Aprovado, 500.00,  1000.00);
		else if (pontuacaoGeral==6) return new AnaliseCredito(StatusProposta.Aprovado, 1000.00,  1500.00);
		else if (pontuacaoGeral==7)return new AnaliseCredito(StatusProposta.Aprovado, 1500.00,  2000.00);
		else return new AnaliseCredito(StatusProposta.Aprovado, 2000.00,  2500.00);
				
	}
}
