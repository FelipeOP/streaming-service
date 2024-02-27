package com.alternova.streaming.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alternova.streaming.dto.StreamingContentDto;
import com.alternova.streaming.dto.UserRequest;
import com.alternova.streaming.persistence.entity.StreamingContent;
import com.alternova.streaming.persistence.entity.StreamingMetadata;
import com.alternova.streaming.service.StreamingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping("/all")
    public ResponseEntity<List<StreamingContentDto>> findAllContent(
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok(streamingService.findAll(sort));
    }

    @GetMapping("/random-movie")
    public ResponseEntity<StreamingContentDto> getRandomMovie() {
        return ResponseEntity.ok(streamingService.getRandomMovie());
    }

    @GetMapping("/random-serie")
    public ResponseEntity<StreamingContentDto> getRandomSerie() {
        return ResponseEntity.ok(streamingService.getRandomSerie());
    }

    @GetMapping("/movies")
    public ResponseEntity<List<StreamingContentDto>> findAllMoviesSortBy(
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok(streamingService.findAllMoviesSortBy(sort));
    }

    @GetMapping("/series")
    public ResponseEntity<List<StreamingContentDto>> findAllSeriesSortBy(
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok(streamingService.findAllSeriesSortBy(sort));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StreamingContentDto>> findContentFilterBy(
            @RequestParam(defaultValue = "name", required = false) String field,
            @RequestParam(required = true) String value) {

        return ResponseEntity.ok(streamingService.findContentFilterBy(field, value));
    }

    @PostMapping("/mark")
    public ResponseEntity<StreamingContentDto> markAsViewed(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(streamingService.markAsViewed(request));
    }

    @PostMapping("/rate")
    public ResponseEntity<StreamingContentDto> rateContent(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(streamingService.rateContent(request));
    }

}
