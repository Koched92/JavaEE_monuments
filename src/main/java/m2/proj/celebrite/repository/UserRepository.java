package m2.proj.celebrite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m2.proj.celebrite.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
