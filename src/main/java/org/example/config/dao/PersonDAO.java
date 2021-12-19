package org.example.config.dao;

import org.example.config.models.Person1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.config.constants.SqlConstants.SELECT_ALL_FROM_PERSON;

@Component
public class PersonDAO {
    private final static Logger logger = LoggerFactory.getLogger(PersonDAO.class);
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person1> index() {
        logger.info(String.valueOf(jdbcTemplate.getDataSource()));
        return jdbcTemplate.query(SELECT_ALL_FROM_PERSON, new PersonMApper());
    }

    public Person1 show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person1.class), id)
                .stream().findAny().orElse(null);
    }

    public void save (Person1 person){
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person1 updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatePerson.getName(), updatePerson.getAge(),
                updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
