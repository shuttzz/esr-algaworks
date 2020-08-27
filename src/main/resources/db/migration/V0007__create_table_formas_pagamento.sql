CREATE TABLE public.formas_pagamento (
     id serial8 NOT NULL,
     descricao varchar(255) NULL,
     created_at timestamp NOT NULL,
     updated_at timestamp NOT NULL,
     CONSTRAINT formas_pagamento_pkey PRIMARY KEY (id)
) WITHOUT OIDS;