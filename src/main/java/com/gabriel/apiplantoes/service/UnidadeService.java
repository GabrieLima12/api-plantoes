package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.CadastroUnidade;
import com.gabriel.apiplantoes.domain.endereco.Endereco;
import com.gabriel.apiplantoes.domain.unidade.Unidade;
import com.gabriel.apiplantoes.exception.UnidadeException;
import com.gabriel.apiplantoes.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoService enderecoService;

    public UnidadeService(UnidadeRepository unidadeRepository, EnderecoService enderecoService) {
        this.unidadeRepository = unidadeRepository;
        this.enderecoService = enderecoService;
    }

    public void cadastrarUnidade(CadastroUnidade dados) {
        Unidade unidade = new Unidade();
        unidade.setNomeUnidade(dados.nomeUnidade());
        Endereco endereco = enderecoService.consultaDeEnderecoPorCep(dados.cep(), dados.numero(), dados.complemento());
        unidade.setEndereco(endereco);

        try {
            unidadeRepository.save(unidade);
        } catch (Exception exception) {
            throw new UnidadeException("Erro ao cadastrar unidade!");
        }
    }

    public List<Unidade> listarUnidades() {
        try {
            return unidadeRepository.findAll();
        } catch (Exception exception) {
            throw new UnidadeException("Erro ao listar unidades!");
        }
    }

    public Unidade listarUnidadePorId(Long id) {
        return unidadeRepository.findById(id).orElseThrow(UnidadeException::new);
    }
}
