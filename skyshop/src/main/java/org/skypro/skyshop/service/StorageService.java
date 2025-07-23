package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    private void addToStorage() {
        products.put(UUID.randomUUID(), new SimpleProduct("tomatoes", UUID.randomUUID(), 575));
        products.put(UUID.randomUUID(), new DiscountedProduct("peaches", UUID.randomUUID(), 575, 10));
        products.put(UUID.randomUUID(), new FixPriceProduct("cucumbers", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Easter Eggs", "For porridge and " +
                "salad: oils!", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Extra sweet sugar!", "To give taste " +
                "without sugar!", UUID.randomUUID()));
    }

    public StorageService(Map<UUID, Product> products, Map<UUID, Article> articles) {
        this.products = products;
        this.articles = articles;
        this.addToStorage();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Collection<Searchable> getAllSearchables() {
        Set<Searchable> searchables = new HashSet<>();
        searchables.addAll(getProducts().values());
        searchables.addAll(getArticles().values());
        return searchables;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
