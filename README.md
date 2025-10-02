# Sistema de Folha de Pagamento - API REST

**Disciplina:** Desenvolvimento Full Stack  
**Aluno:** Lucas Amorim  
**Atividade:** AT - Desenvolvimento de API REST com Javalin

## 📋 Descrição do Projeto

Este projeto implementa uma API REST completa para gerenciamento de folha de pagamento, desenvolvida em Java usando o framework Javalin. O sistema permite criar, listar e buscar informações de funcionários mensalistas.

## 🏗️ Estrutura do Projeto

O trabalho está dividido em **2 projetos Maven**:

### 1. **folha-service** (Projeto Principal)
Contém a API REST com todos os endpoints e testes unitários.

```
folha-service/
├── src/
│   ├── main/
│   │   └── java/br/com/lucasAmorim/
│   │       ├── app/
│   │       │   └── Main.java
│   │       ├── controller/
│   │       │   └── ApiController.java
│   │       ├── model/
│   │       │   └── Mensalista.java
│   │       └── repository/
│   │           └── MensalistaRepository.java
│   └── test/
│       └── java/br/com/lucasAmorim/test/
│           └── ServiceTests.java
└── pom.xml
```

### 2. **folha-client** (Projeto Cliente)
Contém os clientes HttpURLConnection para consumir a API.

```
folha-client/
├── src/
│   └── main/
│       └── java/br/com/lucasAmorim/client/
│           ├── CreateMensalistaClient.java
│           ├── ListMensalistasClient.java
│           ├── GetMensalistaClient.java
│           └── StatusClient.java
└── pom.xml
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### 1. Executar o Servidor (folha-service)

```bash
cd folha-service
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.lucasAmorim.app.Main"
```

O servidor iniciará em uma porta aleatória e salvará o número da porta no arquivo `porta.txt`.

### 2. Executar os Testes Unitários

```bash
cd folha-service
mvn test
```

### 3. Executar os Clientes (folha-client)

**Importante:** O servidor deve estar rodando antes de executar os clientes.

```bash
cd folha-client

# Cliente 1: Criar Mensalista
mvn exec:java -Dexec.mainClass="br.com.lucasAmorim.client.CreateMensalistaClient"

# Cliente 2: Listar Mensalistas
mvn exec:java -Dexec.mainClass="br.com.lucasAmorim.client.ListMensalistasClient"

# Cliente 3: Buscar Mensalista por Matrícula
mvn exec:java -Dexec.mainClass="br.com.lucasAmorim.client.GetMensalistaClient"

