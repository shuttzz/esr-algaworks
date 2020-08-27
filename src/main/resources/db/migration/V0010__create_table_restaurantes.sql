CREATE TABLE public.restaurantes (
     id serial8 NOT NULL,
     endereco_bairro varchar(50) NOT NULL,
     endereco_cep varchar(10) NOT NULL,
     endereco_complemento varchar(60) NULL,
     endereco_logradouro varchar(50) NOT NULL,
     endereco_numero varchar(5) NOT NULL DEFAULT 'S/N',
     nome varchar(150) NOT NULL,
     taxa_frete numeric(19,2) NOT NULL,
     cozinha_id int8 NOT NULL,
     endereco_cidade_id int8 NOT NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT restaurantes_pkey PRIMARY KEY (id),
     CONSTRAINT restaurantes_cozinhas_fk FOREIGN KEY (cozinha_id) REFERENCES cozinhas(id),
     CONSTRAINT restaurantes_cidades_fk FOREIGN KEY (endereco_cidade_id) REFERENCES cidades(id)
) WITHOUT OIDS;