package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.BindUserToCustomerDTO;
import com.i4m1s1.specmed.dto.LoginDTO;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.service.auth.ProviderBindUserAccToCustomerService;
import com.i4m1s1.specmed.service.auth.ProviderUserLoginService;
import com.i4m1s1.specmed.service.auth.ProviderUserRegisterService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler udostępniający API do autoryzacji i logowania
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private ProviderUserRegisterService providerUserRegisterService;

    @Autowired
    private ProviderUserLoginService providerUserLoginService;

    @Autowired
    private ProviderBindUserAccToCustomerService bindService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/register")
    public BasicResponse<Void> registerUser(@RequestBody BasicRequest<User> request) {
        return providerUserRegisterService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public BasicResponse<String> loginUser(@RequestBody BasicRequest<LoginDTO> request) {
        return providerUserLoginService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/bind-account")
    public BasicResponse<Customer> bindAccount(@RequestBody BasicRequest<BindUserToCustomerDTO> request) {
        return bindService.serve(request);
    }
}
