package mock;

import br.com.tt.petshop.dto.AgendamentoDetalhes;
import br.com.tt.petshop.dto.AgendamentoListagem;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class AgendamentoMock {

    public static final LocalDateTime AMANHA_AS_14_HORAS = LocalDateTime.now().plusDays(1)
            .withHour(14).withMinute(0).withSecond(0);

    public static final LocalDateTime AMANHA_AS_15_HORAS = LocalDateTime.now().plusDays(1)
            .withHour(15).withMinute(0).withSecond(0);

    public static AgendamentoListagem criaAgendamentoListagemManha() {
        return new AgendamentoListagem(2L,
                LocalDateTime.parse("2023-04-15T09:00:00"),
                LocalDateTime.parse("2023-04-15T10:30:00"));
    }

    public static AgendamentoListagem criaAgendamentoListagemTarde() {
        return new AgendamentoListagem(1L,
                LocalDateTime.parse("2021-02-01T14:00:00"),
                LocalDateTime.parse("2021-02-01T15:00:00"));
    }

    public static AgendamentoDetalhes criaAgendamentoDetalhesTarde() {
        return new AgendamentoDetalhes(14L, LocalDateTime.parse("2021-02-01T14:00:00"),
                LocalDateTime.parse("2021-02-01T15:00:00"), "Banho e tosa. Animal bravo.");
    }

    public static String criaBodyAgendamento() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("dataHoraInicio", AMANHA_AS_14_HORAS.toString());
        json.put("dataHoraFim", AMANHA_AS_15_HORAS.toString());
        json.put("observacoes", "Banho e tosa");
        return json.toString();
    }
}
