package com.gabriel.apiplantoes.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EspecialidadeDTO {
    private Long id;
    private boolean primary;
}
