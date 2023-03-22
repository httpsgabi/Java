/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

<<<<<<< HEAD
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.net.Socket;
 
 /**
  *
  * @author nicolle 
  */
 public class Cliente extends Thread{
 
     //Flag que indica quando se deve terminar a execução.
     public  static boolean done = false;
     public static void main(String[] args) {
         try{
             //Cria um socket, com o ip do servidor e porta
             Socket conexao = new Socket("10.0.0.53", 2222);
             //Socket conexao = new Socket("172.16.49.5", 1234);
 
             //apos conextar, deve pegar os objetos que controlam o fluxo de comunicação
             PrintStream saida = new PrintStream(conexao.getOutputStream());
             //enviar o nome do usuário
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             System.out.println("Entre com o seu apelido: ");
             String meuNome = teclado.readLine();
             saida.println(meuNome);
 
             //iniciar a tread de recepção de mensagens
             Thread t = new Cliente (conexao);
             t.start();
 
             //captura o que foi digitado e envia pro servidor
             String linha;
             while(true){
                 //captura o que foi digitado
                 System.out.println("> ");
                 linha = teclado.readLine();
                 //antes de enviar, verifica se a conexao n foi fechada
                 if(done){
                     break;
                 }
                 saida.println(linha);
             }
         }catch(IOException e){
             //caso ocorra exceção de I/O
             System.out.println("Exception do main");
             System.out.println("IOException: " + e);
         }
     }
     //recepção de mensagens do cliente
     private Socket conexao;
     //construtor que recebe o socket deste cliente
     public Cliente(Socket s){
         conexao = s;
     }
 
     //execução da thread
     public void run(){
         try{
             BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
             String linha;
             while(true){
                 //pega o que o servidor enviou
                 linha = entrada.readLine();
 
                 //verifica se a linha e valida
                 if(linha == null){
                     System.out.println("Conexão encerrada!");
                     break;
                 }
                 //caso a linha seja valida, imprimi-la
                 System.out.println();
                 System.out.println(linha);
                 System.out.println("...> ");
             }
         }catch(IOException e){
             //caso ocorra alguma exceção
             System.out.println("Exception do run");
             System.out.println("IOException> " + e);
         }
         //sinaliza para o main que a conexão encerrou
         done = true;
     }
 }
=======
 import java.io.*;
 import java.net.*;
 import java.util.Scanner;
 
 /**
  *
  * @author Virginia
  */
 public class Cliente {
    public static void main(String[] args) 
          throws UnknownHostException, IOException {
      // dispara cliente
      new Cliente("10.0.0.53", 12345).executa();
    }
    
    private String host;
    private int porta;
    
    public Cliente (String host, int porta) {
      this.host = host;
      this.porta = porta;
    }
    
    public void executa() throws UnknownHostException, IOException {
      Socket cliente = new Socket(this.host, this.porta);
      System.out.println("O cliente se conectou ao servidor!");
  
      // thread para receber mensagens do servidor
      Recebedor r = new Recebedor(cliente.getInputStream());
      new Thread(r).start();
      
      // lê msgs do teclado e manda pro servidor
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
 
>>>>>>> 8b793c92e806b25c1c05c2896c09ac644e226814
