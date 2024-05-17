package com.gabriel.apiplantoes.service;

import com.gabriel.apiplantoes.domain.dtos.ViaCepDTO;
import com.gabriel.apiplantoes.domain.endereco.Endereco;
import com.gabriel.apiplantoes.exception.EnderecoException;
import com.gabriel.apiplantoes.repository.ViaCepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnderecoService {

    private final ViaCepRepository viaCepRepository;

    public EnderecoService(ViaCepRepository viaCepRepository) {
        this.viaCepRepository = viaCepRepository;
    }

    public Endereco consultaDeEnderecoPorCep(String cep, Integer numero, String complemento) {
        try {
            ViaCepDTO viaCepDTO = viaCepRepository.buscarEnderecoViaCep(Long.parseLong(tratamentoDeCep(cep)));
            Endereco endereco = new Endereco();

            if (viaCepDTO == null) {
                throw new EnderecoException();
            }

            endereco.setCep(tratamentoDeCep(cep));

            if (numero != null) {
                endereco.setNumero(numero);
            }

            if (complemento != null) {
                endereco.setComplemento(complemento);
            }

            endereco.setEstado(viaCepDTO.getUf());
            endereco.setLogradouro(viaCepDTO.getLogradouro());
            endereco.setCidade(viaCepDTO.getLocalidade());
            endereco.setBairro(viaCepDTO.getBairro());

            return endereco;
        } catch (Exception exception) {
            log.error("Erro durante a busca de cep: ", exception);
            throw new EnderecoException("Ocorreu um erro durante a busca do endereço.");
        }
    }

    private String tratamentoDeCep(String cepString) {
        String cepFormatado;
        if (cepString.contains("-")) {
            cepFormatado = cepString.replace("-", "");
        } else {
            cepFormatado = cepString;
        }
        return cepFormatado;
    }
}
