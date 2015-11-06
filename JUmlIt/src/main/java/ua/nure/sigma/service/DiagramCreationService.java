package ua.nure.sigma.service;

import ua.nure.sigma.dao.DiagramDAO;
import ua.nure.sigma.model.DiagramModel;

public class DiagramCreationService {
	
	private DiagramDAO dao;
	
	public DiagramModel createDiagram(DiagramModel diagram) {
		return dao.createDiagram(diagram);
	}
	
	public DiagramModel getDiagramById(long id){
		return dao.getDiagramById(id);
	}
}
