package com.ismaildrcn.utils;

public class SlugUtils {

    public static String generateSlug(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("[^a-z0-9\\s-]", "");
        text = text.replaceAll("[\\s-]+", " ").trim();
        text = text.replaceAll("\\s", "-");
        return text;
    }

}
