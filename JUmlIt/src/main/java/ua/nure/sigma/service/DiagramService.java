package ua.nure.sigma.service;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.mail.MailUtil;

public class DiagramService {

	private DiagramDAO dao = new DiagramDAOImpl();

	public Diagram createDiagram(Diagram diagram) {
		Diagram newDiagram = dao.createDiagram(diagram);
		if (newDiagram != null && newDiagram.getCollaborators().size() != 0) {
			for (User coll : newDiagram.getCollaborators()) {
				 MailUtil.sendMessage(coll.getEmail(), "javaumlit@gmail.com", "jumlit_sigma",newDiagram.getName());
			}
		}
		return newDiagram;
	}

	public Diagram getDiagramById(long id) {
		return dao.getDiagramById(id);
	}

	public void updateDiagram(Diagram newDiagram) {
		Diagram oldDiagram = dao.getDiagramById(newDiagram.getDiagramId());
		if (newDiagram != null && newDiagram.getCollaborators().size() != 0) {
			for (User coll : newDiagram.getCollaborators()) {
				if (!oldDiagram.getCollaborators().contains(coll)) {
					MailUtil.sendMessage(coll.getEmail(), "javaumlit@gmail.com", "jumlit_sigma",newDiagram.getName());
				}
			}
		}
		dao.updateDiagram(newDiagram);
	}

	public void deleteDiagram(long id) {
		dao.deleteDiagram(id);
	}
}
