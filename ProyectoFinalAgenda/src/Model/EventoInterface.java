/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

/**
 *
 * @author NECSOFT
 */
public interface EventoInterface {
    	String getNombre();

	void setNombre(String nombre);

	String getDescripcion();

	void setDescripcion(String descripcion);

	String getFecha();

	void setFecha(String fecha);

	long getDiasFaltantes();
}
