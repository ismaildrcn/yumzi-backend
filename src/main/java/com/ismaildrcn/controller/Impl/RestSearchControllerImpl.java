package com.ismaildrcn.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRestSearchController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.service.ISearchService;

@RestController
@RequestMapping("/rest/api")
public class RestSearchControllerImpl extends RestBaseController implements IRestSearchController {

    @Autowired
    private ISearchService searchService;

    @Override
    @GetMapping("/search")
    public RootEntity<DtoSearchResponse> search(@RequestParam String keyword) {
        return ok(searchService.search(keyword));
    }

}
