package base;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */

@Setter
@Getter
public class BaseRequest {
    /**
     * 私钥地址
     */
    @NotEmpty(message = "私钥地址不能为空")
    private String keyPath ;
    /**
     * 私钥密码
     */
    @NotEmpty(message = "私钥密码不能为空")
    private String keyPwd ;
    /**
     * 请求地址
     */
    @NotEmpty(message = "请求地址不能为空")
    private String request_url;


}
