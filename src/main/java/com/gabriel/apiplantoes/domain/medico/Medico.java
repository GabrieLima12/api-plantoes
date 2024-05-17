package com.gabriel.apiplantoes.domain.medico;

import com.gabriel.apiplantoes.domain.endereco.Endereco;
import com.gabriel.apiplantoes.domain.especialidade.Especialidade;
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
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_primaria_id", nullable = false)
    private Especialidade especialidadePrimaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_secundaria_id")
    private Especialidade especialidadeSecundaria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}