# APP-WISHLIST-LUIZALABS-JAVA

**Desafio Backend:** O objetivo deste projeto é desenvolver um serviço HTTP que implemente a funcionalidade de Wishlist para o cliente.

<div style="display: inline_block">

  <img align="center" alt="java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="mongodb" src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white" />

</div>

## Execute para criar o container da aplicação

Para criar a imagem da aplicação, execute o seguinte comando:

```
  docker build -t wishlist .
```

Para iniciar o container com o banco de dados MongoDB na versão 7.0, execute:

```
  docker-compose up -d
```
Se preferir executar a aplicação diretamente na máquina, certifique-se de ter o MongoDB 7.0 e a JDK instalados.

	1.	Navegue até src > main > resources > application.properties.
	2.	Altere a propriedade spring.data.mongodb.host=db para spring.data.mongodb.host=localhost.

## Documentação da API

### Swagger

```
{URL}/swagger-ui/index.html
```

### Cria um produto

Nesta aplicação, implementei o método POST Produto para facilitar os testes dos endpoints relacionados à Wishlist.

```
  POST /api/product
```
A requisição precisa de um body com os seguintes parâmetros:

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Obrigatório**. Nome do produto |

### Adiciona um produto na Wishlist do cliente.

```
  POST /api/wishlist/{productid}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `productid` | `string` | **Obrigatório**. Id do produto |

### Remove um produto da Wishlist do cliente.

```
  DELETE /api/wishlist/{productid}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `productid` | `string` | **Obrigatório**. Id do produto |

### Consultar todos os produtos da Wishlist do cliente.

```
  GET /api/wishlist
```

### Consultar se um produto está na Wishlist do cliente.

```
  GET /api/wishlist/{productid}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `productid` | `string` | **Obrigatório**. Id do produto |

## Apêndice

Nesta aplicação, estou assumindo que cada usuário possui uma Wishlist. Portanto, o sistema primeiro busca por uma Wishlist existente. Caso não encontre, uma nova Wishlist é criada automaticamente, e o ID gerado é retornado.
