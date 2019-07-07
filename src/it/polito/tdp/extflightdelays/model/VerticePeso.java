package it.polito.tdp.extflightdelays.model;

public class VerticePeso implements Comparable<VerticePeso>{
	
	private Integer vertice;
	private Double peso;
	public VerticePeso(Integer vertice, Double peso) {
		super();
		this.vertice = vertice;
		this.peso = peso;
	}
	public Integer getVertice() {
		return vertice;
	}
	public Double getPeso() {
		return peso;
	}
	@Override
	public int compareTo(VerticePeso o) {
		return peso.compareTo(o.peso);
	}
	
	

}
