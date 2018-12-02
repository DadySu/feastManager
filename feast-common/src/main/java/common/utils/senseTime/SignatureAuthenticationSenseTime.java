package common.utils.senseTime;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.UUID;

/**
 * 签名认证
 */
@Slf4j
public class SignatureAuthenticationSenseTime {

    public static String getSignatureAuthentication(String API_KEY, String API_SECRET) throws InvalidKeyException, NoSuchAlgorithmException {
        StringBuffer str = new StringBuffer("key=");
        //Unix 时间戳
        String timestamp = System.currentTimeMillis() + "";
        //随机数
        String nonce = getUUID();
        //依据字符串首位字符的ASCII码进行升序排列，并join成一个字符串
        String join_str = genjoinstr(timestamp, nonce, API_KEY);
        //用API_SECRET对这个字符串做hamc-sha256 签名，以16进制编码
        String signature = genEncryptString(join_str, API_SECRET);
        str.append(API_KEY).append(",timestamp=").append(timestamp).append(",nonce=")
                .append(nonce).append(",signature=").append(signature);
        log.debug("------------------------签名："+str.toString());
         return str.toString();
    }

    public static synchronized String getUUID(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String nonce=str.replace("-", "");
        return nonce;
    }

    public static String genjoinstr(String timestamp, String nonce, String API_KEY){

        ArrayList<String> beforesort = new ArrayList<String>();
        beforesort.add(API_KEY);
        beforesort.add(timestamp);
        beforesort.add(nonce);

        Collections.sort(beforesort, new SpellComparator());
        StringBuffer aftersort = new StringBuffer();
        for (int i = 0; i < beforesort.size(); i++) {
            aftersort.append(beforesort.get(i));
        }

        String join_str = aftersort.toString();
        return join_str;
    }

    public static String genEncryptString(String join_str, String API_SECRET) throws NoSuchAlgorithmException, InvalidKeyException {

        Key sk = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);
        final byte[] hmac = mac.doFinal(join_str.getBytes());//完成hamc-sha256签名
        StringBuilder sb = new StringBuilder(hmac.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : hmac) {
            formatter.format("%02x", b);
        }
        String signature = sb.toString();//完成16进制编码
        return signature;
    }

}
