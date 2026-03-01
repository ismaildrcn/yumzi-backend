package com.ismaildrcn.service;

import java.util.List;

import com.ismaildrcn.model.dto.DtoRecentSearchResponse;
import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.User;

public interface IRecentSearchService {

    void addRecentSearch(User user, DtoSearchResponse response);

    DtoRecentSearchResponse autoCompleteSearch(User user, String keyword);

    List<DtoRecentSearchResponse> getRecentSearches(User user);
}
