package com.ismaildrcn.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoRecentSearchResponse {

    private UUID uniqueId;

    private String keyword;

    private Integer searchCount;

}
