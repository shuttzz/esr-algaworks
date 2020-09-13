CREATE TABLE public.restaurantes_usuarios (
  restaurante_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  CONSTRAINT restaurantes_usuarios_pkey PRIMARY KEY(restaurante_id, usuario_id),
  CONSTRAINT restaurantes_usuarios_fk FOREIGN KEY (restaurante_id) REFERENCES public.restaurantes(id),
  CONSTRAINT usuarios_restaurantes_fk FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id)
) WITH OIDS;