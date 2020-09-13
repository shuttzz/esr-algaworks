ALTER TABLE public.cidades
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.cozinhas
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.estados
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.formas_pagamento
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.grupos
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.itens_pedidos
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.pedidos
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.permissoes
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.produtos
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.restaurantes
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;

ALTER TABLE public.usuarios
    ADD COLUMN codigo UUID DEFAULT uuid_generate_v4() NOT NULL;