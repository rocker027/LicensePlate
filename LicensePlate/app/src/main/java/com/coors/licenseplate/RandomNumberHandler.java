package com.coors.licenseplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by rocker on 2017/6/8.
 */

public class RandomNumberHandler {
    private static final int DIVINATION_81_QUERY = 100;
    private static final int DIVINATION_4_SUM_QUERY = 200;
    private static final int DIVINATION_TWO_MODE_QUERY = 300;
//    private ArrayList<LicensePlateVO> mLicensePlates;
    private static RandomNumberHandler instance;
    int[] lucky_array = {
            1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25, 28, 31, 32, 38, 40, 44, 46, 47, 51, 56, 62, 64, 66, 67, 70, 72, 80
    };

    int[] custom_number = {1, 3, 5, 6, 7, 8, 11, 13,15, 16, 17, 18, 21, 23};
    private Context mContext;
    private HashMap<Integer, LicensePlateVO> mResults;

    public RandomNumberHandler(Context context) {
        this.mContext = context;
//        mLicensePlates = new ArrayList<>();
        mResults = new HashMap<>();
    }

    public static RandomNumberHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RandomNumberHandler(context);

        }
        return instance;
    }
//    int[] lucky_81_array = {
//            1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25, 28, 31, 32, 38, 40, 44, 46, 47, 51, 56, 62, 64, 66, 67, 70, 72, 80
//    };
//
//    int[] lucky_4sum_array = {
//            1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25, 28, 31, 32, 38, 40, 44, 46, 47, 51, 56, 62, 64, 66, 67, 70, 72, 80
//    };

    public HashMap<Integer, LicensePlateVO> getRandomNumbers(int divination) {
        mResults.clear();
        Integer rnd = 0;
        while (mResults.size() < 100)       //while迴圈命名rndFnc，當rnd >0執行下列迴圈
        {
            rnd = (int) (Math.floor(Math.random() * 9999 + 0001));
//            Log.d("rnd", rnd.toString());
            if (!isNumberIs4(rnd) && !isNumberIs7(rnd)) {
//                Log.d("rnd no 4", rnd.toString());
                if (isLucky(getSelectDivination(divination, rnd))) {
//                    result_list.add(getSelectDivination(divination, rnd));
                    LicensePlateVO licensePlate = new LicensePlateVO(rnd, getSelectDivination(divination, rnd));
                    Log.d("result", "車牌 : "+rnd.toString() +" , 計算結果 ：" + getSelectDivination(divination, rnd));
                    mResults.put(licensePlate.getLicensePlate(), licensePlate);
                }
            }
        }

        return mResults;
    }

    public boolean isNumberIs4(Integer random_number) {
        if ((random_number / 1000) == 4) {
            return true;
        } else if (((random_number % 1000) / 100) == 4) {
            return true;
        } else if ((((random_number % 1000) % 100) / 10) == 4) {
            return true;
        } else if ((random_number % 10) == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNumberIs7(Integer random_number) {
        if ((random_number / 1000) == 7) {
            return true;
        } else if (((random_number % 1000) / 100) == 7) {
            return true;
        } else if ((((random_number % 1000) % 100) / 10) == 7) {
            return true;
        } else if ((random_number % 10) == 7) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLucky(Integer random_number) {
//        for (int i : lucky_array) {
        for (int i : custom_number) {
            if (i == random_number) {
                return true;
            }
        }
        return false;
    }

    public Integer getSelectDivination(int div, Integer random_number) {
        Integer divination_result = -1;
        switch (div) {
            //81數理車牌號碼吉凶查詢表
           /*4位數字5689除以80，得到一個數5689/80=71.1125，然後去掉整數部分，
            留下小數點後面部分71.1125-71(整數部分)=0.1125然後是用這個數乘以80，得出的數字取
            整數部分就是結果： 0.1125×80=9*/
            case DIVINATION_81_QUERY:
                divination_result = getDivination_81(random_number);
                break;
//            車牌數相加
            /*車牌號碼是5678，就是5+6+7+8=26，管你是0001還是9999通通對應的到*/
            case DIVINATION_4_SUM_QUERY:
                divination_result = getDivination_4Sum(random_number);
                break;
//            case DIVINATION_TWO_MODE_QUERY:
//                divination_result =  -1;
//                break;

        }
        return divination_result;
    }

    @NonNull
    private Integer getDivination_4Sum(Integer random_number) {
        int num1, num2, num3, num4;
        Integer divination_result;
        num1 = random_number / 1000;
        num2 = (random_number % 1000) / 100;
        num3 = ((random_number % 1000) % 100) / 10;
        num4 = random_number % 10;
        divination_result = num1 + num2 + num3 + num4;
        return divination_result;
    }

    @NonNull
    private Integer getDivination_81(Integer random_number) {
        Integer divination_result;
        double tmpValue = random_number / 80.0;
        int tmp2Value = (int) Math.floor(tmpValue);
        double tmp3Value = ((tmpValue - tmp2Value) * 80);
        divination_result = (int) Math.floor(tmp3Value);
        return divination_result;
    }

    public String addZeroNumber(Integer number) {
        StringBuilder sb = new StringBuilder();
//        if (number.toString().length() == 1) {
//        }  else if (number.toString().length() == 2) {
//        } else if (number.toString().length() == 3) {
//        } else if (number.toString().length() == 4) {
//        } else {
//            return sb.toString();
//        }
        switch (number.toString().length()) {
            case 1:
                sb.append("0").append("0").append("0").append(number.toString());
                break;
            case 2:
                sb.append("0").append("0").append(number.toString());
                break;
            case 3:
                sb.append("0").append(number.toString());
                break;
            default:
                sb.append(number.toString());
                break;
        }
        return sb.toString();
    }
}
