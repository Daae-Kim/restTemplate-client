package com.practice.springresttemplateclient.naver.service;

import com.practice.springresttemplateclient.dto.ItemDto;
import com.practice.springresttemplateclient.naver.dto.ItemNaverDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestTemplateNaverService {

    private final RestTemplate restTemplate;

    public RestTemplateNaverService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<ItemNaverDto> searchItem(String query){
        //query 에 해당하는 uri 만들어서 네이버에 요청 보내기
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/shop.json")
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
        //header 에 client id, client secret 만들어서 보내기

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "llfOOF0aquscGH7EmopJ")
                .header("X-Naver-Client-Secret", "z030igjWKZ")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        //리턴 값 itemDto 에 맞게 파싱 하기

        return fromJSONtoItems(responseEntity.getBody());


    }

    private List<ItemNaverDto> fromJSONtoItems(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONArray items = jsonObject.getJSONArray("items");
        List<ItemNaverDto> itemDtoList = new ArrayList<>();

        for(Object item : items){
            ItemNaverDto itemDto = new ItemNaverDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;

    }
}
