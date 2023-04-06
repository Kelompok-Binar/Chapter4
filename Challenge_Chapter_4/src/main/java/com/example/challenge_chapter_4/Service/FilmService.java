package com.example.challenge_chapter_4.Service;

import com.example.challenge_chapter_4.Model.FilmEntity;
import com.example.challenge_chapter_4.Repository.FilmInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    @Autowired
    FilmInterface R;

    public List<FilmEntity> getAll(){
        return R.findAll();
    }
    public List<FilmEntity> getByTitle(String film_name){
        return R.findByName(film_name);
    }

    public FilmEntity addFilm(FilmEntity param){
        Optional<FilmEntity> filmExsist = R.findById(param.getFilm_code());
        if(filmExsist.isPresent()){
            throw new RuntimeException("Film Code " + param.getFilm_code() + " Sudah Ada");
        }
        else {
            return R.save(param);
        }

    }

    public FilmEntity updateFilm(FilmEntity param){
        FilmEntity filmExsist = R.findById(param.getFilm_code()).get();
        filmExsist.setFilm_name(param.getFilm_name());
        filmExsist.setTayang_atau_tidak(param.getTayang_atau_tidak());
        return R.save(filmExsist);
    }

    public void delFilm(String param){
        R.deleteById(param);
    }
}
