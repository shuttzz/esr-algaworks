package br.com.badbit.algafoods.infrastructure.service.reports;

import br.com.badbit.algafoods.domain.filter.VendaDiariaFilter;
import br.com.badbit.algafoods.domain.service.VendaQueryService;
import br.com.badbit.algafoods.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class JasperVendasReportService implements VendaReportService {

    private VendaQueryService vendaQueryService;

    public JasperVendasReportService(VendaQueryService vendaQueryService) {
        this.vendaQueryService = vendaQueryService;
    }

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new ReportException("Não foi possível emitir relatório, por favor tente novamente", e);
        }
    }

}
