package com.example.demoUni.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class PedagogDege  implements Serializable {

	@Id
    @ManyToOne
    @JoinColumn
    private Pedagog pedagog;

    @Id
    @ManyToOne
    @JoinColumn
    public Dega dega;

    
    
    
    public PedagogDege() {
    	
    }
    
	public PedagogDege(Pedagog pedagog, Dega dega) {
		super();
		this.pedagog = pedagog;
		this.dega = dega;
	}

	public Pedagog getPedagog() {
		return pedagog;
	}

	public void setPedagog(Pedagog pedagog) {
		this.pedagog = pedagog;
	}

	public Dega getDega() {
		return dega;
	}

	public void setDega(Dega dega) {
		this.dega = dega;
	}
    
    
    
}
