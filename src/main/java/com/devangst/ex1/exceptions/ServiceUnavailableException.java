package com.devangst.ex1.exceptions;

/**
 * Created by jamesmurphy on 23/02/2016.
 */
public class ServiceUnavailableException extends Exception {

    public ServiceUnavailableException(Exception ex) {
        super(ex);
    }
}
