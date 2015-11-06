package ua.nure.sigma.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.model.DiagramModel;
import ua.nure.sigma.service.DiagramCreationService;

@Controller
public class DiagramCreateController {
	private DiagramCreationService service = new DiagramCreationService();

	@RequestMapping(value = "/diagram/create", method = RequestMethod.POST)
	public @ResponseBody DiagramModel registerUser(HttpServletRequest request, HttpSession session)
			throws SQLException {
		DiagramModel diagram = new Gson().fromJson(request.getParameter("diagram"), DiagramModel.class);
		User user = (User) session.getAttribute("user");
		diagram.setOwner(user);
		return service.createDiagram(diagram);
	}

	@RequestMapping(value = "/diagram/{id}", method = RequestMethod.GET)
	public @ResponseBody DiagramModel getUserId(@PathVariable long id, HttpSession session) throws SQLException {
		return service.getDiagramById(id);
	}

	@RequestMapping(value = "/diagram/{id}/update", method = RequestMethod.POST)
	public void updateDiagram(@PathVariable long id, HttpServletRequest request) throws SQLException {
		DiagramModel diagram = new Gson().fromJson(request.getParameter("diagram"), DiagramModel.class);
		service.updateDiagram(diagram);
	}

	@RequestMapping(value = "/diagram/{id}/remove", method = RequestMethod.POST)
	public void updateDiagram(@PathVariable long id) throws SQLException {
		service.deleteDiagram(id);
	}
}
