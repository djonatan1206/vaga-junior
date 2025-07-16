# 🚗 API REST para Gestão de Abastecimentos

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen) ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue) ![License](https://img.shields.io/badge/License-MIT-yellow)

Uma API RESTful completa, construída com Java e Spring Boot, para a gestão eficiente de um posto de combustíveis, incluindo sistema de autenticação e controle de acesso baseado em papéis.

## 📋 Sumário

1. [Visão Geral e Arquitetura](#-visão-geral-e-arquitetura)
2. [Stack Tecnológica](#-stack-tecnológica)
3. [Funcionalidades e Endpoints](#-funcionalidades-e-endpoints)
4. [Configuração e Execução](#-configuração-e-execução)
5. [Autenticação e Autorização](#-autenticação-e-autorização)
6. [Documentação Interativa](#-documentação-interativa)
7. [Exemplos de Uso](#-exemplos-de-uso)
8. [Troubleshooting](#-troubleshooting)
9. [Contribuindo](#-contribuindo)

## 🏗️ Visão Geral e Arquitetura

Este projeto implementa uma API REST para gestão de abastecimentos, desenvolvida em **Java 17** e **Spring Boot 3**. A aplicação foi projetada com uma arquitetura em camadas para garantir separação de responsabilidades, manutenibilidade e escalabilidade.

### Arquitetura em Camadas

```
┌─────────────────────────────────────────────┐
│             Controller Layer                │ ← Endpoints REST
├─────────────────────────────────────────────┤
│              Service Layer                  │ ← Lógica de Negócio
├─────────────────────────────────────────────┤
│            Repository Layer                 │ ← Acesso aos Dados
├─────────────────────────────────────────────┤
│              Model Layer                    │ ← Entidades e DTOs
└─────────────────────────────────────────────┘
```

**Principais Componentes:**
- **Controller Layer** (`/controller`): Expõe os endpoints REST
- **Service Layer** (`/service`): Orquestra a lógica de negócio
- **Repository Layer** (`/repository`): Abstrai o acesso à base de dados
- **Model Layer** (`/model` e `/dto`): Define entidades e DTOs

### Diferencial: Sistema RBAC
Sistema de autenticação e autorização baseado em papéis (Role-Based Access Control) com papéis **ADMIN** e **OPERADOR**.

## 🛠️ Stack Tecnológica

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 3.2.5** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Acesso aos dados
- **Hibernate** - ORM

### Banco de Dados
- **MySQL 8.0** - Banco de dados relacional
- **HikariCP** - Pool de conexões

### Ferramentas e Bibliotecas
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de código boilerplate
- **SpringDoc OpenAPI** - Documentação Swagger
- **JWT** - Tokens de autenticação

## 📡 Funcionalidades e Endpoints

### 🔐 Autenticação
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|---------|
| `POST` | `/api/auth/login` | Autentica um usuário | Público |

### ⛽ Gestão de Abastecimentos
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|---------|
| `GET` | `/api/abastecimentos` | Lista histórico de abastecimentos | ADMIN, OPERADOR |
| `POST` | `/api/abastecimentos` | Registra novo abastecimento | ADMIN, OPERADOR |
| `DELETE` | `/api/abastecimentos/{id}` | Remove um abastecimento | ADMIN |

### 🏭 Gestão de Bombas
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|---------|
| `GET` | `/api/bombas` | Lista todas as bombas | ADMIN |
| `POST` | `/api/bombas` | Registra nova bomba | ADMIN |
| `PUT` | `/api/bombas/{id}` | Atualiza bomba existente | ADMIN |
| `DELETE` | `/api/bombas/{id}` | Remove uma bomba | ADMIN |

### ⛽ Gestão de Combustíveis
| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|---------|
| `GET` | `/api/combustiveis` | Lista todos os combustíveis | ADMIN |
| `POST` | `/api/combustiveis` | Registra novo combustível | ADMIN |
| `PUT` | `/api/combustiveis/{id}` | Atualiza combustível existente | ADMIN |
| `DELETE` | `/api/combustiveis/{id}` | Remove um combustível | ADMIN |

## ⚙️ Configuração e Execução

### Pré-requisitos
- ☑️ **JDK 17** ou superior
- ☑️ **Maven 3.6+**
- ☑️ **MySQL Server 8.0+**
- ☑️ Cliente API (Postman, Insomnia) ou navegador web

### Passo 1: Configuração do Banco de Dados

Execute o script SQL abaixo no seu cliente MySQL:

```sql
-- Criação do schema
DROP DATABASE IF EXISTS abastecimento_db;
CREATE DATABASE abastecimento_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE abastecimento_db;

-- Criação das tabelas
CREATE TABLE combustivel (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    preco_por_litro DECIMAL(10, 3) NOT NULL
);

CREATE TABLE bomba (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    combustivel_id INT NOT NULL,
    FOREIGN KEY (combustivel_id) REFERENCES combustivel(id)
);

CREATE TABLE abastecimento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bomba_id INT NOT NULL,
    data DATETIME NOT NULL,
    litros DECIMAL(10, 3) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (bomba_id) REFERENCES bomba(id)
);

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Dados iniciais
INSERT INTO combustivel (nome, preco_por_litro) VALUES 
    ('Gasolina Comum', 5.899),
    ('Gasolina Aditivada', 6.099),
    ('Etanol', 3.999);

INSERT INTO bomba (nome, combustivel_id) VALUES 
    ('Bomba 01', 1),
    ('Bomba 02', 2),
    ('Bomba 03', 3);

INSERT INTO usuario (username, password, role) VALUES 
    ('admin', 'admin123', 'ADMIN'),
    ('operador', 'operador123', 'OPERADOR');
```

### Passo 2: Configuração da Aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/abastecimento_db
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuração do servidor
server.port=8080

# Configuração do JWT
jwt.secret=minha_chave_secreta_muito_segura
jwt.expiration=86400000
```

### Passo 3: Execução

Na raiz do projeto, execute:

```bash
# Compilar e executar
mvn spring-boot:run

# Ou compilar e executar o JAR
mvn clean package
java -jar target/abastecimento-api-1.0.0.jar
```

**🚀 A API estará disponível em:** `http://localhost:8080`

## 🔐 Autenticação e Autorização

### Como Fazer Login

1. **Faça uma requisição POST para `/api/auth/login`**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

2. **Copie o token retornado**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "admin",
  "role": "ADMIN"
}
```

3. **Inclua o token nas requisições subsequentes**
```bash
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Credenciais Padrão

| Usuário | Senha | Papel | Permissões |
|---------|-------|-------|------------|
| `admin` | `admin123` | ADMIN | Acesso completo |
| `operador` | `operador123` | OPERADOR | Sem operações DELETE |

### Papéis e Permissões

- **🛡️ ADMIN**: Acesso completo a todos os endpoints
- **👤 OPERADOR**: Acesso limitado (sem operações de DELETE)

## 📚 Documentação Interativa

Este projeto inclui **Swagger UI** para documentação e teste interativo da API.

### Acessando o Swagger

1. **Inicie a aplicação**
2. **Acesse:** `http://localhost:8080/swagger-ui.html`
3. **Explore e teste** todos os endpoints diretamente no navegador

### Exemplo: Testando um Endpoint

1. Na página do Swagger, expanda a seção `combustivel-controller`
2. Clique na barra `GET /api/combustiveis`
3. Clique em **"Try it out"**
4. Adicione o token de autenticação se necessário
5. Clique em **"Execute"**
6. Visualize a resposta JSON

## 💡 Exemplos de Uso

### Fluxo Básico de Operação

#### 1. Fazer Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

#### 2. Listar Combustíveis
```bash
curl -X GET http://localhost:8080/api/combustiveis \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### 3. Registrar Abastecimento
```bash
curl -X POST http://localhost:8080/api/abastecimentos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "bombaId": 1,
    "litros": 25.5,
    "valorTotal": 150.45
  }'
```

#### 4. Consultar Histórico
```bash
curl -X GET http://localhost:8080/api/abastecimentos \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

### Exemplos de Respostas

#### Lista de Combustíveis
```json
[
  {
    "id": 1,
    "nome": "Gasolina Comum",
    "precoPorLitro": 5.899
  },
  {
    "id": 2,
    "nome": "Gasolina Aditivada",
    "precoPorLitro": 6.099
  }
]
```

#### Registro de Abastecimento
```json
{
  "id": 1,
  "bombaId": 1,
  "data": "2024-01-15T10:30:00",
  "litros": 25.5,
  "valorTotal": 150.45
}
```

## 🔧 Troubleshooting

### Problemas Comuns

#### ❌ Erro de Conexão com MySQL
```
Caused by: java.net.ConnectException: Connection refused
```
**Solução:**
- Verifique se o MySQL está rodando
- Confirme usuário e senha em `application.properties`
- Teste a conexão: `mysql -u root -p`

#### ❌ Erro 401 Unauthorized
```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 401,
  "error": "Unauthorized"
}
```
**Solução:**
- Verifique se o token JWT está sendo enviado corretamente
- Token pode ter expirado, faça login novamente
- Formato correto: `Authorization: Bearer TOKEN`

#### ❌ Erro 403 Forbidden
```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 403,
  "error": "Forbidden"
}
```
**Solução:**
- Usuário não tem permissão para acessar o recurso
- Verifique se o papel do usuário permite a operação
- OPERADOR não pode executar operações DELETE

#### ❌ Erro na Compilação
```
Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin
```
**Solução:**
- Verifique se está usando JDK 17 ou superior
- Execute: `java -version` e `javac -version`
- Configure `JAVA_HOME` corretamente

### Logs Úteis

Para debug, monitore os logs da aplicação:
```bash
# Logs em tempo real
tail -f logs/application.log

# Logs de erro
grep ERROR logs/application.log
```

## 👨‍💻 Desenvolvedor

Desenvolvido por Djonatan Antunes.

---
