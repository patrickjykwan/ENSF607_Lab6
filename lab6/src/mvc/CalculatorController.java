package mvcCalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
	
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController (CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.addCalcListener(new CalculateListener ());
	}
	
	class CalculateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int first = 0, sec = 0;
			try {
				first = theView.getFirstNumber();
				sec = theView.getSecondNumber();
				theModel.addTwoNumbers(first, sec);
				theView.setSolution (theModel.getValue());
			}catch (NumberFormatException ex) {
				theView.displayErrorMessage("Please enter 2 Integers!");
			}
			
		}
		
	}

}
