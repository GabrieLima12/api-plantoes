package com.gabriel.apiplantoes.controller;

import com.gabriel.apiplantoes.dtos.CadastroMedico;
import com.gabriel.apiplantoes.dtos.ListagemMedico;
import com.gabriel.apiplantoes.medico.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/plantonista")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<CadastroMedico> cadastro(@RequestBody @Valid CadastroMedico dados) {
        medicoService.cadastrarMedico(dados);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listagem")
    public ResponseEntity<List<ListagemMedico>> listarMedicos() {
        List<ListagemMedico> listagemMedicos = medicoService.listarMedicos();
        return ResponseEntity.ok().body(listagemMedicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemMedico> listarMedicoPorId(@PathVariable Long id) {
//        ListagemMedico listagemMedico = medicoService.listarMedicoPorId(id);
        return ResponseEntity.ok().build();
    }
}
