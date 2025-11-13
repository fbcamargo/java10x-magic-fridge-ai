# 🧊 MagicFridgeAI

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?style=flat-square&logo=spring)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Development-yellow?style=flat-square)

> Aplicativo Spring Boot desenvolvido em Java que gerencia o estoque de sua geladeira e gera receitas criativas usando integração ChatGPT.

## 📋 Índice

- [Visão Geral](#-visão-geral)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura / Estrutura de Pastas](#-arquitetura--estrutura-de-pastas)
- [Instalação](#-instalação)
- [Uso / Exemplos de Execução](#-uso--exemplos-de-execução)
- [Ambiente e Configurações](#-ambiente-e-configurações)
- [Licença](#-licença)

## 🎯 Visão Geral

O **MagicFridgeAI** é um projeto desenvolvido como parte do módulo de **Introdução à Inteligência Artificial** do curso Java10x. Este aplicativo permite que você:

- 📝 **Cadastre alimentos** na sua geladeira com informações como nome, categoria, quantidade e validade
- 📊 **Gerencie seu estoque** através de operações CRUD completas
- 🤖 **Gere receitas criativas** automaticamente usando a API do ChatGPT, baseadas nos alimentos disponíveis no seu estoque

O projeto demonstra a integração de uma aplicação Spring Boot tradicional com APIs de IA, utilizando programação reativa (WebFlux) para comunicação assíncrona com a API do OpenAI.

## 🛠 Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring WebFlux** - Programação reativa para integração com ChatGPT
- **H2 Database** - Banco de dados em memória
- **Flyway** - Controle de versão de banco de dados (migrações)
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

### Integração com IA
- **OpenAI ChatGPT API** - Geração de receitas usando o modelo `gpt-4o-mini`

## 📁 Arquitetura / Estrutura de Pastas

```
MagicFridgeAI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/java10x/MagicFridgeAI/
│   │   │       ├── config/          # Configurações (WebClient para ChatGPT)
│   │   │       ├── controller/      # Controllers REST (FoodItem, Recipe)
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       ├── mapper/          # Mappers entre entidades e DTOs
│   │   │       ├── model/           # Entidades JPA (FoodItem, CategoriaEnum)
│   │   │       ├── repository/      # Repositórios JPA
│   │   │       └── service/         # Lógica de negócio (FoodItemService, ChatGptService)
│   │   └── resources/
│   │       ├── application.properties    # Configurações da aplicação
│   │       └── db/migration/             # Scripts Flyway
│   └── test/                       # Testes unitários
├── pom.xml                         # Dependências Maven
└── README.md                       # Documentação
```

### Padrões Arquiteturais

- **MVC (Model-View-Controller)**: Separação clara entre camadas
- **DTO Pattern**: Transferência de dados entre camadas
- **Repository Pattern**: Abstração de acesso a dados
- **Service Layer**: Lógica de negócio isolada
- **Reactive Programming**: Comunicação assíncrona com APIs externas

## 🚀 Instalação

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ ou superior
- Conta na OpenAI com API Key (para geração de receitas)

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd MagicFridgeAI
   ```

2. **Configure as variáveis de ambiente**

   Crie um arquivo `.env` na raiz do projeto ou configure as variáveis no seu sistema operacional:

   ```bash
   # Banco de Dados H2
   DATABASE_URL=jdbc:h2:file:./data/MagicFridgeDb
   DATABASE_USERNAME=sa
   DATABASE_PASSWORD=

   # OpenAI ChatGPT API
   CHATGPT_API_URL=https://api.openai.com/v1/chat/completions
   CHATGPT_API_KEY=sua-api-key-aqui
   ```

   > 💡 **Nota**: Para obter uma API Key da OpenAI, acesse [https://platform.openai.com/api-keys](https://platform.openai.com/api-keys)

3. **Compile o projeto**
   ```bash
   mvn clean install
   ```

4. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

   Ou execute diretamente a classe principal:
   ```bash
   java -jar target/MagicFridgeAI-0.0.1-SNAPSHOT.jar
   ```

5. **Acesse a aplicação**
   - API REST: `http://localhost:8080`
   - Console H2: `http://localhost:8080/h2-console`

## 💻 Uso / Exemplos de Execução

### Endpoints da API

#### 1. **Cadastrar um alimento** (POST)
```bash
curl -X POST http://localhost:8080/food \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tomate",
    "categoria": "VEGETAIS",
    "quantidade": 5,
    "validade": "2024-12-31"
  }'
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Tomate",
  "categoria": "VEGETAIS",
  "quantidade": 5,
  "validade": "2024-12-31"
}
```

#### 2. **Listar todos os alimentos** (GET)
```bash
curl http://localhost:8080/food
```

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Tomate",
    "categoria": "VEGETAIS",
    "quantidade": 5,
    "validade": "2024-12-31"
  },
  {
    "id": 2,
    "nome": "Frango",
    "categoria": "CARNE",
    "quantidade": 2,
    "validade": "2024-12-25"
  }
]
```

#### 3. **Buscar alimento por ID** (GET)
```bash
curl http://localhost:8080/food/1
```

#### 4. **Atualizar alimento** (PUT)
```bash
curl -X PUT http://localhost:8080/food/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tomate Cereja",
    "categoria": "VEGETAIS",
    "quantidade": 10,
    "validade": "2025-01-15"
  }'
```

#### 5. **Deletar alimento** (DELETE)
```bash
curl -X DELETE http://localhost:8080/food/1
```

#### 6. **Gerar receita com ChatGPT** (GET)
```bash
curl http://localhost:8080/recipes/generate
```

**Resposta:**
```
Receita criada com base nos ingredientes disponíveis:

**Risotto de Frango com Tomate**

Ingredientes:
- 2 unidades de Frango
- 5 unidades de Tomate
...

Modo de preparo:
1. ...
```

### Categorias Disponíveis

- `CARNE` - Carnes em geral
- `LATICINIOS` - Produtos lácteos
- `VEGETAIS` - Vegetais e legumes

### Exemplo Completo de Fluxo

1. Cadastre alguns alimentos:
   ```bash
   curl -X POST http://localhost:8080/food -H "Content-Type: application/json" -d '{"nome":"Frango","categoria":"CARNE","quantidade":2,"validade":"2024-12-25"}'
   curl -X POST http://localhost:8080/food -H "Content-Type: application/json" -d '{"nome":"Tomate","categoria":"VEGETAIS","quantidade":5,"validade":"2024-12-31"}'
   curl -X POST http://localhost:8080/food -H "Content-Type: application/json" -d '{"nome":"Queijo","categoria":"LATICINIOS","quantidade":1,"validade":"2025-01-10"}'
   ```

2. Liste os alimentos cadastrados:
   ```bash
   curl http://localhost:8080/food
   ```

3. Gere uma receita baseada nos alimentos:
   ```bash
   curl http://localhost:8080/recipes/generate
   ```

## ⚙️ Ambiente e Configurações

### Variáveis de Ambiente

| Variável | Descrição | Exemplo | Obrigatório |
|----------|-----------|---------|-------------|
| `DATABASE_URL` | URL de conexão com o banco H2 | `jdbc:h2:file:./data/MagicFridgeDb` | ✅ Sim |
| `DATABASE_USERNAME` | Usuário do banco de dados | `sa` | ✅ Sim |
| `DATABASE_PASSWORD` | Senha do banco de dados | (vazio) | ✅ Sim |
| `CHATGPT_API_URL` | URL da API do OpenAI | `https://api.openai.com/v1/chat/completions` | ✅ Sim |
| `CHATGPT_API_KEY` | Chave de API da OpenAI | `sk-...` | ✅ Sim |

### Configurações do Application.properties

O arquivo `src/main/resources/application.properties` contém as seguintes configurações:

```properties
# Nome da aplicação
spring.application.name=MagicFridgeAI

# H2 Console (para desenvolvimento)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configurações do banco de dados (via variáveis de ambiente)
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver=org.h2.Driver
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Flyway (migrações)
spring.flyway.enable=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true

# ChatGPT API
chatgpt.api.url=${CHATGPT_API_URL}
chatgpt.api.key=${CHATGPT_API_KEY}
```

### Dependências Externas

- **OpenAI API**: Requer conexão com a internet e uma API Key válida
- **H2 Database**: Banco em memória, não requer instalação separada

### Porta Padrão

A aplicação roda na porta **8080** por padrão. Para alterar, adicione no `application.properties`:

```properties
server.port=8081
```

## 📚 Contexto do Projeto

Este projeto foi desenvolvido como parte do módulo de **Introdução à Inteligência Artificial** do curso Java10x, com o objetivo de demonstrar:

- Integração de aplicações Spring Boot com APIs de IA
- Uso de programação reativa (WebFlux) para comunicação assíncrona
- Boas práticas de arquitetura em aplicações Java
- Gerenciamento de estado e persistência de dados
- Criação de APIs RESTful

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

**Desenvolvido com ❤️ para o curso Java10x**

