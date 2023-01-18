package com.nchain.util;


import com.nchain.exception.ScoreInvalidException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddScore {
    int id;
    Double score;

    public void setScore(Double s) throws ScoreInvalidException {
        ScoreValidator.checkScore(s);
        score = s;
    }
}
