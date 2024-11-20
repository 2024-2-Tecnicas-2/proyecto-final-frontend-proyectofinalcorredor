/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author NECSOFT
 */
public class Recordatorio extends Evento {
    private List<String> recordatorios;

	public Recordatorio(String nombre, String descripcion, String fecha, List<String> recordatorios) {
		super(nombre, descripcion, fecha);
		this.recordatorios = recordatorios;
	}

	public List<String> getRecordatorios() {
		return recordatorios;
	}

	public void setRecordatorios(List<String> recordatorios) {
		this.recordatorios = recordatorios;
	}

	@Override
	public String toString() {
		return super.toString() + " Recordatorio{" + "recordatorios=" + recordatorios + '}';
	}
}
