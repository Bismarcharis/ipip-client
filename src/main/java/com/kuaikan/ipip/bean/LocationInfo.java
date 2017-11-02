package com.kuaikan.ipip.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liguochao.
 */
@Data
public class LocationInfo implements Serializable {
    private static final long serialVersionUID = -2936251798643350699L;

    private String country;
    private String province;
    private String city;
    private String county;
    private String isp;

    public LocationInfo(String country, String province, String city, String county, String isp) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.county = county;
        this.isp = isp;
    }

    @Override
    public String toString() {
        return country + " " + province + " " + city + " " + county + " " + isp;
    }
}