package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.core.SMException;

/**
 * Klasa abstrakcyjna dla service bez requesta
 * Docelowo usunieta poniewaz powinno byc jakies AUTH
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public abstract class ServiceCatchBasic<Response> {

    public abstract Response provide() throws SMException;

    public ResponseSM serve() {
        try {
            Response result = this.provide();
            return ResponseSM.wrap(result, null);
        } catch (SMException sme) {
            return ResponseSM.wrap("ERR", sme.getMessage());
        }
    }
}
