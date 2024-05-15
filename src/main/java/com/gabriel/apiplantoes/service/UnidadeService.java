package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.CadastroUnidade;
import com.gabriel.apiplantoes.domain.dtos.ListagemUnidade;
import com.gabriel.apiplantoes.domain.unidade.Unidade;
import com.gabriel.apiplantoes.exception.UnidadeException;
import com.gabriel.apiplantoes.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public void cadastrarUnidade(CadastroUnidade dados) {
        Unidade unidade = new Unidade();
        unidade.setNomeUnidade(dados.nomeUnidade());

        try {
            unidadeRepository.save(unidade);
        } catch (UnidadeException exception) {
            throw new UnidadeException("Erro ao cadastrar unidade!");
        }
    }

    public List<Unidade> listarUnidades() {
        try {
            return unidadeRepository.findAll();
        } catch (UnidadeException exception) {
            throw new UnidadeException("Erro ao listar unidades!");
        }
    }

    public ListagemUnidade listarUnidadePorId(Long id) {
        Unidade unidade = unidadeRepository.findById(id).orElseThrow(UnidadeException::new);
        return new ListagemUnidade(unidade.getId(), unidade.getNomeUnidade());
    }
}
