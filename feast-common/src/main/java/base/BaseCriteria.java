package base;


import common.CoreConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @description: api criteria
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
@Getter
@Setter
public abstract class BaseCriteria<T> {
	
	
	/** 
	* @Fields pageNo : 页码
	*/ 
	private Integer pageNum;
	
	/** 
	* @Fields pageSize : 页数
	*/ 
	private Integer pageSize;

	public void setPageParams(Map<String, Object> searchParams) {
		setPageNum(searchParams.get(CoreConstant.PAGENUM_NAME)==null? searchParams.get("pageNum")==null? CoreConstant.DEFAULT_PAGE_NO:
				Integer.parseInt(searchParams.get("pageNum").toString()):
			Integer.parseInt(searchParams.get(CoreConstant.PAGENUM_NAME).toString()));
		setPageSize(
				searchParams.get(CoreConstant.PAGESIZE_NAME)==null? searchParams.get("pageSize")==null? CoreConstant.DEFAULT_PAGE_SIZE:
						Integer.parseInt( searchParams.get("pageSize").toString()):
					Integer.parseInt(searchParams.get(CoreConstant.PAGESIZE_NAME).toString()));
	}
	
	static class InnerCriteria<T> extends BaseCriteria<T> {
	}
	
	public static BaseCriteria newInstance() {
			return new InnerCriteria();
	}
}
