package ua.nure.sigma.dao;

import java.util.List;

import ua.nure.sigma.db_entities.Comment;

public interface CommentDAO {
	Comment insertComment(Comment comment);

	List<Comment> getAllDiagramComments(long deiagramId);
}
