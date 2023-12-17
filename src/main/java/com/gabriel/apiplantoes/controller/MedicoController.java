package com.gabriel.apiplantoes.controller;

import com.gabriel.apiplantoes.model.DadosDeAlteracaoStatusMedico;
import com.gabriel.apiplantoes.model.DadosDeCadastroMedico;
import com.gabriel.apiplantoes.model.MedicoModel;
import com.gabriel.apiplantoes.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantao")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MedicoModel>> listar() {
        List<MedicoModel> lista = service.listagemDeMedicos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<MedicoModel> cadastro(@RequestBody @Valid DadosDeCadastroMedico dados) {
        MedicoModel novoMedico = service.cadastrarMedico(dados);
        return new ResponseEntity<>(novoMedico, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDeAlteracaoStatusMedico> alterarStatusMedico(@RequestBody @Valid DadosDeAlteracaoStatusMedico dados) {
        service.atualizarStatusMedico(dados);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
