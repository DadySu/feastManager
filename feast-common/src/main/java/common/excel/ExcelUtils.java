package common.excel;

import common.DatetimeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

public class ExcelUtils {
	
	private SXSSFWorkbook workbook;
	private static CellStyle headStyle;			// 表头行样式
	private static CellStyle contentStyle ;		// 内容行样式
	private static Font contentFont;			// 内容行字体
	private static Font titleFont;			// 表头行字体
	private  int rowNum=0;
	
	public SXSSFWorkbook buildExcel(ExportSetInfo exportSetInfo){
		init();
		String sheetName= StringUtils.defaultIfEmpty(exportSetInfo.getSheetName(), "sheet1");
		Sheet sheet = workbook.createSheet(sheetName);
		
		//header
		createHeader(sheet,exportSetInfo.getHeader());
		//body
		createBody(sheet,exportSetInfo.getBodys());
		return workbook;
	}
	
	public void createHeader(Sheet sheet, ExcelHeader header){
		if(header==null|| ArrayUtils.isEmpty(header.getTitles())){
			return;
		}
		Row[] titleRows=getRows(sheet, header.getTitles().length);
		creatTableTitleCell(header.getTitles(), titleRows);
		creatTableValueCell(header.getFields(), header.getObjs(), titleRows);
		sheet.createRow(rowNum++);
	}
	
	public void createBody(Sheet sheet, List<ExcelBody> bodys){
		for(ExcelBody body:bodys){
			if(StringUtils.isNotEmpty(body.getTitleHeaders())){
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,body.getTitles().length-1));
				creatTableHeaderRow(body.getTitleHeaders(),sheet);
			}
			creatTableTitleRow(body.getTitles(),sheet);
			creatTableValueRow(body.getFields(), body.getObjs(),sheet);
			sheet.createRow(rowNum++);
		}
	}
	
	private  void creatTableHeaderRow(String header,Sheet sheet)
    {	
		Cell headCell;
    	Row headrow=sheet.createRow(rowNum++);
    	headCell=headrow.createCell(0);
    	headCell.setCellValue(header);
		headCell.setCellStyle(headStyle);
    }
    
    private  void creatTableTitleRow(Object[] titles,Sheet sheet)
    {	
    	Row headrow=sheet.createRow(rowNum++);
    	Cell headCell;
    	for(int i=0;i<titles.length;i++){
    		headCell=headrow.createCell(i);
    		headCell.setCellValue(titles[i].toString());
    		headCell.setCellStyle(headStyle);
    		sheet.setColumnWidth(i,5000);// 设置列宽 
    	}
    }
    
    private  void creatTableTitleCell(String[] titles,Row[] titleRows)
    {
    	Cell headCell;
    	for(int i=0;i<titles.length;i++){
    		headCell=titleRows[i].createCell(0);
    		headCell.setCellValue(titles[i]);
    		headCell.setCellStyle(headStyle);
    	}
    }
    
    private  void creatTableValueCell(String[] fields,List objs,Row[] titleRows)
    {	
    	Cell headCell;
    	if(CollectionUtils.isEmpty(objs)){
    		for(int i=0;i<fields.length;i++){
    			headCell=titleRows[i].createCell(1);
    			headCell.setCellValue(fields[i]);
    			headCell.setCellStyle(contentStyle);
    		}
    		return;
    	}
    	int cellNum=0;
    	for (Object obj : objs)
        {	
            Row contentRow = titleRows[cellNum];
            cellNum++;
            //contentRow.setHeight((short) 300);
            Cell[] cells = getCells(contentRow, fields.length+1,1);
            if(ArrayUtils.isNotEmpty(fields))
            {	
                for (int num = 0; num < fields.length; num++)
                {
                    Object value = ReflectionUtils.invokeGetterMethod(obj, fields[num]);
                    cells[cellNum].setCellValue(value == null ? "" : value.toString());
                    cells[cellNum].setCellStyle(contentStyle);
                }
            }
        }
    	
    }
    
    
    private  void creatTableValueRow(Object[] fields,List objs,Sheet sheet)
    {	
        for (Object obj : objs)
        {
            Row contentRow = sheet.createRow(rowNum++);
            //contentRow.setHeight((short) 300);
            Cell[] cells = getCells(contentRow, fields.length,0);
            if(ArrayUtils.isNotEmpty(fields))
            {
                for (int num = 0; num < fields.length; num++)
                {
                    Object value = ReflectionUtils.invokeGetterMethod(obj, fields[num].toString());
                    value = conversion(value);
                    cells[num].setCellValue(value == null ? "" : value.toString());
                }
            }
        }
    }
    
    private static Object conversion(Object param){
    	Object value = null;
    	if (param instanceof Timestamp){
			value = DatetimeUtil.timeFormatOne((Timestamp) param);
		} else{
			value = param;
		}
		return value;
    }
    
    
    /**
     * @Description: 创建内容行的每一行
     */
    private  Row[] getRows(Sheet sheet, int num)
    {
        Row[] rows = new Row[num];
        for (int i = 0; i < num; i++)
        {
        	rows[i] = sheet.createRow(rowNum++);
        }
        return rows;
    }
    
    /**
     * @Description: 创建内容行的每一列.num,fieldName.length.
     */
    private  Cell[] getCells(Row contentRow, int num, int start)
    {
        Cell[] cells = new Cell[num];
 
        for (int i = start,len = cells.length; i < len; i++)
        {
            cells[i] = contentRow.createCell(i);
           
        }
        return cells;
    }
	
    /**
	 * @Description: 初始化
	 */
	public void init()
	{
		workbook = new SXSSFWorkbook(1000);
		headStyle = workbook.createCellStyle();
		contentStyle = workbook.createCellStyle();
		contentFont = workbook.createFont();
		titleFont = workbook.createFont();
		initHeadCellStyle();
		initFont();
		initContentCellStyle();
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	public void initHeadCellStyle()
	{
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(titleFont);
		headStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		headStyle.setBorderTop(CellStyle.BORDER_THIN);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLUE.index);
		headStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		headStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		headStyle.setRightBorderColor(IndexedColors.BLUE.index);
		headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	public void initContentCellStyle()
	{
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
		contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
	}
	
	/**
	 * @Description: 初始化内容行字体
	 */
	public void initFont()
	{
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 11);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short) 13);
		titleFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
	}
	
	//不同浏览器乱码问题
	public  static  String  adaptBrower(HttpServletRequest request,String fileName) throws UnsupportedEncodingException{
		
		final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = null;
        if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
            finalFileName = URLEncoder.encode(fileName,"UTF8");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            finalFileName = new String(fileName.getBytes(), "ISO8859-1");
        }else{
            finalFileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
        }
		return finalFileName;
	}
    
    
}