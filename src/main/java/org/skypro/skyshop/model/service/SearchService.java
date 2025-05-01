package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String searchTerm) {
        String decoderTerm = URLDecoder.decode(searchTerm, StandardCharsets.UTF_8);

        return storageService.allProductsAndArticle().stream()
                .filter(obj -> obj.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
