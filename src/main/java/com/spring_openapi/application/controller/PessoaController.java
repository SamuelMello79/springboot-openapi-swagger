package com.spring_openapi.application.controller;

import java.net.URI;
import java.util.List;
import com.spring_openapi.application.model.Pessoa;
import com.spring_openapi.application.service.PessoaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoas/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada!"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel encontrar a pessoa!"),
            @ApiResponse(responseCode = "500", description = "Requisição inválida!"),
    })
    // PathVariable captura variáveis da URL
    public Pessoa getPessoaById(@PathVariable Long id) { return pessoaService.getPessoaById(id); }

    @GetMapping("/pessoas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoas encontradas!"),
            @ApiResponse(responseCode = "404", description = "Nenhuma pessoa foi encontrada!"),
            @ApiResponse(responseCode = "500", description = "Requisição inválida"),
    })
    public List<Pessoa> getAllPessoa() { return pessoaService.getAllPessoa(); }

    @PostMapping("/pessoas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa adicionada com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Requisição inválida!"),
    })
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa savedPessoa) {
        Pessoa pessoa =  pessoaService.savePessoa(savedPessoa);
        return ResponseEntity.created(URI.create("/api/pessoas/" + pessoa.getId())).body(pessoa);
    }

    @PutMapping("/pessoas/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informações foram alteradas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possivel encontrar a pessoa!"),
            @ApiResponse(responseCode = "500", description = "Requisição inválida!"),
    })
    public ResponseEntity<Pessoa> edit(@PathVariable Long id, @RequestBody Pessoa updatePessoa) {
        Pessoa pessoa = pessoaService.updatePessoa(id, updatePessoa);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/pessoas/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa excluída com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possivel encontrar a pessoa!"),
            @ApiResponse(responseCode = "500", description = "Requisição inválida!"),
    })
    public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
