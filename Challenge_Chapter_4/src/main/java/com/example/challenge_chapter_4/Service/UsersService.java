package com.example.challenge_chapter_4.Service;


import com.example.challenge_chapter_4.Model.UsersEntity;
import com.example.challenge_chapter_4.Repository.UsersInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersInterface R;

    public List<UsersEntity> getAll(){
        return R.findAll();
    }
    public UsersEntity getById(int id_user){
        return R.findById(id_user).get();
    }

    public UsersEntity updateUser(UsersEntity param) {
        UsersEntity userExist =  R.findById(param.getId_user()).get();
        userExist.setUsername(param.getUsername());
        userExist.setEmail(param.getEmail());
        userExist.setPassword(param.getPassword());
        return R.save(userExist);
    }

    public UsersEntity addUsers(UsersEntity param) {
        Optional<UsersEntity> userExsist = R.findById(param.getId_user());
        if(userExsist.isPresent()){
            throw new RuntimeException("User ID " +param.getId_user() + " Sudah Ada");
        }
        else{
            return R.save(param);
        }

    }

    public void delUser(int param){
        R.deleteById(param);
    }
}
