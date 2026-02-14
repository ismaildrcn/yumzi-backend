package com.ismaildrcn.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
