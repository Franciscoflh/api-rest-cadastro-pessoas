package com.example.apirestcadastropessoas.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome da pessoa é obrigatório")
    private String nome;

    @NotEmpty(message = "O CPF da pessoa é obrigatório")
    @CPF
    private String cpf;

    @Past(message = "A data de nascimento não pode ser uma data futura")
    @NotNull(message = "A data de nascimento da pessoa é obrigatória")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @NotEmpty(message = "A pessoa deve ter pelo menos um contato")
    @Valid
    private List<Contato> contatos;

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", contatos=" + contatos +
                '}';
    }
}
