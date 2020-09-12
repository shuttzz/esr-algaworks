package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.input.UsuarioInDTO;
import br.com.badbit.algafoods.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    private ModelMapper modelMapper;

    public UsuarioInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Usuario toDomainObject(UsuarioInDTO usuarioInDTO) {
        return modelMapper.map(usuarioInDTO, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInDTO usuarioInDTO, Usuario usuario) {
        modelMapper.map(usuarioInDTO, usuario);
    }

}
