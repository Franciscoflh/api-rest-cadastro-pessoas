package com.example.apirestcadastropessoas.service;

import com.example.apirestcadastropessoas.model.Pessoa;
import com.example.apirestcadastropessoas.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PessoaServiceTest {

    @Test
    void getPessoaById() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setCpf("123456789");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        Pessoa result = pessoaService.getPessoaById(1L);

        Assertions.assertEquals(pessoa, result);
    }

    @Test
    void getAllPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1L, "João", "123456789", LocalDate.of(1990, 1, 1), new ArrayList<>()));
        pessoas.add(new Pessoa(2L, "Maria", "987654321", LocalDate.of(1995, 5, 10), new ArrayList<>()));

        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        List<Pessoa> result = pessoaService.getAllPessoas();

        Assertions.assertEquals(pessoas, result);
    }

    @Test
    void getPessoasPaginated() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1L, "João", "123456789", LocalDate.of(1990, 1, 1), new ArrayList<>()));
        pessoas.add(new Pessoa(2L, "Maria", "987654321", LocalDate.of(1995, 5, 10), new ArrayList<>()));

        Page<Pessoa> page = new PageImpl<>(pessoas);
        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Pessoa> result = pessoaService.getPessoasPaginated(pageable);

        Assertions.assertEquals(pessoas, result.getContent());
    }

    @Test
    void createPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setCpf("123456789");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoa.setContatos(new ArrayList<>());

        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        Pessoa result = pessoaService.createPessoa(pessoa);

        Assertions.assertEquals(pessoa, result);

        Mockito.verify(pessoaRepository, Mockito.times(1)).save(pessoa);
    }

    @Test
    void updatePessoa() {
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(1L);
        pessoaExistente.setNome("João");
        pessoaExistente.setCpf("123456789");
        pessoaExistente.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoaExistente.setContatos(new ArrayList<>());

        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setId(1L);
        pessoaAtualizada.setNome("João Silva");
        pessoaAtualizada.setCpf("987654321");
        pessoaAtualizada.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoaAtualizada.setContatos(new ArrayList<>());

        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaExistente));
        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoaAtualizada);

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        Pessoa result = pessoaService.updatePessoa(1L, pessoaAtualizada);

        Assertions.assertEquals(pessoaAtualizada, result);

        Mockito.verify(pessoaRepository, Mockito.times(1)).findById(1L);

        Mockito.verify(pessoaRepository, Mockito.times(1)).save(pessoaExistente);
    }

    @Test
    void deletePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setCpf("123456789");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoa.setContatos(new ArrayList<>());

        PessoaRepository pessoaRepository = Mockito.mock(PessoaRepository.class);
        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaService pessoaService = new PessoaService(pessoaRepository);

        pessoaService.deletePessoa(1L);

        Mockito.verify(pessoaRepository, Mockito.times(1)).findById(1L);

        Mockito.verify(pessoaRepository, Mockito.times(1)).delete(pessoa);
    }
}