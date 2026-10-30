package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private EquipamentoService equipamentoService;

    public EquipamentoController (EquipamentoService equipamentoService){
        this.equipamentoService = equipamentoService;
    }
    
    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
            @Valid @RequestBody Equipamento equipamento) {

        Equipamento novoEquipamento = equipamentoService.cadastrar(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoEquipamento);
    }
    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {

        return ResponseEntity.ok(equipamentoService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(
        @PathVariable int id) {
        return equipamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
            @PathVariable int id,
            @Valid @RequestBody Equipamento equipamentoAtualizado) {

        return equipamentoService.atualizar(id, equipamentoAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable int id) {

        if (equipamentoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

