package br.pucrio.ldw.aloLdw21.model.web;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.pucrio.ldw.aloLdw21.exception.RegraDeNegocioException;

@ControllerAdvice
public class TratadorDeErro{

	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public String tratarErroSimples(NullPointerException e){
		System.out.println("Tratando NullPointerException");
		return " Um erro do tipo NPE foi executado ";
	}

	@ExceptionHandler(RegraDeNegocioException.class)
	@ResponseBody
	public ResponseEntity tratarErroComplexo(RegraDeNegocioException e){
		System.out.println("Tratando RegraDeNegocioException");

		Map<String,String> erro = Map.of("mensagem",e.getMessage());

		return ResponseEntity.badRequest()
		.header("headerAdicional", "valorExtra")
		.body(erro);
	}

}