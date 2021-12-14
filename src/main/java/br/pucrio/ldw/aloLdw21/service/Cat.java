package br.pucrio.ldw.aloLdw21.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="animalEstimacao", havingValue="gato")
public class Cat implements Animal{
	@Override
	public String speak() {
		return "Meow";
	}
}