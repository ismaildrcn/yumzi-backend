package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoSearchResponse;

public interface IRestSearchController {

    RootEntity<DtoSearchResponse> search(String keyword);

}
