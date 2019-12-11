package m2.proj.celebrite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import m2.proj.celebrite.entities.Celebrite;
import m2.proj.celebrite.entities.Monument;
import m2.proj.celebrite.entities.Departement;
import m2.proj.celebrite.entities.Role;
import m2.proj.celebrite.entities.User;
import m2.proj.celebrite.repository.RoleRepository;
import m2.proj.celebrite.repository.UserRepository;

@Repository
public class ProjDao implements IDao {
	@PersistenceContext
	private EntityManager em; 
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Celebrite> listAllCelebrity() {
		String sql = "SELECT num_celebrite, epoque, nationalite, nom, prenom FROM celebrite";
		List<Celebrite> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new CelebrityMapper());
		return list;
	}
	
	private SqlParameterSource getSqlParameterByModel(Celebrite celebrite) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if(celebrite != null) {
			paramSource.addValue("num_celebrite", celebrite.getNumCelebrite());
			paramSource.addValue("epoque", celebrite.getEpoque());
			paramSource.addValue("nationalite", celebrite.getNationalite());
			paramSource.addValue("nom", celebrite.getNom());
			paramSource.addValue("prenom", celebrite.getPrenom());
		}
		return paramSource;
	}
	
	private static final class CelebrityMapper implements RowMapper<Celebrite>{
		public Celebrite mapRow(ResultSet rs, int RowNum) throws SQLException{
			Celebrite celebrite = new Celebrite();
			celebrite.setNumCelebrite(rs.getInt("num_celebrite"));
			celebrite.setEpoque(rs.getString("epoque"));
			celebrite.setNationalite(rs.getString("nationalite"));
			celebrite.setNom(rs.getString("nom"));
			celebrite.setPrenom(rs.getString("prenom"));
			return celebrite;

		}
	}
	
	public void addCelebrity(Celebrite celebrite) {
		String sql = "INSERT INTO celebrite(num_celebrite, epoque, nationalite, nom, prenom) VALUES (:num_celebrite, :epoque, :nationalite, :nom, :prenom)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(celebrite));
		
	}

	public void updateCelebrity(Celebrite celebrite) {
		String sql = "UPDATE celebrite SET epoque  = :epoque, nationalite = :nationalite, nom = :nom,  prenom = :prenom WHERE num_celebrite = :num_celebrite";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(celebrite));

	}

	public void deleteCelebrity(int numCelebrite) {
		String sql = "DELETE FROM celebrite WHERE num_celebrite = :num_celebrite";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new Celebrite(numCelebrite, sql, sql, sql, sql)));
	}

	public Celebrite findCelebrityByNum(int numCelebrite) {
		String sql = "SELECT * FROM celebrite WHERE num_celebrite = :num_celebrite";
			return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(new Celebrite(numCelebrite, sql, sql, sql, sql)), new CelebrityMapper());
	}


	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return false;
	}

	@Override
	public List<Monument> getListMonumentsByLieu(String nomCom) {
		Query req = em.createQuery("select m from lieu m where m.lieu.nomCom =:x");
		req.setParameter("x", nomCom);
		return req.getResultList();
	}
	
	
	public List<Departement> getListDepartements() {
		Query req = em.createQuery("select d from Departement d"); // JPQL
		return req.getResultList();
	}

	@Override
	public List<Monument> getListMonuments() {
		Query req = em.createQuery("select m from Monument m"); // JPQL
		return req.getResultList();
	}

	@Override
	public Monument findMonumentByCodeM(String codeM) {
		Monument monument = em.find(Monument.class, codeM);
		return monument;
	
		
	}

}














