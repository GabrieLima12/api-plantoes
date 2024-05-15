package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.CadastroEspecialidade;
import com.gabriel.apiplantoes.domain.dtos.ListagemEspecialidade;
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
        } catch (EspecialidadeException exception) {
            throw new EspecialidadeException("Não foi possível cadastrar especialidade!");
        }
    }
    public List<Especialidade> listaEspecialidades() {
        try {
            return especialidadeRepository.findAll();
        } catch (EspecialidadeException exception) {
            throw new EspecialidadeException();
        }
    }

    public ListagemEspecialidade listarEspecialidadePorId(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(EspecialidadeException::new);
        return new ListagemEspecialidade(especialidade.getId(), especialidade.getNomeEspecialidade());
    }
}
