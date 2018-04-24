package com.hotel.view;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.hotel.control.Conexion;
import com.hotel.control.EmpleadoCtrl;
import com.hotel.control.FacturaCtrl;
import com.hotel.control.HabitacionCtrl;
import com.hotel.control.HospedajeCtrl;
import com.hotel.control.HuespedCtrl;
import com.hotel.control.RegimenCtrl;
import com.hotel.control.RegistroActividadCtrl;
import com.hotel.entity.Empleado;
import com.hotel.entity.Factura;
import com.hotel.entity.Habitacion;
import com.hotel.entity.Hospedaje;
import com.hotel.entity.Huesped;
import com.hotel.entity.Regimen;
import com.hotel.entity.RegistroActividad;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

public class PantallaController {

	private Conexion conexion;

	@FXML
	private Pane fondo;
	@FXML
	private ComboBox<String> cb_entidad;
	@FXML
	private Button bt_ejecutar;
	@FXML
	private Label lb_campo;
	@FXML
	private RadioButton rb_registrar;
	@FXML
	private RadioButton rb_listar;
	@FXML
	private RadioButton rb_actualizar;
	@FXML
	private RadioButton rb_buscar;
	@FXML
	private DatePicker dp_calendarioL;
	@FXML
	private DatePicker dp_calendarioP;
	@FXML
	private CheckBox cb_fecha;
	@FXML
	private ProgressBar progressBar;

	@FXML
	public void initialize() {
		cb_entidad.setItems(FXCollections.observableArrayList("Huesped", "Regimen", "Empleado", "Habitacion"));
		conexion = new Conexion();
	}

	public void botonEjecutar() throws Throwable {
		try {
			if (rb_registrar.isSelected())
				registrar();
			if (rb_listar.isSelected())
				listar();
			if (rb_buscar.isSelected())
				buscar();
			if (rb_actualizar.isSelected())
				actualizar();
		} catch (NullPointerException e) {
			Mensaje.showMessageDialog(null, "No seleccionaste una entidad");
		}
	}

