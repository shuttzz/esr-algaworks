CREATE TABLE public.grupos_permissoes (
  grupo_id int8 NOT NULL,
  permissoes_id int8 NOT NULL,
  CONSTRAINT grupos_permissoes_fk FOREIGN KEY (grupo_id) REFERENCES grupos(id),
  CONSTRAINT permissoes_grupos_fk FOREIGN KEY (permissoes_id) REFERENCES permissoes(id)
) WITHOUT OIDS;
