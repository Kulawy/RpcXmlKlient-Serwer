package com.pwr190729.ExerciseTwo;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AfterPrintIndexingCall implements AsyncCallback {

    public void handleResult(Object o, URL url, String s) {
        System.out.println("REPEATING WITH INDEXING END");
        System.out.println( o.getClass().toString() + " | " + url.toString() + " | " + s);
        
    }

    public void handleError(Exception e, URL url, String s) {
        System.out.println("Handle-error MESSAGE!");

    }

}
