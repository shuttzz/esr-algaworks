CREATE TABLE public.usuarios (
     id serial8 NOT NULL,
     nome varchar(100) NOT NULL,
     email varchar(150) NOT NULL,
     senha varchar(150) NOT NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT usuarios_pkey PRIMARY KEY (id)
) WITHOUT OIDS;