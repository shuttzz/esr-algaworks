CREATE TABLE public.usuarios_grupos (
    usuario_id int8 NOT NULL,
    grupo_id int8 NOT NULL,
    CONSTRAINT usuarios_grupos_fk FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT grupos_usuarios_fk FOREIGN KEY (grupo_id) REFERENCES grupos(id)
) WITHOUT OIDS;