CREATE TABLE public.restaurantes_formas_pagamento (
  restaurante_id int8 NOT NULL,
  forma_pagamento_id int8 NOT NULL,
  CONSTRAINT restaurantes_formas_pagamento_fk FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
  CONSTRAINT formas_pagamento_restaurantes_fk FOREIGN KEY (forma_pagamento_id) REFERENCES formas_pagamento(id)
) WITHOUT OIDS;