package movies.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import movies.model.Director;
import movies.model.Film;

public class DirectorRepository {
	private static final String SQL_UPDATE_DIRECTOR = "UPDATE TB_DIRECTORS SET NAME_DIRECTOR = ?, AGE = ? WHERE ID_DIRECTOR = ?;";
	//private static final String SQL_SELECT_DIRECTOR = "SELECT ID_DIRECTOR FROM TB_MOVIES WHERE id = ?;";
	private static final String SQL_INSERT_DIRECTOR = "INSERT INTO TB_DIRECTORS (NAME_DIRECTOR, AGE) VALUES (?, ?)";
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final RowMapper<Director> directorsRowMapper = new RowMapper<Director>() {
		public Director mapRow(ResultSet rs, int rowNum) throws SQLException {
			Director d = new Director();
			d.setId(rs.getInt("ID_DIRECTOR"));
			d.setAge(rs.getInt("AGE"));
			d.setName(rs.getString("NAME_DIRECTOR"));
			return d;
		}
	};
	public Object updateDirector(final Film f) {
		int rows = jdbcTemplate.update(SQL_UPDATE_DIRECTOR, f.getDirector().getName(), f.getDirector().getAge(),
				f.getDirector().getId());
		return Collections.singletonMap("success", rows == 1);
	};
	
	public PreparedStatement insertDirector(final Film f,Connection con) throws SQLException{
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_DIRECTOR, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, f.getDirector().getName());
		ps.setInt(2, f.getDirector().getAge());
		return ps;
	}
}



