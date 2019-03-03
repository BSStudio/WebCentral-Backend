package hu.bme.sch.bss.webcentral.videoportal.dao;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoDao extends JpaRepository<Video, Long> {

    @Query("SELECT vid FROM Video vid WHERE vid.visible=true")
    List<Video> findAllPublished();
}
