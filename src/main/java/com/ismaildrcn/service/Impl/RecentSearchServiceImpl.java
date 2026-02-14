package com.ismaildrcn.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildrcn.model.dto.DtoRecentSearchResponse;
import com.ismaildrcn.model.dto.DtoSearchResponse;
import com.ismaildrcn.model.entity.RecentSearch;
import com.ismaildrcn.model.entity.User;
import com.ismaildrcn.repository.RecentSearchRepository;
import com.ismaildrcn.service.IRecentSearchService;

@Service
public class RecentSearchServiceImpl implements IRecentSearchService {

    @Autowired
    private RecentSearchRepository repo;

    @Override
    public DtoRecentSearchResponse autoCompleteSearch(User user, String keyword) {
        List<RecentSearch> autoCompleteList = repo.findByKeywordStartingWithIgnoreCaseAndUserId(keyword, user.getId());
        DtoRecentSearchResponse response = new DtoRecentSearchResponse();
        if (!autoCompleteList.isEmpty()) {
            BeanUtils.copyProperties(autoCompleteList.get(0), response);
            return response;
        }
        return null;
    }

    @Override
    public void addRecentSearch(User user, DtoSearchResponse response) {
        RecentSearch sameRecentSearch = repo.findByKeywordAndUserId(response.getKeyword(), user.getId());
        if (sameRecentSearch != null) {
            sameRecentSearch.setSearchCount(sameRecentSearch.getSearchCount() + 1);
            repo.save(sameRecentSearch);
            return;
        }

        RecentSearch search = new RecentSearch();
        search.setKeyword(response.getKeyword());
        search.setUser(user);
        search.setResultCount(response.getRestaurants().size() + response.getMenuItems().size());
        repo.save(search);
    }

    @Override
    public List<DtoRecentSearchResponse> getRecentSearches(User user) {
        List<RecentSearch> recentList = repo.findTopSearchKeywordsByUser(user.getId(), 8);
        List<DtoRecentSearchResponse> responseList = new ArrayList<>();

        for (RecentSearch recentSearch : recentList) {
            DtoRecentSearchResponse response = new DtoRecentSearchResponse();
            BeanUtils.copyProperties(recentSearch, response);
            responseList.add(response);
        }
        return responseList;
    }

}
