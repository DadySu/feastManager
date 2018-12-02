package common;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ParseExcelUtil {

    /**
     * excel 转换为  List<entityClass>
     *  @param unReadField (可不读的列名)
     *  @param file MultipartFile
     *  @param entityClass (转换后对象T.class)
     *  @param fieldMap (列名对应属性名 map)
     *  @param titleRow 表头所在行(下标从0开始)
     *  @return
     *  @throws Exception 
     * @author mengfanzhu
     */
    public static <T> List<T> excelToList(String unReadField, MultipartFile file, Class<T> entityClass, Map<String, String> fieldMap,
                                          int titleRow) throws Exception {


        // 定义要返回的list
        List<T> resultList = new ArrayList<T>();
        try {
            Workbook wb = null;
            InputStream is = null;
            try {
                is = file.getInputStream();
                wb = WorkbookFactory.create(is);
            } catch (IOException e) {
                log.error(e.toString());
                throw new Exception("程序转换excel异常！");
            } catch (InvalidFormatException e) {
                log.error(e.toString());
                throw new Exception("文件流转换出现问题");
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if(wb !=null){
                        wb.close();
                    }
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
            Sheet hssfSheet = wb.getSheetAt(0);
            // 获取工作表的title行
            Row chineseNameRow = hssfSheet.getRow(titleRow);
            if (chineseNameRow == null) {
                throw new Exception(" 第 " + (titleRow + 1) + "行表头为空！");
            }
            // Excel中的titleName
            String[] titleNames = new String[chineseNameRow.getLastCellNum()];
            for (int i = 0; i < titleNames.length; i++) {
                titleNames[i] = chineseNameRow.getCell(i).getStringCellValue().trim();
            }
            // 判断需要的字段在Excel中是否都存在
            List<String> excelFieldList = Arrays.asList(titleNames);
            for (String cnName : fieldMap.keySet()) {
                if (!excelFieldList.contains(cnName)) {
                	throw new Exception("Excel中缺少必要的字段["+cnName+"]，或此字段名称有误");
                }
            }
            // 将列名和列号放入Map中,这样通过列名就可以拿到列号 <fieldName，index>
            LinkedHashMap<String, Integer> fieldNameMap = new LinkedHashMap<String, Integer>();// "referenceNo"%%"0"
            for (int i = 0; i < titleNames.length; i++) {
                Cell cell = chineseNameRow.getCell(i);
                if(unReadField.contains(cell.getStringCellValue().trim())){
                	continue;
                }
                for (Map.Entry<String, String> field : fieldMap.entrySet()) {
                    String strCellValue = cell.getStringCellValue().trim();
                    if (strCellValue.equals(field.getKey())) {
                        fieldNameMap.put(field.getValue(), cell.getColumnIndex());
                    }
                }
            }
            // 将sheet转换为list
            for (int i = 1; i <= hssfSheet.getLastRowNum(); i++) {
            	if(hssfSheet.getRow(i)==null){
                	break;
                }
                // 新建要转换的对象
                T entity = entityClass.newInstance();
                // 给对象中的字段赋值
                int cellIndex = 1;
                for (Map.Entry<String, Integer> entry : fieldNameMap.entrySet()) {
                    // 获取英文字段名
                    String fieldName = entry.getKey();
                    // 根据中文字段名获取列号
                    int collIndex = fieldNameMap.get(fieldName);
                    // 获取当前单元格中的内容 行 类
                    Cell cell = hssfSheet.getRow(i).getCell(collIndex);
                    // 给对象赋值
                    setFieldValueByName(fieldName, cell, entity,i,cellIndex);
                    cellIndex ++;
                }
                resultList.add(entity);
            }
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
            throw new Exception("导入Excel失败！"+e.getMessage());
        }
        return resultList;
    }

    /**
     *  根据字段名给对象的字段赋值
     * @param fieldName
     *            字段名
     * @param cell
     * @param o
     *            对象
     * @param i
     * @param cellIndex
     * @throws Exception
     */
    private static void setFieldValueByName(String fieldName, Cell cell, Object o, int i, int cellIndex) throws Exception {
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            try {
				field.setAccessible(true);
				// 获取字段类型
				Class<?> fieldType = field.getType();
				switch (cell.getCellType()) {
				//表达式
				case Cell.CELL_TYPE_FORMULA:
					try {
						String s ="";
						if(HSSFDateUtil.isCellDateFormatted(cell)){//判断是否为日期类型
							//用于转化为日期格式
							Date d = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
							s = formater.format(d);
						}else{
							s = String.valueOf(cell.getNumericCellValue()).trim();
						}
//						log.info(s+"%"+fieldName+"%FORMULA");
						field.set(o, s);
					} catch (IllegalStateException e) {
						field.set(o, String.valueOf(cell.getRichStringCellValue()).trim());
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if(HSSFDateUtil.isCellDateFormatted(cell)){//判断是否为日期类型
						//用于转化为日期格式
						Date d = cell.getDateCellValue();
						DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						String value = formater.format(d);
//						log.info(value+"%"+fieldName+"%Date");
						field.set(o, value);
					}else if(String.class == fieldType){
						String s = NumberToTextConverter.toText(cell.getNumericCellValue());
						field.set(o, s);
//						log.info(s+"%"+fieldName+"%String");
					}else if(Long.class == fieldType){
						Long l =  new Double(cell.getNumericCellValue()).longValue();
//						log.info(l+"%"+fieldName+"%Long");
						field.set(o, l);
					}else if(Integer.class == fieldType){
						Integer integer =  new Double(cell.getNumericCellValue()).intValue();
//						log.info(integer+"%"+fieldName+"%Integer");
						field.set(o, integer);
                    }else if(BigDecimal.class == fieldType){
                        BigDecimal integer =  new BigDecimal(cell.getNumericCellValue());
//                        log.info(integer+"%"+fieldName+"%Int§eger");
                        field.set(o, integer);
                    }else{
//						log.info("Other NUMERIC "+fieldName +"%"+fieldType);
					}
					break;
					
				case Cell.CELL_TYPE_STRING:
					String s =  cell.getStringCellValue().trim();

//					log.info(s+"%"+fieldName+"%string");
					field.set(o, s);
					break;
                case Cell.CELL_TYPE_BOOLEAN:
                    boolean booleanCellValue = cell.getBooleanCellValue();
                    field.set(o,booleanCellValue);
				case Cell.CELL_TYPE_BLANK:
				    //log.info("CELL"+fieldName+" 空");
				    break;
				default:
//					log.info("Other CellType "+fieldName +"%"+fieldType);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				  throw new Exception(fieldName+"第"+i+"行，第"+cellIndex+"列值格式错误!");//""+field.getName()+"值格式错误！");
			}
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }
  
    /**
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     * @param fieldName
     *            字段名
     * @param clazz
     *            包含该字段的类
     * @return 字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        // 如果本类和父类都没有，则返回空
        return null;
    }
}
