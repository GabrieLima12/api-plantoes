package com.gabriel.apiplantoes.controller;

import com.gabriel.apiplantoes.model.DadosDeAlteracaoStatusMedico;
import com.gabriel.apiplantoes.model.DadosDeCadastroMedico;
import com.gabriel.apiplantoes.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plantao")
public class MedicoController {

    private MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public void cadastro(@RequestBody @Valid DadosDeCadastroMedico dados) {
        service.cadastrarMedico(dados);
    }

    @PutMapping
    @Transactional
    public void alterarStatusMedico(@RequestBody @Valid DadosDeAlteracaoStatusMedico dados) {
        service.atualizarStatusMedico(dados);
    }

}
