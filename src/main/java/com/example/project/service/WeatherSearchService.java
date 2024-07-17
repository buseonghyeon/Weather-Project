package com.example.project.service;

import com.example.project.model.WeatherInfoVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface WeatherSearchService {
    public List<WeatherInfoVO> searchWeatherList(String urlBuilder) throws Exception;

}
