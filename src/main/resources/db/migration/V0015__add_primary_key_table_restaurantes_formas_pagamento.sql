ALTER TABLE public.restaurantes_formas_pagamento
  ADD CONSTRAINT restaurantes_formas_pagamento_pkey
    PRIMARY KEY ("restaurante_id", "forma_pagamento_id");