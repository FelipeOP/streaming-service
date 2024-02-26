package com.alternova.streaming.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alternova.streaming.dto.StreamingContentDto;
import com.alternova.streaming.persistence.constants.StreamingType;
import com.alternova.streaming.persistence.entity.StreamingContent;

@Repository
public interface StreamingRepository extends JpaRepository<StreamingContent, Long> {

    @Query(value = "SELECT new com.alternova.streaming.dto.StreamingContentDto"
            + "(sc.id, sc.name, sc.genre, sc.type, sc.views, sc.rating) "
            + "FROM StreamingContent sc "
            + "WHERE sc.type = :type "
            + "ORDER BY RANDOM() LIMIT 1")
    StreamingContentDto selectRandomContentByType(@Param("type") StreamingType type);

    List<StreamingContent> findAllByType(StreamingType type, Sort sort);

}
