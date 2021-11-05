# Resumo do projeto
Projeto desafio de finalização do bootcamp de Java (Alura bootcamps).
O projeto contém uma API REST para o gerenciamento de uma biblioteca, sendo feita com o framework Spring Boot e outras ferramentas para facilitar o desenvolvimento.
O desenvolvimento da API foi feita com base no que pude aprender dos exercícios e projetos que foram abordados no bootcamp, mas contendo também minhas próprias modificações.

![GitHub top language](https://img.shields.io/github/languages/top/gui-lirasilva/livrariaAPI)<space><space>
![Lines of Code](https://img.shields.io/tokei/lines/github/gui-lirasilva/livrariaAPI)<space><space>
![GitHub repo size](https://img.shields.io/github/repo-size/gui-lirasilva/livrariaAPI)<space><space>
![Feito por](https://img.shields.io/badge/feito%20por-Guilherme%20Henrique-blueviolet)

## 🔨 Funcionalidades do projeto

- [x] Cadastro de autor
- [x] Listagem de autores
- [x] Atualizar autor
- [x] Remover autor
- [x] Detalhar autor
- [x] Cadastro de livro
- [x] Listagem de livros
- [x] Atualizar livro
- [x] Remover livro
- [x] Detalhar livro
- [x] Relatório de quantidade de livros publicados por autor

 

## ✔️ Técnicas e tecnologias utilizadas

 ``Java 11 (JDK 11.0.12)``

 ``Eclipse Enterprise Edition for Java and web developers``

 ``Spring Boot 2.5.4``
 
 ``Spring Boot DevTools ``
 
 ``Spring Web``
 
 ``Spring Validation (Bean validation)``
 
 ``JPA``
 
 ``Hibernate core 5.4.32``
 
 ``ModelMapper 2.4.2``
 
 ``Lombok 1.18.20``
 
 ``Flyway core 7.7.3``
 
 ``Migrations``
 
 ``MySQL 5.7``
 
 ``Postman 9.0.2``
 
 ``DTO (Data transfer object)``

### Endpoints

Path | Method | Description
---|---|---
/autores           | GET    | Lista autores
/autores           | POST   | Cadastra autor
/autores           | PUT    | Atualiza autor
/autores/id        | DELETE | Remove autor
/autores/id        | GET    | Detalha autor
/livros            | GET    | Lista livros
/livros            | POST   | Cadastra livro
/livros            | PUT    | Atualiza livro
/livros/id         | DELETE | Remove livro
/livros/id         | GET    | Detalha livro
/relatorios/autor  | GET    | Exibe relatório

## 📁 Acesso ao projeto
Você pode acessar os arquivos do projeto clicando [aqui](https://github.com/gui-lirasilva/livrariaAPI/tree/master/src/main/java/br/com/alura/livrariaAPI).

## 🖼 Imagens do funcionamento da API

``Post autores``

![POST_autor](https://github.com/gui-lirasilva/livrariaAPI/blob/master/Imagens/POST_autor.png)

``Get autores``

![GET_autor](https://github.com/gui-lirasilva/livrariaAPI/blob/master/Imagens/GET_autor.png)

``Post livros``

![POST_livro](https://github.com/gui-lirasilva/livrariaAPI/blob/master/Imagens/POST_livros.png)

``Get livros``

![GET_livro](https://github.com/gui-lirasilva/livrariaAPI/blob/master/Imagens/GET_livros.png)

``Get Relatório de livros``

![GET_livro](https://github.com/gui-lirasilva/livrariaAPI/blob/master/Imagens/GET_RelatorioDeLivros.png)