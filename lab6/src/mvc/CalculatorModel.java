package mvcCalculator;

//The model is all about data. All data, and all operations on data will be in the model
public class CalculatorModel {
	
	private int value;
	
	public void addTwoNumbers (int a, int b) {
		value = a + b;
	}

	public int getValue() {
		return value;
	}

}
