package hu.bme.sch.bss.webcentral.core.dao;

import hu.bme.sch.bss.webcentral.core.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PositionDao extends JpaRepository<Position, Long> {
}
