package com.github.telvarost.finalbeta;

import java.util.HashMap;

public class ModHelper {

    public static float lerp(float delta, float start, float end) {
        return start + delta * (end - start);
    }

    public static float clamp(float val, float min, float max) {
        return val < min ? min : Math.min(val, max);
    }

    public static class ModHelperFields {
        public static Boolean IS_LAVA_BUCKET_CONSUMED = false;
    }
}
