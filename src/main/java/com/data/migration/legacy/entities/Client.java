package com.data.migration.legacy.entities;

import lombok.Data;

@Data
public class Client {
    private Long ID;
    private String cpf;
    private String name;
    private String street_name;
    private String complement;
    private String number;
    private String neighborhood;
    private String phone_number;
    private String email;
    private String city;
    private String state;

}
