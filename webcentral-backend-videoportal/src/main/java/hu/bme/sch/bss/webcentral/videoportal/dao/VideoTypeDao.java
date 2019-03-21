package hu.bme.sch.bss.webcentral.videoportal.dao;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface VideoTypeDao extends JpaRepository<VideoType, Long> {
    String FIND_BY_CANONICAL_NAME_QUERY = "SELECT type FROM VideoType type WHERE type.canonicalName = :canonicalName";

    @Nullable
    @Query(value = FIND_BY_CANONICAL_NAME_QUERY)
    Optional<VideoType> findByCanonicalName(@Param("canonicalName") String canonicalName);
}
