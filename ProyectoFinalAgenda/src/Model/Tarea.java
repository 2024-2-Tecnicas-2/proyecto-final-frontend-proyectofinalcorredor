/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NECSOFT
 */
public class Tarea extends Evento {
    private boolean completada;

	public Tarea(String nombre, String descripcion, String fecha, boolean completada) {
		super(nombre, descripcion, fecha);
		this.completada = completada;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	@Override
	public String toString() {
		return super.toString() + " Tarea{" + " completada=" + completada + '}';
	}
}
