package com.alternova.streaming.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alternova.streaming.persistence.entity.StreamingMetadata;

@Repository
public interface StreamingMetadataRepository extends JpaRepository<StreamingMetadata, UUID> {

    @Query(value = "SELECT AVG(sm.score) "
            + "FROM StreamingMetadata sm "
            + "WHERE sm.streamingContent.id = :contentId and sm.score > 0")
    Optional<Float> averageScoreByContentId(Long contentId);

    @Query(value = "SELECT COUNT(sm.id) "
            + "FROM StreamingMetadata sm "
            + "WHERE sm.streamingContent.id = :contentId and sm.viewed = true")
    Optional<Long> countViewsByContentId(Long contentId);

    Optional<StreamingMetadata> findByUserIdAndStreamingContentId(Long userId, Long contentId);

}
