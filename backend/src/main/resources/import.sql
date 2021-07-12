INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user (name, email, password) VALUES ('Ana Clara', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

INSERT INTO tb_genre (name) VALUES ('Ação');
INSERT INTO tb_genre (name) VALUES ('Aventura');
INSERT INTO tb_genre (name) VALUES ('Comédia');
INSERT INTO tb_genre (name) VALUES ('Terror');

INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('Os Cara de Pau', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 2);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('Homem de Ferro', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 3);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('Capitão America', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 4);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('DEUS é Brasileiro', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 3);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('Homem Aranha', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 4);
INSERT INTO tb_movie (title, sub_Title, year, img_Url, synopsis, genre_Id) VALUES ('Se Beber não Case', 'De Volta em Ação', 2005, 'http://moviesflix.com.br/images/movies/os-caras-de-pau.img', 'Este é um filme que vai ...', 1);

INSERT INTO tb_review (text, movie_Id, user_Id) VALUES ('Este filme é muito legal e divertido', 1, 1);
INSERT INTO tb_review (text, movie_Id, user_Id) VALUES ('Não gostei podia ter mais ação', 2, 2);
INSERT INTO tb_review (text, movie_Id, user_Id) VALUES ('Cara da muito medo mesmo', 1, 1);
INSERT INTO tb_review (text, movie_Id, user_Id) VALUES ('Bom não vejo a hora de sair o dois', 3, 3);
