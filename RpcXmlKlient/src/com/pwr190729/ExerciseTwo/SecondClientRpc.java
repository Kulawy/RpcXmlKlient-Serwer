package com.pwr190729.ExerciseTwo;

import org.apache.xmlrpc.XmlRpcClient;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

public class SecondClientRpc {

    public static void main(String[] args) {

        try {
            System.out.println("Hello, welcome on xlm-rpc client for GeronServer");
            System.out.println("Load default params? y/n");
            Scanner sc = new Scanner(System.in);

            String myUrl = sc.nextLine();
            if ( myUrl.toLowerCase().equals("n")){
                System.out.println("Give an address");
                myUrl = "http://" + sc.nextLine();
                System.out.println("Give an Port");
                myUrl += ":" + sc.nextLine();
            }
            else{
                myUrl = "http://localhost:1992";
            }

            XmlRpcClient srv = new XmlRpcClient(myUrl);
            Utils u = new Utils(srv);

            Vector<Integer> showParams = new Vector();
            Vector<String> msgParams = new Vector();
            Vector<String> endingMsgParams = new Vector();

            showParams.addElement(new Integer(1));

            System.out.println("type show to show server functions");
            while (!u.getInput().equals("show")){
                System.out.println("Wrong command");
            }

            boolean cont = true;
            while (cont){
                u.showOnClient(showParams);
                String command = u.getInput().toLowerCase();
                Vector execParams = new Vector();
                switch (command){
                    case "0":
                        u.echo();
                        break;
                    case "1":
                        Vector<String> execStringParams = new Vector();
                        execStringParams.addElement(u.getInput());
                        Object messageResult = srv.execute("GeronServer.echo", execStringParams);
                        String msg = (String) messageResult;
                        System.out.println(msg);
                        break;
                    case "4":
                        Vector indexingParams = new Vector();
                        askQuestionAndExceptAnswer("What is a message ?(Text)");
                        indexingParams.addElement(u.getInput());
                        askQuestionAndExceptAnswer("What is interval?(number)");
                        indexingParams.addElement(Integer.valueOf(u.getInput()));
                        askQuestionAndExceptAnswer("What is a number of repetition?(number)");
                        indexingParams.addElement(Integer.valueOf(u.getInput()));
                        u.fillStringIndexingAsync(indexingParams);
                        break;
                    case "5":
                        Vector rollParams = new Vector();
                        askQuestionAndExceptAnswer("How many elements should bye on list?(number)");
                        rollParams.addElement(Integer.valueOf(u.getInput()));
                        askQuestionAndExceptAnswer("How much time time between rolls?(number)");
                        rollParams.addElement(Integer.valueOf(u.getInput()));
                        askQuestionAndExceptAnswer("What is a min value of elements?(number)");
                        rollParams.addElement(Integer.valueOf(u.getInput()));
                        askQuestionAndExceptAnswer("What is a max value of elements?(number)");
                        rollParams.addElement(Integer.valueOf(u.getInput()));
                        u.rollAndSortAsync(rollParams);
                        break;
                    case "p01":
                        execParams.addElement(LocalDate.now().toString());
                        System.out.println("Type user name:");
                        System.out.print(">");
                        execParams.addElement(u.getInput());
                        u.printMessage01(execParams);
                        execParams = null;
                        break;
                    case "p02":
                        execParams.addElement(LocalDate.now().toString());
                        System.out.println("Type user name:");
                        System.out.print(">");
                        execParams.addElement(u.getInput());
                        u.printList02(execParams);
                        execParams = null;
                        break;
                    case "q":
                        cont = false;
                        break;

                }

            }

            endingMsgParams.addElement("Goodbye");
            Object result = srv.execute("GeronServer.echo", endingMsgParams);
            String res = (String) result;
            System.out.println(res);


        } catch (Exception exception) {
            System.err.println("Client XML-RPC: " + exception);
        }
    }

    public static void askQuestionAndExceptAnswer(String message){
        System.out.println(message);
        System.out.print(">");
    }

}
