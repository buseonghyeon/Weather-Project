package com.example.project.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChangeValueVO {

    public static String getAutoBaseDate() {
        LocalDateTime now = LocalDateTime.now();

        return now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String getAutoBaseTime() {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("HHmm"));
        int minutes = Integer.parseInt(time.substring(2));

        if (minutes >= 30) {
            return time;
        } else {
            LocalDateTime adjustedTime = now.minusHours(1).plusMinutes(30);
            return adjustedTime.format(DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    public String changePTYValue(String value) {
        int intValue = Integer.parseInt(value);
        switch (intValue) {
            case 0:
                return "없음";
            case 1:
                return "비";
            case 2:
                return "비/눈";
            case 3:
                return "눈";
            case 5:
                return "빗방울";
            case 6:
                return "빗방울눈날림";
            case 7:
                return "눈날림";
            default:
                return "오류남";
        }
    }

    public String changeSKYValue(String value) {
        int intValue = Integer.parseInt(value);
        switch (intValue) {
            case 1:
                return "맑음";
            case 3:
                return "구름많음";
            case 4:
                return "흐림";
            default:
                return "오류남";
        }
    }

    public String formatTime(String fcstTime) {
        // Assuming time is in the format "HHmm"
        if (fcstTime.length() != 4) {
            throw new IllegalArgumentException("Time format is incorrect");
        }

        // Parsing the input time
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime localTime = LocalTime.parse(fcstTime, inputFormatter);

        // Formatting the new time to "HH:mm"
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(outputFormatter);
    }

    public static void main(String[] args) {
        ChangeValueVO changeValueVO = new ChangeValueVO();

        // Example usage
        String fcstTime = "1000";
        String formattedTime = changeValueVO.formatTime(fcstTime);
        System.out.println("Original time: " + fcstTime);
        System.out.println("Formatted time: " + formattedTime);
    }

    public String changeWSDValue(String value) {
        int intValue = Integer.parseInt(value);
        if (intValue >= 0 && intValue <= 45) {
            return "북-북동";
        }else if (intValue > 45 && intValue <= 90) {
            return "북동-동";
        }else if (intValue > 90 && intValue <= 135) {
            return "동-남동";
        }else if (intValue > 135 && intValue <= 180) {
            return "남동-남";
        }else if (intValue > 180 && intValue <= 225) {
            return "남-남서";
        }else if (intValue > 225 && intValue <= 270) {
            return "남서-서";
        }else if (intValue > 270 && intValue <= 315) {
            return "서-북서";
        }else if (intValue > 315 && intValue <= 360) {
            return "북서-북";
        }else {
            return "오류";
        }

    }
}