# Cliente 4: Verificar Status
mvn exec:java -Dexec.mainClass="br.com.lucasAmorim.client.StatusClient"
```

## 📡 Endpoints da API

### Rubrica 1 - Endpoints Básicos

| Método | Endpoint | Descrição | Resposta |
|--------|----------|-----------|----------|
| GET | `/hello` | Mensagem de boas-vindas | `"Hello, Javalin!"` |
| GET | `/status` | Status do servidor | `{"status":"ok","timestamp":"..."}` |
| POST | `/echo` | Ecoa mensagem enviada | `{"mensagem":"..."}` |
| GET | `/saudacao/{nome}` | Saudação personalizada | `{"mensagem":"Olá, {nome}!"}` |

### Rubrica 4 - Endpoints de Mensalista

| Método | Endpoint | Descrição | Body | Resposta |
|--------|----------|-----------|------|----------|
| GET | `/mensalistas` | Lista todos os mensalistas | - | Array de mensalistas |
| GET | `/mensalistas/{matricula}` | Busca mensalista por matrícula | - | Objeto mensalista ou 404 |
| POST | `/mensalistas` | Cria novo mensalista | JSON | Status 201 + objeto criado |

### Exemplo de JSON para POST /mensalistas

```json
{
  "matricula": "M001",
  "nome": "João Silva",
  "salario": 5000.0
}
```

## ✅ Testes Implementados (Rubrica 2)

1. **testHello()** - Valida o endpoint `/hello` retornando status 200 e mensagem correta
2. **testCreateMensalista()** - Testa a criação de mensalista via POST, verificando status 201
3. **testGetMensalistaPorMatricula()** - Testa a busca de mensalista por matrícula após criação
4. **testListMensalistas()** - Valida a listagem de mensalistas retornando array não vazio

Todos os testes utilizam JUnit 5 e fazem requisições HTTP reais para o servidor.

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Javalin 5.6.1** - Framework web leve para APIs REST
- **Gson 2.10.1** - Serialização/deserialização JSON
- **JUnit 5.9.2** - Framework de testes unitários
- **Maven** - Gerenciamento de dependências e build
- **HttpURLConnection** - Cliente HTTP nativo do Java

## 📦 Estrutura de Dados

### Classe Mensalista

```java
public class Mensalista {
    private String matricula;  // Identificador único
    private String nome;       // Nome completo
    private double salario;    // Salário mensal
}
```

## 🧪 Validações Implementadas

- **Matrícula obrigatória**: O campo matrícula não pode ser nulo ou vazio
- **JSON válido**: Validação de formato JSON nas requisições POST
- **Mensalista não encontrado**: Retorna 404 quando matrícula não existe

## 📸 Capturas de Tela (Rubrica 1)

As 4 capturas de tela devem mostrar:

1. **Tela 1**: Estrutura do projeto Maven no IDE (árvore de diretórios)
2. **Tela 2**: Arquivo `Main.java` aberto mostrando a configuração dos endpoints
3. **Tela 3**: Servidor rodando no console com a mensagem da porta
4. **Tela 4**: Teste de um endpoint usando navegador, Postman ou curl

### Exemplo de teste com curl:

```bash
# Teste 1: Endpoint /hello
curl http://localhost:PORTA/hello

# Teste 2: Endpoint /status
curl http://localhost:PORTA/status

# Teste 3: Endpoint /echo
curl -X POST http://localhost:PORTA/echo \
  -H "Content-Type: application/json" \
  -d '{"mensagem":"Testando echo"}'

# Teste 4: Endpoint /saudacao/{nome}
curl http://localhost:PORTA/saudacao/Lucas

# Teste 5: Criar mensalista
curl -X POST http://localhost:PORTA/mensalistas \
  -H "Content-Type: application/json" \
  -d '{"matricula":"M001","nome":"João Silva","salario":5000.0}'

# Teste 6: Listar mensalistas
curl http://localhost:PORTA/mensalistas

# Teste 7: Buscar mensalista
curl http://localhost:PORTA/mensalistas/M001
```

## 📝 Observações Importantes

1. **Porta Dinâmica**: O servidor usa porta aleatória (`.start(0)`), que é salva em `porta.txt`
2. **Persistência em Memória**: Os dados são armazenados em memória (List), não há banco de dados
3. **CORS**: Não foi implementado CORS, apenas para uso local
4. **Validação**: Validação básica implementada, pode ser expandida conforme necessidade

## 🎯 Rubricas Atendidas

- ✅ **Rubrica 1**: 4 endpoints básicos implementados (`/hello`, `/status`, `/echo`, `/saudacao/{nome}`)
- ✅ **Rubrica 2**: 4 testes unitários com JUnit 5
- ✅ **Rubrica 3**: 4 clientes HttpURLConnection em projeto separado
- ✅ **Rubrica 4**: 3 endpoints para Mensalista (GET list, GET by id, POST create)

## 🚧 Melhorias Futuras

- Integração com banco de dados (H2, PostgreSQL, MySQL)
- Implementação de PUT e DELETE
- Validação mais robusta (Bean Validation)
- Documentação Swagger/OpenAPI
- Tratamento de erros mais elaborado
- Logging estruturado
- Paginação na listagem
- Autenticação e autorização

## 👨‍💻 Autor

**Lucas Amorim**  
Desenvolvimento Full Stack - AT

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.
