package com.i4m1s1.specmed.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.dto.LoginDTO;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.UserRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.i4m1s1.specmed.core.dict.WarningMsg.AUTH_ERROR;

@Service
public class ProviderUserLoginService extends BasicServiceCatch<LoginDTO, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BasicResponse<String> provide(BasicRequest<LoginDTO> request) throws SMException {
        BasicResponse<String> response = new BasicResponse<>();

        String email = request.getChunkData().getEmail();
        String password = request.getChunkData().getPassword();

        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            response.setError("Nie podano wymaganych pól: email i password.");
            return response;
        }

        User foundUser = userRepository.findByEmail(email);

        if (foundUser == null) {
            response.setError("Nie odnaleziono użytkownika z tym adresem e-mail.");
            return response;
        }

        if (BCrypt.checkpw(password, foundUser.getPassword())) {
            String token = AuthHelper.createToken(foundUser);
            response.setContent(token);
        } else {
            response.setError("Podane hasło jest nieprawidłowe.");
            return response;
        }

        return response;
    }
}
