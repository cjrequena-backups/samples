package com.design.patterns.structural.wrapper;
/**
 * we Adapter/Wrapper AcousticGuitar into 
     * ElectricAcousticGuitar to adapt into the GuitarModel
 */
public class ElectricAcousticGuitar extends Guitar {

	AcousticGuitar acoustic = new AcousticGuitar();
	ElectricGuitar electricGuitar = new ElectricGuitar();
	 
	public void onGuitar() {
		acoustic.play();
	}

	public void offGuitar() {
		acoustic.leaveGuitar();
		electricGuitar.offGuitar();
	}

}
