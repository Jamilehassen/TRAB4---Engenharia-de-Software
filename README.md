# TRABALHO DE BANCO DE DADOS – 2
## TRABALHO T3 e T4 - ENGENHARIA DE SOFTWARE I
Realizar o trabalho em grupo de até 3 alunos.

Trabalho entregue incompleto: -80% da nota.

Trabalho entregue com atraso: -80% da nota.
Observação: Considera-se atraso qualquer trabalho entregue após 10 minutos do início da aula.

Eventuais cópias de conteúdo ou trabalho idêntico a de outros grupos resultarão em nota zero para todos os envolvidos.

Trabalhos a Serem Realizados
### T3A – Modelagem OO de um Estudo de Caso
Peso: 1,0

Apresentação: 29/01/25

Entregáveis (impresso):

Diagrama de Casos de Uso

Diagrama de Classes de Negócio

Modelo E-R

Implementação das Classes de Negócio (componente) – em Java

### T3B – Implementação Back-end / Front-end Endereço
Peso: 2,0

Apresentação: 12/02/25

De acordo com o diagrama de classes discutido em sala de aula, implementar em arquitetura N camadas (orientada a serviços) os serviços de Endereço.

Serviço implementado na classe: UCEnderecoGeralServicos.

### T3C – Implementação Estudo Caso
Objetivo:

Implementação Back-end / Camada de Negócio para os serviços de cadastrarCliente e consultarCliente ou cadastrarPaciente e consultarPaciente, além de outros serviços auxiliares caso necessário para implementar o front-end relacionado ao cadastro escolhido.

Implementação Front-end referente aos serviços implementados no back-end.

A implementação deve refletir o que foi modelado no trabalho T3A.

Utilizar os serviços de Endereço implementados no trabalho T3B.

Indicar o(s) endereço(s) HTTP(s) de cada serviço implementado.

Implementar:
a) Camada de Serviços (Back-end)
b) Interface Web dos Serviços (Front-end)

Observação: Observar a estruturação de projetos (BO, Serviços, Infra) conforme a lógica do trabalho T3B.

### T4A – Implementação do Front-end e Back-end Requerido (Estudo de Caso Escolhido)
Objetivo:

Implementação Back-end / Camada de Negócio para os serviços de cadastrarOS e consultarOS ou cadastrarReceita e consultarReceita, além de outros serviços auxiliares caso necessário para implementar o front-end relacionado ao estudo de caso escolhido (por exemplo, cadastrar OS ou cadastrar Venda).

Implementação Front-end referente aos serviços implementados no back-end.

A implementação deve refletir o que foi modelado no trabalho T3A.

Indicar o(s) endereço(s) HTTP(s) de cada serviço implementado.

### T4B – Uso de Microserviços de IA
Objetivo:
Utilizar serviços de IA para treinar a máquina a interpretar textos que permitam ao usuário dizer, por exemplo:

Consultar um cliente ou paciente específico.
Exemplo: "Eu quero saber sobre o produto código 12345"

Consultar uma Receita Médica ou Ordem de Serviço.

Interface com o usuário (Web) para demonstrar a funcionalidade.

Para recuperar os dados de “consulta”, é obrigatório que se chame o mesmo serviço implementado no trabalho T3C e T4A, e não uma consulta direta ao SGBD ou reimplementação de lógica em alguma linguagem.

Na apresentação:
Apresentar a solução funcionando, chamando os serviços (parte 2) e demonstrar quais ferramentas, frameworks e arquiteturas foram utilizados para a implementação.

Requisitos Técnicos e Não Funcionais
1. Camada de Negócio / Serviço – Back-end
Exposição dos Serviços: via WebServices.

Conectividade: Uso de pool de conexões com o SGBD.

Tecnologias: Utilização de tecnologias Java para a implementação.

Transações: Observação do contexto transacional dos serviços.

Arquitetura: Seguir o padrão de desenvolvimento exposto em aula.

Modelagem OO: Uso do diagrama padrão das classes de negócio (por exemplo, pessoa, pessoa física, pessoa jurídica, etc).

2. Camada de Interface – Front-end
Pode ser realizada com qualquer técnica ou ferramenta.

Deve refletir uma interface semelhante ao protótipo proposto (UX – User Experience).

Interface de teste pode ser textual (focada apenas em testar os serviços).

Estrutura dos Projetos
Camada de Negócio (Back-end)
Projeto 1 – MyEnderecoBO:

Classes tipo entidade (Business Object / Value Objects)

Pacote: unioeste.geral.endereco.bo

Observação: Cada classe de negócio deve implementar Serializable.

Projeto 2 – MyInfraAPI:

Classe de conectividade com o SGBD

Pacote: unioeste.apoio.<nome_do_pacote_conforme_tipo_infra> (ex.: BD, etc).

Projeto 3 – MyEnderecoServicos:

