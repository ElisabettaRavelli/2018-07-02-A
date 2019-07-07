package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<ArcoPeso> connessioni;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
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

}
