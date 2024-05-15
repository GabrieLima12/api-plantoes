package com.gabriel.apiplantoes.relacaomedico;

import com.gabriel.apiplantoes.medico.Medico;
import com.gabriel.apiplantoes.unidade.Unidade;
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
