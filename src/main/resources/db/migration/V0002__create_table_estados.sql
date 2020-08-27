CREATE TABLE public.estados (
    id serial8 NOT NULL,
    nome varchar(50) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT estados_pkey PRIMARY KEY (id)
) WITHOUT OIDS ;