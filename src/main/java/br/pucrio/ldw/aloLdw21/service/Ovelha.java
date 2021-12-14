package br.pucrio.ldw.aloLdw21.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(Animal.class)
public class Ovelha implements Animal{
	@Override
	public String speak() {
		return "Breeee";
	}
}