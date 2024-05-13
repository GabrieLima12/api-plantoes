package com.gabriel.apiplantoes.medico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_medico", nullable = false)
    private String nomeMedico;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

}