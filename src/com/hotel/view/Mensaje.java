package com.hotel.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Mensaje {

	public static void showMessageDialog(Object object, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file::../../resources/img/icono.png"));
		alert.setTitle("Mensaje");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		alert.showAndWait();
	}

	public static String showInputDialog(String mensaje) throws Throwable {
		String respuesta = "";

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Ingresar datos");
		dialog.setHeaderText(null);
		dialog.setContentText(mensaje);
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file::../../resources/img/icono.png"));

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			respuesta = result.get();
		}

		return respuesta;
	}

	public static boolean showConfirmDialog(Object object, String mensaje) throws Throwable {
		boolean confirmacion = false;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacion");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file::../../resources/img/icono.png"));

		ButtonType buttonTypeOne = new ButtonType("Si");
		ButtonType buttonTypeTwo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			confirmacion = true;
		}

		return confirmacion;
	}

	public static Object showChoiceDialog(ArrayList<?> lista, String mensaje) throws Throwable {
		List<String> choices = new ArrayList<>();

		for (int i = 0; i < lista.size(); i++) {
			choices.add(lista.get(i).toString());
		}

		ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
		dialog.setTitle("Elige una opcion");
		dialog.setHeaderText(" ");
		dialog.setContentText(mensaje);

		Optional<String> result = dialog.showAndWait();

		int indice = 0;

		if (result.isPresent()) {
			for (int i = 0; i < lista.size(); i++) {
				if (result.get() == lista.get(i).toString()) {
					indice = i;
				}
			}
		}

		return lista.get(indice);
	}

}
