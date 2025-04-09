package org.skypro.skyshop.model.search;

import org.skypro.skyshop.model.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String searchTerm) {
        return storageService.allProductsAndArticle().stream()
                .filter(obj -> obj.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
