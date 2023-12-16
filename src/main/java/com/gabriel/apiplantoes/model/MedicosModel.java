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
@Entity(name = "PLANTAO")
@Table(name = "plantao")
@EqualsAndHashCode(of = "id")
public class MedicosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMedico;
    private String crm;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String nomeUnidadeAssistencial;

    public MedicosModel(DadosDeCadastroMedico dados) {
        this.nomeMedico = dados.nomeMedico();
        this.crm = dados.crm();
        this.data = LocalDate.now();
        this.especialidade = dados.especialidade();
        this.status = Status.ATIVO;
        this.nomeUnidadeAssistencial = dados.nomeUnidadeAssistencial();
    }

    public void atualizarInformacoes(DadosDeAlteracaoCadastral dados) {
        if (dados.nomeUnidadeAssistencial() != null) {
            this.nomeUnidadeAssistencial = dados.nomeUnidadeAssistencial();
        }
    }

}
