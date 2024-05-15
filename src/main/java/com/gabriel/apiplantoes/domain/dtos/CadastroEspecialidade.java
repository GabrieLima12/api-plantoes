package com.gabriel.apiplantoes.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroEspecialidade(
        @NotBlank
        String nomeEspecialidade
) {
}
