package com.thoughtworks.apidoc.resources;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultQueryParams {
    private static final int PER_PAGE_DEFAULT = 5;
    private String sort;
    private int page = 1;
    private int perPage = PER_PAGE_DEFAULT;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    @Override
    public String toString() {
        return "DefaultQueryParams{" +
                "sort='" + sort + '\'' +
                ", page=" + page +
                ", perPage=" + perPage +
                '}';
    }

    public List<SortKey> getSortKeys() {
        if (sort == null) { return Collections.emptyList(); }

        String[] keys = sort.split(",");
        return Arrays.stream(keys).map(SortKey::fromString).collect(Collectors.toList());
    }
}
