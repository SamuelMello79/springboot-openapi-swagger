package com.spring_openapi.application.controller;

import java.net.URI;
import java.util.List;

import com.spring_openapi.application.exception.ErrorDetails;
import com.spring_openapi.application.model.Pessoa;
import com.spring_openapi.application.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas", description = "Gerenciamento de pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Método GET
    @GetMapping("/{id}")
    @Operation(summary = "Obter pessoa por ID", description = "Retorna uma pessoa com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada!"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
    })
    public Pessoa getPessoaById(@PathVariable Long id) { return pessoaService.getPessoaById(id); }

    // Método GET
    @GetMapping
    @Operation(summary = "Obter todas as pessoas", description = "Retorna uma lista de pessoas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas!"),
            @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
    })
    public List<Pessoa> getAllPessoa() { return pessoaService.getAllPessoa(); }

    // Método POST
    @PostMapping
    @Operation(summary = "Adicionar pessoa", description = "Adicionar uma pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa adicionada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
    })
    public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa savedPessoa) {
        Pessoa pessoa =  pessoaService.savePessoa(savedPessoa);
        return ResponseEntity.created(URI.create("/api/pessoas/" + pessoa.getId())).body(pessoa);
    }

    // Método UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualizar o registro de uma pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informações alteradas com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
    })
    public ResponseEntity<Pessoa> edit(@PathVariable Long id,@Valid @RequestBody Pessoa updatePessoa) {
        Pessoa pessoa = pessoaService.updatePessoa(id, updatePessoa);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    // Método DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar pessoa", description = "Apagar uma pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Requisição inválida!",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
