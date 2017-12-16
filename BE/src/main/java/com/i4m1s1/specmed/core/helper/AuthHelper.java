package com.i4m1s1.specmed.core.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.persistence.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import static com.i4m1s1.specmed.core.dict.WarningMsg.AUTH_ERROR;

/**
 * Helper służący do tworzenia i weryfikacji/odczytywania JWT
 * @author br33f
 */
public class AuthHelper {
    private static final String secret = "specmed";

    public static String createToken(User user) throws SMException {
        Algorithm algorithm = genereteAlgorithm();

        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + 3600 * 1000);
        String serializedPermissions = null;
        try {
            serializedPermissions = serializeObject(user.getPermissions());
        } catch (IOException e) {
            throw new SMException("545664578888", AUTH_ERROR, e.getMessage());
        }

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("email", user.getEmail())
                .withClaim("permissions", serializedPermissions); // TODO: zastanowić się czy da się to zrobić jakoś fajniej - bez serializacji listy uprawnien?

        return tokenBuilder.sign(algorithm);
    }

    public static User readToken(String token) throws SMException {
        Algorithm algorithm = genereteAlgorithm();

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new SMException("545544548888221", AUTH_ERROR, e.getMessage());
        }

        String jwtEmail = jwt.getClaim("email").asString();
        String jwtSerializedPermissions = jwt.getClaim("permissions").asString();
        ArrayList<Permission> jwtPermissions = null;
        try {
            jwtPermissions = (ArrayList<Permission>)deserializeObject(jwtSerializedPermissions);
        } catch (Exception e) {
            throw new SMException("53454356354346", AUTH_ERROR, e.getMessage());
        }

        User jwtUser = new User();
        jwtUser.setEmail(jwtEmail);
        jwtUser.setPermissions(jwtPermissions);

        return jwtUser;
    }

    private static Algorithm genereteAlgorithm() throws SMException {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC512(secret);
        } catch (UnsupportedEncodingException e) {
            throw new SMException("124214124444", AUTH_ERROR, e.getMessage());
        }
        return algorithm;
    }


    private static Object deserializeObject(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static String serializeObject(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
