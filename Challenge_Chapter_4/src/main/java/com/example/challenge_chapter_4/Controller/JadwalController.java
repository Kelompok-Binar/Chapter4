package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.JadwalEntity;
import com.example.challenge_chapter_4.Service.JadwalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/Jadwal")
public class JadwalController {
    @Autowired
    JadwalService js;

    @GetMapping
    public List<JadwalEntity> getAll(){
        return js.getAll();
    }

    @GetMapping(value = "/Jadwal-Film/{id_jadwal}")
    public JadwalEntity getById(@PathVariable int id_jadwal){
        return js.getById(id_jadwal);
    }

    @PostMapping(value = "/addJadwal")
    public JadwalEntity addJadwal(@RequestBody JadwalEntity param){
        return js.addJadwal(param);
    }

    @PutMapping(value = "/updateJadwal")
    public JadwalEntity updateJadwal(@RequestBody JadwalEntity param){
        return js.updateJadwal(param);
    }

    @DeleteMapping(value = "/deleteJadwal/{id_jadwal}")
    public void deleteJadwal(@PathVariable int id_jadwal){
        js.deleteJadwal(id_jadwal);
    }

}
