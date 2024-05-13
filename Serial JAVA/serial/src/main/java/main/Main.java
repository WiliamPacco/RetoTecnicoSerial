package main;

import com.fazecast.jSerialComm.SerialPort;

public class Main {

    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();

        if (ports.length == 0) {
            System.out.println("No se encontro ningun puerto serial está disponible.");
            return;
        }

        System.out.println("Puertos seriales disponibles:");
        for (SerialPort port : ports) {
            System.out.println(port.getDescriptivePortName());
        }

        SerialPort port = ports[0];

        System.out.println("Puerto serial: " + port.getSystemPortName());
        port.openPort();
        port.setBaudRate(9600);
        port.setNumDataBits(8);
        port.setParity(SerialPort.NO_PARITY);
        port.setNumStopBits(SerialPort.ONE_STOP_BIT);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error al dormir el hilo.");
        }

        String message = "Enviando mensaje a puerto serial!";
        port.writeBytes(message.getBytes(), message.length());


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error al dormir el hilo.");
        }

        byte[] buffer = new byte[1024];
        int bytesLeidos = port.readBytes(buffer, buffer.length);

        if (bytesLeidos > 0) {
            String respuesta = new String(buffer, 0, bytesLeidos);
            System.out.println("Respuesta recibida: " + respuesta);
        } else {
            System.out.println("No se recibió ninguna respuesta.");
        }

        port.closePort();


    }
}
