package com.alternova.streaming.dto;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long contentId;

    private Long userId;

    @Range(min = 0, max = 5)
    private int score;

    private boolean viewed;

}
