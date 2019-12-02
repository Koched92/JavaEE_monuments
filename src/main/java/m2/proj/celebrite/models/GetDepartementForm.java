package m2.proj.celebrite.models;

import m2.proj.celebrite.entities.Departement;

public class GetDepartementForm {
	private String numDep;
	private Departement departement;
	
	public String getNumDep() {
		return numDep;
	}
	public void setNumDep(String numDep) {
		this.numDep = numDep;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
}


