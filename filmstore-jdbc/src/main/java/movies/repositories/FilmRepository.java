package movies.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import movies.model.Director;
import movies.model.Film;

@Repository
public class FilmRepository {
	private static final String SQL_INSERT_DIRECTOR = "INSERT INTO TB_DIRECTORS (NAME_DIRECTOR, AGE) VALUES (?, ?)";
	private static final String SQL_INSERT_MOVIE = "INSERT INTO TB_MOVIES (TITLE, CATEGORY, ID_DIRECTOR) VALUES (?, ?, ?);";
	private static final String SQL_LIST_MOVIE = "SELECT * FROM TB_MOVIES M , TB_DIRECTORS D WHERE M.ID_DIRECTOR = D.ID_DIRECTOR;";
	private static final String SQL_SELECT_MOVIE = "SELECT * FROM TB_MOVIES M , TB_DIRECTORS D WHERE M.ID_DIRECTOR = D.ID_DIRECTOR AND ID_FILM = ?;";
//	private static final String SQL_SELECT_DIRECTOR = "SELECT ID_DIRECTOR FROM TB_MOVIES WHERE id = ?;";
	//private static final String SQL_UPDATE_DIRECTOR = "UPDATE TB_DIRECTORS SET NAME_DIRECTOR = ?, AGE = ? WHERE ID_DIRECTOR = ?;";
	private static final String SQL_UPDATE_MOVIE = "UPDATE TB_MOVIES SET TITLE = ?, CATEGORY = ? WHERE ID_FILM = ?;";
	private static final String SQL_DELETE_MOVIE = "DELETE FROM TB_MOVIES WHERE ID_FILM = ?;";

	@Autowired
	JdbcTemplate jdbcTemplate;
	DirectorRepository dr;
	private final RowMapper<Film> moviesRowMapper = new RowMapper<Film>() {
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
			Director d = new Director();
			d.setId(rs.getInt("ID_DIRECTOR"));
			d.setAge(rs.getInt("AGE"));
			d.setName(rs.getString("NAME_DIRECTOR"));

			Film f = new Film();
			f.setId(rs.getInt("ID_FILM"));
			f.setTitle(rs.getString("TITLE"));
			f.setCategory(rs.getString("CATEGORY"));
			f.setDirector(d);

			return f;
		};
	};

	public List<Film> list() {
		List<Film> movies = jdbcTemplate.query(SQL_LIST_MOVIE, moviesRowMapper);
		return movies;
	};

	public Object update(final Film f, int id) {
		dr.updateDirector(f);
		int rows = jdbcTemplate.update(SQL_UPDATE_MOVIE, f.getTitle(), f.getCategory(), id);
		Object o = Collections.singletonMap("success", rows == 1);
		return o;
	}

	public Object delete(int id) {
		int rows = jdbcTemplate.update(SQL_DELETE_MOVIE, id);
		return Collections.singletonMap("success", rows == 1);
	}

	public Film getMovie(int id){
		Film f = jdbcTemplate.queryForObject(SQL_SELECT_MOVIE, new Object[] { id }, moviesRowMapper);
		return f;
	}

	public Object create(final Film f) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_DIRECTOR, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, f.getDirector().getName());
				ps.setInt(2, f.getDirector().getAge());
				return ps;
			}
		};

		jdbcTemplate.update(psc, keyHolder);

		PreparedStatementCreator psc2 = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, f.getTitle());
				ps.setString(2, f.getCategory());
				ps.setInt(3, keyHolder.getKey().intValue());
				return ps;
			}
		};

		KeyHolder keyHolder2 = new GeneratedKeyHolder();
		jdbcTemplate.update(psc2, keyHolder2);
		f.setId(keyHolder2.getKey().intValue());
		f.getDirector().setId(keyHolder.getKey().intValue());

		return f;
	};

}
