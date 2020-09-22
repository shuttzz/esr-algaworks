package br.com.badbit.algafoods.infrastructure.service.email;

import br.com.badbit.algafoods.core.email.EmailProperties;
import br.com.badbit.algafoods.domain.service.EnvioEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

public class AmazonSESEmailServiceSandboxImpl implements EnvioEmailService {

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem);

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(emailProperties.getSandbox().getDestinatario()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(corpo))).withSubject(new Content()
                                    .withCharset("UTF-8").withData(mensagem.getAssunto())))
                    .withSource("contato@badbit.com.br");
            amazonSimpleEmailService.sendEmail(request);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new EmailException("Não foi possível enviar e-mail", exception);
        }
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }

}
