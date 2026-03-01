package com.ismaildrcn.controller;

import java.util.List;

import com.ismaildrcn.model.dto.DtoRecentSearchResponse;
import com.ismaildrcn.model.entity.User;

public interface IRecentSearchController {

    RootEntity<DtoRecentSearchResponse> autoCompleteSearch(User user, String keyword);

    RootEntity<List<DtoRecentSearchResponse>> getRecentSearches(User user);

}
