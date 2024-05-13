package com.gabriel.apiplantoes.dtos;

import jakarta.validation.constraints.NotBlank;

public record CadastroUnidade(@NotBlank String nomeUnidade) {
}
