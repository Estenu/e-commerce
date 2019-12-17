package es.uc3m.tiw.banco.domains;

import java.io.Serializable;

public class Result2 implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private double result;
	
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	
	
	@Override
	public String toString() {
		return "Result [result=" + result + "]";
	}
	
	
}
