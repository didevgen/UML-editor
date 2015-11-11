package ua.nure.sigma.code.generation;

import javax.lang.model.element.Modifier;

public class GeneratorService {
	public Modifier getModifier(String modifier) {
		return Modifier.valueOf(modifier.toUpperCase());
	}
}
