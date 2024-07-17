package com.example.project.service.impl;

import com.example.project.adapter.WeatherInfoSearchInterface;
import com.example.project.model.ResponseVO;
import com.example.project.model.WeatherInfoVO;
import com.example.project.model.WeatherSearchResultVO;
import com.example.project.service.WeatherSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("weatherSearchService")
public class WeatherSearchServiceImpl implements WeatherSearchService {
    WeatherInfoSearchInterface weatherSearchInterface = new WeatherInfoSearchInterface();

    public List<WeatherInfoVO> searchWeatherList (String urlBuilder ) throws Exception {
        String responseBody = weatherSearchInterface.getWeatherInfo(urlBuilder);
        ObjectMapper mapper = new ObjectMapper();
        WeatherSearchResultVO resultVO = null;


        try {
            resultVO = mapper.readValue(responseBody, WeatherSearchResultVO.class);
        } catch (JsonMappingException e) {
            throw new Exception("JSON 에러 : " + e);
        } catch (JsonProcessingException e) {
            throw new Exception("JSON 에러 : " + e);
        }

        List<WeatherInfoVO> weathers = resultVO.getResponse().getBody().getItems().getItem();

        for (int i = 0; i < weathers.size(); i++) {
            WeatherInfoVO weatherInfo = new WeatherInfoVO();
            weatherInfo.setBaseDate(weathers.get(i).getBaseDate());
            weatherInfo.setBaseTime(weathers.get(i).getBaseTime());
            weatherInfo.setCategory(weathers.get(i).getCategory());
            weatherInfo.setFcstDate(weathers.get(i).getFcstDate());
            weatherInfo.setFcstTime(weathers.get(i).getFcstTime());
            weatherInfo.setFcstValue(weathers.get(i).getFcstValue());
            weatherInfo.setNx(weathers.get(i).getNx());
            weatherInfo.setNy(weathers.get(i).getNy());

            System.out.println(i + "==>" + weatherInfo);
        }
        return weathers;
    }
}
