/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinalagenda;

import Model.AgendaModelo;
import Model.EventoFactory;
import Model.EventoInterface;
import Model.EventoNoEncontradoException;
import Model.FechaInvalidaException;
import Model.Tarea;
import Model.TipoEventoInvalidoException;
import Presentacion.AgendaVista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;



public class AgendaControlador {
	private AgendaModelo agendaModelo;
	private AgendaVista vista;

	public AgendaControlador(AgendaModelo agendaModelo, AgendaVista vista) {
		this.agendaModelo = agendaModelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	public void mostrarEventos() {
		List<Object> eventos = agendaModelo.obtenerEventos();
		vista.mostrarEventos(eventos);
	}

	public void mostrarDiasFaltantes() {
		List<Object> eventos = agendaModelo.obtenerEventos();
		vista.mostrarDiasFaltantes(eventos);
	}

	public void agregarEvento(String nombre, String descripcion, String fecha, String tipoEvento, String participantes,
			String lugar, String recordatorios) {
		try {
			validarEntradaEvento(nombre, descripcion, fecha, tipoEvento);
			EventoInterface evento = EventoFactory.crearEvento(tipoEvento, nombre, descripcion, fecha, participantes,
					lugar, recordatorios);
			agendaModelo.agregarEvento(evento);

			if (LocalDate.parse(fecha).equals(LocalDate.now())) {
				vista.mostrarMensaje("¡El evento se lleva a cabo hoy!");
			}

			vista.mostrarMensaje("Evento agregado exitosamente.");
		} catch (Exception e) {
			vista.mostrarMensaje("Error: " + e.getMessage());
		}
	}

	private void validarEntradaEvento(String nombre, String descripcion, String fecha, String tipoEvento)
			throws FechaInvalidaException, TipoEventoInvalidoException {
		if (nombre == null || nombre.isEmpty() || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del evento no puede estar vacío.");
		}

		if (descripcion == null || descripcion.isEmpty() || descripcion.isBlank()) {
			throw new IllegalArgumentException("La descripción del evento no puede estar vacía.");
		}

		if (!validarFecha(fecha)) {
			throw new FechaInvalidaException("Formato de fecha no válido (yyyy-MM-dd).");
		}

		if (!tipoEvento.equalsIgnoreCase("tarea") && !tipoEvento.equalsIgnoreCase("reunión")
				&& !tipoEvento.equalsIgnoreCase("recordatorio")) {
			throw new TipoEventoInvalidoException("Tipo de evento no válido (tarea, reunión, recordatorio).");
		}
	}

	private boolean validarFecha(String fecha) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate.parse(fecha, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public void editarEventoNombre(EventoInterface evento, String nuevoNombre) {
		agendaModelo.editarEventoNombre(evento, nuevoNombre);
		vista.mostrarMensaje("Nombre del evento actualizado exitosamente.");
	}

	public void editarEventoDescripcion(EventoInterface evento, String nuevaDescripcion) {
		agendaModelo.editarEventoDescripcion(evento, nuevaDescripcion);
		vista.mostrarMensaje("Descripción del evento actualizada exitosamente.");
	}

	public void editarEventoFecha(EventoInterface evento, LocalDate nuevaFecha) {
		evento.setFecha(nuevaFecha.toString());
		vista.mostrarMensaje("Fecha del evento actualizada exitosamente.");
	}

	private LocalDate parsearFecha(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(fecha, formatter);
	}

	public void agregarEventoDesdeVista(String nombre, String descripcion, String fecha, String tipoEvento,
			String participantes, String lugar, String recordatorios) {
		try {
			agregarEvento(nombre, descripcion, fecha, tipoEvento, participantes, lugar, recordatorios);
		} catch (Exception e) {
			vista.mostrarMensaje("Error al agregar el evento: " + e.getMessage());
		}
	}

	public void editarEventoDesdeVista(String nombreEvento, String nuevoNombre, String nuevaDescripcion,
			String nuevaFecha) {
		try {
			EventoInterface evento = agendaModelo.buscarEventoPorNombre(nombreEvento);
			if (evento == null) {
				throw new EventoNoEncontradoException("No se encontró ningún evento con ese nombre.");
			}

			if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
				editarEventoNombre(evento, nuevoNombre);
			}
			if (nuevaDescripcion != null && !nuevaDescripcion.isEmpty()) {
				editarEventoDescripcion(evento, nuevaDescripcion);
			}
			if (nuevaFecha != null && !nuevaFecha.isEmpty()) {
				LocalDate nuevaFechaLocalDate = parsearFecha(nuevaFecha);
				editarEventoFecha(evento, nuevaFechaLocalDate);
			}
		} catch (EventoNoEncontradoException e) {
			vista.mostrarMensaje("Error: " + e.getMessage());
		}
	}

	public void eliminarEventoDesdeVista(String nombreEvento) {
		try {
			EventoInterface evento = agendaModelo.buscarEventoPorNombre(nombreEvento);
			if (evento == null) {
				throw new EventoNoEncontradoException("No se encontró ningún evento con ese nombre.");
			}
			agendaModelo.eliminarEvento(evento);
			vista.mostrarMensaje("Evento eliminado exitosamente.");
		} catch (EventoNoEncontradoException e) {
			vista.mostrarMensaje("Error: " + e.getMessage());
		}
	}

	public void marcarTareaComoCompletada(String nombreEvento) {
		try {
			EventoInterface evento = agendaModelo.buscarEventoPorNombre(nombreEvento);
			if (evento == null) {
				throw new EventoNoEncontradoException("No se encontró ningún evento con ese nombre.");
			}
			if (evento instanceof Tarea) {
				((Tarea) evento).setCompletada(true);
				vista.mostrarMensaje("Tarea marcada como completada.");
			} else {
				vista.mostrarMensaje("El evento no es una tarea.");
			}
		} catch (EventoNoEncontradoException e) {
			vista.mostrarMensaje("Error: " + e.getMessage());
		}
	}
}