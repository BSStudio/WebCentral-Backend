package hu.bme.sch.bss.webcentral.videoportal.dao;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoDao extends JpaRepository<Video, Long> {

    String LIST_PUBLISHED_NOT_ARCHIVED_QUERY =
            "SELECT vid FROM Video vid WHERE vid.visible=true and vid.archived=false";
    String LIST_NOT_ARCHIVED_QUERY = "SELECT vid FROM Video vid WHERE vid.archived=false";
    String LIST_ARCHIVED_QUERY = "SELECT vid FROM Video vid WHERE vid.archived=true";

    @Nullable
    @Query(LIST_PUBLISHED_NOT_ARCHIVED_QUERY)
    List<Video> findAllPublished();

    @Nullable
    @Query(LIST_NOT_ARCHIVED_QUERY)
    List<Video> findAll();

    @Nullable
    @Query(LIST_ARCHIVED_QUERY)
    List<Video> findAllArchived();
}