Classe UCEnderecoGeralServicos, classes auxiliares e DAOs.

Pacotes:

unioeste.geral.endereco.manager

unioeste.geral.endereco.col (para objetos do tipo Col)

unioeste.geral.endereco.dao (para objetos DAO)

Camada de Interface Web (Front-end)
Caso de uso: Manter Endereço

Deve incluir uma opção para demonstrar a funcionalidade – por exemplo, mostrar o endereço obtido de um site.

Projeto 4 – Camada Interface – Projeto Teste:

Teste unitário para cada serviço disponibilizado.

Critérios de Avaliação
Compatibilidade da implementação com a arquitetura de desenvolvimento proposta.

Correta implementação e funcionamento das aplicações.

# Entrega dos serviços
| SERVIÇO              | Descrição                                                        | Parâmetro Entrada | Parâmetro Saída                                                          | Endereço HTTP do serviço                                                                                                                                                             |
|----------------------|------------------------------------------------------------------|-------------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| cadastrarEndereco    | Incluir novo endereço. Validar CEP, S/N, dados em branco.        | Objeto Endereco   | Erro: EnderecoException (validação) ou Exception (infra). Se OK: Objeto Endereco | http://localhost:8080/Servlet_Paciente/CadastrarEndereco?sigla_estado=SIGLA&nome_estado=ESTADO&id_cidade=IDC&nome_cidade=NOMECIDADE&id_bairro=IDB&nome_bairro=NOMEBAIRRO&id_logradouro=IDL&nome_logradouro=NOMELOG&id_tipo_logradouro=IDTL&nome_tipo_logradouro=NOMETIPOLOG&cep=CEP |
| obterEnderecoPorCEP  | Retorna lista de endereços para um CEP específico.               | CEP               | Lista de Endereços                                                       | http://localhost:8080/Servlet_Paciente/ObterEnderecoPorCEP?cep=CEP                                                                                                                   |
| obterEnderecoPorID   | Retorna o endereço para um ID específico.                        | ID                | Objeto Endereço com ID. Erro: EnderecoException ou Exception             | http://localhost:8080/Servlet_Paciente/ObterEnderecoPorID?id=ID                                                                                                                      |
| cadastrarPaciente    | Incluir novo paciente. Validar CPF, RG, S/N e dados em branco.     | Objeto Paciente   | Erro: PacienteException ou Exception. Se OK: Objeto Paciente             | http://localhost:8080/Servlet_Paciente/CadastrarPaciente?nome_paciente=NOMEPACIENTE&cpf=CPF&rg=RG&numero_endereco=NÚMERO&complemento_endereco=COMPLEMENTO&data_nascimento_paciente=DATANASC8&cep=CEP                          |
| obterEnderecoExterno | Retorna endereço de site, microserviço ou webservice.              | Site a pesquisar  | Erro: EnderecoException ou Exception. Se OK: Objeto EnderecoEspecifico     | http://localhost:8080/Servlet_Paciente/ObterEnderecoExterno?cep=CEP                                                                                                                   |
| obterCidade          | Retorna o objeto Cidade para um ID de cidade.                      | ID da Cidade      | Objeto Cidade com UF. Erro: EnderecoException ou Exception               | http://localhost:8080/Servlet_Paciente/ObterCidade?id=ID                                                                                                                             |
| obterPacientes       | Retorna todos os pacientes cadastrados.                           | -                 | Lista de Pacientes                                                       | http://localhost:8080/Servlet_Paciente/ObterPacientes                                                                                                                                 |
| cadastrarReceita     | Incluir receita médica. Valida se já existe número da receita.      | Objeto Receita    | Se OK: Objeto Receita. Senao, Exception (validação ou infra)             | http://localhost:8080/Servlet_Paciente/CadastrarReceitaMedica                                                                                                                         |
| consultarReceitas    | Retorna todas as receitas cadastradas.                            | -                 | Lista de Receitas                                                        | http://localhost:8080/Servlet_Paciente/ConsultarReceitaMedica                                                                                                                         |
| consultarReceita     | Retorna a receita pelo número passado como parâmetro.             | Inteiro           | Objeto Receita                                                           | http://localhost:8080/Servlet_Paciente/ConsultarReceitaMedica?nro_receita={x}                                                                                                         |
| consultarMedicos     | Retorna lista de médicos.                                           | -                 | Lista de Médicos                                                         | http://localhost:8080/Servlet_Paciente/ConsultarMedicos                                                                                                                              |
| consultarMedicamentos| Retorna lista de medicamentos.                                      | -                 | Lista de Medicamentos                                                    | http://localhost:8080/Servlet_Paciente/ConsultarMedicamentos                                                                                                                         |
| consultarCIDs        | Retorna lista de CIDs cadastradas.                                  | -                 | Lista de CIDs                                                            | http://localhost:8080/Servlet_Paciente/ConsultarCIDs                                                                                                                                |
