package com.thoughtworks.apidoc.resources;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DefaultQueryParamsTest {

    @Test
    public void shouldConvertSingleItemInSortToKeyList() {
        DefaultQueryParams params = new DefaultQueryParams();
        params.setSort("foo");

        List<SortKey> sortKeys = params.getSortKeys();

        assertThat(sortKeys.size(), is(1));
        SortKey sortKey = sortKeys.get(0);
        assertThat(sortKey.key, is("foo"));
        assertThat(sortKey.ascending, is(true));
    }

    @Test
    public void shouldMarkKeyBeginningWithMinusAsDescendingOrder() {
        DefaultQueryParams params = new DefaultQueryParams();
        params.setSort("-foo");

        List<SortKey> sortKeys = params.getSortKeys();

        assertThat(sortKeys.size(), is(1));
        SortKey sortKey = sortKeys.get(0);
        assertThat(sortKey.key, is("foo"));
        assertThat(sortKey.ascending, is(false));
    }

    @Test
    public void shouldConvertCommaSeparatedSortStringToList() {
        DefaultQueryParams params = new DefaultQueryParams();
        params.setSort("foo,-bar,baz");
        List<SortKey> expected = Arrays.asList(new SortKey("foo", true), new SortKey("bar", false), new SortKey("baz", true));

        List<SortKey> sortKeys = params.getSortKeys();

        assertThat(sortKeys, CoreMatchers.equalTo(expected));

    }

}