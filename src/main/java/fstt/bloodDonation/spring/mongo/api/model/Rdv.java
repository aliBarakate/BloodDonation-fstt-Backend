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

	
}
