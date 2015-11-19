package ua.nure.sigma.dao;

import ua.nure.sigma.db_entities.Token;

public interface TokenDAO {
	Token createToken(Token token);

	boolean checkTokenExisting(Token token);

	void updateToken(Token token);

	void deleteToken(Token token);
	
	void deleteToken (long userId);
}
