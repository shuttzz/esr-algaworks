CREATE TABLE public.produtos (
     id serial8 NOT NULL,
     ativo bool NOT NULL DEFAULT true,
     descricao varchar(255) NULL,
     nome varchar(100) NOT NULL,
     preco numeric(19,2) NOT NULL,
     restaurante_id int8 NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT produtos_pkey PRIMARY KEY (id),
     CONSTRAINT produtos_restaurantes_fk FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id)
) WITHOUT OIDS;