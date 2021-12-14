package br.pucrio.ldw.aloLdw21.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("umCachorro")
@ConditionalOnProperty(name="animalEstimacao", havingValue="cachorro" , matchIfMissing=true)
public class Dog implements Animal{
	@Override
	public String speak() {
		return "Woof";
	}
}