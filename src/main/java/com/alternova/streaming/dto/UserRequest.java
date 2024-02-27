package com.alternova.streaming.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull
    private Long contentId;

    @NotNull
    private Long userId;

    @Range(min = 0, max = 5)
    private int score;

    @NotNull
    private boolean viewed;

}
