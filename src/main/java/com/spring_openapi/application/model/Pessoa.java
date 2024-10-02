package com.spring_openapi.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    // Classe modelo da entidade Pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio!")
    @Size(min = 3, max = 100, message = "O nome deve estar entre 3 e 100 caracteres.")
    private String name;

    @NotBlank(message = "A idade não pode estar vazia!")
    @Min(value = 0, message = "IA idade deve estar entre 1 e 150 anos.")
    private int idade;

    @NotBlank(message = "A data de nascimento não pode estar vazia!")
    @Past(message = "A data deve estar no passado!")
    private LocalDate nascimento;
}
