CREATE TABLE public.cidades (
    id serial8 NOT NULL,
    nome varchar(80) NOT NULL,
    estado_id int8 NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT cidades_pkey PRIMARY KEY (id),
    CONSTRAINT cidades_estados_fk FOREIGN KEY (estado_id) REFERENCES estados(id)
) WITHOUT OIDS ;