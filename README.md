# Sistema de Pedidos

API REST para gerenciamento de produtos e pedidos, desenvolvida como projeto de estudo em Java.

O projeto começou em Java puro, com foco em Programacao Orientada a Objetos, Collections, Streams e excecoes. Depois foi evoluido para Spring Boot e atualmente utiliza Spring Data JPA para persistencia dos dados em banco H2.

## Objetivo

Consolidar conceitos de backend Java na pratica, evoluindo o mesmo projeto por etapas:

- Java puro
- Arquitetura em camadas
- Spring Boot
- API REST
- Spring Data JPA
- DTOs
- Validacoes de regra de negocio
- Tratamento global de excecoes

## Funcionalidades

- Cadastro de produtos
- Listagem de produtos
- Busca de produto por ID
- Atualizacao de produto
- Remocao de produto
- Criacao de pedidos
- Baixa automatica de estoque ao realizar pedido
- Calculo automatico do valor total do pedido
- Listagem de pedidos
- Busca de pedido por ID
- Validacao de estoque insuficiente
- Tratamento padronizado de erros

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

## Estrutura do projeto

```text
src/main/java/com/isaque/sistemapedidos
├── controller
├── dto
├── exceptionhandler
├── exceptions
├── model
├── repository
├── response
└── service
```

## Principais conceitos praticados

### Entidades JPA

As classes `Produto`, `Pedido` e `ItemPedido` representam tabelas no banco de dados.

### Repositories

Os repositories usam `JpaRepository`, permitindo operacoes como salvar, listar, buscar por ID e remover registros sem escrever SQL manualmente.

### DTOs

O projeto usa DTO para criacao de pedido. Isso evita expor diretamente as entidades da aplicacao na entrada da API.

Exemplo de DTO de pedido:

```json
{
  "nomeCliente": "Isaque",
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    }
  ]
}
```

### Transacoes

A criacao de pedido utiliza transacao para garantir que a baixa de estoque e o salvamento do pedido acontecam juntos.

## Como executar

1. Clone o repositorio:

```bash
git clone <url-do-repositorio>
```

2. Entre na pasta do projeto:

```bash
cd SistemaDePedidos
```

3. Execute a aplicacao:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API ficara disponivel em:

```text
http://localhost:8080
```

## Banco de dados

O projeto utiliza H2 em memoria para ambiente de desenvolvimento.

Console do H2:

```text
http://localhost:8080/h2-console
```

Configuracao padrao:

```text
JDBC URL: jdbc:h2:mem:sistemapedidos
User: sa
Password: <vazio>
```

Observacao: por estar em memoria, os dados sao apagados quando a aplicacao for reiniciada.

## Endpoints

### Produtos

#### Cadastrar produto

```http
POST /produtos
```

Exemplo de body:

```json
{
  "nome": "Mouse",
  "preco": 80.0,
  "quantidadeEstoque": 10
}
```

#### Listar produtos

```http
GET /produtos
```

#### Buscar produto por ID

```http
GET /produtos/{id}
```

#### Atualizar produto

```http
PUT /produtos/{id}
```

Exemplo de body:

```json
{
  "nome": "Mouse Gamer",
  "preco": 120.0,
  "quantidadeEstoque": 15
}
```

#### Remover produto

```http
DELETE /produtos/{id}
```

### Pedidos

#### Criar pedido

```http
POST /pedidos
```

Exemplo de body:

```json
{
  "nomeCliente": "Isaque",
  "itens": [
    {
      "produtoId": 1,
      "quantidade": 2
    }
  ]
}
```

#### Listar pedidos

```http
GET /pedidos
```

#### Buscar pedido por ID

```http
GET /pedidos/{id}
```

## Exemplos de validacoes

O sistema bloqueia pedidos invalidos, como:

- Pedido sem nome do cliente
- Pedido sem itens
- Item sem produto
- Quantidade menor ou igual a zero
- Produto inexistente
- Estoque insuficiente

Exemplo de resposta de erro:

```json
{
  "timestamp": "2026-06-23T14:30:10.123",
  "status": 400,
  "erro": "Quantidade deve ser maior que zero"
}
```

## Status do projeto

Projeto em evolucao, criado com foco em aprendizado pratico de backend Java.

Proximas melhorias possiveis:

- Criar DTOs para produto
- Melhorar os codigos de resposta HTTP
- Retornar `201 Created` ao criar recursos
- Adicionar testes automatizados
- Trocar H2 em memoria por banco persistente
- Documentar a API com Swagger/OpenAPI

## Autor

Desenvolvido por Heck.
