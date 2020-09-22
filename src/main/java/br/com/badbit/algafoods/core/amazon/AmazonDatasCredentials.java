package br.com.badbit.algafoods.core.amazon;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.amazon")
public class AmazonDatasCredentials {

    private String idChaveAcesso;
    private String chaveAcessoSecreta;
    private Regions regiao;

    public BasicAWSCredentials getCredentials() {
        return new BasicAWSCredentials(
                getIdChaveAcesso(),
                getChaveAcessoSecreta()
        );
    }

}
