package com.trj.tbase.dialog;

import java.util.ArrayList;

/**
 * @author tong
 * @date 2018/2/27 15:22
 * 省市区的 javabean
 *
 */

public class ProvinceBean {
    /**
     * areaId : 110000
     * areaName : 北京市
     * cities : [{"areaId":110100,"areaName":"市辖区","counties":[{"areaId":110101,"areaName":"东城区"},{"areaId":110102,"areaName":"西城区"},{"areaId":110105,"areaName":"朝阳区"},{"areaId":110106,"areaName":"丰台区"},{"areaId":110107,"areaName":"石景山区"},{"areaId":110108,"areaName":"海淀区"},{"areaId":110109,"areaName":"门头沟区"},{"areaId":110111,"areaName":"房山区"},{"areaId":110112,"areaName":"通州区"},{"areaId":110113,"areaName":"顺义区"},{"areaId":110114,"areaName":"昌平区"},{"areaId":110115,"areaName":"大兴区"},{"areaId":110116,"areaName":"怀柔区"},{"areaId":110117,"areaName":"平谷区"},{"areaId":110118,"areaName":"密云区"},{"areaId":110119,"areaName":"延庆区"}]}]
     */

    private int areaId;
    private String areaName;
    private ArrayList<CitiesBean> cities;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public ArrayList<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CitiesBean> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return areaName;
    }

    public static class CitiesBean {
        /**
         * areaId : 110100
         * areaName : 市辖区
         * counties : [{"areaId":110101,"areaName":"东城区"},{"areaId":110102,"areaName":"西城区"},{"areaId":110105,"areaName":"朝阳区"},{"areaId":110106,"areaName":"丰台区"},{"areaId":110107,"areaName":"石景山区"},{"areaId":110108,"areaName":"海淀区"},{"areaId":110109,"areaName":"门头沟区"},{"areaId":110111,"areaName":"房山区"},{"areaId":110112,"areaName":"通州区"},{"areaId":110113,"areaName":"顺义区"},{"areaId":110114,"areaName":"昌平区"},{"areaId":110115,"areaName":"大兴区"},{"areaId":110116,"areaName":"怀柔区"},{"areaId":110117,"areaName":"平谷区"},{"areaId":110118,"areaName":"密云区"},{"areaId":110119,"areaName":"延庆区"}]
         */

        private int areaId;
        private String areaName;
        private ArrayList<CountiesBean> counties;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public ArrayList<CountiesBean> getCounties() {
            return counties;
        }

        public void setCounties(ArrayList<CountiesBean> counties) {
            this.counties = counties;
        }

        @Override
        public String toString() {
            return areaName;
        }

    }

    public static class CountiesBean {
        /**
         * areaId : 110101
         * areaName : 东城区
         */

        private int areaId;
        private String areaName;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        @Override
        public String toString() {
            return areaName;
        }

    }

}
