package com.example.project.controller;

import com.example.project.changeValue.CoordinateConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class MapController {

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String showMapPage() {
        return "map";
    }

    @RequestMapping(value = "/getGridXY", method = RequestMethod.GET)
    public String getGridXY(@RequestParam("address") String address, Model model) throws IOException {
        String clientId = "hh8wofz0sz";
        String clientPw = "ZUiL4hLLp5Jw19ebEA8lTLJirsUgwy4CLA26KwZY";
        String apiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";

        // 주소별로 HTTP 요청 보내기
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        URL url = new URL(apiUrl + encodedAddress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientPw);

        // HTTP 응답 처리
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSON 파싱
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray addresses = jsonObject.getAsJsonArray("addresses");
            if (addresses.size() > 0) {
                JsonObject firstAddress = addresses.get(0).getAsJsonObject();
                String latitude = firstAddress.get("y").getAsString();
                String longitude = firstAddress.get("x").getAsString();

                // 그리드 X와 Y 값으로 변환
                double lat = Double.parseDouble(latitude);
                double lng = Double.parseDouble(longitude);

                // CoordinateConverter 클래스의 인스턴스 생성
                CoordinateConverter converter = new CoordinateConverter();
                // 인스턴스의 멤버 변수에 위도와 경도 설정
                converter.setLatitude(lat);
                converter.setLongitude(lng);
                // 인스턴스의 toGrid() 메서드 호출
                int[] grid = converter.toGrid();
                model.addAttribute("grid", grid);
                return "mapSearchResult";

            } else {
                System.out.println("No address found.");
            }
        } else {
            System.out.println("HTTP error code: " + responseCode);
        }
        conn.disconnect();

        return "mapSearchResult";
    }
}
