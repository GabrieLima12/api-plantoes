package com.gabriel.apiplantoes.controller;

import com.gabriel.apiplantoes.domain.dtos.CadastroEspecialidade;
import com.gabriel.apiplantoes.domain.dtos.ListagemEspecialidade;
import com.gabriel.apiplantoes.domain.especialidade.Especialidade;
import com.gabriel.apiplantoes.service.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<CadastroEspecialidade> cadastro(@RequestBody @Valid CadastroEspecialidade dados) {
        especialidadeService.cadastrarEspecialidade(dados);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listagem")
    public ResponseEntity<List<Especialidade>> listarMedicos() {
        List<Especialidade> especialidades = especialidadeService.listaEspecialidades();
        return ResponseEntity.ok().body(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemEspecialidade> listarMedicoPorId(@PathVariable Long id) {
        ListagemEspecialidade listagemEspecialidade = especialidadeService.listarEspecialidadePorId(id);
        return ResponseEntity.ok(listagemEspecialidade);
    }
}
