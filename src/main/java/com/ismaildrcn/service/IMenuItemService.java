package com.ismaildrcn.service;

import com.ismaildrcn.model.dto.DtoMenuItemRequest;
import com.ismaildrcn.model.dto.DtoMenuItemResponse;

public interface IMenuItemService {

    DtoMenuItemResponse saveMenuItem(DtoMenuItemRequest request);

}
