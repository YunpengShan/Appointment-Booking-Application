package ca.sheridancollege.shanyunp.database;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.shanyunp.beans.Appointment;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public ArrayList<Appointment> getAllAppointments() {
		String q = "Select * from appointment";
		ArrayList<Appointment> appointments =
		(ArrayList<Appointment>)jdbc.query(q,
		new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return appointments;
	}
	
	public void addAppointment(Appointment appointment) {
		MapSqlParameterSource namedParameters =
		new MapSqlParameterSource();
		namedParameters.addValue("id", appointment.getId());
		namedParameters.addValue("firstName", appointment.getFirstName());
		namedParameters.addValue("email", appointment.getEmail());
		namedParameters.addValue("appointmentDate", appointment.getAppointmentDate());
		namedParameters.addValue("appointmentTime", appointment.getAppointmentTime());
		String query="INSERT INTO appointment (id, firstName, email, appointmentDate, appointmentTime) "
				+ "VALUES (:id, :firstName, :email, :appointmentDate, :appointmentTime)";
		jdbc.update(query, namedParameters);
	}
	
	public Appointment selectAppointment(int id) {
		Appointment i = null;
		String sql = "select * from appointment where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		ArrayList<Appointment> appointments = 
				(ArrayList<Appointment>)jdbc.query(sql, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
		i = appointments.get(0);
		return i;
	}
	
	public void updateAppointment(Appointment i) {
		String sql = "update appointment set firstName=:firstName, email=:email, " +
						"appointmentDate=:appointmentDate, appointmentTime=:appointmentTime where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", i.getId());
		namedParameters.addValue("firstName", i.getFirstName());
		namedParameters.addValue("email", i.getEmail());
		namedParameters.addValue("appointmentDate", i.getAppointmentDate());
		namedParameters.addValue("appointmentTime", i.getAppointmentTime());
		jdbc.update(sql, namedParameters);
	}
	
	
	public void deleteAppointment(int id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String sql = "Delete from appointment where id = :id";
		namedParameters.addValue("id", id);
		jdbc.update(sql, namedParameters);
		
	}
}
