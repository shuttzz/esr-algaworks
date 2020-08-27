CREATE TABLE public.pedidos (
    id serial8 NOT NULL,
    subtotal numeric(10,2) NOT NULL,
    taxa_frete numeric(10,2) NOT NULL,
    valor_total numeric(10,2) NOT NULL,
    data_criacao timestamp NOT NULL,
    forma_pagamento_id int8 NOT NULL,
    restaurante_id int8 NOT NULL,
    cliente_id int8 NOT NULL,
    status varchar(10) NOT NULL,
    data_confirmacao timestamp NULL,
    data_cancelamento timestamp NULL,
    data_entrega timestamp NULL,
    updated_at timestamp NOT NULL,
    endereco_cep varchar(10) NOT NULL,
    endereco_logradouro varchar(100) NOT NULL,
    endereco_numero varchar(10) NOT NULL DEFAULT 'S/N',
    endereco_complemento varchar(60) NULL,
    endereco_bairro varchar(60) NOT NULL,
    endereco_cidade_id int8 NOT NULL,
    CONSTRAINT pedidos_pkey PRIMARY KEY (id),
    CONSTRAINT pedidos_cidades_fk FOREIGN KEY (endereco_cidade_id) REFERENCES public.cidades(id),
    CONSTRAINT pedidos_formas_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.formas_pagamento(id),
    CONSTRAINT pedidos_restaurantes_fk FOREIGN KEY (restaurante_id) REFERENCES public.restaurantes(id),
    CONSTRAINT pedidos_clientes_fk FOREIGN KEY (cliente_id) REFERENCES public.usuarios(id)
) WITHOUT OIDS;

CREATE TABLE public.itens_pedidos (
    id serial8 NOT NULL,
    quantidade int4 NOT NULL,
    preco_unitario numeric(10,2) NOT NULL,
    preco_total numeric(10,2) NOT NULL,
    produto_id int8 NOT NULL,
    pedido_id int8 NOT NULL,
    observacao varchar(255) NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT itens_pedidos_pkey PRIMARY KEY (id),
    CONSTRAINT itens_pedidos_produtos_fk FOREIGN KEY (produto_id) REFERENCES public.produtos(id),
    CONSTRAINT itens_pedidos_pedidos_fk FOREIGN KEY (pedido_id) REFERENCES public.pedidos(id)
) WITHOUT OIDS;


