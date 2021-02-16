package fstt.bloodDonation.spring.mongo.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Rdv")
public class Rdv {
	@Transient
	public static final String SEQUENCE_NAME="Rdv_seq";
	@Id
	private int id;
	private Date DateRdv;
	private String Lieu;
	private Donneur donneur;
	//private Medecin medecin;

	
	
	public Rdv(int id, Date dateRdv, String lieu, Donneur donneur) {
		super();
		this.id = id;
		DateRdv = dateRdv;
		Lieu = lieu;
		this.donneur = donneur;
	}



	public Rdv() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateRdv() {
		return DateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		DateRdv = dateRdv;
	}
	public String getLieu() {
		return Lieu;
	}
	public void setLieu(String lieu) {
		Lieu = lieu;
	}
	public Donneur getDonneur() {
		return donneur;
	}
	public void setDonneur(Donneur donneur) {
		this.donneur = donneur;
	}
	
	/*public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	*/	
	
}
