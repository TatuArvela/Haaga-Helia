package com.example.h5.bean;

public class Paino {
	
	private String pvm;
	private double paino;
	
	public String getPvm() {
		return pvm;
	}
	
	public double getPaino() {
		return paino;
	}
	
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}
	
	public void setPaino(double paino) {
		this.paino = paino;
	}
	
	public Paino() {
		super();
	}
	
	public Paino(double paino) {
		super();
		this.paino = paino;
	}

	public Paino(int id, String pvm, double paino) {
		super();
		this.pvm = pvm;
		this.paino = paino;
	}

	@Override
	public String toString() {
		return "Paino [pvm=" + pvm + ", paino=" + paino + "]";
	}
}
