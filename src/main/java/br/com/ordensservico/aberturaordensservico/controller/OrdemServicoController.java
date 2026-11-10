package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
            @Valid @RequestBody OrdemServicoRequest dadosOrdemServico) {

        Optional<OrdemServico> novaOrdemServico = ordemServicoService.cadastrar(dadosOrdemServico);

        return novaOrdemServico
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

        return ResponseEntity.ok(ordemServicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable int id) {
        return ordemServicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}