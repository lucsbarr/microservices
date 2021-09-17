# Projeto Spring-Boot Product-MS

## OBJETIVO

&emsp;O objetivo deste projeto é expor uma API de produto para fins de revisão e validação de conhecimento sobre o tema microserviços, implantado através do SpringBoot Java.<br />

### Endpoints

***O projeto foi configurado para rodar na porta 9999*** <br />
| Verbo HTTP  |  Resource path    |           Descrição           |
|-------------|:------------------|:------------------------------|
| POST        |  /products        |   Criação de um produto       |
| PUT         |  /products/{id}   |   Atualização de um produto   |
| GET         |  /products/{id}   |   Busca de um produto por ID  |
| GET         |  /products        |   Lista de produtos           |
| GET         |  /products/search |   Lista de produtos filtrados |
| DELETE      |  /products/{id}   |   Deleção de um produto       |

### Exemplo de requests

```
Criação de um produto  
POST
http://localhost:9999/products
```
```
Atualização de um produto
PUT
http://localhost:9999/products/3
```
```
Busca de um produto por ID
GET
http://localhost:9999/products/3
```
```
Lista de produtos
GET
http://localhost:9999/products
```
```
Lista de produtos filtrados
GET
http://localhost:9999/products/search?min_price=0.99&max_price=5.99&q=faca
```
```
Deleção de um produto
DELETE
http://localhost:9999/products/3
```

### Exemplo de Body de entrada para inclusão e alteração

```json
{
"name": "Notebook",
"description": "laptop tipo laptop",
"price": 80.00
}
  ```

### Formato de saida

Os retornos são feitos por meio de json, seguindo a estutura abaixo

```json
{
    "id": "string",
    "name": "string",
    "description": "string",
    "price": 5.99
  }
  ```
Em caso de um retorno vazio, é exibido

```json
[]
```

### Formato do objeto utilizado

| Nome do campo | Tipo do Campo |               Descrição                 |
|:--------------|:--------------|:----------------------------------------|
| id            |  String       |   Id gerado automaticamente do produto  |
| name          |  String       |   Nome do produto                       |
| description   |  String       |   Descrição do produto                  |
| price         |  BigDecimal   |   Valor monetário do produto            |


### Cenários de Exceção

Os codigos de retorno para cenários de exceção são:
- 404 - NOT_FOUND: Quando o requisito não for encontrado para cenários onde o valor do id foi enviado
- 400 - BAD_REQUEST: Quando a requisição de entrada não cumprir os requisitos de validação para os campos 
  neste cenário o retorno será conforme a seguir:
  ```json
  {
    "status_code": código de erro http,
    "message": "[descrição de erro][descrição de erro]..."
  }
  ```