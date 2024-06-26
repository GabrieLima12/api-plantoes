package com.gabriel.apiplantoes.controller;


import com.gabriel.apiplantoes.domain.dtos.CadastroUnidade;
import com.gabriel.apiplantoes.domain.unidade.Unidade;
import com.gabriel.apiplantoes.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<CadastroUnidade> cadastrarUnidade(@RequestBody @Valid CadastroUnidade dados) {
        unidadeService.cadastrarUnidade(dados);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listagem")
    public ResponseEntity<List<Unidade>> listarUnidades() {
        List<Unidade> unidades = unidadeService.listarUnidades();
        return ResponseEntity.ok().body(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> listarUnidadePorId(@PathVariable Long id) {
        Unidade unidade = unidadeService.listarUnidadePorId(id);
        return ResponseEntity.ok(unidade);
    }
}
