package common.excel;

import java.util.List;

public class ExcelHeader implements Excel{
	
	private String[]  titles;
	private String[]  fields;//统计信息可以放在这
	private List objs;
	
	public ExcelHeader(String[]  titles,String[] fields,List objs){
		this.titles=titles;
		this.fields=fields;
		this.objs=objs;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public List getObjs() {
		return objs;
	}

	public void setObjs(List objs) {
		this.objs = objs;
	}
	
}
