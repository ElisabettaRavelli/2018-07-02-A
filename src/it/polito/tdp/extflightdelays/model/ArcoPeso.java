package it.polito.tdp.extflightdelays.model;

public class ArcoPeso {
	
	private Integer v1;
	private Integer v2;
	private Double peso;
	public ArcoPeso(Integer v1, Integer v2, Double peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}
	public Integer getV1() {
		return v1;
	}
	public Integer getV2() {
		return v2;
	}
	public Double getPeso() {
		return peso;
	}
	

}
