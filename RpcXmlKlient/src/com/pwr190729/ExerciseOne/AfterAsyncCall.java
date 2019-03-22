package com.pwr190729.ExerciseOne;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AfterAsyncCall implements AsyncCallback {

    public void handleResult(Object o, URL url, String s) {
        System.out.println("KOMUNIKAT OD handle-result");
    }

    public void handleError(Exception e, URL url, String s) {
        System.out.println("KOMUNIKAT OD handle-error");

    }

}
