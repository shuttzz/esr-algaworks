CREATE TABLE public.permissoes (
   id serial8 NOT NULL,
   descricao varchar(100) NOT NULL,
   nome varchar(50) NOT NULL,
   created_at timestamp NOT NULL,
   updated_at timestamp NOT NULL,
   CONSTRAINT permissoes_pkey PRIMARY KEY (id)
) WITHOUT OIDS;