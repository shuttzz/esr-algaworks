package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.RestauranteController;
import br.com.badbit.algafoods.api.model.output.RestauranteOutDTO;
import br.com.badbit.algafoods.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteOutDTO> {
    
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteDTOAssembler(ModelMapper modelMapper) {
        super(RestauranteController.class, RestauranteOutDTO.class);
        this.modelMapper = modelMapper;
    }

    public RestauranteOutDTO toModel(Restaurante restaurante) {
        RestauranteOutDTO restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModel.add(
                    algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteModel.getCozinha().add(
                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

        restauranteModel.getEndereco().getCidade().add(
                algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

        restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
                "formas-pagamento"));

        restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restaurante.getId(),
                "responsaveis"));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteOutDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
        return super.toCollectionModel(restaurantes)
                .add(algaLinks.linkToRestaurantes());
    }

}
