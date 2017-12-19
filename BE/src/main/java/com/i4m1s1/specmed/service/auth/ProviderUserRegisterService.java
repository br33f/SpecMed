package com.i4m1s1.specmed.service.auth;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.UserRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderUserRegisterService extends BasicServiceCatch<User, Void> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BasicResponse<Void> provide(BasicRequest<User> request) throws SMException {
        BasicResponse<Void> response = new BasicResponse<>();

        String email = request.getChunkData().getEmail();
        String password = request.getChunkData().getPassword();
        ArrayList<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.CUSTOMER);

        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            response.setError("Nie podano wymaganych pól: email i password.");
            return response;
        }

        if (userRepository.findByEmail(email) != null) {
            response.setError("Istnieje już konto przypisane do tego adresu.");
            return response;
        }

        User userToSave = new User();
        userToSave.setEmail(email);
        userToSave.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        userToSave.setPermissions(permissions);
        userRepository.save(userToSave);

        return response;
    }
}
