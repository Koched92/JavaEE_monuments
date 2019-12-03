package m2.proj.celebrite.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2.proj.celebrite.dao.IDao;
import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.entities.Departement;
import m2.proj.celebrite.entities.Monument;
import m2.proj.celebrite.entities.User;

@Service
public class ProjMetier implements IMetier {
	
	private IDao dao;
	@Autowired
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	
	public List<Celebrite> listAllCelebrity() {
		return dao.listAllCelebrity();
	}

	public void addCelebrity(Celebrite celebrite) {
		 dao.addCelebrity(celebrite);
		
	}

	public void updateCelebrity(Celebrite celebrite) {
		dao.updateCelebrity(celebrite);
	}

	
	public void deleteCelebrity(int numCelebrite) {
		dao.deleteCelebrity(numCelebrite);
	}

	public Celebrite findCelebrityByNum(int numCelebrite) {
		return dao.findCelebrityByNum(numCelebrite);
	}

	@Override
	public void saveUser(User user) {
		 dao.saveUser(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return false;
	}

}
