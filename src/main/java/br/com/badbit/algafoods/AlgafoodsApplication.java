package br.com.badbit.algafoods;

import br.com.badbit.algafoods.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgafoodsApplication.class, args);
    }

}
