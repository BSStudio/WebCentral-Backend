package hu.bme.sch.bss.webcentral.videoportal.dao;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface VideoTagDao extends JpaRepository<VideoTag, Long> {
    String FIND_BY_CANONICAL_NAME_QUERY = "SELECT tag FROM VideoTag tag WHERE tag.canonicalName = :canonicalName";

    @Nullable
    @Query(FIND_BY_CANONICAL_NAME_QUERY)
    Optional<VideoTag> findByCanonicalName(@Param("canonicalName") String canonicalName);
}
