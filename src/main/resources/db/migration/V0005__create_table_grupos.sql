CREATE TABLE public.grupos (
   id serial8 NOT NULL,
   nome varchar(100) NOT NULL,
   created_at timestamp NOT NULL,
   updated_at timestamp NOT NULL,
   CONSTRAINT grupos_pkey PRIMARY KEY (id)
) WITHOUT OIDS;