package ua.nure.sigma.code.fileManager;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.squareup.javapoet.JavaFile;

import ua.nure.sigma.code.converter.Converter;
import ua.nure.sigma.code.generation.CodeGenerator;
import ua.nure.sigma.code.model.Clazz;
import ua.nure.sigma.code.model.Interface;
import ua.nure.sigma.controller.CodeGenController;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.service.DiagramService;

public class ArchiveGenerator {
	DiagramService service = new DiagramService();
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	public synchronized void generateCode(HttpServletRequest request, HttpServletResponse response, long diagramId) {
		String rootDirectory = request.getServletContext().getRealPath("data");
		try {
			FileUtils.cleanDirectory(new File(rootDirectory));
		} catch (IOException e) {
			e.printStackTrace();
		}
		generateJavaClasses(diagramId,rootDirectory);
		generateZipcrhive(request, response, "data");
	}

	private void generateJavaClasses(long diagramId, String root) {
		Diagram diagram = service.getDiagramById(diagramId);
		for (ua.nure.sigma.db_entities.diagram.Clazz clazz : diagram.getClasses()) {
			generateFile(root+"\\"+clazz.getName(), new Converter().diagramToClassModel(clazz));
		}
	}

	private void generateFile(String fileName, Object clazz) {
		if (clazz == null) {
			return;
		}

		CodeGenerator codegen = new CodeGenerator();
		JavaFile javaFile;
		if (clazz instanceof Clazz) {
			javaFile = JavaFile.builder("ua.nure.jumlit", codegen.generateClass((Clazz) clazz)).build();
		} else if (clazz instanceof Interface) {
			javaFile = JavaFile.builder("ua.nure.jumlit", codegen.generateInterface((Interface) clazz)).build();
		} else {
			System.out.println("FileGenerator#unknown instance of");
			return;
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName + ".java", "UTF-8");
			javaFile.writeTo(writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private synchronized void generateZipcrhive(HttpServletRequest request, HttpServletResponse response,
			String folderName) {
		try {
			String path = request.getServletContext().getRealPath(folderName);
			File directory = new File(path);
			String[] files = directory.list();
			if (files != null && files.length > 0) {
				byte[] zip = zipFiles(directory, files);
				ServletOutputStream sos = response.getOutputStream();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=\"JUMLIT.ZIP\"");
				sos.write(zip);
				sos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized byte[] zipFiles(File directory, String[] files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];
		for (String fileName : files) {
			FileInputStream fis = new FileInputStream(
					directory.getPath() + ArchiveGenerator.FILE_SEPARATOR + fileName);
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
}
