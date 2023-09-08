package com.doubantop.service.impl;

import com.doubantop.mapper.MovieMapper;
import com.doubantop.pojo.Movie;
import com.doubantop.service.MovieService;
import com.doubantop.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    @Override
    public void crawlerAll() {
        long start = System.currentTimeMillis();
        HttpUtils httpUtils = new HttpUtils();
        String URL = "https://movie.douban.com/top250?start=";
        for (int i = 0; i <= 250; i+=25) {
            String html = httpUtils.doGetHtml(URL+i);
            Document doc = Jsoup.parse(html);
            Elements lis = doc.select(".article .grid_view li");
            for (Element li:lis) {
                try {
                    Movie movie = new Movie();
                    //获取详情页链接
                    Element a = li.select(".item .pic a").first();
                    if (a != null) {
                        movie.setDetail_link(a.attr("href"));
                    }
                    //获取图片链接
                    Element img = li.select(".item .pic a img").first();
                    if (img != null) {
                        movie.setCover_link(img.attr("src"));
                    }
                    //获取电影名称
                    Elements nameSpans = li.select(".item .info .hd a span");
                    String name = "";
                    for (Element span : nameSpans) {
                        name = name.concat(span.text());
                    }
                    movie.setMovie_name(name);
                    //获取导演、主演、电影类型、国家、年份
                    Element p = li.select(".item .info .bd p").first();
                    if (p != null) {
                        String textP = p.text();
                        Pattern pattern = Pattern.compile("导演: (.*?) 主演: (.*?) (\\d{4}) / (.*?) / (.*)");
                        Matcher matcher = pattern.matcher(textP);
                        if(matcher.find()){
                            String dircetion = matcher.group(1);
                            String actors = matcher.group(2);
                            String year = matcher.group(3);
                            String country = matcher.group(4);
                            String genres = matcher.group(5);
                            movie.setDirector(dircetion);
                            movie.setActor(actors);
                            movie.setRelease_date(year);
                            movie.setMovie_type(genres);
                            movie.setCountry(country);
                        }
                    }
                    //获取评分
                    String rating = li.select(".rating_num").first().text();
                    movie.setRating(Double.parseDouble(rating) );
                    //获取电影简介
                    Element comment = li.select(".inq").first();
                    if (comment != null) {
                        movie.setComment(comment.text());
                    }
                    //获取电影排名
                    String ranking = li.select(".item .pic em").first().text();
                    movie.setRanking(Integer.valueOf(ranking));
                    movie.setId(Integer.valueOf(ranking));
                    movieMapper.insert(movie);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("爬取完成，总耗时："+(end-start));
    }

    @Override
    public List<Movie> findAll() {
        return movieMapper.all();
    }
}
