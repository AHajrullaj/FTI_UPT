package com.example.demoUni.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
 

@Entity
public class DekaniSekretare implements Serializable {
	@Id
    @ManyToOne
    @JoinColumn
    private Dekani dekani;

    @Id
    @ManyToOne
    @JoinColumn
    private Sekretari sekretari;
    
    
    private Boolean konfirmim;
    
    public DekaniSekretare() {
    	
    }
    
    
	/*
	 * ne vend te course do jete
	 * 
	 * @Override public boolean equals(Object o) { if (this == o) return true; if
	 * (!(o instanceof DekaniSekretare)) return false; DekaniSekretare that =
	 * (DekaniSekretare) o; return Objects.equals(dekani.getFirstname(),
	 * that.sekretari.getFirstname()) && Objects.equals(course.getName(),
	 * that.course.getName()) && Objects.equals(paid, that.paid); }
	 * 
	 * @Override public int hashCode() { return
	 * Objects.hash(sekretari.getFirstname(), course.getName(), paid); }
	 */
}
