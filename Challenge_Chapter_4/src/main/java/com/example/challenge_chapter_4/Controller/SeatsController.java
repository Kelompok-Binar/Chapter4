package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.SeatsEntity;
import com.example.challenge_chapter_4.Model.StudioEntity;
import com.example.challenge_chapter_4.Response.SeatsResponse;
import com.example.challenge_chapter_4.Response.SeatsResponseGenerator;
import com.example.challenge_chapter_4.Service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/Seats")
public class SeatsController {
    @Autowired
    SeatsService ss;
    @Autowired
    SeatsResponseGenerator srg;

    @GetMapping
    public List<SeatsEntity> getAll(){
        return ss.getAll();
    }

    @GetMapping(value = "Studios/{studio}/{nomor_kursi}")
    public SeatsResponse<SeatsEntity> getByStudio(@PathVariable char studio, @PathVariable Integer nomor_kursi){
        SeatsEntity seats = ss.getByStudioSeats(studio, nomor_kursi);
        if(seats != null){
            return srg.succsesResponse1(seats,"Sukses Menampilkan Data " +seats.getNomor_kursi());
            //return srg.succsesResponse2;
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

//    @GetMapping(value = "/Studio/{studio_name}/{nomor_kursi}")
//    public List<SeatsEntity> getByStudioSeat(@PathVariable int nomor_kursi){
//        return ss.getByStudioSeat(nomor_kursi);
//    }
}
