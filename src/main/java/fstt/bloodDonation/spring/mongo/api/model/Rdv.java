package fstt.bloodDonation.spring.mongo.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Rdv")
public class Rdv {
	@Id
	private int id;
	private Date DateVaccin;
	private String Lieu;
	private Donneur donneur;
	private Receveur receveur;
	private Medecin medecin;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateVaccin() {
		return DateVaccin;
	}
	public void setDateVaccin(Date dateVaccin) {
		DateVaccin = dateVaccin;
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
	public Receveur getReceveur() {
		return receveur;
	}
	public void setReceveur(Receveur receveur) {
		this.receveur = receveur;
	}
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	
	
}
