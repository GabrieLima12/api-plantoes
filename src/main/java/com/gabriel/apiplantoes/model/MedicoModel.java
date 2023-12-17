package com.gabriel.apiplantoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MEDICOS")
@Table(name = "medicos")
@EqualsAndHashCode(of = "id")
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome_medico")
    private String nomeMedico;
    @Column(name = "crm")
    private String crm;
    @Column(name = "data_cadastro")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidade")
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "nome_unidade")
    private String nomeUnidadeAssistencial;

    public MedicoModel(DadosDeCadastroMedico dados) {
        this.nomeMedico = dados.nomeMedico();
        this.crm = dados.crm();
        this.data = LocalDate.now();
        this.especialidade = dados.especialidade();
        this.status = Status.ATIVO;
        this.nomeUnidadeAssistencial = dados.nomeUnidadeAssistencial();
    }

    public void atualizarUnidadeAssistencial(DadosDeAlteracaoUnidadeAssistencial dados) {
        if (dados.nomeUnidadeAssistencial() != null) {
            this.nomeUnidadeAssistencial = dados.nomeUnidadeAssistencial();
        }
    }

    public void atualizarStatusMedico(DadosDeAlteracaoStatusMedico dados) {
        if (dados.status() == Status.ATIVO) {
            this.status = Status.ATIVO;
        }
        if (dados.status() == Status.INATIVO) {
            this.status = Status.INATIVO;
        }
    }

}
