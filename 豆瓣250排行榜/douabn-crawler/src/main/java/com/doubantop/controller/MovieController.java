package com.doubantop.controller;

import com.doubantop.service.impl.MovieServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;

    @CrossOrigin
    @GetMapping("/movies")
    @ResponseBody
    public String getMovies(){
        Gson gson =new Gson();
        return gson.toJson(movieService.findAll());
    }

    @CrossOrigin
    @PostMapping("/crawler")
    public String crawlerMovies(){
        movieService.crawlerAll();
        return "爬取完成";
    }
}
