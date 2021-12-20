package br.com.tt.petshop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Agendamento {
    @Id
    @Column
    private Long id;
    @Column
    private LocalDateTime dataHoraInicio;
    @Column
    private LocalDateTime dataHoraFim;
    @Column
    private String observacoes;
}
