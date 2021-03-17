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
@Entity
public class Pedagog {
	
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

		@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
				CascadeType.DETACH,CascadeType.REFRESH},mappedBy="pedagog")
		public List<PedagogDege> dega;
		
		@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
				CascadeType.DETACH,CascadeType.REFRESH},mappedBy="pedagog")
		public List<PedagogLende> lende;
		
		
		
		
		
		
		public List<PedagogDege> getDega() {
			return dega;
		}

		public void setDega(List<PedagogDege> dega) {
			this.dega = dega;
		}

		public List<PedagogLende> getLende() {
			return lende;
		}

		public void setLende(List<PedagogLende> lende) {
			this.lende = lende;
		}

		public Pedagog() {
			
		}
		
		public Pedagog(long id, String firstname, String lastname, String email, String pass) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.pass = pass;
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


		 
		
		
		
}
