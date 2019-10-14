package hu.bme.sch.bss.webcentral.core.dao;

import hu.bme.sch.bss.webcentral.core.model.Status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface StatusDao extends JpaRepository<Status, Long> {

    String FIND_BY_NAME_QUERY = "SELECT status FROM Status status WHERE status.name = :name";

    @Query(FIND_BY_NAME_QUERY)
    Optional<Status> findByName(@Param("name") String name);
}
