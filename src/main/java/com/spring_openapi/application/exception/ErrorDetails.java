package com.spring_openapi.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    // Definindo o modelo padrão de Erros de Detalhe
    private int statusCode;
    private String message;
    private String details;
}
