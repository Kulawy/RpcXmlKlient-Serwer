package com.pwr190729.ExerciseOne;
import com.pwr190729.ExerciseOne.AfterAsyncCall;
import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;


public class ClientRpc {

    public static void main(String[] args) {

        try {
            XmlRpcClient srv = new
                    XmlRpcClient("http://localhost:10003");
            Vector params = new Vector();
            params.addElement(new Integer(13));
            params.addElement(new Integer(21));

            AfterAsyncCall ac = new AfterAsyncCall();

            Vector params2 = new Vector();
            params2.addElement(new Integer(9000));
            srv.executeAsync("MyServer.execAsy", params2, ac);
            System.out.println("Wywolano asynchronicznie");

            Object result =
                    srv.execute("MyServer.echo", params);

            int res = ((Integer) result).intValue();
            System.out.println(res);


        } catch (Exception exception) {
            System.err.println("Klient XML-RPC: " +exception);
        }




    }
}
