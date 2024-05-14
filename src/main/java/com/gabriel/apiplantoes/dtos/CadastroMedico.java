package com.gabriel.apiplantoes.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CadastroMedico(
        @NotBlank
        String nomeMedico,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotEmpty
        List<EspecialidadeDTO> especialidades,
        @NotEmpty
        List<Long> idsUnidades) {
}
