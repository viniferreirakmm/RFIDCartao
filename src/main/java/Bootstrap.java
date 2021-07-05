import br.com.kmm.rfidcc.enums.EepromErrorResponse;
import jssc.*;


public class Bootstrap {
    public static void main(String[] args) {

        System.out.println("Iniciando Sistema RFID...");
        String[] ports = SerialPortList.getPortNames();
        for (String port : ports) {
            System.out.println(port);
        }

        EepromErrorResponse response = EepromErrorResponse.E;
        System.out.println(response.getResponse());
    }
}