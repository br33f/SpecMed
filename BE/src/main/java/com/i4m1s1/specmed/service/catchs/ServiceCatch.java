package com.i4m1s1.specmed.service.catchs;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.helper.DateHelper;
import com.i4m1s1.specmed.service.request.ApiRequest;
import com.i4m1s1.specmed.service.response.ApiResponse;

/**
 * Klasa abstrakcyjna dla Servise z requestem i responsem.
 * docelowo kazda powinna miec request i response gdzie mozna
 * TODO dodac autoryzacje i logi w audycie jak bedzie czas
 *
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public abstract class ServiceCatch<Request extends ApiRequest, Response extends ApiResponse> {

    protected abstract Response provide(Request request) throws SMException;

    protected abstract Response createStubResponse();

    /**
     * Generyczna metoda budująca response na podstawie wyniku metody {@link ServiceCatch#provide(ApiRequest)}
     * @param request request przychodzący na api (JSON)
     * @return response (JSON)
     */
    public Response serve(Request request) {
        try {
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
}
