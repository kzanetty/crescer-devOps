
## A Aplicação conta com os seguintes endpoints:

## - login

#### Rota: `POST /login`
- Para realizar o login
- Precisa ter uma conta criada para poder realizar o login.

#### Rota: `POST /logout`
- Para fazer logout com o usuario.

#### Rota: `POST /usuarios`
- Para criar um novo usuario.

## - Usuario

#### Rota: `GET /vikings`
- Vai listar todos os vikings criado.

#### Rota: `GET /vikings/me`
- Ira exibir o usuario logado no momento.

#### Rota: `GET /vikings/pesquisar?text=nomeOuemail`
- Aqui nós podemos pesquisar qualquer usuario por nome ou email.

#### Rota: `GET /vikings/detalhar/idUsuario`
- Aqui nós podemos procurar um usuario por id.

#### Rota: `POST /vikings/editar`
- Aqui nós podemos editar o usuario que está logado no momento.
- Devemos informar um body como no exemplo a seguir:

```json
{
    "nome": "Nome alterado",
    "apelido": "Atualizado",
    "imageUrl": "Nova imagem atualizada"
}
```

## - postagem

#### Rota: `GET /postagens?size=2&page=0&sort=id`
- Vai nós podemos listar todas postagens já feita pelo usuario logado.

#### Rota: `GET /postagens/timeline`
- Ira exibir todas postagens do usuario logado no momento e de seus amigos.

#### Rota: `GET /postagens/idUsuario?size=10&page=0&sort=id`
- Aqui nós podemos pesquisar as postagem de um usuario especifico.

#### Rota: `GET /postagens/amigos`
- Aqui nós podemos procurar as postagens do amigos do usuario logado.

#### Rota: `POST /postagens`
- Aqui nós podemos adicionar uma postagem.
- Devemos informar um body como no exemplo a seguir:

```json
{
    "idUsuario": 6,
    "descricao": "Que foto bonita. Incrivel!!",
    "imageUrl": "URL Imaginaria aqui",
    "visibilidade": "PUBLICO"
}
```

#### Rota: `POST /postagens/visibilidade/idPostagem`
- Ira alterar a visiblidade da postagem, entre publico e privado.
- Devemos informar um body como no exemplo a seguir:

```json
{
    "visibilidade": "PRIVADO"
}
```

#### Rota: `POST /postagens/comentar/idPostagem`
- Aqui nós podemos comentar em um publicação. Informando o id da postagem.
- Devemos informar um body como no exemplo a seguir:

```json
{
    "idUsuario": 6,
    "idPostagem": 1,
    "comentario": "Comentario aqui"
}
```

#### Rota: `POST /postagens/curtir/idPostagem`
- Aqui nós podemos curtir a postagem. Informando o id da postagem.

#### Rota: `POST /postagens/descurtir/idPostagem`
- Aqui nós podemos descutir a postagem. Informando o id da postagem.


## - Amigos

#### Rota: `GET /amigos/disponiveis`
- Aqui nós podemos listar todos usuario que não são amigos do usuario logado.

#### Rota: `GET /amigos`
- Aqui nós podemos listar todos amigos do usuario.

#### Rota: `GET /amigos/pedidos`
- Aqui nós podemos listar todos pedidos de amizade que o usuario logado recebeu.

#### Rota: `POST /amigos/enviar/idUsuario`
- Aqui nós podemos enviar um pedido de amizade para o id informado.

#### Rota: `POST /amigos/pedidos/aceitar/idAmizade`
- Aqui nós podemos nós podemos aceitar um pedido de amizade recebido.

#### Rota: `POST /amigos/pedidos/rejeitar/idAmizade`
- Aqui nós podemos rejeitar um pedido de amizade.

#### Rota: `POST /amigos/desfazer/idAmizade`
- Aqui nós podemos desfazer uma amizade.

#### Rota: `GET /amigos/pesquisar?text=NomeOuEmail`
- Aqui nós podemos procurar um amigo por texto, informando email ou nome.




#### Geramos a tabela  no banco de dados com o seguinte comando:

```sql
 
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
    
```
