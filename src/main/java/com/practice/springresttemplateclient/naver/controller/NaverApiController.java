package com.practice.springresttemplateclient.naver.controller;

import com.practice.springresttemplateclient.naver.dto.ItemNaverDto;
import com.practice.springresttemplateclient.naver.service.RestTemplateNaverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NaverApiController {
    private final RestTemplateNaverService restTemplateNaverService;

    public NaverApiController(RestTemplateNaverService restTemplateNaverService) {
        this.restTemplateNaverService = restTemplateNaverService;
    }

    @GetMapping("/naver-shopping")
    public List<ItemNaverDto> naverGet(@RequestParam String query){
        return restTemplateNaverService.searchItem(query);
    }
}
