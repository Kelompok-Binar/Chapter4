package com.example.challenge_chapter_4.Service;

import com.example.challenge_chapter_4.Model.JadwalEntity;
import com.example.challenge_chapter_4.Repository.JadwalInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JadwalService {
    @Autowired
    JadwalInterface R;
    public List<JadwalEntity> getAll() {
        return R.findAll();
    }

    public JadwalEntity addJadwal(JadwalEntity param) {
        Optional<JadwalEntity> jadwalExsist = R.findById(param.getId_jadwal());
        if(jadwalExsist.isPresent()){
            throw new RuntimeException("Film Id " + param.getId_jadwal() + " Sudah Ada") ;
        }
        else{
            return R.save(param);
        }

    }

    public JadwalEntity getById(int idJadwal) {
        return R.findById(idJadwal).get();
    }

    public JadwalEntity updateJadwal(JadwalEntity param) {
        JadwalEntity jadwalExsist = R.findById(param.getId_jadwal()).get();
        jadwalExsist.setFilm_code(param.getFilm_code());
        jadwalExsist.setHarga_tiket(param.getHarga_tiket());
        jadwalExsist.setTanggal_tayang(param.getTanggal_tayang());
        jadwalExsist.setJam_mulai(param.getJam_mulai());
        jadwalExsist.setJam_selesai(param.getJam_selesai());

        return jadwalExsist;
    }

    public void deleteJadwal(int idJadwal) {
        R.deleteById(idJadwal);
    }
}
