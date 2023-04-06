package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.UsersEntity;

import com.example.challenge_chapter_4.Response.UserResponse;
import com.example.challenge_chapter_4.Response.UserResponseGenerator;
import com.example.challenge_chapter_4.Service.UsersService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/Users")
public class UsersController {
    @Autowired
    UsersService us;
    @Autowired
    UserResponseGenerator urg;

    @GetMapping()
    public List<UsersEntity> getAll(){
        return us.getAll();
    }

    @GetMapping(value = "/findUser/{id_user}") //yang ada di dalam {} disamakan dengan
    public UserResponse<UsersEntity> getById(@PathVariable int id_user){ // yang ini "id_user"
        try {
            UsersEntity user = us.getById(id_user);
            return urg.succsesResponse(user,"Sukses Mencari Data " + user.getId_user());
        }
        catch (Exception e){
            return urg.failedResponse(e.getMessage());
        }

    }

    @PostMapping(value = "/addUsers")
    public UserResponse<UsersEntity> addUsers(@RequestBody UsersEntity param){
        try {
            UsersEntity user = us.addUsers(param);
            return urg.succsesResponse(user, "Sukses Menambahkan Data " + user.getId_user());
        }
        catch (Exception e){
            return urg.failedResponse(e.getMessage());
        }

    }

    @PutMapping(value = "/updateUser")
    public UserResponse<UsersEntity> updateUser(@RequestBody UsersEntity param){

        try {
            UsersEntity user = us.updateUser(param);
            return urg.succsesResponse(user,"Sukses Update Data " +user.getId_user());
        }
        catch (Exception e){
            return urg.failedResponse(e.getMessage());
        }

    }

    @DeleteMapping(value = "/deleteUser/{id_user}")
    public void deleteUser(@PathVariable int id_user){
        try {
            us.delUser(id_user);
        }
        catch (Exception e){

        }
    }
}
