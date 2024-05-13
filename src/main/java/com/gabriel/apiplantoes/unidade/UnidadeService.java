package com.gabriel.apiplantoes.unidade;

import com.gabriel.apiplantoes.dtos.CadastroUnidade;
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

        unidadeRepository.save(unidade);
    }

    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }
}
