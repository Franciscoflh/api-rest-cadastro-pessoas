package com.example.apirestcadastropessoas.controller;

import com.example.apirestcadastropessoas.model.Pessoa;
import com.example.apirestcadastropessoas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    public PessoaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPessoaById() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        when(pessoaService.getPessoaById(id)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.getPessoaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
        verify(pessoaService, times(1)).getPessoaById(id);
    }

    @Test
    void testGetAllPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        when(pessoaService.getAllPessoas()).thenReturn(pessoas);

        ResponseEntity<List<Pessoa>> response = pessoaController.getAllPessoas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoas, response.getBody());
        verify(pessoaService, times(1)).getAllPessoas();
    }

    @Test
    void testGetPessoasPaginated() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pessoa> pessoas = new PageImpl<>(new ArrayList<>());
        when(pessoaService.getPessoasPaginated(pageable)).thenReturn(pessoas);

        ResponseEntity<Page<Pessoa>> response = pessoaController.getPessoasPaginated(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoas, response.getBody());
        verify(pessoaService, times(1)).getPessoasPaginated(pageable);
    }

    @Test
    void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        Pessoa novaPessoa = new Pessoa();
        when(pessoaService.createPessoa(pessoa)).thenReturn(novaPessoa);

        ResponseEntity<Pessoa> response = pessoaController.createPessoa(pessoa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novaPessoa, response.getBody());
        verify(pessoaService, times(1)).createPessoa(pessoa);
    }

    @Test
    void testUpdatePessoa() {
        // Arrange
        Long id = 1L;
        Pessoa pessoaAtualizada = new Pessoa();
        Pessoa pessoa = new Pessoa();
        when(pessoaService.updatePessoa(id, pessoaAtualizada)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.updatePessoa(id, pessoaAtualizada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
        verify(pessoaService, times(1)).updatePessoa(id, pessoaAtualizada);
    }

    @Test
    void testDeletePessoa() {
        Long id = 1L;

        ResponseEntity<Void> response = pessoaController.deletePessoa(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pessoaService, times(1)).deletePessoa(id);
    }

}
