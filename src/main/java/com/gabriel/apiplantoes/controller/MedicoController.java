package com.gabriel.apiplantoes.controller;

import com.gabriel.apiplantoes.model.*;
import com.gabriel.apiplantoes.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/plantao")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping("/listagem")
    public ResponseEntity<List<ListagemMedicos>> listar() {
        List<ListagemMedicos> lista = service.listagemDeMedicos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/plantonista/{id}")
    public ResponseEntity<ListagemMedicos> plantonistaPorId(@PathVariable Long id) {
        ListagemMedicos plantonista = service.plantonistaPorId(id);
        return ResponseEntity.ok(plantonista);
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<CadastroMedico> cadastro(@RequestBody @Valid CadastroMedico dados) {
        service.cadastrarMedico(dados);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/status")
    @Transactional
    public ResponseEntity<AlteracaoStatusMedico> alterarStatusMedico(@RequestBody @Valid AlteracaoStatusMedico dados) {
        service.atualizarStatusMedico(dados);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/unidade")
    @Transactional
    public ResponseEntity<AlteracaoUnidadeAssistencial> alterarUnidadeMedico(@RequestBody @Valid AlteracaoUnidadeAssistencial dados) {
        service.atualizarUnidadeMedico(dados);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
