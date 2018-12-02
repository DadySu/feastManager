package common.excel;

import java.util.List;


/**
 * 封装Excel导出的设置信息
 */
public class ExportSetInfo {
	private String sheetName; //标签页名称

	ExcelHeader header; //excel表头
	List<ExcelBody> bodys;//excel内容

	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public ExcelHeader getHeader() {
		return header;
	}
	public void setHeader(ExcelHeader header) {
		this.header = header;
	}
	public List<ExcelBody> getBodys() {
		return bodys;
	}
	public void setBodys(List<ExcelBody> bodys) {
		this.bodys = bodys;
	}
	public ExportSetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExportSetInfo(String sheetName, List<ExcelBody> bodys) {
		super();
		this.sheetName = sheetName;
		this.bodys = bodys;
	}
	
	
}
