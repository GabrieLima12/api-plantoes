package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.CadastroEspecialidade;
import com.gabriel.apiplantoes.domain.especialidade.Especialidade;
import com.gabriel.apiplantoes.exception.EspecialidadeException;
import com.gabriel.apiplantoes.repository.EspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public void cadastrarEspecialidade(CadastroEspecialidade dados) {
        Especialidade especialidade = new Especialidade();
        especialidade.setNomeEspecialidade(dados.nomeEspecialidade());
        try {
            especialidadeRepository.save(especialidade);
        } catch (Exception exception) {
            throw new EspecialidadeException("Não foi possível cadastrar especialidade!");
        }
    }
    public List<Especialidade> listaEspecialidades() {
        try {
            return especialidadeRepository.findAll();
        } catch (Exception exception) {
            throw new EspecialidadeException();
        }
    }

    public Especialidade listarEspecialidadePorId(Long id) {
        return especialidadeRepository.findById(id).orElseThrow(EspecialidadeException::new);
    }
}
