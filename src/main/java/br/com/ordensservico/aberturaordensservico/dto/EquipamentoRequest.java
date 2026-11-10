package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EquipamentoRequest {
    @NotBlank(message = "O nome do equipamento é obrigatório.")
    @Size(max = 100, message = "O nome do equipamento deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O número do patrimônio é obrigatório.")
    @Size(max = 50, message = "O número do patrimônio deve ter no máximo 50 caracteres.")
    private String numeroPatrimonio;

    @NotNull (message = "O ID do setor é obrigatório.")
    private int setorId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public int getSetorId() {
        return setorId;
    }

    public void setSetorId(int setorId) {
        this.setorId = setorId;
    }

    public EquipamentoRequest() {
    }
}