package com.coors.licenseplate;

import java.io.Serializable;

/**
 * Created by rocker on 2017/6/8.
 */

class LicensePlateVO implements Serializable {
    private Integer licensePlate;
    private Integer luckyResult;

    public LicensePlateVO(Integer licensePlate, Integer luckyResult) {
        this.licensePlate = licensePlate;
        this.luckyResult = luckyResult;
    }

    public Integer getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(Integer licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getLuckyResult() {
        return luckyResult;
    }

    public void setLuckyResult(Integer luckyResult) {
        this.luckyResult = luckyResult;
    }
}
