package org.example.config.dao;

import org.example.config.models.Person1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMApper implements RowMapper<Person1> {
    @Override
    public Person1 mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person1 person = new Person1();

        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}
