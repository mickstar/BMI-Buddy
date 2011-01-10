package com.mickstarify.BMIBuddy;

//import android.util.Log;

public class BMI {
	
	public final int UNDER_WEIGHT 	= 0;
	public final int NORMAL 		= 1;
	public final int OBESE 			= 2;
	public final int VERY_OBESE 	= 3;
	public final int SEVERELY_OBESE	= 4;
	
	//private static final String TAG = "BMI";
	
	public double calculateBMI (double weight, double height, char units){
		boolean metric;
		if (units == 'm'){
			metric = true;			
		}
		else {
			metric = false;			
		}
		
		/*
		Log.v(TAG, "units = " + units);
		Log.v(TAG, "height = " + height);
		Log.v(TAG, "weight = " + weight);
		*/
		
		double BMI;
		if (metric){
			height = height / 100; //Convert to metres
			//Log.v(TAG, "height = " + height);
			BMI = weight / (height * height);			
		}
		else{
			BMI = weight / (height * height) * 703;
			
		}
		//Log.v(TAG, "" + BMI);
		return BMI;
	}
	
	public int whichRange (double BMI){
		if (BMI < 18.5){
			return UNDER_WEIGHT;			
		}
		else if (BMI >= 18.5 && BMI < 25){
			return NORMAL;			
		}
		else if (BMI >= 25 && BMI < 30){
			return OBESE;
		}
		else if (BMI >= 30 && BMI < 40){
			return VERY_OBESE;			
		}
		else if (BMI >= 40){
			return SEVERELY_OBESE;
		}
		//Should not reach here.
		return 99;
		
	}
	
	public String messageForBMI(double BMI){
		String msg;
		if (whichRange (BMI) == UNDER_WEIGHT){
			msg = "You are underweight!";			
		}
		else if (whichRange (BMI) == NORMAL){
			msg = "You are normal Weight";			
		}
		else if (whichRange (BMI) == OBESE){
			msg = "You are Obese";			
		}
		else if (whichRange (BMI) == VERY_OBESE){
			msg = "You are Very Obese";
		}
		else {
			msg = "You are Severely Obese!";
		}
		return msg;
	}
	public String idealWeight (double height,char units){
		String msg;
		String uWeight = "lbs";
		if (units == 'm'){
			uWeight = "kg";
		}
		double minWeight;
		double maxWeight;
		if (units == 'm'){
			height = height / 100; //Change to meters

			minWeight = 18.5 * (height * height);
			maxWeight = 25 	* (height * height);
		}
		else{
			minWeight = 18.5 * (height * height) / 703;
			maxWeight = 25 	* (height * height)  / 703;			
		}
		msg = String.format("%.2f - %.2f%s", minWeight, maxWeight, uWeight);
		
		return msg;
	}
}
