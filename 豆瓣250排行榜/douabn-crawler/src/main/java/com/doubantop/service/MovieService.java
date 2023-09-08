package com.doubantop.service;

import com.doubantop.pojo.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    void crawlerAll();
}
