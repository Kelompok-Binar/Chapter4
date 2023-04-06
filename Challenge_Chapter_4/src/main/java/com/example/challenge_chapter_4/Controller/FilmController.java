package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.FilmEntity;
import com.example.challenge_chapter_4.Response.FilmResponse;
import com.example.challenge_chapter_4.Response.FilmResponseGenerator;
import com.example.challenge_chapter_4.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*
* Note @RequestParm nanti di webnya menggunakan ?(yang dicari)=(yang dicari) misal ?id = 4
* Note @Patth Variable nanti di webnya menggunakan /(apa yang di cari) misal /4
*
* catatan punya nurul
* - pake @DeleteMapping, endpoint nya gaperlu pake 'value' langsung ajj
*Example :
@DeleteMapping("/deleteFilm/{filmCode}") *perhatikan tanda / (slash)

- bagian @PathVariable, tambahin string paramnya, baru variabel paramnya
*Example :
delFilm(@PathVariable("filmCode") String filmCode)

- untuk delete, pke bawaan jpa,.. cukup gini,
fi.deleteById(filmCode)

- untuk responnya, aku biasa pke boolean sih (true/false) blm tau klo tau pke string😅

*
* */



@RestController
@RequestMapping(value ="/Film")
public class FilmController {
    @Autowired
    FilmService fs;
    @Autowired
    FilmResponseGenerator frg;

    @GetMapping
    public FilmResponse<List<FilmEntity>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize){

        try {
            Page<FilmEntity> filmResult = fs.getAll(pageNumber, pageSize);
            List<FilmEntity> filmData = filmResult.getContent();
            long totalItems = filmResult.getTotalElements();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalItems));

            return frg.succsesResponse(ResponseEntity.ok().headers(headers).body(filmData),"Sukses Tampil Data");
        }
        catch (Exception e){
            return frg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/Judul-Film/{film_name}")
    public FilmResponse<List<FilmEntity>> getByFilm_name(@PathVariable String film_name){
        try {
            List<FilmEntity> film = fs.getByTitle(film_name);
            return frg.succsesResponse(film,"Sukses Mencari Data '" + film_name + "'");
        }
        catch (Exception e){
            return frg.failedResponse(e.getMessage());
        }


    }

    @PostMapping(value = "/addFilm")
    public FilmResponse<FilmEntity> addFilm(@RequestBody FilmEntity param){
        try {
            FilmEntity film = fs.addFilm(param);
            return frg.succsesResponse(film,"Sukses Add Data" + film.getFilm_code()) ;
        }
        catch (Exception e){
            return frg.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/updateFilm")
    public FilmResponse<FilmEntity> updateFilm(@RequestBody FilmEntity param){
        try {
            FilmEntity film = fs.updateFilm(param);
            return frg.succsesResponse(film,"Sukses Update Data " + param.getFilm_code());
        }
        catch (Exception e){
            return frg.failedResponse(e.getMessage());
        }
    }

    // add dan update film sebenarnya caranya sama cuma biar endpointnya berbeda saya buat 2 post mapping

//    @GetMapping(value = "/deleteFilm/{film_code}")
//    public List<FilmEntity> deleteFilm(@PathVariable String film_code){
//        return fs.deleteByNameCode(film_code);
//    }// mau coba pakai ini tapi bad 500 gateway karena sql tidak bisa

    @DeleteMapping(value = "deleteFilm/{film_code}")
    public FilmEntity delFilm(@PathVariable String film_code){
        try {
            return fs.delFilm(film_code);

        }
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found", e);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Film", e);
        }
    }
}
