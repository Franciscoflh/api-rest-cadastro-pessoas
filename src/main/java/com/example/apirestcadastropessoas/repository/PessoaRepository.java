package com.example.apirestcadastropessoas.repository;

import com.example.apirestcadastropessoas.model.Pessoa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @EntityGraph(attributePaths = "contatos")
    List<Pessoa> findAll();
}