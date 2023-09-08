package com.doubantop.mapper;

import com.doubantop.pojo.Movie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MovieMapper {
    @Select("select * from movies")
    List<Movie> all();

    @Insert("insert into movies(id,ranking,movie_name,director,actor,release_date,country,movie_type,rating,comment,detail_link,cover_link) values(#{id},#{ranking},#{movie_name},#{director},#{actor},#{release_date},#{country},#{movie_type},#{rating},#{comment},#{detail_link},#{cover_link})")
    void insert(Movie movie);
}
