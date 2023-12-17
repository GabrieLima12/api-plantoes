package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.model.DadosDeAlteracaoStatusMedico;
import com.gabriel.apiplantoes.model.DadosDeCadastroMedico;
import com.gabriel.apiplantoes.model.MedicoModel;
import com.gabriel.apiplantoes.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public MedicoModel cadastrarMedico(DadosDeCadastroMedico dados) {
        MedicoModel medico = new MedicoModel(dados);
        return repository.save(medico);
    }

    public void atualizarStatusMedico(DadosDeAlteracaoStatusMedico dados) {
        MedicoModel medico = repository.getReferenceById(dados.id());
        medico.atualizarStatusMedico(dados);
    }

    public List<MedicoModel> listagemDeMedicos() {
        return repository.findAll();
    }

}
