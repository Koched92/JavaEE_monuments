package m2.proj.celebrite.models;

import m2.proj.celebrite.entities.Celebrite;

public class GetCelebriteForm {
		private String nom;
		private Celebrite celebrite;
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public Celebrite getCelebrite() {
			return celebrite;
		}
		public void setCelebrite(Celebrite celebrite) {
			this.celebrite = celebrite;
		}
		

}
