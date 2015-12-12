package ua.nure.sigma.service;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;

public class DiagramService {

	private DiagramDAO dao = new DiagramDAOImpl();

	public Diagram createDiagram(Diagram diagram) {
		Diagram newDiagram = dao.createDiagram(diagram);
		if (newDiagram != null && newDiagram.getCollaborators().size() != 0) {
			for (User coll : newDiagram.getCollaborators()) {
				// MailUtil.sendMessage(coll.getEmail(), "", "");
			}
		}
		return newDiagram;
	}

	public Diagram getDiagramById(long id) {
		return dao.getDiagramById(id);
	}

	public void updateDiagram(Diagram newDiagram) {
		if (newDiagram != null && newDiagram.getCollaborators().size() != 0) {
			for (User coll : newDiagram.getCollaborators()) {
				//TODO MUST BE IMPLEMENTED 
					// MailUtil.sendMessage(coll.getEmail(), "", "");
			}
		}
		dao.updateDiagram(newDiagram);
	}

	public void deleteDiagram(long id) {
		dao.deleteDiagram(id);
	}
}
