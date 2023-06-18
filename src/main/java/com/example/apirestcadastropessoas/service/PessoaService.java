package com.example.apirestcadastropessoas.service;

import com.example.apirestcadastropessoas.exception.ResourceNotFoundException;
import com.example.apirestcadastropessoas.model.Contato;
import com.example.apirestcadastropessoas.model.Pessoa;
import com.example.apirestcadastropessoas.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
    }

    public List<Pessoa> getAllPessoas() {
        logger.info("Pessoa: {}", pessoaRepository.findAllWithContatos());
        return pessoaRepository.findAllWithContatos();
    }

    public Page<Pessoa> getPessoasPaginated(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa createPessoa(@Valid Pessoa pessoa) {
        logger.info("Iniciando cadastro de pessoa");
        for (Contato contato : pessoa.getContatos()) {
            contato.setPessoa(pessoa);
        }
        logger.info("Pessoa: {}", pessoa.toString());
        logger.info("Contatos: {}", Arrays.toString(pessoa.getContatos().toArray()));
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = getPessoaById(id);
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setCpf(pessoaAtualizada.getCpf());
        pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoa.setContatos(pessoaAtualizada.getContatos());
        return pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        Pessoa pessoa = getPessoaById(id);
        pessoaRepository.delete(pessoa);
    }

}
