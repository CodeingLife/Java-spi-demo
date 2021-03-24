package com.kaixin.demo.service.impl;

import com.kaixin.demo.service.Search;

import java.util.List;

public class FileSearch implements Search {
    @Override
    public List<String> search(String keyword) {
        System.out.println("now use file system search. keyword:" + keyword);
        return null;
    }
}
