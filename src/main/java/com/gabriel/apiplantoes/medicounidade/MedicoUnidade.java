package com.gabriel.apiplantoes.medicounidade;

import com.gabriel.apiplantoes.medico.Medico;
import com.gabriel.apiplantoes.unidade.Unidade;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medico_unidade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoUnidade {

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
