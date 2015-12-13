package ua.nure.sigma.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.code.fileManager.ArchiveGenerator;

@RestController
public class CodeGenController {

	@RequestMapping(value = "/code/generate/{diagramId}", method = RequestMethod.GET)
	public void generateCode(@PathVariable long diagramId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ArchiveGenerator generator = new ArchiveGenerator();
		generator.generateCode(request, response, diagramId);
	}

}
