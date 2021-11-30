package com.example.restaurante.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.restaurante.models.Mesa;
import com.example.restaurante.repos.MesaRepository;
import com.example.restaurante.services.MesaService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MesaController implements Initializable {
	MesaRepository repo = new MesaRepository();
	MesaService service = new MesaService(repo);

	@FXML
	VBox items;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList(this.service.getAllAtivos());
	}

	public void updateList(List<Mesa> items) {
		ObservableList<Node> nodes = this.items.getChildren();
		nodes.clear();

		for (Mesa item : items) {
			Text numero = new Text(Integer.toString(item.getNumero()));
			numero.setFont(new Font(22));

			Text ocupado = new Text(item.isOcupado() ? "Ocupado" : "Livre");

			HBox hbox = new HBox(numero, ocupado);
			hbox.setSpacing(10);
			hbox.setAlignment(Pos.BOTTOM_LEFT);

			nodes.add(hbox);
		}
	}
}
