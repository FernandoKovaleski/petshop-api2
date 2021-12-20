package mock;

import br.com.tt.petshop.model.Cliente;

import java.time.LocalDate;

public class ClienteMock {

    public static Cliente theo(){
        return new Cliente(1L, "Theo",
                LocalDate.parse("2018-10-03"),
                "51 9999 8888",
                "751.904.090-90");
    }

}
