package com.i4m1s1.specmed.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.annotation.ParamMap;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ServiceResponse;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa abstrakcyjna dla Servise z requestem i responsem.
 * docelowo kazda powinna miec request i response gdzie mozna
 * zobaczyc uzytkownika Mozna to dodac potem :)
 * //todo sprint 3
 * https://www.mkyong.com/java/java-custom-annotations-example/
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public abstract class ServiceCatch<Response extends ServiceResponse, Request> {

    public abstract Response provide(Request request) throws SMException;
    // Dla list: TODO: sprawdzenie czy w requescie istnieje to samo co w ParamMap (name())
    protected Map<String, JsonNode> params;

    public ResponseSM serve(Request request) {
        //TODO dodac autoryzacje i logi w audycie jak bedzie czas
        try {
            //dla list:
            if(request instanceof ListRequest) {
                if(this.getClass().isAnnotationPresent(ParamMap.class)) {
                    params = new HashMap<>();
                    Annotation annotation = this.getClass().getAnnotation(ParamMap.class);
                    ParamMap paramMap = (ParamMap)annotation;
                    for (String singleParam : paramMap.name()) {
                        JsonNode idParameter = ((ListRequest) request).getSearchCriteria().get(singleParam);
                        params.put(singleParam, idParameter);
                    }
                }
            }
            Response result = this.provide(request);
            return ResponseSM.wrap(result, null);
        } catch (SMException sme) {
            return ResponseSM.wrap("ERR", sme.getMessage());
        } catch (Exception e) {
            //osobny catch bo moze sie jszcze cos zmieni
            return ResponseSM.wrap("ERR", e.getMessage());
        }
    }
}
