package com.example.demoUni.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Kerkesa {
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
	
	@Column(name="kategori")
	private String kategori;
	
	@Column(name="dega")
	private String dega;
	
	@Column(name="derguar")
	private boolean derguar;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dokumenti", referencedColumnName = "id")
    private Doc file;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false,
			cascade= {CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(nullable=false, name = "id_student")
	private Student student ;

	
	
	
	
	
	
	public Kerkesa() {
		
	}
	
	
	
	public Kerkesa(long id, String firstname, String lastname, String email, String kategori, String dega,
			boolean derguar) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.kategori = kategori;
		this.dega = dega;
		this.derguar = derguar;
	}



	public Kerkesa(long id, String firstname, String lastname, String email, String kategori, String dega,
			boolean derguar, Student student) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.kategori = kategori;
		this.dega = dega;
		this.derguar = derguar;
		this.student = student;
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

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getDega() {
		return dega;
	}

	public void setDega(String dega) {
		this.dega = dega;
	}

	public boolean isDerguar() {
		return derguar;
	}

	public void setDerguar(boolean derguar) {
		this.derguar = derguar;
	}

	public Doc getFile() {
		return file;
	}

	public void setFile(Doc file) {
		this.file = file;
	}



	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}

	 
	
	
}
