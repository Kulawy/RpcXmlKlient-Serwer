package com.pwr190729;
import org.apache.xmlrpc.WebServer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class GeronSerwerRpc {

    String asyncMessage01="Empty";
    List<Integer> listOfNumbers;

    public static void main(String[] args){

        try {
            System.out.println("Starts serwer XML-RPC...");
            int port = 1992;
            WebServer server = new WebServer(port);
            //ponizej tworzy się obiekt swojej klasy serwera i uruchomia się go:
            server.addHandler("GeronServer", new GeronSerwerRpc());
            server.start();
            System.out.println("Serwer Starts successful.");
            System.out.println("Is Listening on port: " + port);
            System.out.println("To stop serwer press crl+c");

        } catch (Exception exception) {
            System.err.println("Serwer XML-RPC: " + exception);
        }



    }

    public String echo(String message){
        System.out.println(message);
        return message;
    }

    public String show(int status){
        return "Short instruction - press key to make an action\n" +
                "Key\t| Action\t\t\t\n" +
                "0  \t| Print message on server and client\t\t\n" +
                "1  \t| Something\t\t\n" +
                "2  \t| Something\t\t\n"+
                "3  \t| Something\t\t\n"+
                "4  \t| repeat message (Async)\t\t\n"+
                "5  \t| Roll some numbers and sort them (Async)\t\n"+
                "p01\t| To print first message after Async call function 4\t\n"+
                "p02\t| repeat message (Async)\t\n"+
                "q  \t| EXIT\t\n";
    }

    public int execAsy(int x) {
        System.out.println("... call Asynchronously - Counting");
        try {
            Thread.sleep(x);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... Asynch - end of counting");
        return (200);
    }

    public int rollAndSortNumbers(int howManyElements, int interval, int min, int max){
        listOfNumbers  = new ArrayList<>(howManyElements);
        Random rnd = new Random();
        try{
            for(int i = 0; i < howManyElements; i++){
                Thread.sleep(interval);
                listOfNumbers.add(rnd.nextInt(max-min)+min);
            }
            Collections.sort(listOfNumbers);

        }catch (InterruptedException ex){
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return (200);
    }

    public int printWithIndexingAsync(String message, int interval, int howMany){
        asyncMessage01 = "";
        System.out.println("... call Asynchronously - Messaging");
        try {
            for(int i=1; i<=howMany; i++){
                Thread.sleep(interval);
                asyncMessage01 += i + ") " + message + "\n";
                System.out.println(asyncMessage01);
            }
        } catch(InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... Asynch - end of Messaging");

        return (200);
    }

    public String getAsyncMessage01(String date, String userName){

        return "User: " + userName + "\n" +
                "Date: " + date + "\n" +
                asyncMessage01;

    }

    public String getAsyncList02(String date, String userName){
        String content;
        if (listOfNumbers != null){
            content = listOfNumbers.toString();
        }
        else {
            content = "The list is Empty";
        }

        return "User: " + userName + "\n" +
                "Date: " + date + "\n" +
                content;

    }

}
