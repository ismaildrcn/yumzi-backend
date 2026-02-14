package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.User;

public interface IRecentSearchService {

    void addRecentSearch(User user, DtoSearchResponse response);

}
