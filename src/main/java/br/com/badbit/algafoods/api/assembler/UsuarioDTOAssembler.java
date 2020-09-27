package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.api.controller.UsuarioController;
import br.com.badbit.algafoods.api.model.output.UsuarioOutDTO;
import br.com.badbit.algafoods.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioOutDTO> {

    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UsuarioDTOAssembler(ModelMapper modelMapper) {
        super(UsuarioController.class, UsuarioOutDTO.class);
        this.modelMapper = modelMapper;
    }

//    public UsuarioOutDTO toDTO(Usuario usuario) {
//        return modelMapper.map(usuario, UsuarioOutDTO.class);
//    }
//
//    public List<UsuarioOutDTO> toCollectionDTO(Collection<Usuario> usuarios) {
//        return usuarios.stream()
//                .map(usuario -> toDTO(usuario))
//                .collect(Collectors.toList());
//    }

    @Override
    public UsuarioOutDTO toModel(Usuario usuario) {
        UsuarioOutDTO usuarioOutDTO = modelMapper.map(usuario, UsuarioOutDTO.class);

        usuarioOutDTO.add(algaLinks.linkToUsuarios("usuarios"));
        usuarioOutDTO.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
        return usuarioOutDTO;
    }

    @Override
    public CollectionModel<UsuarioOutDTO> toCollectionModel(Iterable<? extends Usuario> usuarios) {
        return super.toCollectionModel(usuarios)
                .add(algaLinks.linkToUsuarios());
    }
}
