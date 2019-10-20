package hu.bme.sch.bss.webcentral.core.dao;

import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    String LIST_NOT_ARCHIVED_QUERY = "SELECT user FROM User user WHERE user.archived=false";
    String LIST_ARCHIVED_QUERY = "SELECT user FROM User user WHERE user.archived=true";

    @Nullable
    @Query(LIST_NOT_ARCHIVED_QUERY)
    List<User> findAllNotArchived();

    @Nullable
    @Query(LIST_ARCHIVED_QUERY)
    List<User> findAllArchived();
}
