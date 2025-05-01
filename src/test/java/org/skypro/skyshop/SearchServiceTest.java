package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void search_noItems() {
        when(storageService.allProductsAndArticle()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("пряник");
        assertTrue(results.isEmpty());
    }

    @Test
    void search_noMatch() {
        List<Searchable> searchableList = new ArrayList<>();
        searchableList.add(new SimpleProduct("печенье", 100));
        when(storageService.allProductsAndArticle()).thenReturn(searchableList);
        List<SearchResult> results = searchService.search("пряник");
        assertTrue(results.isEmpty());
    }

    @Test
    void search_withMatch() {
        List<Searchable> searchableList = new ArrayList<>();
        searchableList.add(new SimpleProduct("пряник", 100));
        when(storageService.allProductsAndArticle()).thenReturn(searchableList);
        List<SearchResult> results = searchService.search("пряник");

        assertEquals(1, results.size());
        assertEquals("пряник", results.get(0).getName());
    }
}
