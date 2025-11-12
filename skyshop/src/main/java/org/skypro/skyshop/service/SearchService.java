package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String keyWord) {
        return storageService.getAllSearchables().stream()
                .filter(x -> x.getSearchedTerm().contains(keyWord))
                .map(SearchResult::fromSearchable)
                .toList();
    }


}
