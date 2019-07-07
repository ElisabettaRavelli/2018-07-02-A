package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<ArcoPeso> connessioni;
	private Map<Integer, Airport> idMap;
	private List<Airport> aeroporti;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		this.idMap = new HashMap<>();
		this.dao.loadAllAirports(idMap);
	}
	
	public void creaGrafo(Integer distanzaMedia) {
		this.grafo = new SimpleWeightedGraph(DefaultWeightedEdge.class);
		this.connessioni = new ArrayList<>();
		this.connessioni = this.dao.getConnessioni(distanzaMedia);
		
		for(ArcoPeso tmp: this.connessioni) {
			Graphs.addEdgeWithVertices(this.grafo, tmp.getV1(), tmp.getV2(), tmp.getPeso());
			System.out.println("Arco aggiunto: "+ tmp.getV1() + " -> "+ tmp.getV2());
		}
		
	}

	public int getVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Airport> getAeroporti(){
		this.aeroporti = new ArrayList<>();
		for(Integer i: this.grafo.vertexSet()) {
			if(idMap.containsKey(i)) {
				this.aeroporti.add(idMap.get(i));
			}
		}
		Collections.sort(this.aeroporti);
		return this.aeroporti;
	}
	
	public List<VerticePeso> getAdiacenti(Integer id){
		List<Integer> adiacenti = new ArrayList<>();
		List<VerticePeso> result = new ArrayList<>();
		adiacenti = Graphs.neighborListOf(this.grafo, id);
		for(Integer i : adiacenti) {
			result.add(new VerticePeso(i, this.grafo.getEdgeWeight(this.grafo.getEdge(id, i))));
		}
		return result;
	}

}
