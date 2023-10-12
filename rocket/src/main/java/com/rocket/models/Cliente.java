package com.rocket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "Cleintes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Nome não pode ser Vazio.")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "CPF não pode ser Vazio.")
    @CPF(message = "CPF Invalido!")
    private String cpf;

    @Column(nullable = false)
    @NotEmpty(message = "Nome da mãe não pode ser Vazio.")
    private String nomeMae;

    @Length(min = 8, max = 20)
    @Column(nullable = false)
    @NotEmpty(message = "Senha não pode ser Vazio.")
    private String senha;

    @Builder
    public Cliente(String nome, String cpf, String nomeMae, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.nomeMae = nomeMae;
        this.senha = senha;


    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nomeMae='" + nomeMae + '\'' +

                '}';
    }


}
