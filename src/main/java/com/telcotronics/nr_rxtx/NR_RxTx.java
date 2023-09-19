/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.telcotronics.nr_rxtx;

import gnu.io.NRSerialPort;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author pablinux
 */
public class NR_RxTx {

    public static void main(String[] args) {
        String port = "/dev/ttyACM0";
        for (String s : NRSerialPort.getAvailableSerialPorts()) {
            System.out.println("Availible port: " + s);
            port = s;
        }

        int baudRate = 9600;
        NRSerialPort serial = new NRSerialPort(port, baudRate);
        serial.connect();

        DataInputStream ins = new DataInputStream(serial.getInputStream());
        DataOutputStream outs = new DataOutputStream(serial.getOutputStream());
        try {
            //while(ins.available()==0 && !Thread.interrupted());// wait for a byte
            while (!Thread.interrupted()) {// read all bytes
                if (ins.available() > 0) {
                    char b = (char) ins.read();
                    //outs.write((byte)b);
                    System.out.print(b);
                }
                Thread.sleep(5);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        serial.disconnect();
    }
}
