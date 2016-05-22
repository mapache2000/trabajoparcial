package pe.ulima.edu.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import beans.Alumno;

public class AlumnoDAO extends ConexionDAO{

	public List<Alumno> obtenerAlumnos(int numSalas) throws ServletException{
		Connection conn = conectarse();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM alumnos");	
			ResultSet rs = ps.executeQuery();
			List<Alumno> al = new ArrayList<Alumno>();
			while(rs.next()){
				al.add(new Alumno(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8)));
			}
			desconectarse(conn);
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
			desconectarse(conn);
			throw new ServletException("Error SQL: " + e.getMessage());
		}
	}
	
	public Alumno obtenerAlumno(int id) throws ServletException{
		Connection conn = conectarse();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM alumnos WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Alumno alum = new Alumno(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8));
				desconectarse(conn);
				return alum;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			desconectarse(conn);
			throw new ServletException("Error SQL: " + e.getMessage());
		}
	}
	
	public void registrarAlumno(Alumno alum) throws ServletException{
		Connection conn = conectarse();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO alumnos (id, nombres, apellido_paterno, apellido_materno, dni, foto_url, codigo, id_colegio) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, alum.getId());
			ps.setString(2,alum.getNombres());
			ps.setString(3,alum.getApellido_paterno());
			ps.setString(4,alum.getApellido_materno());
			ps.setString(5,alum.getDni());
			ps.setString(6,alum.getFoto_url());
			ps.setInt(7, alum.getCodigo());
			ps.setInt(8, alum.getId_colegio());
			ps.execute();
			desconectarse(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			desconectarse(conn);
			throw new ServletException("Error SQL: " + e.getMessage());
		}
	}

	public void modificarAlumno(Alumno alum) throws ServletException{
		Connection conn = conectarse();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE alumnos set nombres=?, apellido_paterno=?, apellido_materno=?, dni=?, foto_url=?, codigo=?, id_colegio=? "
					+ " WHERE id=?");
			ps.setInt(1, alum.getId());
			ps.setString(2,alum.getNombres());
			ps.setString(3,alum.getApellido_paterno());
			ps.setString(4,alum.getApellido_materno());
			ps.setString(5,alum.getDni());
			ps.setString(6,alum.getFoto_url());
			ps.setInt(7, alum.getCodigo());
			ps.setInt(8, alum.getId_colegio());
			ps.execute();
			desconectarse(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			desconectarse(conn);
			throw new ServletException("Error SQL: " + e.getMessage());
		}
	}

	public void eliminarAlumno(int id) throws ServletException{
		Connection conn = conectarse();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM alumnos WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
			desconectarse(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			desconectarse(conn);
			throw new ServletException("Error SQL: " + e.getMessage());
		}
	}
}
