package br.com.badbit.algafoods.core.email;

import br.com.badbit.algafoods.core.amazon.AmazonDatasCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSESConfig {

    private AmazonDatasCredentials amazonDatasCredentials;

    public AmazonSESConfig(AmazonDatasCredentials amazonDatasCredentials) {
        this.amazonDatasCredentials = amazonDatasCredentials;
    }

    @Bean
    public AmazonSimpleEmailService amazonSes() {
        var credentials = amazonDatasCredentials.getCredentials();
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(amazonDatasCredentials.getRegiao())
                .build();
    }
}
