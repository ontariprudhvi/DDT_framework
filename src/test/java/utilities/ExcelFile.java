package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFile {
	XSSFWorkbook wb;
	//Contractor for reading path
	public ExcelFile(String excelpath) throws Throwable
	{
		FileInputStream fi=new FileInputStream(excelpath);
		wb=new XSSFWorkbook(fi);
	}
	//method of rowcount
	public int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();

	}
	//method to count of no cell in row 
	public int cellcount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();

	}
	//method for cell data
	public String getcelldata(String sheetname,int row,int column)
	{
		String data ="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();

		}
		return data;
	}
	//method for setcelldata
	public  void setcelldata(String sheetname,int row,int column,String status,String writeexcel)throws Throwable
	{
		//get sheet for workbook
		XSSFSheet ws=wb.getSheet(sheetname);
		//get row for sheet
		XSSFRow rownum=ws.getRow(row);
		//create new cell in row
		XSSFCell cell=rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);

		}
		else if(status.equalsIgnoreCase("blocked"))
		{
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream(writeexcel);
		wb.write(fo);

	}
	public static void main(String[] args) throws Throwable 
	{
		ExcelFile xl=new ExcelFile("d:/dummy.xlsx");
		//count no of rows
		int rc=xl.rowcount("login");
		//count no of cell in row
		int cc=xl.cellcount("login");
		System.out.println("no of rows are::"+rc+"     "+"num of cells in row "+cc);
		for(int i=1;i<=rc;i++)
		{
			String username=xl.getcelldata("login", i, 0);
			String password=xl.getcelldata("login", i, 1);
			System.out.println(username+"   "+password);
			//xl.setcelldata("login", i, 2, "pass", "D:/results1.xlsx");
			//xl.setcelldata("login", i, 2, "fail", "D:/results1.xlsx");
			xl.setcelldata("login", i, 2, "Blocked", "D:/results1.xlsx");
		}
	}

}

