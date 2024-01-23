/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	String input = this.distanzaMinima.getText();
    	int inputNum= 0;
    	
    	try {
    		inputNum = Integer.parseInt(input);
    	} catch(NumberFormatException e) {
    		this.txtResult.setText("Input non valido.");
    		return;
    	}
    	
    	this.model.creaGrafo(inputNum);
    	
    	this.txtResult.appendText("Il grafo creato ha " + this.model.getGrafo().vertexSet().size() + " vertici.\n");
    	this.txtResult.appendText("Il grafo creato ha " + this.model.getGrafo().edgeSet().size() + " archi.\n");
    	
    	for(DefaultWeightedEdge e : this.model.getGrafo().edgeSet()) {
    		this.txtResult.appendText("Aereoporto di partenza: " + this.model.getGrafo().getEdgeSource(e).getAirportName() + "; Aereoporto di arrivo: " + this.model.getGrafo().getEdgeTarget(e).getAirportName() + "; Distanza media: " + this.model.getGrafo().getEdgeWeight(e) + "\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
