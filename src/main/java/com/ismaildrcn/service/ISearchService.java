package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.User;

public interface ISearchService {

    DtoSearchResponse search(User user, String keyword);

}
