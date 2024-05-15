package com.gabriel.apiplantoes.domain.dtos;

import jakarta.validation.constraints.*;

import java.util.List;

public record CadastroMedico(
        @NotBlank
        String nomeMedico,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Long idEspecialidadePrincipal,
        Long idEspecialidadeSecundaria,
        @NotEmpty
        List<Long> idsUnidades) {
}
