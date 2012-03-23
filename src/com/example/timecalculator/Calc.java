package com.example.timecalculator;

import java.util.Date;

public class Calc {
	
	public static String calculate(Date from, Date to) {
		int minutes  = (int)( (to.getTime()-from.getTime()) /(1000*60));
		return minutes + " minutes";
	}
}

