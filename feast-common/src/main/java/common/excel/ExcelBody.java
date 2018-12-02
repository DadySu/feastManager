package common.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelBody implements Excel{
	private String titleHeaders;
	private Object[] titles;
	private Object[] fields;
	private List objs;
	
	public ExcelBody(String titleHeaders,String[] titles,String[] fields,List objs){
		this.titleHeaders=titleHeaders;
		this.titles=titles;
		this.fields=fields;
		this.objs=objs;
	}

	public ExcelBody(Object[] titles, Object[] fields, List objs) {
		super();
		this.titles = titles;
		this.fields = fields;
		this.objs = objs;
	}
	
	
}
