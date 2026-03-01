package com.ismaildrcn.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildrcn.controller.IRecentSearchController;
import com.ismaildrcn.controller.RestBaseController;
import com.ismaildrcn.controller.RootEntity;
import com.ismaildrcn.model.dto.DtoRecentSearchResponse;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.service.IRecentSearchService;

@RestController
@RequestMapping("/rest/api/search")
public class RecentSearchControllerImpl extends RestBaseController implements IRecentSearchController {

    @Autowired
    private IRecentSearchService service;

    @Override
    @GetMapping("/auto-complete")
    public RootEntity<DtoRecentSearchResponse> autoCompleteSearch(
            @AuthenticationPrincipal User user,
            @RequestParam String keyword) {
        return ok(service.autoCompleteSearch(user, keyword));
    }

    @Override
    @GetMapping("/recent")
    public RootEntity<List<DtoRecentSearchResponse>> getRecentSearches(
            @AuthenticationPrincipal User user) {
        return ok(service.getRecentSearches(user));
    }

}
