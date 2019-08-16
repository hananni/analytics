# Avaliação Técnica - Java + Angular (Zallpy)

Repositório destinado a entrega do projeto de software de análise de crédito bancário.

## Tecnologias Utilizadas:

- Backend: Java, Spring Boot, JPA e Maven (IDE - STS Spring Tool Suite);
- Frontend: HTML5, CSS3, Bootstrap v4, AngularJs, Node, NPM e Gulp (IDE - Visual studio code);
- Banco de Dados: PostgreSQL (Heroku Cloud);
- Testes: JUnit, Mockito;
- Documentação: Swagger.

## Instalação: 

Após o clone, importe como projeto maven na IDE (Recomendado: STS - Spring Tool Suíte)
No diretório do projeto, vá até a pasta src/main/resources/public e execute o comando para fazer o download de dependências do frontend:

```
npm install

```

Após o término do comando, execute o comando para gerar os arquivos para build:


```
gulp build

```

Em seguida, execute o servidor utilizando o tomcat na IDE, e acesse http://localhost:8080 no browser

![zallpy](https://i.imgur.com/NFTqfvF.png "zallpy")


## Documentação:

Para acessar a documentação de end points da API no Swagger, acesse:  http://localhost:8080/swagger-ui.html


## Testes:

Para a criação dos testes foram considerados as funções do escopo do projeto:

- Cadastro do Cliente
- Cadastro da Análise de Crédito
- Busca da Análise de Crédito pelo CPF
- Casos de Teste

Para executar os testes, no diretório do projeto execute: 

```
mvn test

``` 

## Funcionalidade: 

Para a construção da funcionalidade, foi necessário descobrir os padrões de análise de acordo com os resultados e pesos (níveis de importância) de cada variável informada.
Foi definido uma "tabela de pontuação", de maneira que cada variável analisada terá um peso e sua pontuação específica, e assim realizar a média ponderada para descobrir a pontuação geral, no qual foi definido na tabela abaixo:

| CONDIÇÕES             | STATUS                                       |
| ----------------      | -------------------------------------------- |     
| Renda Menor que 600   | Negado   - renda baixa                       | 
| Pontuação Menor que 3 | Negado   - reprovado pela política de crédito| 
| Pontuação = 4	        | Aprovado - Limite entre 100 - 500            | 
| Pontuação = 5	        | Aprovado - Limite entre 500 - 1000           |
| Pontuação = 6	        | Aprovado - Limite entre 1000 - 1500          |
| Pontuação = 7	        | Aprovado - Limite entre 1500 - 2000          | 
| Pontuação =Maior que 8| Aprovado - Superior a 2000                   |

Para análisar os padrões de análise de acordo com o peso das variáveis conforme os resultados, foi utilizado o conceito de *Backtracking*, 
no que consiste em um algorítimo de força bruta para realizar inúmeros casos de teste comparando os valores de entrada e o resultado esperado. 
Após o monitoramento do algoritmo, foi identificado o melhor caso **(12 de 14 casos - 85% de precisão)** para os pesos com as seguintes variáveis analisadas: 

#### Pesos: 

| VARIÁVEIS 		| PESO					       |
| ----------------      | -------------------------------------------- |     
| RENDA			| 5			                       | 
| ESTADO CIVIL		| 2					       | 
| DEPENDENTES	        | 1    					       | 
| GENERO	        | 1      				       |
| IDADE		        | 1					       |


Critérios de pontuação (Também obtidos pelos resultados do algorítimo de *backtracking*):

#### Renda:
 
| RENDA			| PONTUAÇÃO				       |
| ----------------      | -------------------------------------------- |     
| MENOR QUE 600		| 0			                       | 
| ENTRE 601 a 2200	| 2					       | 
| ENTRE 2201 a 2800     | 4					       | 
| ENTRE 2801 a 5000     | 9           				       |
| MAIOR QUE 5000        | 12 (BONIFICAÇÃO POR RENDA ALTA)	       |

#### Estado Civil

| ESTADO CIVIL | PONTUAÇÃO |
|--------------|-----------|
| SEPARADO     | 9         |
| VIUVO        | 5         |
| SOLTEIRO     | 7         |
| CASADO       | 4         |


#### Idade:

| IDADE               | PONTUAÇÃO |
|---------------------|-----------|
| MENOR OU IGUAL A 20 | 9         |
| ENTRE 21 A 30       | 3         |
| ENTRE 31 A 40       | 6         |
| ENTRE 41 A 50       | 5         |
| MAIOR QUE 50        | 7         |

#### Gênero

| GENERO    | PONTUAÇÃO |
|-----------|-----------|
| FEMININO  | 5         |
| MASCULINO | 7         |

#### DEPENDENTES

| DEPENDENTES | PONTUAÇÃO |
|-------------|-----------|
| 0           | 8         |
| 1           | 5         |
| 2           | 5         |
| 3           | 4         |
| MAIOR QUE 3 | 1         |


   