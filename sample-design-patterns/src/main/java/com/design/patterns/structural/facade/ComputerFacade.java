package com.design.patterns.structural.facade;

public class ComputerFacade {
	 private CPU processor;
	    private Memory ram;
	    private HardDrive hd;
	 
	    public ComputerFacade() {
	        this.processor = new CPU();
	        this.ram = new Memory();
	        this.hd = new HardDrive();
	    }
	 
	    public void start() {
	        processor.freeze();
	        ram.load(1, hd.read(1, 1));
	        processor.jump(1);
	        processor.execute();
	    }
}
