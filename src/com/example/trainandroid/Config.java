package com.example.trainandroid;

public class Config {

	public static int currentSample = 0;
	public static boolean processing;
	public static int sampleRate = 1000; //millisecond
	public static int startTrainTime = 20 * (1000/sampleRate);
	public static int stopTrainTime = 100 * (1000/sampleRate);
	public static int DUT = 1; //1=Nexus S, 2=S4
	
}
