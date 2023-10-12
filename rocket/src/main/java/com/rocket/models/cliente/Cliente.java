package com.rocket.models.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private String documentoIdentidadeFrente;

    @Column(nullable = false)
    private String comprovanteReseidenciaFrente;

    @Column(nullable = false)
    private String documentoIdentidadeVerso;

    @Column(nullable = false)
    private String comprovanteReseidenciaVerso;

    @Column(nullable = false)
    private String selfRosto;

    @Column()
    private boolean aprovado = false;



    @Builder
    public Cliente(String nome, String cpf, String nomeMae, String senha, String documentoIdentidadeFrente, String comprovanteReseidenciaFrente,
    String documentoIdentidadeVerso, String comprovanteReseidenciaVerso,  String selfRosto, boolean aprovado) {
        this.nome = nome;
        this.cpf = cpf;
        this.nomeMae = nomeMae;
        this.senha = senha;
        this.documentoIdentidadeFrente = documentoIdentidadeFrente;
        this.documentoIdentidadeVerso = documentoIdentidadeVerso;
        this.comprovanteReseidenciaVerso = comprovanteReseidenciaVerso;
        this.comprovanteReseidenciaFrente = comprovanteReseidenciaFrente;

        this.selfRosto = selfRosto;
        this.aprovado = aprovado;

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
