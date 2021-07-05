package br.com.kmm.rfidcc.enums;

public enum CardCommands {

    C("Leitura Contínua de ID's dos Cartões"),
    S("Seleciona Um Cartão Para Operações"),
    M("Seleciona cartão o lista de cartões para operação"),
    L("Login"),
    R("Lê um bloco de 16 bytes"),
    W("Escreve em um bloco de 16 bytes"),
    RV("Lê um bloco de 4 bytes"),
    WV("Escreve um valor em um bloco (4 bytes)");

    private final String response;

    public String getResponse() {
        return response;
    }

    CardCommands(String response) {
        this.response = response;
    }
}
