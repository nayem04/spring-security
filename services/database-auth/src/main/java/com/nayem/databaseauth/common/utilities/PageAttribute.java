package com.nayem.databaseauth.common.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageAttribute {
    private static final int PAGE_SIZE = 10;

    private static final String SORT_BY_FIELD_ID = "id";

    public static PageRequest getPageRequest(int page) {
        return PageRequest.of(Math.abs(page), PageAttribute.PAGE_SIZE, Sort.Direction.DESC, PageAttribute.SORT_BY_FIELD_ID);
    }

    public static PageRequest getPageRequest(int page, int pageSize) {
        if (Math.abs(pageSize) > 100)
            pageSize = 100;
        return PageRequest.of(Math.abs(page), Math.abs(pageSize), Sort.Direction.DESC, PageAttribute.SORT_BY_FIELD_ID);
    }

    public static PageRequest getPageRequestExact(int page, int pageSize) {
        return PageRequest.of(Math.abs(page), Math.abs(pageSize), Sort.Direction.DESC, PageAttribute.SORT_BY_FIELD_ID);
    }

    public static PageRequest getPageRequestWithSort(int page, int pageSize, Sort.Direction direction, String sortedFieldName) {
        if (Math.abs(pageSize) > 100)
            pageSize = 100;
        return PageRequest.of(Math.abs(page), Math.abs(pageSize), direction, sortedFieldName);
    }

    public static PageRequest getPageRequestExactWithSort(int page, int pageSize, Sort.Direction direction, String sortedFieldName) {
        return PageRequest.of(Math.abs(page), Math.abs(pageSize), direction, sortedFieldName);
    }
}
