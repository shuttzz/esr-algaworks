package br.com.badbit.algafoods.domain.service;

import br.com.badbit.algafoods.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
