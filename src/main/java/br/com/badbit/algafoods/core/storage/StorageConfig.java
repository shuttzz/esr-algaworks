package br.com.badbit.algafoods.core.storage;

import br.com.badbit.algafoods.core.amazon.AmazonDatasCredentials;
import br.com.badbit.algafoods.domain.service.FotoStorageService;
import br.com.badbit.algafoods.infrastructure.service.storage.LocalStorageServiceImpl;
import br.com.badbit.algafoods.infrastructure.service.storage.S3FotoStorageServiceImpl;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    private StorageProperties storageProperties;
    private AmazonDatasCredentials amazonDatasCredentials;

    public StorageConfig(StorageProperties storageProperties, AmazonDatasCredentials amazonDatasCredentials) {
        this.storageProperties = storageProperties;
        this.amazonDatasCredentials = amazonDatasCredentials;
    }

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.tipo-storage", havingValue = "s3")
    public AmazonS3 amazonS3() {
        var credentials = amazonDatasCredentials.getCredentials();
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(amazonDatasCredentials.getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (StorageProperties.TipoStorage.S3.equals(storageProperties.getTipoStorage())) {
            return new S3FotoStorageServiceImpl();
        } else {
            return new LocalStorageServiceImpl();
        }
    }

}
