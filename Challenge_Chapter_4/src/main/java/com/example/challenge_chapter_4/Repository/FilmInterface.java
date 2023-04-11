package com.example.challenge_chapter_4.Repository;

import com.example.challenge_chapter_4.Model.FilmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmInterface extends JpaRepository<FilmEntity, String> {
    Page<FilmEntity> findAll(Pageable pageable);
    @Query("SELECT f FROM FilmEntity f WHERE LOWER(f.film_name) LIKE LOWER(concat('%',:film_name,'%') ) ")
    List<FilmEntity> findByName(@Param("film_name") String film_name);

    @Query("SELECT f FROM FilmEntity  f WHERE f.tayang_atau_tidak = 'Sedang Tayang'")
    List<FilmEntity> findFilmTayang();

    @Query("SELECT f,j FROM FilmEntity f JOIN JadwalEntity j ON f.film_code = j.film_code WHERE LOWER(f.film_name) LIKE LOWER(concat('%',:film_name,'%') )")
    List<FilmEntity> getByFilmJadwal(@Param("film_name") String film_name);
//    @Query("DELETE FROM FilmEntity f WHERE LOWER(f.film_code) = LOWER(:film_code)")
//    List<FilmEntity> deleteByCode(@Param("film_code") String film_code);

    //mau pake ini cuma ga bisa
}
