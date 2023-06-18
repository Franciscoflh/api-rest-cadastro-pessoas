package com.example.apirestcadastropessoas.repository;

import com.example.apirestcadastropessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT DISTINCT p FROM Pessoa p LEFT JOIN FETCH p.contatos")
    List<Pessoa> findAllWithContatos();
}