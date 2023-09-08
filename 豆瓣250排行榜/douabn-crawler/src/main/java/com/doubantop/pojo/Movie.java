package com.doubantop.pojo;

import lombok.Data;

@Data
public class Movie {
    private Integer id;
    private Integer ranking;
    private String movie_name;
    private String director;
    private String actor;
    private String release_date;
    private String country;
    private String movie_type;
    private double rating;
    private String comment;
    private String detail_link;
    private String cover_link;
}
