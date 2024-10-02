package com.spring_openapi.application.repository;

import com.spring_openapi.application.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

// implementando os métodos de banco de dados do JPA para a entidade Pessoa
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
