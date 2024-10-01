package com.spring_openapi.application.service;

import com.spring_openapi.application.model.Pessoa;
import com.spring_openapi.application.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // adicionar pessoa
    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // lista pessoa do {id}
    public Pessoa getPessoaById(Long id) { return pessoaRepository.findById(id).orElse(null); }

    // listar todos as pessoas
    public List<Pessoa> getAllPessoa() { return pessoaRepository.findAll(); }

    // editar os dados da pessoa
    public Pessoa updatePessoa(Long id, Pessoa updatePessoa) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setName(updatePessoa.getName());
            pessoa.setIdade(updatePessoa.getIdade());
            pessoa.setNascimento(updatePessoa.getNascimento());
            return pessoaRepository.save(pessoa);
        }).orElse(null);
    }

    // deletar pessoa pelo {id}
    public boolean deletePessoa(Long id) {
        if(pessoaRepository.existsById(id)) {
            pessoaRepository.existsById(id);
            return true;
        }
        return false;
    }

}
