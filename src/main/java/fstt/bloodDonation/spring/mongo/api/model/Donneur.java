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

@Document(collection = "Donneur")
public class Donneur {
	@Transient
	public static final String SEQUENCE_NAME="donneur_seq";
	
	@Id
	private int id;

	private String nom;
	private String prenom;
	
	private String cin;
	private String genre;
	private String ville;
	private String groupeSanguin;
	private String tel;
	private String mail;
	private int compteur;
	private int actif;
	private Date date_derniere_donation;
	private String adresse; 
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getGroupeSanguin() {
		return groupeSanguin;
	}
	public void setGroupeSanguin(String groupeSanguin) {
		this.groupeSanguin = groupeSanguin;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCompteur() {
		return compteur;
	}
	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String bookName) {
		this.nom = bookName;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String authorName) {
		this.prenom = authorName;
	}
	public int getActif() {
		return actif;
	}
	public void setActif(int actif) {
		this.actif = actif;
	}
	public Date getDate_derniere_donation() {
		return date_derniere_donation;
	}
	public void setDate_derniere_donation(Date date_derniere_donation) {
		this.date_derniere_donation = date_derniere_donation;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}
