CREATE TABLE public.cozinhas (
     id serial8 NOT NULL,
     nome varchar(60) NOT NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT cozinhas_pkey PRIMARY KEY (id)
) WITHOUT OIDS;