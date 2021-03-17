package com.example.demoUni.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

 

@Entity
public class Lenda {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@OrderBy("id DESC")
	private long id;
	
	@Column(name="emri")
	private String emri;
	
	@Column(name="kredite")
	private int kredite;
	
	@Column(name="simestri")
	private int simestri;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false,
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(nullable=false, name = "id_dege")
	private Dega dega;
	
	 
	
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH},mappedBy="lenda")
	private List<LENDEstudent> lendStudent;
	
	
	public Lenda () {
		
	}

	public Lenda(long id, String emri, int kredite) {
		super();
		this.id = id;
		this.emri = emri;
		this.kredite = kredite;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public int getKredite() {
		return kredite;
	}

	public void setKredite(int kredite) {
		this.kredite = kredite;
	}

	public Dega getDega() {
		return dega;
	}

	public void setDega(Dega dega) {
		this.dega = dega;
	}

	public List<LENDEstudent> getLendStudent() {
		return lendStudent;
	}

	public void setLendStudent(List<LENDEstudent> lendStudent) {
		this.lendStudent = lendStudent;
	}

	public int getSimestri() {
		return simestri;
	}

	public void setSimestri(int simestri) {
		this.simestri = simestri;
	}

 
	
	
}
