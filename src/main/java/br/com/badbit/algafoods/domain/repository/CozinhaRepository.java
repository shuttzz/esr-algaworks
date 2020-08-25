package br.com.badbit.algafoods.domain.repository;

import br.com.badbit.algafoods.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContaining(String nome);
    Optional<Cozinha> findByNome(String nome);

}
