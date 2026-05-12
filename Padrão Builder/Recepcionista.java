package hotel.builder;

public class Recepcionista {

    private final PacoteBuilder builder;

    public Recepcionista(PacoteBuilder builder) {
        this.builder = builder;
    }

    public PacoteHospedagem montarPacoteEconomico() {
        return builder
                .quarto("Standard")
                .diarias(2)
                .comCafeDaManha()
                .build();
    }

    public PacoteHospedagem montarPacoteConforto() {
        return builder
                .quarto("Luxo")
                .diarias(3)
                .comCafeDaManha()
                .comEstacionamento()
                .build();
    }

    public PacoteHospedagem montarPacoteVip() {
        return builder
                .quarto("Suite Master")
                .diarias(5)
                .comCafeDaManha()
                .comSpa()
                .comTransfer()
                .comEstacionamento()
                .servicoExtra("Mordomo 24h")
                .servicoExtra("Decoracao romantica")
                .build();
    }
}
