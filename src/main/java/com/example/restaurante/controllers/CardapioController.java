package com.example.restaurante.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.restaurante.models.Prato;
import com.example.restaurante.repos.PratoRepository;
import com.example.restaurante.services.CardapioService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CardapioController implements Initializable {
	PratoRepository repo = new PratoRepository();
	CardapioService service = new CardapioService(repo);

	@FXML
	VBox items;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateList(this.service.getAll());
	}

	public void updateList(List<Prato> items) {
		ObservableList<Node> nodes = this.items.getChildren();
		nodes.clear();

		for (Prato item : items) {
			Text nome = new Text(item.getNome());
			nome.setFont(new Font(22));

			Text preco = new Text("R$ " + item.getPreco());
			Text ingredientes = new Text(item.getIngredientes());

			HBox titleBox = new HBox(nome, preco);
			titleBox.setSpacing(10);
			titleBox.setAlignment(Pos.BOTTOM_LEFT);

			VBox itemBox = new VBox(titleBox, ingredientes);
			itemBox.setSpacing(10);

			nodes.add(itemBox);
		}
	}
}
