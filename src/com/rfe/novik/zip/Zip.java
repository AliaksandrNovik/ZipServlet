package com.rfe.novik.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	final static int partHTTP = 1024;

	public  void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

		System.out.println("Writing '" + fileName + "' to zip file");

		File file = new File( fileName); 
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry("D:/epam/" +  fileName);
		zos.putNextEntry(zipEntry);
		
		byte[] bytes = new byte[partHTTP];
		
		int length;
		
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		
		zos.closeEntry();
		fis.close();
		System.out.println(fileName + "' has been added to zip");
		
	} 
}
