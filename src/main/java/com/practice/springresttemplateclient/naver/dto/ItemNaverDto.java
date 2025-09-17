package com.practice.springresttemplateclient.naver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemNaverDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ItemNaverDto(JSONObject itemJson){
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}
