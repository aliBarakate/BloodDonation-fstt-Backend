package fstt.bloodDonation.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Donneur")
public class Donneur {
	@Id
	private int id;

	private String nom;
	private String prenom;
	
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
}
