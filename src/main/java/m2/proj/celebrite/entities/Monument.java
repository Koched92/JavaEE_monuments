package m2.proj.celebrite.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Monument{
	@Id
	private String codeM;
	private String nomM;
	private String proprietaire;
	private String typeMonument;
	private Float longitude;
	private Float latitude;
	public String codeLieu;
	@ManyToMany(mappedBy = "monuments")
	private List<Celebrite> celebrites;
	
	@ManyToOne 
	public Lieu lieu;

	public String getCodeM() {
		return codeM;
	}


	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}


	public String getNomM() {
		return nomM;
	}


	public void setNomM(String nomM) {
		this.nomM = nomM;
	}


	public String getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}


	public String getTypeMonument() {
		return typeMonument;
	}


	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}


	public Float getLongitude() {
		return longitude;
	}


	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}


	public Float getLatitude() {
		return latitude;
	}


	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}


	
	}
	
	
	
	


