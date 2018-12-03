package common.result;

import com.github.pagehelper.PageInfo;
import common.result.err.BizException;
import common.result.err.ErrorInfoEnum;
import common.result.err.ErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 返回体
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultBody {
    /**
     * 响应代码
     */
    private String retCode;

    /**
     * 响应消息
     */
    private String retMessage;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody(ErrorInterface errorInfo) {
        this.retCode = errorInfo.getRetCode();
        this.retMessage = errorInfo.getRetMessage();
    }
	public ResultBody(BizException result) {
		this.retCode = result.getCode();
		this.retMessage = result.getMessage();
	}

    public ResultBody(String retCode,String retMessage){
    	this.retCode = retCode;
    	this.retMessage = retMessage;
	}
    public ResultBody(Object result) {
        this.retCode = ErrorInfoEnum.SUCCESS.getRetCode();
        this.retMessage =ErrorInfoEnum.SUCCESS.getRetMessage();
        this.result = result;
    }

	public ResultBody(Boolean isSuccess,Object result) {
		if(isSuccess){
			this.retCode =ErrorInfoEnum.SUCCESS.getRetCode();
			this.retMessage =ErrorInfoEnum.SUCCESS.getRetMessage();
		}else{
			this.retCode =ErrorInfoEnum.FAILED.getRetCode();
			this.retMessage =ErrorInfoEnum.FAILED.getRetMessage();
		}
		this.result = result;
	}

	public ResultBody( BindingResult result){
		this.retCode = ErrorInfoEnum.FAILED.getRetCode();
		this.retMessage =ErrorInfoEnum.FAILED.getRetMessage();
		List<ObjectError> list = result.getAllErrors();
		String[] messages = new String[list.size()] ;
		for (int i = 0;i < list.size();i++) {
			ObjectError error = list.get(i);
			messages[i] = error.getDefaultMessage();
		}
		this.result = messages;
	}

	public ResultBody(Boolean isSuccess) {
		if(isSuccess){
			this.retCode =ErrorInfoEnum.SUCCESS.getRetCode();
			this.retMessage =ErrorInfoEnum.SUCCESS.getRetMessage();
		}else{
			this.retCode =ErrorInfoEnum.FAILED.getRetCode();
			this.retMessage =ErrorInfoEnum.FAILED.getRetMessage();
		}
	}


	@SuppressWarnings("unchecked")
	public ResultBody(Object result,Boolean isPage) {
    	if(isPage){
    		if(result instanceof List){
    			this.result = new PageInfo((List) result);
    		}
    	}
    	this.retCode =ErrorInfoEnum.SUCCESS.getRetCode();
    	this.retMessage =ErrorInfoEnum.SUCCESS.getRetMessage();
    }
    

}
