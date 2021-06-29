package com.handira.librarify.tool;
import java.lang.Math;

public class RandomIdGenerator {
    public static int idMin = 123200001;
    public static int idMax = 123200999;
    public static int getRandom() {
        int randomId = (int)Math.floor(Math.random()*(idMax-idMin+1)+idMin);
        return randomId;
    }
}
