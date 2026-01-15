package com.ismaildrcn.utils;

import com.github.slugify.Slugify;

public class SlugUtils {

    public static String generateSlug(String text) {
        Slugify slugify = Slugify.builder()
                .transliterator(true)
                .customReplacement("'", "")
                .build();
        return slugify.slugify(text);
    }

}
