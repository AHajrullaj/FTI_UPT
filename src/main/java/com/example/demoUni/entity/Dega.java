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
public class Dega {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@OrderBy("id DESC")
	private long id;
	
	@Column(name="emri")
	private String emri;
	
	@Column(name="viteStudimi")
	private int viteStudimi;
	
	/* @Column(name="profili") private String profili; */
	 @OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
				CascadeType.DETACH,CascadeType.REFRESH}) 
	 List <Lenda> lenda;
	 
	
	
	
	public Dega() {}

	public Dega(long id, String emri, int viteStudimi, String profili) {
		this.id = id;
		this.emri = emri;
		this.viteStudimi = viteStudimi;
		/* this.profili = profili; */
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

	public int getViteStudimi() {
		return viteStudimi;
	}

	public void setViteStudimi(int viteStudimi) {
		this.viteStudimi = viteStudimi;
	}

	public List<Lenda> getLenda() {
		return lenda;
	}

	public void setLenda(List<Lenda> lenda) {
		this.lenda = lenda;
	}

	

	/*
	 * public String getProfili() { return profili; }
	 * 
	 * public void setProfili(String profili) { this.profili = profili; }
	 */

	/*
	 * public List<Student> getStudents() { return students; }
	 * 
	 * public void setStudents(List<Student> students) { this.students = students; }
	 */
	
	
}
