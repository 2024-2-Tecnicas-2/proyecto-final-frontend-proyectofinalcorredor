/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Model.EventoInterface;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import proyectofinalagenda.AgendaControlador;

/**
 *
 * @author NECSOFT
 */
public class AgendaVista extends JFrame{
    private AgendaControlador controlador;
	private JTextArea textArea;
	private JTextField nombreField;
	private JTextField descripcionField;
	private JTextField fechaField;
	private JTextField tipoField;
	private JTextField participantesField;
	private JTextField lugarField;
	private JTextField recordatoriosField;

	public AgendaVista() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setTitle("Agenda");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);

		JPanel inputPanel = crearPanelDeEntrada();
		add(inputPanel, BorderLayout.NORTH);

		setVisible(true);
	}

	private JPanel crearPanelDeEntrada() {
		JPanel inputPanel = new JPanel(new GridLayout(0, 2));
		inputPanel.add(new JLabel("Nombre:"));
		nombreField = new JTextField();
		inputPanel.add(nombreField);

		inputPanel.add(new JLabel("Descripción:"));
		descripcionField = new JTextField();
		inputPanel.add(descripcionField);

		inputPanel.add(new JLabel("Fecha (yyyy-MM-dd):"));
		fechaField = new JTextField();
		inputPanel.add(fechaField);

		inputPanel.add(new JLabel("Tipo de Evento (tarea, reunión, recordatorio):"));
		tipoField = new JTextField();
		inputPanel.add(tipoField);

		inputPanel.add(new JLabel("Participantes (separados por comas):"));
		participantesField = new JTextField();
		inputPanel.add(participantesField);

		inputPanel.add(new JLabel("Lugar:"));
		lugarField = new JTextField();
		inputPanel.add(lugarField);

		inputPanel.add(new JLabel("Recordatorios (separados por comas):"));
		recordatoriosField = new JTextField();
		inputPanel.add(recordatoriosField);

		JButton agregarButton = new JButton("Agregar Evento");
		agregarButton.addActionListener(e -> agregarEvento());
		inputPanel.add(agregarButton);

		JButton mostrarButton = new JButton("Mostrar Eventos");
		mostrarButton.addActionListener(e -> controlador.mostrarEventos());
		inputPanel.add(mostrarButton);

		JButton mostrarDiasButton = new JButton("Mostrar Dí­as Faltantes");
		mostrarDiasButton.addActionListener(e -> controlador.mostrarDiasFaltantes());
		inputPanel.add(mostrarDiasButton);

		JButton eliminarButton = new JButton("Eliminar Evento");
		eliminarButton.addActionListener(e -> eliminarEvento());
		inputPanel.add(eliminarButton);

		JButton editarButton = new JButton("Editar Evento");
		editarButton.addActionListener(e -> editarEvento());
		inputPanel.add(editarButton);

		JButton completarButton = new JButton("Marcar Tarea Como Completada");
		completarButton.addActionListener(e -> marcarTareaComoCompletada());
		inputPanel.add(completarButton);

		return inputPanel;
	}

	public void setControlador(AgendaControlador controlador) {
		this.controlador = controlador;
	}

	public void mostrarEventos(List<Object> eventos) {
		textArea.setText("");
		for (Object obj : eventos) {
			if (obj instanceof EventoInterface) {
				EventoInterface evento = (EventoInterface) obj;
				textArea.append(evento.toString() + "\n");
			}
		}
	}

	public void mostrarDiasFaltantes(List<Object> eventos) {
		textArea.setText("");
		for (Object obj : eventos) {
			if (obj instanceof EventoInterface) {
				EventoInterface evento = (EventoInterface) obj;
				textArea.append(
						"Evento: " + evento.getNombre() + ", Dí­as faltantes: " + evento.getDiasFaltantes() + "\n");
			}
		}
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	private void agregarEvento() {
		String nombre = nombreField.getText();
		String descripcion = descripcionField.getText();
		String fecha = fechaField.getText();
		String tipoEvento = tipoField.getText();
		String participantes = participantesField.getText();
		String lugar = lugarField.getText();
		String recordatorios = recordatoriosField.getText();
		controlador.agregarEvento(nombre, descripcion, fecha, tipoEvento, participantes, lugar,
				recordatorios);
	}

	private void eliminarEvento() {
		String nombreEvento = nombreField.getText();
		controlador.eliminarEventoDesdeVista(nombreEvento);
	}

	private void editarEvento() {
		String nombreEvento = nombreField.getText();
		String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
		String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:");
		String nuevaFecha = JOptionPane.showInputDialog("Nueva fecha (yyyy-MM-dd):");
		controlador.editarEventoDesdeVista(nombreEvento, nuevoNombre, nuevaDescripcion, nuevaFecha);
	}

	private void marcarTareaComoCompletada() {
		String nombreEvento = nombreField.getText();
		controlador.marcarTareaComoCompletada(nombreEvento);
	}
}
    

