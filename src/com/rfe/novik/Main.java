package com.rfe.novik;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Download" )

public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String WORK_PATH = "D:/EclipseEE/eclipse";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtmlWriter writer = new HtmlWriter(response, request);
		writer.printCssStyleTable(response);
		writer.printHeaderTable(response, request);
		writer.printListFolders(response, request);
		writer.printListFiles(response, request);
		writer.printButtonZip(response, request);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] listCheckedFiles = request.getParameterValues("fileName");
		
		File directory = new File(WORK_PATH);
		ZipEditor zipEditor = new ZipEditor(directory, listCheckedFiles);
		byte[] zip = zipEditor.addToZip();
		
		ServletOutputStream sos = response.getOutputStream();
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename='DATA.ZIP'");
		sos.write(zip);
		sos.flush();
	}

}
