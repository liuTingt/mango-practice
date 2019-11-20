package com.louis.mango.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * @Description 
 *	POI相关工具类
 * @author lt
 *
 */
public class PoiUtils {

	public static File createExcelFile(Workbook workbook, String fileName) {
		OutputStream os = null;
		File file = null;
		try {
			file = file.createTempFile(fileName, ".xlsx");
			os = new FileOutputStream(file.getAbsoluteFile());
			workbook.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(workbook);
			IOUtils.closeQuietly(os);
		}
		return file;
	}
}
