package br.com.badbit.algafoods.core.jackson;

import br.com.badbit.algafoods.api.model.mixin.*;
import br.com.badbit.algafoods.domain.model.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public abstract class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Estado.class, EstadoMixin.class);
        setMixInAnnotation(FormaPagamento.class, FormaPagamentoMixin.class);
        setMixInAnnotation(Grupo.class, GrupoMixin.class);
        setMixInAnnotation(ItemPedido.class, ItemPedidoMixin.class);
        setMixInAnnotation(Pedido.class, PedidoMixin.class);
        setMixInAnnotation(Permissao.class, PermissaoMixin.class);
        setMixInAnnotation(Produto.class, ProdutoMixin.class);
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
