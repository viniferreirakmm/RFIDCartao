package br.com.kmm.rfidcc.enums;

public enum ReadersCommands {
    X   ("Reader Reset"),
    WM  ("Write Master Key"),
    PW01("Red Light On/Off"),
    PW04("Buzzer On"),
    PW05("Red Light and Buzzer On"),
    PW00("ShutDown All Signals"),
    PR  ("Read buzzer and Led Statuses"),
    V   ("Show Reader Software Version"),
    G   ("Show Reader's Communication ID");

    private final String response;

    public String getResponse() {
        return response;
    }

    ReadersCommands(String response) {
        this.response = response;
    }
}
