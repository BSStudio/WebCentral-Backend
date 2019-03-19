package hu.bme.sch.bss.webcentral.core.dao;

import hu.bme.sch.bss.webcentral.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

}
