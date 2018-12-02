package common.token;

import common.utils.MD5Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * Created by songpingping on 2017/8/28.
 */
@Slf4j
public class JavaWebToken {


    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("LOGIN_APP");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public static String createJavaWebToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static String createJavaWebTokenByMd5(Map<String, Object> claims){
        String javaWebToken = createJavaWebToken(claims);
        return MD5Utils.encoderByMd5(javaWebToken);
    }

    public static Map<String, Object> verifyJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }

    public static void main(String [] args){
        String salt1 = "IOS--";
        String salt2 = "ANDROID--";
        String system_code1 = "IOSV1.0";
        String system_code2 = "ANDROIDV1.0";
        String d1 = MD5Utils.encoderByMd5(salt1+system_code1);
        String d2 = MD5Utils.encoderByMd5(salt2+system_code2);
        System.out.println(d1);
        System.out.println(d2);
    }
}
