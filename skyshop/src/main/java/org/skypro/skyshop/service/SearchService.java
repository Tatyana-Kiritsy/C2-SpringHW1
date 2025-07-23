package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.skypro.skyshop.model.search.SearchResult.fromSearchable;

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
