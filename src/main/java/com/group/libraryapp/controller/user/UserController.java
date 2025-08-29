package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService = new UserService();

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //회원가입
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    //회원목록
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs,rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");

            return new UserResponse(id,name,age);
        });
    }

    //회원수정
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(jdbcTemplate, request);
    }

    //회원삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        String readSql = "SELECT * FROM user WHERE name = ? ";
        boolean isUserNotExits = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if(isUserNotExits){
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ? ";
        jdbcTemplate.update(sql,name);
    }

}
