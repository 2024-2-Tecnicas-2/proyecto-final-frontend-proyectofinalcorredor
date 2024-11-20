/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author NECSOFT
 */
public class Evento implements EventoInterface{
    	private String nombre;
	private String descripcion;
	private LocalDate fecha;

	public Evento(String nombre, String descripcion, String fecha) throws DateTimeParseException {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = LocalDate.parse(fecha);
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String getFecha() {
		return fecha.toString();
	}

	@Override
	public void setFecha(String fecha) {
		this.fecha = LocalDate.parse(fecha);
	}

	@Override
	public long getDiasFaltantes() {
		return LocalDate.now().until(fecha).getDays();
	}

	@Override
	public String toString() {
		return "Evento{" + "nombre='" + nombre + '\'' + ", descripcion='" + descripcion + '\'' + ", fecha=" + fecha
				+ ", dÃ­as faltantes=" + getDiasFaltantes() + '}';
	}
}
