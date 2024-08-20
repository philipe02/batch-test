package com.example.batchtest.dto;

import com.example.batchtest.models.PersonEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToCreateDTO {
    private Integer id;
    private Integer usersToCreateId;
    private PersonEntity person;
    private String username;
    private String password;
}
