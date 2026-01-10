package com.ismaildrcn.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpeningHours {
    private Map<String, DaySchedule> schedule;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DaySchedule {
        private String open;
        private String close;
        private boolean closed;
    }
}
