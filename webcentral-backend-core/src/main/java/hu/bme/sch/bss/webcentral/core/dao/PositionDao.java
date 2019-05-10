package hu.bme.sch.bss.webcentral.core.dao;

import hu.bme.sch.bss.webcentral.core.model.Position;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface PositionDao extends JpaRepository<Position, Long> {
    String FIND_BY_NAME_QUERY = "SELECT position FROM Position position WHERE position.name = :name";

    @Nullable
    @Query(FIND_BY_NAME_QUERY)
    Optional<Position> findByName(@Param("name") String name);
}
