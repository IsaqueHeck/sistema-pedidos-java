# Sistema de Pedidos em Java

Projeto backend desenvolvido em Java com foco em prática de Programação Orientada a Objetos, arquitetura em camadas, Collections, Streams API e tratamento de exceções customizadas.

---

# Funcionalidades

✔ Cadastro de produtos  
✔ Controle de estoque  
✔ Realização de pedidos  
✔ Atualização automática de estoque  
✔ Cálculo de valor total do pedido  
✔ Listagem de produtos e pedidos  
✔ Filtros utilizando Streams API  
✔ Tratamento de exceções personalizadas

---

# Tecnologias e conceitos utilizados

- Java
- Programação Orientada a Objetos (POO)
- Collections Framework
    - Map
    - List
- Streams API
- Exceptions customizadas
- Arquitetura em camadas
    - model
    - repository
    - service
    - exception

---

# Estrutura do projeto

```text
src/
 ├── model/
 ├── repository/
 ├── service/
 ├── exception/
 └── Main.java
```

---

# Regras de negócio implementadas

- Não permite cadastro de produtos duplicados
- Não permite estoque negativo
- Não permite preço negativo
- Validação de estoque durante pedidos
- Atualização automática do estoque após compra
- Lançamento de exceções em operações inválidas

---

# Funcionalidades com Streams API

- Listagem de produtos caros
- Produtos sem estoque
- Listagem apenas dos nomes dos produtos
- Pedidos acima de determinado valor

---

# Exemplo de fluxo do sistema

1. Cadastro de produtos
2. Criação de pedidos
3. Validação de estoque
4. Atualização automática do estoque
5. Cálculo do valor total
6. Salvamento do pedido

---

# Objetivo do projeto

Projeto desenvolvido com objetivo de consolidar conhecimentos fundamentais de backend Java antes do início dos estudos com Spring Boot.

---

# Autor

Desenvolvido por Heck.