package com.example.demoUni.entity;

import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

 





@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate dtRegjistrimi;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false,
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="id_dege",nullable = false)
	private Dega dega;
	
	
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.DETACH,CascadeType.REFRESH},mappedBy="student")
	public List<LENDEstudent> lendStudent;
	
	
	 @OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
				CascadeType.DETACH,CascadeType.REFRESH}) 
	 List <Kerkesa> kerkesa;
	 
	 
	 
	/*
	 * @Column(name="dtRegjistrimi") private String dtRegjistrimi;
	 */
	
	/*
	 * @OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
	 * CascadeType.DETACH,CascadeType.REFRESH},mappedBy="student") private
	 * List<LendeStudent> lendeStudent;
	 */
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(nullable=false, name = "id_dege") private Dega dega;
	 * 
	 * public List<LendeStudent> getLendeStudent() { return lendeStudent; }
	 * 
	 * 
	 * public void setLendeStudent(List<LendeStudent> lendeStudent) {
	 * this.lendeStudent = lendeStudent; }
	 */

	public Dega getDega() {
		return dega;
	}


	public void setDega(Dega dega) {
		this.dega = dega;
	}


	public Student() {}
	

	public Student(long id, String firstname, String lastname, String pass, LocalDate dtRegjistrimi) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pass = pass;
		this.dtRegjistrimi = dtRegjistrimi;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	


	public LocalDate getDtRegjistrimi() {
		return dtRegjistrimi;
	}


	public void setDtRegjistrimi(LocalDate dtRegjistrimi) {
		this.dtRegjistrimi = dtRegjistrimi;
	}


	public List<LENDEstudent> getLendStudent() {
		return lendStudent;
	}


	public void setLendStudent(List<LENDEstudent> lendStudent) {
		this.lendStudent = lendStudent;
	}


	public List<Kerkesa> getKerkesa() {
		return kerkesa;
	}


	public void setKerkesa(List<Kerkesa> kerkesa) {
		this.kerkesa = kerkesa;
	}

}