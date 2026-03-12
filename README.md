<h1>Spring Boot Testing</h1>

<p>
Projeto dedicado ao estudo e aplicação de testes automatizados
em aplicações backend construídas com Spring Boot.
</p>

---

## 📌 Sobre o Projeto

Este projeto explora estratégias de testes automatizados aplicadas a uma API REST simples de gerenciamento de veículos.

A aplicação foi estruturada em camadas (Controller, Service e Repository) para permitir a validação isolada de cada componente e também a execução de testes de integração que simulam o comportamento real da aplicação.

O objetivo é compreender como diferentes tipos de testes podem ser utilizados para garantir a confiabilidade de APIs backend.

---

## 🧪 Estratégia de Testes

Os testes foram organizados para validar diferentes níveis da aplicação:

* **Controller Tests** — validação de endpoints REST e comportamento das rotas HTTP
* **Service Tests** — verificação da lógica de negócio com isolamento de dependências
* **Repository Tests** — testes de persistência utilizando banco de dados em memória
* **Integration Tests** — validação do funcionamento integrado entre as camadas

Essa abordagem permite verificar tanto unidades isoladas quanto o fluxo completo da aplicação.

---

## 🧰 Tecnologias Utilizadas

* Java
* Spring Boot
* JUnit 5
* Mockito
* Spring Boot Test
* H2 Database

---

## 🧱 Estrutura do Projeto

A aplicação segue uma arquitetura simples em camadas:

```
controller
 └─ CarController

service
 └─ CarService

repository
 └─ CarRepository

model
 └─ CarEntity

exception
 └─ Exceções de domínio
```

Os testes foram organizados espelhando essa mesma estrutura.

```
test
 ├─ controller
 │   └─ CarControllerTest
 │
 ├─ service
 │   └─ CarServiceTest
 │
 └─ repository
     ├─ CarRepositoryTest
     └─ CarRepositorySQLTest
```

Também foi utilizado um dataset de teste localizado em:

```
src/test/resources/sql/fill-cars.sql
```

Esse arquivo é utilizado para popular o banco em memória durante a execução de alguns testes de persistência.

---

## 🖼️ Exemplos de Testes

### Testes unitários da camada de serviço com Mockito

Isolamento da lógica de negócio utilizando mocks para simular dependências externas.

<img width="1303" height="851" alt="2" src="https://github.com/user-attachments/assets/8e4b84ca-cba4-4083-8d5d-2792f80ef64c" />

---

### Testes de API REST com MockMvc

Validação de endpoints REST utilizando `@WebMvcTest` e `MockMvc`.

<img width="1177" height="760" alt="3" src="https://github.com/user-attachments/assets/14d6d682-96c0-4962-9364-555400b1c375" />

---

### Testes de persistência com JPA e banco em memória

Testes de integração da camada de persistência utilizando `@DataJpaTest` e banco H2.

<img width="1418" height="793" alt="4" src="https://github.com/user-attachments/assets/d1a53a0f-f980-4b05-adf3-170ae3b9cd32" />

---

### Execução da suíte de testes

Execução completa da suíte validando o comportamento da aplicação.

<img width="1068" height="400" alt="5" src="https://github.com/user-attachments/assets/2464a51b-3430-467d-90a5-dc7048cd3365" />

---

<p align="center">
<em>🔍 Projeto criado para consolidar práticas de testes automatizados no backend e compreender estratégias de validação em aplicações Spring Boot.</em>
</p>
