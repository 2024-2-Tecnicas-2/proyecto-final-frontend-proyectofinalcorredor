/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NECSOFT
 */
public class EventoFactory {
    public static EventoInterface crearEvento(String tipoEvento, String nombre, String descripcion, String fecha,
			String participantes, String lugar, String recordatorios) {
		switch (tipoEvento.toLowerCase()) {
		case "tarea":
			return new Tarea(nombre, descripcion, fecha, false);
		case "reuniÃ³n":
			List<String> participantesList = new ArrayList<>();
			if (participantes != null && !participantes.isEmpty()) {
				participantesList = Arrays.asList(participantes.split(","));
			}
			return new Reunion(nombre, descripcion, fecha, lugar, participantesList);
		case "recordatorio":
			List<String> recordatoriosList = new ArrayList<>();
			if (recordatorios != null && !recordatorios.isEmpty()) {
				recordatoriosList = Arrays.asList(recordatorios.split(","));
			}
			return new Recordatorio(nombre, descripcion, fecha, recordatoriosList);
		default:
			throw new IllegalArgumentException("Tipo de evento no vÃ¡lido: " + tipoEvento);
		}
	}
}
    

