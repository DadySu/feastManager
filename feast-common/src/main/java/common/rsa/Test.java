package common.rsa;

import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 4/16/18 16:22
 * @Version 1.0
 */
public class Test {
    static String publicKey;
    static String privateKey;

    static {
        try {
            File file = ResourceUtils.getFile("/Users/mfz/Desktop/keys/private_key.keystore");
            privateKey = RSAEncrypt.loadByFile(file);

            File filePublic = ResourceUtils.getFile("/Users/mfz/Desktop/keys/public_key.keystore");
            publicKey = RSAEncrypt.loadByFile(filePublic);
          /*  Map<String, Object> keyMap = RSAEncrypt.genKeyPair();
            publicKey = RSAEncrypt.getPublicKey(keyMap);
            privateKey = RSAEncrypt.getPrivateKey(keyMap);*/
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        test();
    }
    //加密数据
    static void test() throws Exception {
        String source = "Java端RSA";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();

        String a = "X12/uFVxnK01ZACx3TNVP531EpKF19JfLPW79iH3Mje+F7Klajfi3JowNNlsWSXqryjNh7gXiExeCWvq0ymLkaEuZnwcdLQBD81XDvH0byYmsxEW4C46+slL8qySAdEuP+r4LzMnmk3DNc/IuZt2FgQJDaW3jecgsDWiiIK5Gc8=";
//        byte[] encodedData =  RSAEncrypt.encryptByPrivateKey(data,privateKey);
        System.out.println("加密后文字：\r\n" + new String(a));

//        byte[] decodedData = RSAEncrypt.decryptByPublicKey(encodedData, publicKey);
        byte[] decodedData = RSAEncrypt.decryptByPrivateKey(Base64Utils.decode(a), privateKey);

        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }
    //验证签名
    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAEncrypt.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAEncrypt.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAEncrypt.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAEncrypt.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }

}
