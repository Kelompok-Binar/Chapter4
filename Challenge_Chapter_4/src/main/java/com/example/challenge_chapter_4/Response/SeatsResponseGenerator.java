package com.example.challenge_chapter_4.Response;

import com.example.challenge_chapter_4.Model.SeatsEntity;
import com.example.challenge_chapter_4.Model.StudioEntity;
import org.springframework.stereotype.Component;

@Component
public class SeatsResponseGenerator {
    public <T> SeatsResponse <T> succsesResponse1(T datas, String msg){
        SeatsResponse seatsResponse = new SeatsResponse();
        seatsResponse.setStatus("200");
        seatsResponse.setMsg(msg);
        seatsResponse.setDatas(datas);

        return seatsResponse;
    }
}
