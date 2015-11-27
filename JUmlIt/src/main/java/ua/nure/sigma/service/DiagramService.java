package ua.nure.sigma.service;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.model.DiagramModel;

public class DiagramService {
	
	private DiagramDAO dao = new DiagramDAOImpl();
	
	public DiagramModel createDiagram(DiagramModel diagram) {
		return dao.createDiagram(diagram);
	}
	
	public DiagramModel getDiagramById(long id){
		return dao.getDiagramById(id);
	}
	
	public void updateDiagram(DiagramModel model) {
		dao.updateDiagram(model);
	}
	
	public void deleteDiagram(long id){
		dao.deleteDiagram(id);
	}
}
