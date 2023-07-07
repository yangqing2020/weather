package com.example.myweather.gson;

import java.util.List;

public class Weather {

    private String status;//状态信息
    private String count;//返回结果数量
    private String info;//信息
    private String infocode;//信息代码
    private List<ForecastsBean> forecasts;//天气预报列表

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfocode() {
        return infocode;
    }
    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
    public List<ForecastsBean> getForecasts() {
        return forecasts;
    }
    public void setForecasts(List<ForecastsBean> forecasts) {
        this.forecasts = forecasts;
    }

    public static class ForecastsBean {
        /**
         * city : 朝阳区
         * adcode : 110105
         * province : 北京
         * reporttime : 2020-11-15 12:01:06
         * casts : [{"date":"2020-11-15","week":"7","dayweather":"多云","nightweather":"多云","daytemp":"16","nighttemp":"5","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-16","week":"1","dayweather":"阴","nightweather":"阴","daytemp":"12","nighttemp":"8","daywind":"东","nightwind":"东","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-17","week":"2","dayweather":"阴","nightweather":"小雨","daytemp":"13","nighttemp":"10","daywind":"西北","nightwind":"西北","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-18","week":"3","dayweather":"小雨","nightweather":"小雨","daytemp":"13","nighttemp":"6","daywind":"北","nightwind":"北","daypower":"≤3","nightpower":"≤3"}]
         */

        private String city;
        private String adcode;
        private String province;
        private String reporttime;
        private List<CastsBean> casts;//天气预报列表
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getAdcode() {
            return adcode;
        }
        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }
        public String getProvince() {
            return province;
        }
        public void setProvince(String province) {
            this.province = province;
        }
        public String getReporttime() {
            return reporttime;
        }
        public void setReporttime(String reporttime) {
            this.reporttime = reporttime;
        }
        public List<CastsBean> getCasts() {
            return casts;
        }
        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }
        public static class CastsBean {

            private String date;
            private String week;
            private String dayweather;//天气
            private String nightweather;
            private String daytemp;//温度
            private String nighttemp;
            private String daywind;//风向
            private String nightwind;
            private String daypower;//风力
            private String nightpower;

            public String getDate() {
                return date;
            }
            public void setDate(String date) {
                this.date = date;
            }
            public String getWeek() {
                return week;
            }
            public void setWeek(String week) {
                this.week = week;
            }
            public String getDayweather() {
                return dayweather;
            }
            public void setDayweather(String dayweather) {
                this.dayweather = dayweather;
            }
            public String getNightweather() {
                return nightweather;
            }
            public void setNightweather(String nightweather) {
                this.nightweather = nightweather;
            }
            public String getDaytemp() {
                return daytemp;
            }
            public void setDaytemp(String daytemp) {
                this.daytemp = daytemp;
            }

            public String getNighttemp() {
                return nighttemp;
            }
            public void setNighttemp(String nighttemp) {
                this.nighttemp = nighttemp;
            }
            public String getDaywind() {
                return daywind;
            }
            public void setDaywind(String daywind) {
                this.daywind = daywind;
            }
            public String getNightwind() {
                return nightwind;
            }
            public void setNightwind(String nightwind) {
                this.nightwind = nightwind;
            }
            public String getDaypower() {
                return daypower;
            }
            public void setDaypower(String daypower) {
                this.daypower = daypower;
            }
            public String getNightpower() {
                return nightpower;
            }
            public void setNightpower(String nightpower) {
                this.nightpower = nightpower;
            }
        }
    }
}
