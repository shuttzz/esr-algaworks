package br.com.badbit.algafoods.core.email;

import br.com.badbit.algafoods.domain.service.EnvioEmailService;
import br.com.badbit.algafoods.infrastructure.service.email.AmazonSESEmailServiceImpl;
import br.com.badbit.algafoods.infrastructure.service.email.AmazonSESEmailServiceSandboxImpl;
import br.com.badbit.algafoods.infrastructure.service.email.FakeEnvioEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailServiceImpl();
            case AMAZON_SES:
                return new AmazonSESEmailServiceImpl();
            case SANDBOX:
                return new AmazonSESEmailServiceSandboxImpl();
            default:
                return null;
        }
    }

}
