package com.rfe.novik;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlWriter {

	public HttpServletRequest request;
	public HttpServletResponse response;

	private final String WORK_PATH = "D:/EclipseEE/eclipse";
	private final Double BYTE = 1024.0;
	private String URL_FILE = "https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/512/file.png";
	private String URL_FOLDER = "http://photosinbox.com/wp-content/uploads/2011/07/manila-folder.jpg";


	public HtmlWriter (HttpServletResponse response, HttpServletRequest request){
		this.request = request;
		this.response = response;
	}
	
	public void printListFiles(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html");
		String filePath = WORK_PATH;
		File currentFolder = new File(filePath);
		File[] listdFiles = currentFolder.listFiles();
		PrintWriter out = response.getWriter();
		for(File file: listdFiles){
			if(file.isFile()){
				out.println("<tr>"); 
				if (file.isFile()){
					out.print("<td><img src='" +URL_FILE + "'"
							+ " alt='"+"folder"+"' + style='width:20px;height:22px'> </td>");
				}else{
					out.print("<td><img src='"+URL_FOLDER+"'"
							+ " alt='"+"folder"+"' + style='width:20px;height:22px'> </td>");
				}
				out.println( "<td>"+ file.getName() + "</td>" );/**/
				if(file.isFile()){
					out.println( "<td>"+ getFileSizeInKB(file)+ "</td>" );
				}else{
					out.println( "<td>"+ "  "+ "</td>" );
				}
				out.println( "<td>"+  getTypeFile(file)+ "</td>" );
				out.println("<td><input type='checkbox' name ='fileName' value='" +file.getName()+"'/><td>" );
				out.println("</tr>");
			}
		}
	}
	
	public void printListFolders(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setContentType("text/html");
		String filePath = WORK_PATH;
		File currentFolder = new File(filePath);
		File[] listdFiles = currentFolder.listFiles();
		PrintWriter out = response.getWriter();
		for(File file: listdFiles){
			if(file.isDirectory()){
				out.println("<tr>"); 
				if (file.isFile()){
					out.print("<td><img src='" +URL_FILE + "'"
							+ " alt='"+"folder"+"' + style='width:20px;height:22px'> </td>");
				}else{
					out.print("<td><img src='"+URL_FOLDER+"'"
							+ " alt='"+"folder"+"' + style='width:20px;height:22px'> </td>");
				}
				out.println( "<td>"+ file.getName() + "</td>" );/**/
				if(file.isFile()){
					out.println( "<td>"+ getFileSizeInKB(file)+ "</td>" );
				}else{
					out.println( "<td>"+ "  "+ "</td>" );
				}
				out.println( "<td>"+  getTypeFile(file)+ "</td>" );
				out.println("<td><input type='checkbox' name ='fileName' value='" +file.getName()+"'/><td>" );
				out.println("</tr>");
			}
		}
	}
	public void printHeaderTable(HttpServletResponse response,HttpServletRequest request) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("<body>");
		out.print("<form  method='post'>");
		out.println("<table cellspacing='0' class='layout'>");
		out.println("<tr>");
		out.println("<th>" + "" + "</th>");
		out.println("<th>" + "Name" + "</th>");
		out.println("<th>" + "Size" + "</th>");
		out.println("<th>" + "Type" + "</th>");
		out.println("<th>" + "Zip" + "</th>");
		out.println("</tr>");
	}
	public void printButtonZip(HttpServletResponse response,HttpServletRequest request) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("</tr>");
		out.println("<table>");
		out.println( "<input type='submit' name='button1' value='Get Zip Url' />"	+"</form>");
		out.println("</body>");
		out.println("</html>");
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
		out.println("    vertical-align: top; ");
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

	public String getTypeFile(File file){
		if (file.isDirectory()){
			return "Directory";
		}
		else{
			return "File";
		}
	}

	public String getFileSizeInKB(File file){
		double sizeFile = file.length();
		String formatedSizeOfFile = String.format("%.3f", sizeFile/BYTE );
		return formatedSizeOfFile + "  KB"; 
	}
}


