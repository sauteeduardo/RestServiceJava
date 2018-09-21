package com.upf.graduacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/carros")
public class CarroRecurso {

	//DADOS FAKE
	static private HashMap<Integer, Carro> carrosMap;

	static {
		//QUANDO ELE INSTANCIA ESSE RECURSO
		//EU CRIO ALGUNS CARROS FAKE
		//POR ESSA RAZÃO É IMPORTANTÍSSIMOOOOOOOOOOOOOOOOOO
		//O USO DO STATIC
		//STATIC: PERTENCE A CLASSE, LOGO, SÓ TEM UM
		Carro fusca = new Carro();
		fusca.setAnoFabricacao((short)1972);
		fusca.setId(1);
		fusca.setKilometragem(134200);
		fusca.setMarca("Volkswagem");
		fusca.setModelo("Fusca");

		Carro gol = new Carro();
		gol.setAnoFabricacao((short)2002);
		gol.setId(2);
		gol.setKilometragem(45000);
		gol.setMarca("Volkswagem");
		gol.setModelo("Gol");

		Carro fiesta = new Carro();
		fiesta.setAnoFabricacao((short)2003);
		fiesta.setId(3);
		fiesta.setKilometragem(56000);
		fiesta.setMarca("Ford");
		fiesta.setModelo("Fiesta");

		Carro corcel = new Carro();
		corcel.setAnoFabricacao((short)1981);
		corcel.setId(4);
		corcel.setKilometragem(87340);
		corcel.setMarca("Ford");
		corcel.setModelo("Corcel");

		carrosMap = new HashMap<Integer, Carro>();
		carrosMap.put(fusca.getId(), fusca);
		carrosMap.put(gol.getId(), gol);
		carrosMap.put(fiesta.getId(), fiesta);
		carrosMap.put(corcel.getId(), corcel);
	}

	//RESTfull

	//GET - lista os carros
	@GET
	@Produces("text/xml")
	public List<Carro> listaCarros(){
		//collections do hashmap -> arraylist -> list
		return new ArrayList<Carro>(carrosMap.values());
	}

	@Path("{id}")
	@GET
	@Produces("text/xml")
	public Carro mostraCarro(@PathParam("id") int id){
		return carrosMap.get(id);
	}

	@POST
	@Consumes("text/xml")
	@Produces("text/plain")
	//JAX-RS XML -> SERIALIZA - INSTANCIA DE CARRO
	public String insereCarro(Carro carro){
		if (carro != null){
			carrosMap.put(carro.getId(), carro);
			return "Sucesso!";
		} else {
			return "Falha!";
		}
	}

	@Path("{id}")
	@DELETE
	@Produces("text/plain")
	public String removeCarro(@PathParam("id") int idCarro){//PATH PARAM
		if (carrosMap.containsKey(idCarro)){
			Carro carro = carrosMap.get(idCarro);
			carrosMap.remove(idCarro);
			return carro.getModelo() + " foi excluído com sucesso!";
		} else {
			return "Chave inexistente!";
		}
	}
	
	@PUT
	@Consumes("text/xml")
	//XML -> SERIALIZA - INSTANCIA DE CARRO
	public String alteraCarro(Carro carro){
		if (carrosMap.containsKey(carro.getId())){
			carrosMap.replace(carro.getId(), carro);
			return "Sucesso!";
		} else {
			return "Carro para alteração não existe ainda!";
		}
	}

}
