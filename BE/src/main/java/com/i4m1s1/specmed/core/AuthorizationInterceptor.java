package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.annotation.PermitWith;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.persistence.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors
 * https://stackoverflow.com/questions/35882674/how-to-do-a-customannotation-annotation-that-triggers-a-filter-in-spring-3-mvc
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    /*
    na : http://localhost:8080/auth/login

    wysyłam:

    {
	"chunkData": {
	"email":"student",
	"password":"pass"
		}
    }

    dostaje np odpowiedz z errors null i time czas oraz:

    "content": "eyJhbGciOiJIUzUxMiJ9.
    eyJwZXJtaXNzaW9ucyI6InJPMEFCWE55QUJOcVlYWmhMblYwYVd3dVFYSnlZWGxNYVhOMGVJSFNIWm5IWVowREFBRkpBQVJ6YVhwbGVIQUFBQUFCZHdRQUFBQUJmbklBSjJOdmJTNXBORzB4Y3pFdWMzQmxZMjFsWkM1amIzSmxMbVJwWTNRdVVHVnliV2x6YzJsdmJnQUFBQUFBQUFBQUVnQUFlSElBRG1waGRtRXViR0Z1Wnk1RmJuVnRBQUFBQUFBQUFBQVNBQUI0Y0hRQUNFTlZVMVJQVFVWU2VBPT0iLCJleHAiOjE1MTM2NTM4MjQsImlhdCI6MTUxMzY1MDIyNCwiZW1haWwiOiJzdHVkZW50In0.
    bPMFg-6e42YAlqFKEpjxsyEC6zozuvzzloE9wILzK7zDrBWMwG-hndiBnENasT_3otI2RR4XcYUSpa6WBomHIg"

    czasami wyskakują błędy że zły algorytm albo coś, ale to wina wklejenia tokena do headera xD Z jakimiś enterami.
    ogólnie wydaje mi się że o to chodzi?
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo optimize
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PermitWith filter = handlerMethod.getMethod().getAnnotation(PermitWith.class);
            if (filter != null) {

                String token = request.getHeader(AuthHelper.JWT_HEADER);
                User fromToken = AuthHelper.readToken(token);
                if(fromToken == null) {
                    return false;
                }
                List<Permission> permsOfUser = fromToken.getPermissions();
                //todo można zrobić metodę static AuthHelper#readHeaderFromRequest()
                List<String> permsNeeded = Arrays.asList(filter.value());
                if(permsOfUser == null) {
                    return false;
                }
                return permsOfUser.containsAll(permsNeeded);
            }
        }
        return true;
    }
}