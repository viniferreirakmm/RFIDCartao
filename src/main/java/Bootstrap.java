import jssc.*;

public class Bootstrap {
    public static void main(String[] args) {

        System.out.println("Iniciando Sistema RFID...");


        String[] ports = SerialPortList.getPortNames();
        for (String port : ports) {
            System.out.println(port);
        }
    }
}
