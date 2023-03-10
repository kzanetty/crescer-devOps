DROP TABLE IF EXISTS permissao CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS amigo CASCADE;
DROP TABLE IF EXISTS postagem CASCADE;
DROP TABLE IF EXISTS curtida CASCADE;
DROP TABLE IF EXISTS comentario CASCADE;

CREATE TABLE usuario (
	id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	apelido VARCHAR(50),
	data_nascimento DATE NOT NULL,
	senha VARCHAR(128) NOT NULL,
	image_url VARCHAR(512),
	ativo BOOLEAN NOT NULL
);

CREATE TABLE permissao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL
);
ALTER TABLE permissao ADD CONSTRAINT pk_permissao PRIMARY KEY (id);
ALTER TABLE permissao ADD CONSTRAINT uk_permissao UNIQUE (funcao, usuario_id);
ALTER TABLE permissao ADD CONSTRAINT fk_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id);


CREATE TABLE amigo (
	id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	id_usuario_solicitante BIGINT NOT NULL,
	id_usuario_recebedor BIGINT NOT NULL,
	status VARCHAR(50) NOT NULL,
	CONSTRAINT check_diferentes_usuarios CHECK (id_usuario_solicitante != id_usuario_recebedor),
	CONSTRAINT check_solicitacao_unica UNIQUE (id_usuario_solicitante, id_usuario_recebedor),
	CONSTRAINT pk_solicitacao_amizade_solicitante FOREIGN KEY (id_usuario_solicitante) REFERENCES usuario(id),
	CONSTRAINT pk_solicitacao_amizade_recebedor FOREIGN KEY (id_usuario_recebedor) REFERENCES usuario(id),
	CONSTRAINT check_amigos_status check (status in ('PENDENTE', 'ACEITO', 'NEGADO'))
);

CREATE TABLE postagem (
	id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	id_usuario BIGINT NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	image_url VARCHAR(512) NOT NULL,
	data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	visibilidade varchar(50) DEFAULT 'PUBLICO',
	CONSTRAINT pk_postagem_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	CONSTRAINT check_postagem_visibilidade check (visibilidade in ('PRIVADO', 'PUBLICO'))
);

CREATE TABLE curtida (
	id_postagem BIGINT NOT NULL,
	id_usuario BIGINT NOT NULL,
	CONSTRAINT check_curtidas_unique UNIQUE (id_postagem, id_usuario),
	CONSTRAINT pk_curtida_id_usuario FOREIGN KEY (id_postagem) REFERENCES postagem(id),
	CONSTRAINT pk_curtida_id_postagem FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE comentario (
	id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	id_postagem BIGINT NOT NULL,
	id_usuario BIGINT NOT NULL,
	comentario VARCHAR(255) NOT NULL,
	CONSTRAINT pk_comentario_id_postagem FOREIGN KEY (id_postagem) REFERENCES postagem(id),
	CONSTRAINT pk_comentario_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);