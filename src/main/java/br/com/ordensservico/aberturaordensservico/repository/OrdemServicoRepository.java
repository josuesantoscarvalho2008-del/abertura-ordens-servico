package br.com.ordensservico.aberturaordensservico.repository;

import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends 
        JpaRepository<OrdemServico, Integer> {
}