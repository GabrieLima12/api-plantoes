package com.gabriel.apiplantoes.repository;

import com.gabriel.apiplantoes.apiclient.ViaCepClient;
import com.gabriel.apiplantoes.domain.dtos.ViaCepDTO;
import org.springframework.stereotype.Service;

@Service
public class ViaCepRepository {

    private final ViaCepClient viaCepClient;

    public ViaCepRepository(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public ViaCepDTO buscarEnderecoViaCep(Long cep) {
        return viaCepClient.buscarEnderecoViaCep(cep);
    }

}
