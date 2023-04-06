package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.StudioEntity;
import com.example.challenge_chapter_4.Service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/Studio")
public class StudioController {
    @Autowired
    StudioService ss;

    @GetMapping
    public ResponseEntity<List<StudioEntity>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "studio_name") String sortBy){
        Page<StudioEntity> result = ss.getAll(pageNumber, pageSize, sortBy);
        List<StudioEntity> data = result.getContent();
        int currentPage = result.getNumber();
        int totalPages = result.getTotalPages();
        long totalItems = result.getTotalElements();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(totalItems))
                .body(data);
    }
//    @GetMapping(value = "Studios/{studio}/{nomor_kursi}")
//    public StudioEntity getByStudio(@PathVariable char studio, @PathVariable Integer nomor_kursi){
//        return ss.getByStudio(studio, nomor_kursi);
//    }
}
