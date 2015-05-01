package com.rfe.novik;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;







import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rfe.novik.zip.Zip;

@WebServlet("/Download" )

public class Download extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private final String WORK_PATH = "D:/epam/";
    
	public void printButtonAddToZip(HttpServletResponse response,HttpServletRequest request) throws IOException{
		PrintWriter out = response.getWriter();
	}
	
	public void printCssStyleTable(HttpServletResponse response) throws IOException{
		 PrintWriter out = response.getWriter();
		out.println(" <!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Frameset//EN''http://www.w3.org/TR/html4/frameset.dtd'>");
		out.println("<html xmlns='http://www.w3.org/1999/xhtml'>"); 
		out.println("<style type='text/css'>");
		out.println(" .layout {");
		out.println("    width: 100%; ");/* Ўирина всей таблицы */
		out.println("   }");  
		out.println("  TD {");
		out.println("    vertical-align: top; ");/* ¬ертикальное выравнивание в €чейках */
		out.println("    padding: 5px;"); /* ѕол€ вокруг €чеек */
		out.println("   }");
		out.println("   TD.leftcol {");
		out.println("    width: 200px;"); /* Ўирина левой колонки */
		out.println("    background: 'black';"); /* ÷вет фона левой колонки */
		out.println("   }");
		out.println("   TD.rightcol {");
		out.println("    background: #fc3;"); /* ÷вет фона правой колонки */
		out.println("   }");
		out.println("  </style>");
	}
	public void showListFiles(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html");
		String filePath = WORK_PATH;
		File currentFolder = new File(filePath);
        File[] listdFiles = currentFolder.listFiles();
        PrintWriter out = response.getWriter();
        out.println("<body>");
        out.print("<form  method='post'>");
        out.println("<table cellspacing='0' class='layout'>");
        out.println("<tr>"); 
        for(File file: listdFiles){
            out.println("<tr>"); 
	        out.println( "<td>"+ file.getName() + "</td>" );
	        out.println("<td><input type='checkbox' name ='fileName' value='" +file.getName()+"'/><td>" );
	        out.println("</tr>");
        }
        out.println("</tr>");
        out.println("<table>");
        out.println( "<input type='submit' name='button1' value='Get Zip Url' />"	+"</form>");
        out.println("</body>");
        out.println("</html>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printCssStyleTable(response);
		showListFiles(response, request);
		printButtonAddToZip(response, request);

	}
    private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];
 
        for (String fileName : files) {
            FileInputStream fis = new FileInputStream(directory.getPath() + 
               "/" +fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
 
            zos.putNextEntry(new ZipEntry(fileName));
 
            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
 
        return baos.toByteArray();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] listCheckedFiles = request.getParameterValues("fileName");
		 File directory = new File("D:/epam/");
		  byte[] zip = zipFiles(directory, listCheckedFiles);
		  ServletOutputStream sos = response.getOutputStream();
          response.setContentType("application/zip");
          response.setHeader("Content-Disposition", "attachment; filename='DATA.ZIP'");
          
          sos.write(zip);
          sos.flush();
	}

}
