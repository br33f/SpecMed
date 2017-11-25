package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.service.response.ServiceResponse;

/**
 * Klasa abstrakcyjna dla Servise z requestem i responsem.
 * docelowo kazda powinna miec request i response gdzie mozna
 * zobaczyc uzytkownika Mozna to dodac potem :)
 * //todo sprint 3
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public abstract class ServiceCatch <Response extends ServiceResponse, Request> {

    public abstract Response provide(Request request) throws SMException;

    public ResponseSM serve(Request request) {
        try {
            Response result = this.provide(request);
            return ResponseSM.wrap(result, null);
        } catch (SMException sme) {
            return ResponseSM.wrap("ERR", sme.getMessage());
        }
    }
}