	public void botonHospedar() throws Throwable {
		HospedajeCtrl h = new HospedajeCtrl(conexion);

		ArrayList<String> lista = new ArrayList<String>();
		lista.add("Registrar");
		lista.add("Actualizar");
		lista.add("Buscar");
		lista.add("Listar");

		switch ((String) Mensaje.showChoiceDialog(lista, "¿Qué quieres hacer?")) {
		case "Registrar":
			if (dp_calendarioL.getValue() != null && dp_calendarioP.getValue() != null) {

				try {
					ArrayList<Huesped> lista1 = new HuespedCtrl(conexion).list();
					lb_campo.setText("");
					for (int i = 0; i < lista1.size(); i++) {
						lb_campo.setText(lb_campo.getText() + lista1.get(i).toString() + "\n");
					}
					int codigoHuesped = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código del huesped:\n"));

					ArrayList<Habitacion> lista2 = new HabitacionCtrl(conexion).list();
					String m1 = "";
					for (int i = 0; i < lista2.size(); i++) {
						m1 += lista2.get(i).toString() + "\n";
					}

					HabitacionCtrl ha = new HabitacionCtrl(conexion);
					Habitacion h1;
					int codigoHabitacion;
					do {
						codigoHabitacion = Integer
								.parseInt(Mensaje.showInputDialog(m1 + "\nIngresa el código de la habitación:\n"));
						h1 = new Habitacion(codigoHabitacion);
						ha.search(h1);
					} while (!h1.isDisponibilidad());

					h1.setDisponibilidad(false);
					ha.cambiarDisponibilidad(h1);

					ArrayList<Regimen> lista3 = new RegimenCtrl(conexion).list();
					lb_campo.setText("");
					for (int i = 0; i < lista3.size(); i++) {
						lb_campo.setText(lb_campo.getText() + lista3.get(i).toString() + "\n");
					}
					int codigoRegimen = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código del régimen:\n"));

					Hospedaje hospedaje = new Hospedaje(codigoHuesped, codigoHabitacion, codigoRegimen,
							new java.sql.Date(Date
									.from(dp_calendarioL.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
									.getTime()),
							new java.sql.Date(Date
									.from(dp_calendarioP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())
									.getTime()));

					if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {
						h.insert(hospedaje);
						Mensaje.showMessageDialog(null, "¡Se han ingresado los datos!");
						lb_campo.setText("Código hospedaje: " + hospedaje.getCodigoHospedaje() + "\nCódigo habitación: "
								+ hospedaje.getCodigoHabitacion() + "\nCódigo huésped: " + hospedaje.getCodigoHuesped()
								+ "\nCódigo régimen: " + hospedaje.getCodigoRegimen());
					}

				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else {
				Mensaje.showMessageDialog(null, "Primero debes ingresar las fechas :(");
			}
			break;
		case "Listar":
			ArrayList<Hospedaje> listaH = h.list();

			lb_campo.setText("");
			for (int i = 0; i < listaH.size(); i++) {
				lb_campo.setText(lb_campo.getText() + listaH.get(i).toString() + "\n");
			}
			break;
		case "Buscar":
			int codigo = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código a buscar: "));

			Hospedaje hospedaje = new Hospedaje(codigo);

			h.search(hospedaje);

			lb_campo.setText(hospedaje.toString());

			break;
		case "Actualizar":
			if (dp_calendarioL.getValue() != null && dp_calendarioP.getValue() != null) {
				int codigoHuesped = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código huesped:"));
				int codigoHabitacion = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código habitacion:"));
				int codigoRegimen = Integer.parseInt(Mensaje.showInputDialog("Ingresa el código regimen:"));
				java.sql.Date fechaLlegada = null;
				java.sql.Date fechaPartida = null;

				fechaLlegada = new java.sql.Date(Date
						.from(dp_calendarioL.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
				fechaPartida = new java.sql.Date(Date
						.from(dp_calendarioP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
				hospedaje = new Hospedaje(codigoHuesped, codigoHabitacion, codigoRegimen, fechaLlegada, fechaPartida);

				h.update(hospedaje);
			} else {
				Mensaje.showMessageDialog(null, "Primero debes ingresar las fechas :(");
			}
		default:
			break;
		}

	}

	public void emitirFactura() throws Throwable {
		try {
			registrarActividad();

			int codigo = Integer.parseInt(Mensaje.showInputDialog("Ingresa el codigo del registro de actividad: "));

			FacturaCtrl facturaControl = new FacturaCtrl(conexion);
			Factura factura = new Factura(codigo, "");
			factura.setDescripcion(facturaControl.emitir(factura));
			facturaControl.insert(factura);

			Mensaje.showMessageDialog(null, factura.getDescripcion());
			progressBar.setProgress(1);
		} catch (Exception e) {

		}
	}

	private void registrarActividad() throws Throwable {
		int codigoHospedaje = Integer.parseInt(Mensaje.showInputDialog("Ingresa el codigo del hospedaje a facturar: "));
		int codigoEmpleado = Integer.parseInt(Mensaje.showInputDialog("Ingresa el codigo del empleado: "));

		RegistroActividadCtrl registroActividadControl = new RegistroActividadCtrl(conexion);
		RegistroActividad registroActividad = new RegistroActividad(codigoHospedaje, codigoEmpleado);

		if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {
			registroActividadControl.insert(registroActividad);

			ArrayList<RegistroActividad> lista = registroActividadControl.list();
			for (int i = 0; i < lista.size(); i++) {
				lb_campo.setText(lista.get(i).toString());
			}
		}
	}

	private void registrar() throws Throwable {
		switch (cb_entidad.getValue().toString()) {
		case "Huesped":
			HuespedCtrl h = new HuespedCtrl(conexion);

			String nombres = Mensaje.showInputDialog("Ingresa nombres: ");
			String apellidos = Mensaje.showInputDialog("Ingresa los apellidos: ");

			int telefono = 0;
			boolean leido = false;
			do {
				try {
					telefono = Integer.parseInt(Mensaje.showInputDialog("Ingresa el telefono:"));
					leido = true;
				} catch (Exception e) {
					Mensaje.showMessageDialog(null, "Te equivocaste al ingresar el telefono");
				}
			} while (!leido);

			String direccion = Mensaje.showInputDialog("Ingresa la direccion: ");
			String NIT = Mensaje.showInputDialog("Ingresa el NIT: ");

			if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {
				h.insert(new Huesped(nombres, apellidos, telefono, direccion, NIT));
				Mensaje.showMessageDialog(null, "Se han ingresado los datos correctamente!");
				lb_campo.setText("Ingresaste los siguientes datos\nNombres: " + nombres + "\nApellidos: " + apellidos
						+ "\nTelefono: " + telefono + "\nDireccion: " + direccion + "\nNIT: " + NIT);
			}
			break;
		case "Empleado":
			EmpleadoCtrl e = new EmpleadoCtrl(conexion);

			nombres = Mensaje.showInputDialog("Ingresa nombres: ");
			apellidos = Mensaje.showInputDialog("Ingresa los apellidos: ");
			double salario = Double.parseDouble(Mensaje.showInputDialog("Ingresa el salario: "));
			direccion = Mensaje.showInputDialog("Ingresa la direccion: ");

			telefono = 0;
			leido = false;
			do {
				try {
					telefono = Integer.parseInt(Mensaje.showInputDialog("Ingresa el telefono:"));
					leido = true;
				} catch (Exception er) {
				}
			} while (!leido);

			if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {
				e.insert(new Empleado(nombres, apellidos, salario, direccion, telefono));
				Mensaje.showMessageDialog(null, "Se han ingresado los datos correctamente!");
				lb_campo.setText("Ingresaste los siguientes datos\nNombres: " + nombres + "\nApellidos: " + apellidos
						+ "\nSalario: " + salario + "\nDireccion: " + direccion + "\nTelefono: " + telefono);
			}
			break;
		case "Habitacion":
			HabitacionCtrl hab = new HabitacionCtrl(conexion);

			String nombre = Mensaje.showInputDialog("Ingresa el nombre:");
			String descripcion = Mensaje.showInputDialog("Ingresa la descripcion:");

			double precio = 0;
			leido = false;
			do {
				try {
					precio = Integer.parseInt(Mensaje.showInputDialog("Ingresa el precio de la habitacion:"));
					leido = true;
				} catch (Exception er) {
					Mensaje.showMessageDialog(null, "Te equivocaste al ingresar el precio");
				}
			} while (!leido);

			boolean disponibilidad = Mensaje.showConfirmDialog(null, "¿Esta disponible?");

			if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {

				hab.insert(new Habitacion(descripcion, nombre, precio, disponibilidad));
				Mensaje.showMessageDialog(null, "Se han ingresado los datos correctamente!");
				String mensaje = "";
				if (disponibilidad) {
					mensaje = "Esta disponible";
				} else {
					mensaje = "No esta disponible";
				}
				lb_campo.setText("Ingresaste los siguientes datos\nNombre: " + nombre + "\nDescripcion: " + descripcion
						+ "\nPrecio: " + precio + "\nDisponibilidad: " + mensaje);
			}
			break;
		case "Regimen":
			RegimenCtrl reg = new RegimenCtrl(conexion);

			descripcion = Mensaje.showInputDialog("Ingresa la descripcion de regimen: ");
			precio = 0;
			leido = false;
			do {
				try {
					precio = Double.parseDouble(Mensaje.showInputDialog("Ingresa el precio del regimen:"));
					leido = true;
				} catch (Exception er) {
					Mensaje.showMessageDialog(null, "Se han ingresado los datos correctamente!");
				}
			} while (!leido);

			if (Mensaje.showConfirmDialog(null, "¿Quieres ingresar estos datos?")) {
				reg.insert(new Regimen(descripcion, precio));
				Mensaje.showMessageDialog(null, "Se han ingresado los datos correctamente!");
				lb_campo.setText(
						"Ingresaste los siguientes datos\nDescripcion: " + descripcion + "\nPrecio: " + precio);
			}
			break;
		}
	}

	private void listar() throws Throwable {
		switch (cb_entidad.getValue().toString()) {
		case "Huesped":
			HuespedCtrl h = new HuespedCtrl(conexion);

			ArrayList<Huesped> lista = h.list();

			lb_campo.setText("Lista de huespedes:\n");
			for (int i = 0; i < lista.size(); i++) {
				lb_campo.setText(lb_campo.getText() + lista.get(i).toString() + "\n");
			}
			break;
		case "Empleado":
			EmpleadoCtrl e = new EmpleadoCtrl(conexion);

			ArrayList<Empleado> lista1 = e.list();

			lb_campo.setText("Lista de empleados:\n");
			for (int i = 0; i < lista1.size(); i++) {
				lb_campo.setText(lb_campo.getText() + lista1.get(i).toString() + "\n");
			}

			break;
		case "Habitacion":
			HabitacionCtrl ha = new HabitacionCtrl(conexion);

			ArrayList<Habitacion> lista2 = ha.list();

			lb_campo.setText("Lista de habitaciones:\n");
			for (int i = 0; i < lista2.size(); i++) {
				lb_campo.setText(lb_campo.getText() + lista2.get(i).toString() + "\n");
			}

			break;
		case "Regimen":
			RegimenCtrl hc = new RegimenCtrl(conexion);

			ArrayList<Regimen> lista3 = hc.list();

			lb_campo.setText("Lista de regimen:\n");
			for (int i = 0; i < lista3.size(); i++) {
				lb_campo.setText(lb_campo.getText() + lista3.get(i).toString() + "\n");
			}

			break;
		}
	}

	private void buscar() throws Throwable {
		lb_campo.setText("Mostrando datos:\n");

		int codigo = Integer.parseInt(Mensaje.showInputDialog(
				"Ingrese el codigo de " + cb_entidad.getValue().toString().toLowerCase() + " que desea buscar: "));

		switch (cb_entidad.getValue().toString()) {
		case "Huesped":
			try {
				HuespedCtrl huesped = new HuespedCtrl(conexion);
				Huesped h = new Huesped(codigo);
				huesped.search(h);
				lb_campo.setText(h.toString());
			} catch (Exception e) {
				Mensaje.showMessageDialog(null, "Debe ingresar un numero :(");
			}
			break;
		case "Empleado":
			try {
				EmpleadoCtrl empleado = new EmpleadoCtrl(conexion);
				Empleado emp = new Empleado(codigo);
				empleado.search(emp);
				lb_campo.setText(emp.toString());
			} catch (Exception e) {
				Mensaje.showMessageDialog(null, "Debe ingresar un numero :(");
			}
			break;
		case "Habitacion":
			try {
				HabitacionCtrl habitacion = new HabitacionCtrl(conexion);
				Habitacion hab = new Habitacion(codigo);
				habitacion.search(hab);
				lb_campo.setText(hab.toString());
			} catch (Exception e) {
				Mensaje.showMessageDialog(null, "Debe ingresar un numero :(");
			}
			break;
		case "Regimen":
			try {
				RegimenCtrl regimen = new RegimenCtrl(conexion);
				Regimen reg = new Regimen(codigo);
				regimen.search(reg);
				lb_campo.setText(reg.toString());
			} catch (Exception e) {
				Mensaje.showMessageDialog(null, "Debe ingresar un numero :(");
			}
			break;
		}
	}

	private void actualizar() throws Throwable {
		int codigo = Integer.parseInt(Mensaje.showInputDialog(
				"Ingrese el codigo de " + cb_entidad.getValue().toString().toLowerCase() + " que desea actualizar: "));

		switch (cb_entidad.getValue().toString()) {
		case "Huesped":
			HuespedCtrl huesped = new HuespedCtrl(conexion);

			String nombres = Mensaje.showInputDialog("Ingrese los nuevos nombres: ");
			String apellidos = Mensaje.showInputDialog("Ingrese los nuevos apellidos: ");
			int telefono = Integer.parseInt(Mensaje.showInputDialog("Ingrese el nuevo telefono: "));
			String direccion = Mensaje.showInputDialog("Ingrese la nueva direccion: ");
			String NIT = Mensaje.showInputDialog("Ingrese el nuevo NIT: ");

			Huesped h = new Huesped(codigo, nombres, apellidos, telefono, direccion, NIT);
			huesped.update(h);
			Mensaje.showMessageDialog(null, "Se ha actualizado correctamente ;)");
			break;
		case "Empleado":
			EmpleadoCtrl e = new EmpleadoCtrl(conexion);

			double salario = Double.parseDouble("Ingrese el salario:");
			nombres = Mensaje.showInputDialog("Ingrese los nuevos nombres: ");
			apellidos = Mensaje.showInputDialog("Ingrese los nuevos apellidos: ");
			telefono = Integer.parseInt(Mensaje.showInputDialog("Ingrese el nuevo telefono: "));
			direccion = Mensaje.showInputDialog("Ingrese la nueva direccion: ");

			Empleado e1 = new Empleado(codigo, salario, nombres, apellidos, direccion, telefono);
			e.update(e1);
			Mensaje.showMessageDialog(null, "Se ha actualizado correctamente ;)");
			break;
		case "Habitacion":
			HabitacionCtrl ha = new HabitacionCtrl(conexion);

			nombres = Mensaje.showInputDialog("Ingresa el nombre:");
			String descripcion = Mensaje.showInputDialog("Ingresa descripcion:");
			double precio = Double.parseDouble(Mensaje.showInputDialog("Ingresa el precio:"));
			boolean disponibilidad = Mensaje.showConfirmDialog(null, "¿Esta disponible?");

			Habitacion habitacion = new Habitacion(codigo, nombres, descripcion, precio, disponibilidad);
			ha.update(habitacion);
			Mensaje.showMessageDialog(null, "Se ha actualizado correctamente ;)");
			break;
		case "Regimen":
			RegimenCtrl r = new RegimenCtrl(conexion);

			descripcion = Mensaje.showInputDialog("Ingresa descripcion:");
			precio = Double.parseDouble(Mensaje.showInputDialog("Ingresa el precio:"));

			Regimen re = new Regimen(codigo, descripcion, precio);
			r.update(re);
			Mensaje.showMessageDialog(null, "Se ha actualizado correctamente ;)");
			break;
		}

	}

}
