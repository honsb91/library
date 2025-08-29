package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {

    public void updateUser(JdbcTemplate jdbcTemplate, UserUpdateRequest request){
        // id를 기준으로 유저가 존재하는지 확인
        String readSql = "SELECT * FROM user WHERE id = ? ";
        boolean isUserNotExits = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if(isUserNotExits){
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ? ";
        jdbcTemplate.update(sql, request.getName(), request.getId());

    }
}
