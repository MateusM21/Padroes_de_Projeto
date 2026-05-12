package hotel.builder;

public interface PacoteBuilder {
    PacoteBuilder quarto(String tipo);
    PacoteBuilder diarias(int quantidade);
    PacoteBuilder comCafeDaManha();
    PacoteBuilder comSpa();
    PacoteBuilder comTransfer();
    PacoteBuilder comEstacionamento();
    PacoteBuilder servicoExtra(String servico);
    PacoteHospedagem build();
}
