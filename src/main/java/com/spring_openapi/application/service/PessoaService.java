package com.spring_openapi.application.service;

import com.spring_openapi.application.exception.ResourceNotFoundException;
import com.spring_openapi.application.model.Pessoa;
import com.spring_openapi.application.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // adicionar pessoa
    public Pessoa savePessoa(Pessoa pessoa) {
        if (pessoa.getName() == null || pessoa.getName().isEmpty() || pessoa.getName().equals("string")) {
            throw  new IllegalArgumentException("O nome é obrigatorio.");
        }

        if (pessoa.getIdade() <= 0) {
            throw new IllegalArgumentException("A idade deve ser maior ou igual a 0.");
        }

        if(pessoa.getNascimento() == null || pessoa.getNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento deve estar no passado.");
        }
        return pessoaRepository.save(pessoa);
    }

    // listar pessoa do {id}
    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada."));
    }

    // listar todos as pessoas
    public List<Pessoa> getAllPessoa() {
        return pessoaRepository.findAll();
    }

    // editar os dados da pessoa
    public Pessoa updatePessoa(Long id, Pessoa updatePessoa) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setName(updatePessoa.getName());
            pessoa.setIdade(updatePessoa.getIdade());
            pessoa.setNascimento(updatePessoa.getNascimento());
            return pessoaRepository.save(pessoa);
        }).orElseThrow(() -> new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada."));
    }

    // deletar pessoa pelo {id}w
    public boolean deletePessoa(Long id) {
        if(pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada.");
        }
    }

}
