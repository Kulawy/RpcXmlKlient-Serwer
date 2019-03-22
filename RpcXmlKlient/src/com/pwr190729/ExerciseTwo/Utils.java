package com.pwr190729.ExerciseTwo;

import com.pwr190729.ExerciseOne.AfterAsyncCall;
import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.XmlRpcClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

public class Utils {

    private XmlRpcClient client;

    public Utils(XmlRpcClient xmlRpcClient){
        client = xmlRpcClient;
    }

    public void rollAndSortAsync(Vector asyncParams){

        AfterPrintIndexingCall ac = new AfterPrintIndexingCall();
        client.executeAsync("GeronServer.rollAndSortNumbers", asyncParams, ac);
    }

    public void printList02(Vector execParams) throws Exception{

        Object messageResult = client.execute("GeronServer.getAsyncList02", execParams);
        String msg = (String) messageResult;
        System.out.println(msg);

    }

    public void fillStringIndexingAsync(Vector asyncParams){

        AfterPrintIndexingCall ac = new AfterPrintIndexingCall();
        client.executeAsync("GeronServer.printWithIndexingAsync", asyncParams, ac);
    }

    public void printMessage01(Vector execParams) throws Exception{

        Object messageResult = client.execute("GeronServer.getAsyncMessage01", execParams);
        String msg = (String) messageResult;
        System.out.println(msg);

    }

    public void echo() throws Exception{
        Vector<String> execParams = new Vector();
        System.out.println("Write message to print:");
        System.out.print(">");
        execParams.addElement(getInput());
        Object messageResult = client.execute("GeronServer.echo", execParams);
        String msg = (String) messageResult;
        System.out.println(msg);
    }

    public void showOnClient(Vector params) throws  Exception{
        Object messageResult = client.execute("GeronServer.show", params);
        String msg = (String) messageResult;
        System.out.println(msg);
    }

    public void asyncPattern(){
        AfterAsyncCall ac = new AfterAsyncCall();
        Vector<Object> asyncParams = new Vector<>();
        Vector<String> msgParams = new Vector();

        asyncParams.addElement(new Integer(6000));
        msgParams.addElement("Now i have a message, so everybody listen!");

        client.executeAsync("GeronServer.execAsy", asyncParams, ac);
        System.out.println("Called Asynchronously");
    }

    public String getInput(){

        String line = "";
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            line = r.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return line;
    }

}
