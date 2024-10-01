package com.spring_openapi.application.repository;

import com.spring_openapi.application.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
