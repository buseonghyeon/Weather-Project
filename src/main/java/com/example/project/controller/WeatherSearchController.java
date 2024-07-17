package com.example.project.controller;

import com.example.project.changeValue.CoordinateConverter;
import com.example.project.model.GridXYVO;
import com.example.project.model.ParamDTO;
import com.example.project.model.WeatherInfoVO;
import com.example.project.service.WeatherSearchService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WeatherSearchController {

    @Resource(name = "weatherSearchService")
    private WeatherSearchService weatherSearchService;

    @GetMapping("/weather")
    public String showSearchForm() {
        return "weather";
    }

    @PostMapping("/search")
    public String searchAddressAndWeather(@RequestParam("address") String address, Model model) {
        try {
            // GridXYVO 클래스의 주소 검색 메서드 호출
            GridXYVO mapTest = new GridXYVO();
            GridXYVO.CoordinateConverter coordinateConverter = new GridXYVO.CoordinateConverter();

            // 주소 검색 결과 가져오기
            JsonObject addressInfo = mapTest.searchAddress(address);
            if (addressInfo != null) {
                if (addressInfo.has("addresses") && addressInfo.getAsJsonArray("addresses").size() > 0) {
                    JsonObject firstAddress = addressInfo.getAsJsonArray("addresses").get(0).getAsJsonObject();
                    double latitude = firstAddress.get("y").getAsDouble();
                    double longitude = firstAddress.get("x").getAsDouble();
                    int[] grid = coordinateConverter.toGrid(latitude, longitude);

                    // 그리드 값을 파라미터 DTO에 추가
                    ParamDTO paramDTO = new ParamDTO();
                    paramDTO.setNx(String.valueOf(grid[0]));
                    paramDTO.setNy(String.valueOf(grid[1]));
                    paramDTO.setBaseDate(com.example.project.model.ChangeValueVO.getAutoBaseDate());
                    paramDTO.setBaseTime(com.example.project.model.ChangeValueVO.getAutoBaseTime());

                    // 기상청 API service Key값, 받아올 자료 URL 설정
                    String serviceKey = "=yuuAIagVrMk4nnZy2%2Fy47WSlSR6qihjxhcjfgbeNq5%2BeNCpfO%2FvoIwRuplGaBHclEUKpYaAmKL6aoxBKPfZqSw%3D%3D";
                    String URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";

                    // URI 및 WEB에서 설정한 값 설정
                    StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
                    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + serviceKey); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
                    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
                    urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
                    urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(paramDTO.getBaseDate(), "UTF-8")); /*‘21년 6월 28일 발표*/
                    urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(paramDTO.getBaseTime(), "UTF-8")); /*06시 발표(정시단위) */
                    urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(paramDTO.getNx(), "UTF-8")); /*예보지점의 X 좌표값*/
                    urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(paramDTO.getNy(), "UTF-8")); /*예보지점의 Y 좌표값*/

                    List<WeatherInfoVO> weathers = weatherSearchService.searchWeatherList(urlBuilder.toString());

                    System.out.println(" URL ==> " + urlBuilder);

                    model.addAttribute("weathers", weathers);
                    return "weatherSearchResult";
                } else {
                    model.addAttribute("error", "검색 결과가 없습니다.");
                }
            } else {
                model.addAttribute("error", "주소 정보를 불러올 수 없습니다.");
            }
        } catch (Exception e) {
            // 오류 처리
            e.printStackTrace();  // 콘솔에 예외 스택 트레이스를 출력하여 디버깅에 도움
            model.addAttribute("error", "검색 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "weather";
    }
}