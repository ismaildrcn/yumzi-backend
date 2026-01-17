package com.ismaildrcn.utils;

import java.util.Map;

public class SlugUtils {

    public static String generateSlug(String text) {
        text = text.toLowerCase();
        Map<String, String> charMap = Map.of(
                "ç", "c",
                "ğ", "g",
                "ı", "i",
                "ö", "o",
                "ü", "u",
                "ş", "s");
        for (Map.Entry<String, String> entry : charMap.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        text = text.replaceAll("[^a-z0-9\\s-]", "");
        text = text.replaceAll("[\\s-]+", " ").trim();
        text = text.replaceAll("\\s", "-");
        return text;

    }
}
