package com.example.project.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WeatherInfoSearchInterface {

    /*
    * 날씨 검색 결과
    * @param urlBuilder
    * @return
    * @throws Exception
    */
    public String getWeatherInfo(String urlBuilder) throws Exception {
        String resposeBody = get(urlBuilder);

        return resposeBody;
    }

    /*
    * GET 요청
    * @param apiUrl
    */
    private static String get(String urlBuilder) {
        HttpURLConnection conn = connect(urlBuilder);
        try {
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode(); // 암시적 연결 완료
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(conn.getInputStream());
            } else { // 오류 발생
                return readBody(conn.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            conn.disconnect();
        }
    }

    /* URL connection
    * @param urlBuilder
    * @return*/
    private static HttpURLConnection connect(String urlBuilder) {
        try{
            URL url = new URL(urlBuilder);
            return(HttpURLConnection)url.openConnection();
        }catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + urlBuilder, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + urlBuilder, e);
        }
    }

    /*
     * 응답 바디 읽어오기
     * @param body
     * @return
     */

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)){
            StringBuilder responsebody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null){
                responsebody.append(line);
            }
            return responsebody.toString();
        }catch (IOException e){
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

}
