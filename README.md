# MovieFlix

# Sobre o projeto

O sistema MovieFlix consiste em um banco de filmes, os quais podem ser listados e avaliados pelos usuários.
Usuários podem ser visitantes (VISITOR) e membros (MEMBER)e apenas usuários membros podem inserir avaliações no sistema.
Ao acessar o sistema, o usuário deve fazer seu login. Apenas usuários logados podem navegar nos filmes.
Logo após fazer o login, o usuário vai para a listagem de filmes, que mostra os filmes de forma paginada, ordenados alfabeticamente por título.
O usuário pode filtrar os filmes por gênero.
Ao selecionar um filme da listagem, é mostrada uma página de detalhes, onde é possível ver todas informações do filme, e também suas avaliações.
Se o usuário for MEMBER, ele pode ainda registrar uma avaliação nessa tela.
Um usuário possui nome, email e senha, sendo que o email é seu nome de usuário.
Cada filme possui um título, subtítulo, uma imagem, ano de lançamento, sinopse, e um gênero.
Os usuários membros podem registrar avaliações para os filmes.
Um mesmo usuário membro pode deixar mais de uma avaliação para o mesmo filme.


# Modelo Conceitual

![Modelo](https://github.com/galetedanilo/movieflix/blob/master/assets/modelo.png)

# Padrão Camada

![Padrao](https://github.com/galetedanilo/movieflix/blob/master/assets/camadas.png)

# Tecnologias Utilizadas

## Back end

- Java
- Spring Boot
- Spring Security
- Spring Validation
- OAuth 2.0
- Token JWT
- JPA / Hibernate
- Lombok
- Maven
- JUnit
- Mockito

# Como Executar o Projeto

## Back end:

Pré-requisito: Java 11

```bash
# clonar repositório
git clone https://github.com/galetedanilo/movieflix.git

# entrar na pasta do back end
cd backend

# executar projeto
./mvnw spring-boot:run
```

# Agradecimentos

-  Equipe DevSuperior
-  Prof. Nélio Alves

# Autor

Danilo Fernandes Galete

https://www.linkedin.com/in/galetedanilo/

