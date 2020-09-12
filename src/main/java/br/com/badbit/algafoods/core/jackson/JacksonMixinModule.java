package br.com.badbit.algafoods.core.jackson;

import br.com.badbit.algafoods.api.model.mixin.*;
import br.com.badbit.algafoods.domain.model.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public abstract class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(ItemPedido.class, ItemPedidoMixin.class);
        setMixInAnnotation(Pedido.class, PedidoMixin.class);
        setMixInAnnotation(Permissao.class, PermissaoMixin.class);
        setMixInAnnotation(Produto.class, ProdutoMixin.class);
    }
}
