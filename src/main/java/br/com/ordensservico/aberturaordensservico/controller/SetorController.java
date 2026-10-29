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

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrar(
            @Valid @RequestBody Setor setor) {

        Setor novoSetor = setorService.cadastrar(setor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoSetor);
    }
    @GetMapping
    public ResponseEntity<List<Setor>> listar() {

        return ResponseEntity.ok(setorService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(
        @PathVariable int id) {
        return setorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
            @PathVariable int id,
            @Valid @RequestBody Setor setorAtualizado) {

        return setorService.atualizar(id, setorAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable int id) {

        if (setorService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
