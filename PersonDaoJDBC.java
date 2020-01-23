package com.jdbcjpa.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jdbcjpa.model.Person;

@Repository
public class PersonDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setDateBirth(rs.getTimestamp("birth_date"));
			person.setLocation(rs.getString("location"));
			return person;
		}

		
		
	}
	
	public List<Person> findAll() {
		return
				jdbcTemplate.query("Select * from Person", 
						new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findById(int id) {
		return 
			jdbcTemplate.queryForObject("Select * from Person where id = ?", 
									new Object[] {id},
								new PersonRowMapper());
	}
	
	public int deleteById(int id) {
		return 
				jdbcTemplate.update("delete from Person where id = ?", 
										new Object[] {id});
	}
	
	public int insert(Person person) {
		return 
			jdbcTemplate.update("insert into person values(?,?,?,?)",
					new Object[] { person.getId(),person.getName(),new Timestamp(person.getDateBirth().getTime())
							,person.getLocation()});
							
	}
	
	public int update(Person person) {
		return 
			jdbcTemplate.update("update person set name = ?,birth_date = ?,"
					+ "location = ? where id = ?",
					new Object[] { person.getName(),new Timestamp(person.getDateBirth().getTime())
							,person.getLocation(),person.getId()});
							
	}
}
