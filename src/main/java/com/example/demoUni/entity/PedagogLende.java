package com.example.demoUni.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class PedagogLende  implements Serializable {

	@Id
    @ManyToOne
    @JoinColumn
    private Pedagog pedagog;

    @Id
    @ManyToOne
    @JoinColumn
    public Lenda lenda;

    public PedagogLende() {
    	
    }
	public PedagogLende(Pedagog pedagog, Lenda lenda) {
		super();
		this.pedagog = pedagog;
		this.lenda = lenda;
	}
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
