# Spring Batch Parallel Jobs

Este projeto é um exemplo de aplicação Spring Batch que executa 
dois Jobs em paralelo. A aplicação é responsável por criar 
usuários e enviar notificações utilizando as 
tabelas `person`, `address`, `user`, `users_to_create`, `notification` e 
`notifications_to_send`, `job_config`.

## Descrição

A aplicação possui dois Jobs principais:

1. **CreateUsersJob**: Este Job é responsável por criar usuários 
(user) para as pessoas (person) que estão registradas na tabela 
`users_to_create`. Além disso, uma notificação (notification) é 
criada para cada usuário criado e registrada na tabela 
`notifications_to_send`.

2. **SendNotificationsJob**: Este Job é responsável por enviar as 
notificações que estão registradas na tabela `notifications_to_send`.

Os Jobs são configurados para serem executados em paralelo, aproveitando o poder de processamento de múltiplos threads.

## Estrutura do Banco de Dados

As tabelas utilizadas pela aplicação são:

- `person`: Armazena informações sobre as pessoas.
  - `id`: Identificador único da pessoa.
  - `name`: Nome da pessoa.
  - `birthdate`: Data de nascimento da pessoa.
  - `cpf`: CPF da pessoa.
  - `email`: Email da pessoa.
  - `phone`: Telefone da pessoa.
- `address`: Armazena informações sobre os endereços das pessoas.
  - `id`: Identificador único do endereço.
  - `person_id`: Referência à pessoa na tabela person.
  - `street`: Rua do endereço.
  - `number`: Número do endereço.
  - `complement`: Complemento do endereço.
  - `neighborhood`: Bairro do endereço.
  - `city`: Cidade do endereço.
  - `state`: Estado do endereço.
  - `zip_code`: CEP do endereço.
- `user`: Armazena os usuários criados com base nas informações da tabela person.
  - `id`: Identificador único do usuário.
  - `person_id`: Referência à pessoa na tabela person.
  - `username`: Nome de usuário gerado.
  - `password`: Senha gerada.
- `users_to_create`: Armazena os IDs das pessoas que precisam ter usuários criados.
  - `id`: Identificador único.
  - `person_id`: Referência à pessoa na tabela person.
  - `created`: Indica se o usuário foi criado (0 - Não, 1 - Sim).
  - `added_at`: Data de adição do registro.
  - `created_at`: Data de criação do usuário.
- `notification`: Armazena as notificações criadas para os usuários.
  - `id`: Identificador único da notificação.
  - `message`: Mensagem da notificação.
  - `user_id`: Referência ao usuário na tabela user.
- `notifications_to_send`: Armazena as notificações que precisam ser enviadas.
  - `id`: Identificador único.
  - `notification_id`: Referência à notificação na tabela notification.
  - `added_at`: Data de adição do registro.
  - `sent_at`: Data de envio da notificação.
  - `sent`: Indica se a notificação foi enviada (0 - Não, 1 - Sim).
- `job_config`: Armazena a configuração dos Jobs.
  - `id`: Identificador único.
  - `job_name`: Nome do Job.
  - `enabled`: Indica se o Job está habilitado (0 - Não, 1 - Sim).

## Configuração e Execução

### Requisitos
- JDK 17 ou superior
- Maven 3.6+
- Banco de dados MySQL e H2 para testes

### Passos para executar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/spring-batch-parallel-jobs.git
cd spring-batch-parallel-jobs
```

2. Configure o banco de dados:

- Atualize o arquivo application.properties com as configurações do seu banco de dados.

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

4. Monitorando os Jobs:

Os Jobs são configurados para serem executados automaticamente. Você pode monitorar a execução dos Jobs através dos logs gerados pela aplicação.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- config: Contém as configurações dos Jobs e do Spring Batch.
- jobs: Contém as definições dos Jobs CreateUsersJob e SendNotificationsJob.
- models: Contém as classes de modelo mapeadas para as tabelas do banco de dados.
- repositories: Contém as interfaces dos repositórios que acessam as tabelas do banco de dados.
- services: Contém as classes de serviço responsáveis pela lógica de negócio.
- utils: Contém classes utilitárias.
- dto: Contém os Data Transfer Objects.
- tasks: Contém os componentes de tarefa que executam as etapas dos Jobs.
  - readers: Contém os leitores de dados.
  - processors: Contém os processadores de dados.
  - writers: Contém os escritores de dados.
  - listeners: Contém os listeners dos Jobs.
  - flows: Contém os flows dos Jobs.
  - steps: Contém os steps dos Jobs.
  - tasklets: Contém os tasklets dos Jobs.
