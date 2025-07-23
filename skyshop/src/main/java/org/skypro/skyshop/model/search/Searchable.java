package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String getSearchedTerm();

    String getContentType();

    String getName();

    UUID getId();

    default void getStringRepresentation() {
        System.out.println("Наименование: " + getSearchedTerm() + " тип:" + getContentType());
    }
}
