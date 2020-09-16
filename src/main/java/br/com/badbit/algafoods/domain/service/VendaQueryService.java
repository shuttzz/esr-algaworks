package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.model.dto.VendaDiaria;
import br.com.badbit.algafoods.domain.filter.VendaDiariaFilter;

import java.util.List;

//@Service
public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
