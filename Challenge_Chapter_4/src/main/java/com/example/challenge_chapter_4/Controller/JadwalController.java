package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.JadwalEntity;
import com.example.challenge_chapter_4.Response.JadwalResponse;
import com.example.challenge_chapter_4.Response.JadwalResponseGenerator;
import com.example.challenge_chapter_4.Service.JadwalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value ="/Jadwal")
public class JadwalController {
    @Autowired
    JadwalService js;
    @Autowired
    JadwalResponseGenerator jrg;

    @GetMapping
    public JadwalResponse<ResponseEntity<List<JadwalEntity>>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        try {
            Page<JadwalEntity> result = js.getAll(pageNumber, pageSize);
            List<JadwalEntity> data = result.getContent();
            long totalItems = result.getTotalElements();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalItems));
            return jrg.succsesResponse(ResponseEntity.ok().headers(headers).body(data),"Sukses Tampil Data");
        }
        catch (Exception e){
            return jrg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/Jadwal-Film/{id_jadwal}")
    public JadwalResponse<JadwalEntity> getById(@PathVariable int id_jadwal){
        try {
            JadwalEntity jadwal = js.getById(id_jadwal);
            return jrg.succsesResponse(jadwal,"Sukses Tampil Data " + jadwal.getId_jadwal());
        }
        catch (Exception e){
            return jrg.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addJadwal")
    public JadwalResponse<JadwalEntity> addJadwal(@RequestBody JadwalEntity param){
        try {
            JadwalEntity jadwal = js.addJadwal(param);
            return jrg.succsesResponse(jadwal,"Sukses add Data " + jadwal.getId_jadwal());
        }
        catch(Exception e){
            return jrg.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/updateJadwal")
    public JadwalResponse<JadwalEntity> updateJadwal(@RequestBody JadwalEntity param){
        try {
            JadwalEntity jadwal = js.updateJadwal(param);
            return jrg.succsesResponse(jadwal, "Sukses Update Data " + jadwal.getId_jadwal());
        }
        catch (Exception e){
            return jrg.failedResponse(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteJadwal/{id_jadwal}")
    public void deleteJadwal(@PathVariable int id_jadwal){
        try {
            js.deleteJadwal(id_jadwal);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jadwal not found", e);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Jadwal", e);
        }
    }

}
