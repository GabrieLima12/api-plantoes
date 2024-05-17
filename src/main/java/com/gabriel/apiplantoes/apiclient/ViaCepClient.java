package com.gabriel.apiplantoes.apiclient;

import com.gabriel.apiplantoes.domain.dtos.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${api-gateway.url}")
public interface ViaCepClient {

    @GetMapping("{cep}/json/")
    ViaCepDTO buscarEnderecoViaCep(@PathVariable Long cep);
}
