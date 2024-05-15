package com.gabriel.apiplantoes.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroUnidade(@NotBlank String nomeUnidade) {
}
