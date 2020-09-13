package br.com.badbit.algafoods.api.assembler;

import br.com.badbit.algafoods.api.model.output.PermissaoOutDTO;
import br.com.badbit.algafoods.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoDTOAssembler {

    private ModelMapper modelMapper;

    public PermissaoDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PermissaoOutDTO toDTO(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoOutDTO.class);
    }

    public List<PermissaoOutDTO> toCollectionDTO(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(permissao -> toDTO(permissao))
                .collect(Collectors.toList());
    }

}
