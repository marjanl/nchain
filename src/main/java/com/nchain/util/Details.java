package com.nchain.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Details {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Integer ratings;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Double score;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Integer size;
}
