/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NECSOFT
 */
public class AgendaModelo {
    private List<Object> eventos;

	public AgendaModelo() {
		this.eventos = new ArrayList<>();
	}

	public void agregarEvento(EventoInterface evento) throws EventoDuplicadoException {
		if (buscarEventoPorNombre(evento.getNombre()) != null) {
			throw new EventoDuplicadoException("El evento con el nombre '" + evento.getNombre() + "' ya existe.");
		}
		eventos.add(evento);
	}

	public EventoInterface buscarEventoPorNombre(String nombre) {
		for (Object obj : eventos) {
			if (obj instanceof EventoInterface) {
				EventoInterface evento = (EventoInterface) obj;
				if (evento.getNombre().equalsIgnoreCase(nombre)) {
					return evento;
				}
			}
		}
		return null;
	}

	public void editarEventoNombre(EventoInterface evento, String nuevoNombre) {
		evento.setNombre(nuevoNombre);
	}

	public void editarEventoDescripcion(EventoInterface evento, String nuevaDescripcion) {
		evento.setDescripcion(nuevaDescripcion);
	}

	public List<Object> obtenerEventos() {
		return eventos;
	}

	public void eliminarEvento(EventoInterface evento) {
		eventos.remove(evento);
	}
    
}
