package com.example.demoUni.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Mesazh {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="email")
	public String email;
	
	@Column(name="emailM")
	private String emailM;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="title")
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate dtDerg;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	public Mesazh() {
		
	}
	
	

	public Mesazh(long id, String email, String emailM, String subject, String title, LocalDate dtDerg) {
		super();
		this.id = id;
		this.email = email;
		this.emailM = emailM;
		this.subject = subject;
		this.title = title;
		this.dtDerg = dtDerg;
	}


	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailD() {
		return email;
	}

	public void setEmailD(String emailD) {
		this.email = emailD;
	}

	public String getEmailM() {
		return emailM;
	}

	public void setEmailM(String emailM) {
		this.emailM = emailM;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDtDerg() {
		return dtDerg;
	}

	public void setDtDerg(LocalDate dtDerg) {
		this.dtDerg = dtDerg;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
