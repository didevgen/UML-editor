package ua.nure.sigma.service;

import java.util.List;

import ua.nure.sigma.dao.impl.CommentDAOImpl;
import ua.nure.sigma.db_entities.Comment;

public class CommentService {
	public Comment insertComment(Comment comment) {
		return new CommentDAOImpl().insertComment(comment);
	}
	public List<Comment> getDiagramComments(long id) {
		return new CommentDAOImpl().getAllDiagramComments(id);
	}
}	
