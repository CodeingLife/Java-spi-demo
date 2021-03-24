package com.kaixin.demo.service.impl;

import com.kaixin.demo.service.Search;

import java.util.List;

public class DatabaseSearch implements Search {
    @Override
    public List<String> search(String keyword) {
        System.out.println("now use database search. keyword:" + keyword);
        return null;
    }
}
