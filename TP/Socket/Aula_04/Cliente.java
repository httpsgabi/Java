package TP_Sockets.Aula_04;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket cliente = new Socket("10.0.0.53", 12345);
        System.out.println("O Cliente se conectou ao servidor!");

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();
        cliente.close();
    }
}
