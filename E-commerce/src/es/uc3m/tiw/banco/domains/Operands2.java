package es.uc3m.tiw.banco.domains;

import java.io.Serializable;

public class Operands2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String operand1;
	private String operand2;
	
	
	public String getOperand1() {
		return operand1;
	}
	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}
	public String getOperand2() {
		return operand2;
	}
	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}
	@Override
	public String toString() {
		return "Operands [operand1=" + operand1 + ", operand=" + operand2 + "]";
	}
	
	
	
}