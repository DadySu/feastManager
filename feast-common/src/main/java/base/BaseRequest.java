package base;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by songpingping on 2017/8/31.
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
