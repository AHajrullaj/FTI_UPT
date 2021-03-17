package com.example.demoUni.entity;

import java.io.Serializable;

public class RelationshipId implements Serializable {
	
	 private Pedagog pedagog;
	 public Lenda lenda;
	public Pedagog getPedagog() {
		return pedagog;
	}
	public void setPedagog(Pedagog pedagog) {
		this.pedagog = pedagog;
	}
	public Lenda getLenda() {
		return lenda;
	}
	public void setLenda(Lenda lenda) {
		this.lenda = lenda;
	}
	 
	 

}
