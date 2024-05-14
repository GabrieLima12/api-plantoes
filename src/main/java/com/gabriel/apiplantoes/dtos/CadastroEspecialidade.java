package com.gabriel.apiplantoes.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroEspecialidade(
        @NotBlank
        String nomeEspecialidade
) {
}
