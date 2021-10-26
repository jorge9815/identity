package com.identity.shared;

public class Pagination {
    public static int  makeOffset(int page, int perPage) {
        return ((page * perPage) - perPage);
    }
}
