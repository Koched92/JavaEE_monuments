package m2.proj.celebrite.dao;
import java.util.List;

import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.entities.User;

public interface IDao {
	
	public List<Celebrite> listAllCelebrity();
	
	public void addCelebrity (Celebrite celebrite);
	
	public void updateCelebrity (Celebrite celebrite);
	
	public void deleteCelebrity (int numCelebrite);
	
	public Celebrite findCelebrityByNum (int numCelebrite);
	
	//////////////////////LOGIN//////////////////////////
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	
	
	
	
}