package com.nchain.util;

import com.nchain.exception.ScoreInvalidException;

public class ScoreValidator {

    public static void checkScore(Double s) throws ScoreInvalidException {
        if(s.doubleValue() < 1 || s.doubleValue() > 5){
            throw new ScoreInvalidException("Score invalid");
        }
    }
}
