package com.example.demoUni.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
@Entity
public class LENDEstudent  implements Serializable {

 
		
		@Id
	    @ManyToOne
	    @JoinColumn
	    private Student student;

	    @Id
	    @ManyToOne
	    @JoinColumn
	    public Lenda lenda;
	    
	    
	    private String nota;


	    public LENDEstudent(){
	    	
	    }
	    
	    
	    
		public LENDEstudent(Student student, Lenda lenda, String nota) {
			super();
			this.student = student;
			this.lenda = lenda;
			this.nota = nota;
		}


		public Student getStudent() {
			return student;
		}


		public void setStudent(Student student) {
			this.student = student;
		}


		public Lenda getLenda() {
			return lenda;
		}


		public void setLenda(Lenda lenda) {
			this.lenda = lenda;
		}


		public String getNota() {
			return nota;
		}


		public void setNota(String nota) {
			this.nota = nota;
		}
	    
	    
}
