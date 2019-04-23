package com.sj.esPolygonDemo;

import org.elasticsearch.common.geo.builders.PolygonBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Chen Sijia
 * @Date: 2019/3/10 01:34
 */
@Component
public class Location {

    private String city;

    private Date time;

    private PolygonBuilder polygon;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PolygonBuilder getPolygon() {
        return polygon;
    }

    public void setPolygon(PolygonBuilder polygon) {
        this.polygon = polygon;
    }
}
