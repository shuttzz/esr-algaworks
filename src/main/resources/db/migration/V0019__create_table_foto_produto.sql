CREATE TABLE public.fotos_produtos (
     produto_id int8 NOT NULL,
     nome_arquivo varchar(150) NOT NULL,
     descricao varchar(150),
     content_type varchar(80) NOT NULL,
     tamanho int8 NOT NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT fotos_produtos_pkey PRIMARY KEY (produto_id),
     CONSTRAINT fotos_produtos_fk FOREIGN KEY (produto_id) REFERENCES produtos(id)
) WITHOUT OIDS;