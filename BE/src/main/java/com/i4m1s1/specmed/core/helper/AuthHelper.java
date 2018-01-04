package com.i4m1s1.specmed.core.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.persistence.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.i4m1s1.specmed.core.dict.WarningMsg.AUTH_ERROR;

/**
 * Helper służący do tworzenia i weryfikacji/odczytywania JWT
 *
 * @author br33f
 */
public class AuthHelper {

    public static final String JWT_HEADER = "Authorization";
    private static final String SECRET = "specmed";
    private static final long ONE_SECOND = 1000;
    private static final long ONE_MINUTE = 60 * ONE_SECOND;
    private static final long ONE_HOUR = 60 * ONE_MINUTE;

    public static String createToken(User user) throws SMException {
        Algorithm algorithm = generateAlgorithm();

        long now = DateHelper.getCurrentDateAsLong();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + ONE_HOUR);
        String serializedPermissions = null;
        try {
            serializedPermissions = serializeObject(user.getPermissions());
        } catch (IOException e) {
            throw new SMException("545664578888", AUTH_ERROR, e.getMessage());
        }

        String entityId = user.getEntityId();
        if (entityId == null) {
            entityId = "";
        }

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("entityId", entityId)
                .withClaim("permissions", serializedPermissions); // TODO: zastanowić się czy da się to zrobić jakoś fajniej - bez serializacji listy uprawnien?

        return tokenBuilder.sign(algorithm);
    }

    public static User readToken(String token) throws SMException {
        if (token == null) {
            return null;
        }
        Algorithm algorithm = generateAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new SMException("545544548888221", AUTH_ERROR, e.getMessage());
        }

        String jwtId = jwt.getClaim("id").asString();
        String jwtEntityId = jwt.getClaim("entityId").asString();
        String jwtEmail = jwt.getClaim("email").asString();
        String jwtSerializedPermissions = jwt.getClaim("permissions").asString();
        ArrayList<Permission> jwtPermissions = null;
        try {
            jwtPermissions = (ArrayList<Permission>) deserializeList(jwtSerializedPermissions);
        } catch (Exception e) {
            throw new SMException("53454356354346", AUTH_ERROR, e.getMessage());
        }

        User jwtUser = new User();
        jwtUser.setId(jwtId);
        jwtUser.setEntityId(jwtEntityId);
        jwtUser.setEmail(jwtEmail);
        jwtUser.setPermissions(jwtPermissions);

        return jwtUser;
    }

    private static Algorithm generateAlgorithm() throws SMException {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC512(SECRET);
        } catch (UnsupportedEncodingException e) {
            throw new SMException("124214124444", AUTH_ERROR, e.getMessage());
        }
        return algorithm;
    }


    private static List deserializeList(String s) throws IOException, ClassNotFoundException {
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(s, List.class);
    }

    private static String serializeObject(Serializable o) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, o);

        return out.toString();
    }
}
