package com.design.patterns.structural.wrapper;

public class ElectricGuitar extends Guitar {

	public void onGuitar() {
		System.out.println("Playing Guitar");
	}

	public void offGuitar() {
		System.out.println("I'm tired to play the guitar");
	}
}
