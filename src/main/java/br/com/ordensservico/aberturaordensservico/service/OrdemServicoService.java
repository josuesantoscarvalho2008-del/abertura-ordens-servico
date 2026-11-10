package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;


@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(
        OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(
        OrdemServicoRequest dadosOrdemServico) {

        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(dadosOrdemServico.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricao(dadosOrdemServico.getDescricao());
        ordemServico.setEquipamento(equipamentoEncontrado.get());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(int id) {
        return ordemServicoRepository.findById(id);
    }
}