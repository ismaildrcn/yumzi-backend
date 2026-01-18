package com.ismaildrcn.controller;

import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;

public interface IRestMenuItemController {

    RootEntity<DtoMenuItemResponse> saveMenuItem(DtoMenuItemRequest request);

}
