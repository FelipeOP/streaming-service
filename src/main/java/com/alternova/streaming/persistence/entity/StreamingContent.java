package com.alternova.streaming.persistence.entity;

import java.util.Set;
import java.util.UUID;

import com.alternova.streaming.persistence.constants.StreamingType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "streamings")
public class StreamingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String genre;

    private StreamingType type;

    private Long views;

    private Float rating;

    @OneToMany(mappedBy = "streamingContent")
    private Set<StreamingMetadata> streamingMetadata;

}
