package com.gabriel.apiplantoes.model;

public record ListagemMedicos(
        Long id,
        String nomeMedico,
        String crm,
        Especialidade especialidade,
        Status status,
        String nomeUnidadeAssistencial) {

    public ListagemMedicos(MedicoModel medico) {
        this(medico.getId(), medico.getNomeMedico(), medico.getCrm(), medico.getEspecialidade(), medico.getStatus(), medico.getNomeUnidadeAssistencial());
    }
}
