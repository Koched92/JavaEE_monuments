package m2.proj.celebrite.dao;
import java.util.List;

import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.entities.Monument;
import m2.proj.celebrite.entities.Departement;
import m2.proj.celebrite.entities.User;

public interface IDao {
	
	public List<Celebrite> listAllCelebrity();
	
	public void addCelebrity (Celebrite celebrite);
	
	public void updateCelebrity (Celebrite celebrite);
	
	public void deleteCelebrity (int numCelebrite);
	
	public Celebrite findCelebrityByNum (int numCelebrite);
	
	public List<Monument> getListMonumentsByLieu(String codeLieu );
	public List<Departement> getListDepartements();
	public List<Monument> getListMonuments();
	public Monument findMonumentByCodeM (String codeM);
	//////////////////////LOGIN//////////////////////////
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	
	
	
	
}