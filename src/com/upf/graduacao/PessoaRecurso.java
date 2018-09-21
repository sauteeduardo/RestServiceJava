package com.upf.graduacao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/pessoas")
public class PessoaRecurso {

	@GET
	@Produces("text/plain")
	public String listaPessoas(){
		return "Huummm, lembrei da aula passada";
	}
	
	@POST
	@Produces("text/plain")
	public String testePost(){
		return "Hi POST";
	}
	
}
