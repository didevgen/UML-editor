package ua.nure.sigma.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.code.fileManager.ArchiveGenerator;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class CodeGenController {

	@RequestMapping(value = "/code/generate/{diagramId}", method = RequestMethod.GET)
	public void generateCode(HttpServletRequest request, HttpServletResponse response, @PathVariable long diagramId, Principal principal)
			throws IOException {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			response.sendError(403);
		}
		ArchiveGenerator generator = new ArchiveGenerator();
		generator.generateCode(request, response, diagramId);
	}

}
