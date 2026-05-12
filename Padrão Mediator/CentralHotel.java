package hotel.mediator;

import java.util.HashMap;
import java.util.Map;

public class CentralHotel implements HotelMediator {

    private final Map<String, Departamento> departamentos = new HashMap<>();

    @Override
    public void registrar(Departamento departamento) {
        departamentos.put(departamento.getNome(), departamento);
    }

    @Override
    public void enviar(String mensagem, String remetente, String destinatario) {
        Departamento dest = departamentos.get(destinatario);
        if (dest != null) {
            dest.receberMensagem(mensagem, remetente);
        } else {
            System.out.println("[Central] Departamento '" + destinatario + "' não encontrado.");
        }
    }
}
