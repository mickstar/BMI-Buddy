package com.mickstarify.BMIBuddy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class BMIBuddy extends Activity {
    /** Called when the activity is first created. */
    //@Override
    
   private char units = 'i';
   private BMI Bmi = new BMI();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toggleUnits();
        
        final EditText txtHeight = (EditText) findViewById (R.id.txtHeight);
        final EditText txtWeight = (EditText) findViewById (R.id.txtWeight);
        final Button btnCalc = (Button) findViewById (R.id.btnCalculate);
        
        btnCalc.setOnClickListener(new View.OnClickListener() {
        	 
        	
            public void onClick(View v) {
            	// hide the soft keyboard.
            	InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            	IBinder binder = txtWeight.getApplicationWindowToken();
                if (binder != null) {
                        manager.hideSoftInputFromWindow(binder, 0);
                }
                binder = txtHeight.getApplicationWindowToken();
                if (binder != null) {
                    manager.hideSoftInputFromWindow(binder, 0);
                }
            	double weight, height;
            	try{
            		height = Double.parseDouble(txtHeight.getText().toString());
            		weight = Double.parseDouble(txtWeight.getText().toString());
            	}
            	catch(Exception e){
            		return;
            	}
            	getBMI (weight, height);
            }
        });
    }
    
    private void getBMI (double weight, double height){
    	TextView lblBmi = (TextView) findViewById (R.id.lblBMI_VALUE);
    	
    	double BMI = Bmi.calculateBMI(weight, height, units);
    	String bmiString = String.format("%.2f", BMI);
    	
    	lblBmi.setText (bmiString);
    	
    	String msg = Bmi.messageForBMI(BMI);
    	
    	TextView bmiMsg = (TextView) findViewById (R.id.BMIMessage);
    	bmiMsg.setText(msg);
    	
    	String idealmsg = Bmi.idealWeight(height, units);
    	TextView preIdealMsg = (TextView) findViewById (R.id.idealWeightPreMsg);
    	//preIdealMsg.setText ("The ideal weight for your height is between");
    	preIdealMsg.setVisibility(View.VISIBLE);
    	TextView lblIdealWeight = (TextView) findViewById (R.id.idealMsg);
    	lblIdealWeight.setText(idealmsg);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.toggleUnits:
            toggleUnits();
            return true;
        case R.id.menuClear:
        	clear();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    private void toggleUnits (){
    	if (units == 'm'){
    		units = 'i';    		
    	}
    	else{
    		units = 'm';
    	}
    	TextView lblHeightU = (TextView) findViewById (R.id.lblUnitsHeight);
    	TextView lblWeightU = (TextView) findViewById (R.id.lblUnitsWeight);
    	
    	if (lblHeightU.getText() == "cm"){
    		lblHeightU.setText("Inches");
    	}
    	else{
    		lblHeightU.setText("cm");
    	}
    	
    	if (lblWeightU.getText() == "kg"){
    		lblWeightU.setText("lbs");
    	}
    	else{
    		lblWeightU.setText("kg");
    	}
    	
    	return;
    }
    private void clear(){
    	//Clears all values entered
    	TextView lblBMI_VALUE = (TextView) findViewById(R.id.lblBMI_VALUE);
    	TextView BMIMessage = (TextView) findViewById(R.id.BMIMessage);
    	TextView idealMsg = (TextView) findViewById(R.id.idealMsg);
    	TextView idealWeightPreMsg = (TextView) findViewById(R.id.idealWeightPreMsg);
    	EditText txtHeight = (EditText) findViewById (R.id.txtHeight);
        EditText txtWeight = (EditText) findViewById (R.id.txtWeight);
        
        lblBMI_VALUE.setText ("");
        BMIMessage.setText ("");
        idealMsg.setText ("");
        idealWeightPreMsg.setVisibility(View.INVISIBLE);
        txtHeight.setText ("");
        txtWeight.setText ("");
        
        return;
    }
}