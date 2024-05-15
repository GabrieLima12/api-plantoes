package com.gabriel.apiplantoes.domain.relacaomedico;

import com.gabriel.apiplantoes.domain.unidade.Unidade;
import com.gabriel.apiplantoes.domain.medico.Medico;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "relacao_medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelacaoMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;
}
