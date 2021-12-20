package br.com.tt.petshop.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
public class AgendamentoCriacao {

    @NotNull(message = "Data/hora início obrigatória")
    @FutureOrPresent(message = "Informe uma data/hora início posterior a data atual")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "Data/hora fim obrigatória")
    @FutureOrPresent(message = "Informe uma data/hora fim posterior a data atual")
    private LocalDateTime dataHoraFim;

    private String observacoes;

    @ConstructorProperties({"dataHoraInicio", "dataHoraFim", "observacoes"})
    public AgendamentoCriacao(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String observacoes) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.observacoes = observacoes;
    }
}
