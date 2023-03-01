package com.am.retrofit.ui;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 测试数据
 * {"message":"success感谢又拍云(upyun.com)提供CDN赞助","status":200,"date":"20230301","time":"2023-03-01 18:06:20","cityInfo":{"city":"天津市","citykey":"101030100","parent":"天津","updateTime":"13:31"},"data":{"shidu":"11%","pm25":6.0,"pm10":36.0,"quality":"优","wendu":"2","ganmao":"各类人群可自由活动","forecast":[{"date":"01","high":"高温 10℃","low":"低温 2℃","ymd":"2023-03-01","week":"星期三","sunrise":"06:43","sunset":"18:03","aqi":37,"fx":"北风","fl":"3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"02","high":"高温 15℃","low":"低温 2℃","ymd":"2023-03-02","week":"星期四","sunrise":"06:41","sunset":"18:04","aqi":46,"fx":"西南风","fl":"3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"03","high":"高温 17℃","low":"低温 3℃","ymd":"2023-03-03","week":"星期五","sunrise":"06:40","sunset":"18:05","aqi":71,"fx":"西南风","fl":"2级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"04","high":"高温 17℃","low":"低温 5℃","ymd":"2023-03-04","week":"星期六","sunrise":"06:39","sunset":"18:06","aqi":69,"fx":"东风","fl":"2级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"05","high":"高温 19℃","low":"低温 5℃","ymd":"2023-03-05","week":"星期日","sunrise":"06:37","sunset":"18:07","aqi":71,"fx":"东南风","fl":"2级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"06","high":"高温 16℃","low":"低温 3℃","ymd":"2023-03-06","week":"星期一","sunrise":"06:36","sunset":"18:08","aqi":79,"fx":"西南风","fl":"2级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"07","high":"高温 22℃","low":"低温 6℃","ymd":"2023-03-07","week":"星期二","sunrise":"06:34","sunset":"18:09","aqi":86,"fx":"西北风","fl":"3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"08","high":"高温 14℃","low":"低温 3℃","ymd":"2023-03-08","week":"星期三","sunrise":"06:33","sunset":"18:10","aqi":30,"fx":"北风","fl":"4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"09","high":"高温 19℃","low":"低温 6℃","ymd":"2023-03-09","week":"星期四","sunrise":"06:31","sunset":"18:11","aqi":28,"fx":"西北风","fl":"3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"10","high":"高温 23℃","low":"低温 3℃","ymd":"2023-03-10","week":"星期五","sunrise":"06:29","sunset":"18:12","aqi":46,"fx":"西风","fl":"3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"11","high":"高温 10℃","low":"低温 4℃","ymd":"2023-03-11","week":"星期六","sunrise":"06:28","sunset":"18:13","aqi":40,"fx":"西南风","fl":"2级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"12","high":"高温 17℃","low":"低温 6℃","ymd":"2023-03-12","week":"星期日","sunrise":"06:26","sunset":"18:14","aqi":41,"fx":"西北风","fl":"4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"13","high":"高温 25℃","low":"低温 3℃","ymd":"2023-03-13","week":"星期一","sunrise":"06:25","sunset":"18:15","aqi":40,"fx":"西南风","fl":"3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"14","high":"高温 8℃","low":"低温 3℃","ymd":"2023-03-14","week":"星期二","sunrise":"06:23","sunset":"18:16","aqi":69,"fx":"西南风","fl":"3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"15","high":"高温 14℃","low":"低温 4℃","ymd":"2023-03-15","week":"星期三","sunrise":"06:22","sunset":"18:17","aqi":82,"fx":"西风","fl":"4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}],"yesterday":{"date":"28","high":"高温 13℃","low":"低温 4℃","ymd":"2023-02-28","week":"星期二","sunrise":"06:44","sunset":"18:02","aqi":44,"fx":"西北风","fl":"3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}}}
 * Created by Alex on 2019/1/29.
 */
@SuppressWarnings("unused")
class TestData {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("cityInfo")
    private CityInfo cityInfo;
    private Data data;

    public static class CityInfo {
        @SerializedName("city")
        private String city;
        @SerializedName("citykey")
        private String citykey;
        @SerializedName("parent")
        private String parent;
        @SerializedName("updateTime")
        private String updateTime;
    }

    public static class Data {
        @SerializedName("shidu")
        private String shidu;
        @SerializedName("pm25")
        private double pm25;
        @SerializedName("pm10")
        private double pm10;
        @SerializedName("quality")
        private String quality;
        @SerializedName("wendu")
        private String wendu;
        @SerializedName("ganmao")
        private String ganmao;
        @SerializedName("forecast")
        private List<WeatherData> forecast;
        @SerializedName("yesterday")
        private WeatherData yesterday;
    }

    public static class WeatherData {
        @SerializedName("date")
        private String date;
        @SerializedName("high")
        private String high;
        @SerializedName("low")
        private String low;
        @SerializedName("ymd")
        private String ymd;
        @SerializedName("week")
        private String week;
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("aqi")
        private int aqi;
        @SerializedName("fx")
        private String fx;
        @SerializedName("fl")
        private String fl;
        @SerializedName("type")
        private String type;
        @SerializedName("notice")
        private String notice;
    }
}
