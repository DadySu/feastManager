package common.result.err;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 应用统一错误异常
 * @Author mengfanzhu
 * @Date 8/10/17 16:33
 * @Version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException {

	private String code;
	private String message;

    public BizException(ErrorInterface errorInfo) {
    	this.message = errorInfo.getRetMessage();
		this.code = errorInfo.getRetCode();
    }

	public BizException(ErrorInterface errorInfo,String errorMsg) {
		this.message = errorInfo.getRetMessage()+errorMsg;
		this.code = errorInfo.getRetCode();
	}

	public BizException(String errMsg) {
		this.code = ErrorInfoEnum.PARAM_ERR.getRetCode();
		this.message = errMsg;
	}

	public BizException(ErrorInterface errorInterface,String replaceMsg,boolean isReplace) {
		String errorMsg = errorInterface.getRetMessage();
		if (isReplace){
			errorMsg = errorMsg.replaceAll(ErrorInfoEnum.ERROR_REPLACE.getRetCode(), replaceMsg);
		}
		this.message = errorMsg;
		this.code = errorInterface.getRetCode();
	}
}
