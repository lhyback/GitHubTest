package com.wechatvr.training.model;

public class People {
    private Animal pet;
    
    public Animal getPet() {
		return pet;
	}

	public void setPet(Animal pet) {
		this.pet = pet;
	}
    //@Test
	public void feedPet() {
    		pet.eat();
    }
}
