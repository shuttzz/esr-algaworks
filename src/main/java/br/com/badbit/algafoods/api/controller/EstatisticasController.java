package br.com.badbit.algafoods.api.controller;

import br.com.badbit.algafoods.api.AlgaLinks;
import br.com.badbit.algafoods.domain.filter.VendaDiariaFilter;
import br.com.badbit.algafoods.domain.model.dto.VendaDiaria;
import br.com.badbit.algafoods.domain.service.VendaQueryService;
import br.com.badbit.algafoods.domain.service.VendaReportService;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    private VendaQueryService vendaQueryService;
    private VendaReportService vendaReportService;
    private AlgaLinks algaLinks;

    public EstatisticasController(VendaQueryService vendaQueryService, VendaReportService vendaReportService, AlgaLinks algaLinks) {
        this.vendaQueryService = vendaQueryService;
        this.vendaReportService = vendaReportService;
        this.algaLinks = algaLinks;
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticasModel estatisticas() {
        var estatisticasModel = new EstatisticasModel();

        estatisticasModel.add(algaLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));

        return estatisticasModel;
    }

    public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
    }
}
