package com.i4m1s1.specmed.service.common.catchs;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.DateHelper;
import com.i4m1s1.specmed.service.common.request.ApiRequest;
import com.i4m1s1.specmed.service.common.response.ApiResponse;

import java.lang.reflect.ParameterizedType;

/**
 * Klasa abstrakcyjna dla Servise z requestem i responsem.
 * docelowo kazda powinna miec request i response gdzie mozna
 * TODO dodac autoryzacje i logi w audycie jak bedzie czas
 *
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public abstract class ServiceCatch<Request extends ApiRequest, Response extends ApiResponse> {

    protected boolean isValid;
    protected Request request;

    protected abstract Response provide(Request request) throws SMException;

    protected abstract Response createStubResponse();

    /**
     * Generyczna metoda budująca response na podstawie wyniku metody {@link ServiceCatch#provide(ApiRequest)}
     *
     * @param request request przychodzący na api (JSON)
     * @return response (JSON)
     */
    public Response serve(Request request) {
        try {
            validateRequest(request);
            Response response = provide(request);
            response.setTime(DateHelper.getCurrentDateAsLong());
            return response;
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    /**
     * @param error wiadomosc z wyjątku do wpisania w response
     * @return {@link Response} posiadający wiadomość z wyjątku
     */
    private Response createErrorResponse(String error) {
        Response response = createStubResponse();
        response.setTime(DateHelper.getCurrentDateAsLong());
        response.setError(error);
        return response;
    }

    private void validateRequest(Request request) throws SMException {
        this.isValid = true;
        this.request = request;
        validate();
        if (!isValid) {
            throw new SMException("20171223035759", WarningMsg.GENERIC_VALIDATE);
        }
    }

    /**
     * Metoda do walidacji requesta. Gdy brak @Override - zawsze true
     *
     */
    protected void validate(){}
}
