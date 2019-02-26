package hu.bme.sch.bss.webcentral.dao;

import hu.bme.sch.bss.webcentral.model.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VideoDao extends JpaRepository<Video, Long> {
}
