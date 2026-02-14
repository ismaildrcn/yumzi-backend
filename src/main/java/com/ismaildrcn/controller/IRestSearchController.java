package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.User;

public interface IRestSearchController {

    RootEntity<DtoSearchResponse> search(User user, String keyword);

}
