package com.alternova.streaming.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alternova.streaming.persistence.entity.StreamingContent;

@Repository
public interface StreamingRepository extends JpaRepository<StreamingContent, Long> {

    @Query("SELECT s FROM StreamingContent s "
            + "WHERE s.type = :type "
            + "ORDER BY RANDOM() LIMIT 1")
    StreamingContent findRandomByType(String type);

    List<StreamingContent> findAllByType(String type, Sort sort);

}
