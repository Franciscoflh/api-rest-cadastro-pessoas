package com.example.apirestcadastropessoas.repository;

import com.example.apirestcadastropessoas.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
