package common.result;


import com.github.pagehelper.PageInfo;
import common.result.err.ErrorInfoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回体
 */
@Getter
@Setter
@AllArgsConstructor
public class ResultPage<T> {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    private Long count;

    private List<T> data;

    public ResultPage(ErrorInfoEnum errorInfo) {
    	this.code = errorInfo.getRetCode();
        this.msg = errorInfo.getRetMessage();
	}
    
    public ResultPage(PageInfo pageInfo, String msg){
        this.code = "0";
        this.msg = msg;
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }
    public ResultPage(PageInfo pageInfo){
        this.code = "0";
        this.count = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }

    public ResultPage(Boolean isFalse){
        if(isFalse){
            this.code="0";
            this.count=0L;
            this.data=new ArrayList<>();
        }
    }
    public ResultPage(Boolean isFalse,String msg){
        if(isFalse){
            this.code="0";
            this.count=0L;
            this.data=new ArrayList<>();
        }
        this.msg = msg;
    }
    
}
