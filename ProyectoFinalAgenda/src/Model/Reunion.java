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
public class Reunion extends Evento{
    	private List<String> participantes;
	private String lugar;

	public Reunion(String nombre, String descripcion, String fecha, String lugar, List<String> participantes) {
		super(nombre, descripcion, fecha);
		this.participantes = participantes;
		this.lugar = lugar;
	}

	public List<String> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<String> participantes) {
		this.participantes = participantes;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return super.toString() + " Reunion{" + "participantes=" + participantes + ", lugar='" + lugar + '\'' + '}';
	}
    
}
