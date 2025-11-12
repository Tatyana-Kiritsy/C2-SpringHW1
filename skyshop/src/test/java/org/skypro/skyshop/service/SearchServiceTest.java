package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void search_WhenStorageIsEmpty_ThenReturnEmptyList() {
        String keyWord = "test";
        when(storageService.getAllSearchables()).thenReturn(new ArrayList<>());

        Collection<SearchResult> result = searchService.search(keyWord);

        assertThat(result).isEmpty();

        verify(storageService, times(1)).getAllSearchables();

    }

    @Test
    void search_WhenProductByKeyWordIsFound() {
        String keyWord = "test";
        SimpleProduct product = new SimpleProduct(keyWord, UUID.randomUUID(), 100);
        when(storageService.getAllSearchables()).thenReturn(List.of(product));

        Collection<SearchResult> result = searchService.search(keyWord);

        assertThat(result)
                .isNotNull()
                .singleElement()
                .extracting(
                        s -> s.getId(),
                        d -> d.getName(),
                        j -> j.getContentType()
                )
                .containsExactly(product.getId().toString(), product.getProductName(), product.getContentType());

        verify(storageService).getAllSearchables();
        verifyNoMoreInteractions(storageService);
    }

    @Test
    void search_WhenProductByKeyWordIsNotFound() {
        String keyWord = "test";
        when(storageService.getAllSearchables()).thenReturn(List.of(new SimpleProduct("grapes",
                UUID.randomUUID(), 100), new SimpleProduct("tomato",
                UUID.randomUUID(), 100), new SimpleProduct("eggs",
                UUID.randomUUID(), 100)));

        Collection<SearchResult> result = searchService.search(keyWord);

        assertThat(result).isEmpty();

        verify(storageService).getAllSearchables();
    }

}