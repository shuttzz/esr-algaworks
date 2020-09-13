package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.UsuarioOutDTO;
import br.com.badbit.algafoods.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOAssembler {

    private ModelMapper modelMapper;

    public UsuarioDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioOutDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioOutDTO.class);
    }

    public List<UsuarioOutDTO> toCollectionDTO(Collection<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toDTO(usuario))
                .collect(Collectors.toList());
    }

}
