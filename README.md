# API REST para Gestão de Abastecimentos

Uma API RESTful completa, construída com Java e Spring Boot, para a gestão de um posto de combustíveis, incluindo um sistema de autenticação e controlo de acesso baseado em papéis.

---

## Sumário
* [1. Visão Geral e Arquitetura](#1-visão-geral-e-arquitetura)
* [2. Funcionalidades e Endpoints](#2-funcionalidades-e-endpoints)
* [3. Tecnologias Utilizadas](#3-tecnologias-utilizadas)
* [4. Guia de Configuração e Execução](#4-guia-de-configuração-e-execução)
* [5. Como Testar a API (Documentação Interativa)](#5-como-testar-a-api-documentação-interativa)

---

## 1. Visão Geral e Arquitetura

Este projeto implementa uma API REST para a gestão de abastecimentos, desenvolvida em **Java 17** e **Spring Boot 3**. A aplicação foi desenhada com uma **arquitetura em camadas** para garantir a separação de responsabilidades, manutenibilidade e escalabilidade.

As decisões arquitetónicas chave incluem:
* **Camada de Controller (`/controller`):** Expõe os endpoints REST, atuando como a porta de entrada da aplicação.
* **Camada de Serviço (`/service`):** Orquestra a lógica de negócio (validações, cálculos, etc.).
* **Camada de Repository (`/repository`):** Abstrai o acesso à base de dados com **Spring Data JPA**.
* **Camada de Modelo (`/model` e `/dto`):** Define as entidades (`@Entity`) e os Data Transfer Objects (DTOs) para uma comunicação segura e flexível com a API.

Como um diferencial significativo, foi implementado um sistema de **autenticação e autorização baseado em papéis (RBAC)**, com os papéis `ADMIN` e `OPERADOR`, para proteger os endpoints de gestão.

---

## 2. Funcionalidades e Endpoints

A API está estruturada em torno dos recursos principais do sistema.

#### Autenticação
* `POST /api/auth/login`: Autentica um utilizador.

#### Gestão de Abastecimentos (`ADMIN` e `OPERADOR`)
* `GET /api/abastecimentos`: Lista o histórico de todos os abastecimentos.
* `POST /api/abastecimentos`: Regista um novo abastecimento (suporta registo por litros ou por valor).
* `DELETE /api/abastecimentos/{id}`: Remove um abastecimento (**Acesso:** `ADMIN`).

#### Gestão de Bombas (`ADMIN`)
* `GET /api/bombas`: Lista todas as bombas.
* `POST /api/bombas`: Regista uma nova bomba.
* `PUT /api/bombas/{id}`: Atualiza uma bomba existente.
* `DELETE /api/bombas/{id}`: Remove uma bomba.

#### Gestão de Combustíveis (`ADMIN`)
* `GET /api/combustiveis`: Lista todos os tipos de combustível.
* `POST /api/combustiveis`: Regista um novo tipo de combustível.
* `PUT /api/combustiveis/{id}`: Atualiza um combustível existente.
* `DELETE /api/combustiveis/{id}`: Remove um combustível.

---

## 3. Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3.2.5
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Base de Dados:** MySQL
* **Gestão de Projeto:** Apache Maven
* **Documentação da API:** SpringDoc OpenAPI (Swagger UI)
* **Ferramentas Auxiliares:** Lombok

---

## 4. Guia de Configuração e Execução

Para executar o projeto localmente, siga os passos abaixo.

### Pré-requisitos
* JDK 17
* Maven 3.6+
* MySQL Server
* Um cliente API (Postman, Insomnia) ou um navegador web.

### Passo 1: Base de Dados
Execute o script abaixo no seu cliente MySQL para criar o esquema `abastecimento_db` e popular com os dados iniciais.

```sql
DROP DATABASE IF EXISTS abastecimento_db;
CREATE DATABASE abastecimento_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE abastecimento_db;

CREATE TABLE combustivel (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50) NOT NULL, preco_por_litro DECIMAL(10, 3) NOT NULL);
CREATE TABLE bomba (id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(50) NOT NULL, combustivel_id INT NOT NULL, FOREIGN KEY (combustivel_id) REFERENCES combustivel(id));
CREATE TABLE abastecimento (id INT PRIMARY KEY AUTO_INCREMENT, bomba_id INT NOT NULL, data DATETIME NOT NULL, litros DECIMAL(10, 3) NOT NULL, valor_total DECIMAL(10, 2) NOT NULL, FOREIGN KEY (bomba_id) REFERENCES bomba(id));
CREATE TABLE usuario (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, role VARCHAR(20) NOT NULL);

INSERT INTO combustivel (nome, preco_por_litro) VALUES ('Gasolina Comum', 5.899), ('Gasolina Aditivada', 6.099), ('Etanol', 3.999);
INSERT INTO bomba (nome, combustivel_id) VALUES ('Bomba 01', 1), ('Bomba 02', 2), ('Bomba 03', 3);
INSERT INTO usuario (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');
```

### Passo 2: Configuração da Aplicação
Edite o ficheiro `src/main/resources/application.properties` e atualize a sua palavra-passe da base de dados.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/abastecimento_db
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui
```

### Passo 3: Execução
Navegue até à raiz do projeto e execute o seguinte comando Maven:

```bash
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

---

## 5. Como Testar a API (Documentação Interativa)

Este projeto inclui o **Swagger UI** para documentação e teste interativo da API.

1.  Com a aplicação a ser executada, abra o seu navegador web.
2.  Aceda ao seguinte URL:
    **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

A partir desta página, pode visualizar todos os endpoints, os seus parâmetros, e executar pedidos de teste diretamente no navegador.

#### Exemplo: Testar o endpoint de listar combustíveis
1.  Na página do Swagger, expanda a secção `combustivel-controller`.
2.  Clique na barra `GET /api/combustiveis`.
3.  Clique no botão **"Try it out"**.
4.  Clique no botão **"Execute"**.
5.  A resposta JSON aparecerá logo abaixo.
