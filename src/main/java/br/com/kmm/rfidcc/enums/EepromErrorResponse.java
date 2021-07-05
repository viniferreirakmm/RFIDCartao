package br.com.kmm.rfidcc.enums;

public enum EepromErrorResponse {
    N("Sem cartão no campo do leitor"),
    F("Falha na operação"),
    E("Chave interna da Eeprom inválida para Login"),
    I("Tentativa operação com valor em campo não configurado"),
    X("Sem leitura após escrita"),
    U("Leitura após escrita não confere");

    private final String response;

    public String getResponse() {
        return response;
    }

    EepromErrorResponse(String response) {
        this.response = response;
    }
}
