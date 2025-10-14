/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.gestion_ressources.ServiceMaintenance;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ServiceController {

    

    @FXML
    private TextField txtBesoinDeReparation;

    @FXML
    private TextField txtCoutEstime;

    @FXML
    private TextField txtDureeEstimee;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSupprimer;
      @FXML
    private Button btnretourner;

    @FXML
    private TableView<ServiceMaintenance> tableServices;

    @FXML
    private TableColumn<ServiceMaintenance, Boolean> colBesoinDeReparation;

    

    @FXML
    private TableColumn<ServiceMaintenance, Float> colCoutEstime;

    @FXML
    private TableColumn<ServiceMaintenance, Integer> colDureeEstimee;

    // Liste observable pour relier les données au TableView
    private ObservableList<ServiceMaintenance> servicesList = FXCollections.observableArrayList();
    private String equipement;

 @FXML
public void initialize() {
    // Vérifiez si les colonnes ne sont pas nulles avant de les configurer
    if (colBesoinDeReparation != null) {
        colBesoinDeReparation.setCellValueFactory(new PropertyValueFactory<>("besoinDeReparation"));
    } else {
        System.out.println("colBesoinDeReparation est null !");
    }

   

    if (colCoutEstime != null) {
        colCoutEstime.setCellValueFactory(new PropertyValueFactory<>("coutEstime")); 
    } else {
        System.out.println("colCoutEstime est null !");
    }

    if (colDureeEstimee != null) {
        colDureeEstimee.setCellValueFactory(new PropertyValueFactory<>("dureeEstimee")); 
    } else {
        System.out.println("colDureeEstimee est null !");
    }

    // Lier la liste observable au tableau
    tableServices.setItems(servicesList);
    btnSupprimer.setOnAction(event -> supprimerservice(event));
    btnAjouter.setOnAction(event -> ajouterService(event));
}
@FXML
    void ajouterService(ActionEvent event) {
        try {
            // Validation des champs
           
            boolean besoinDeReparation = Boolean.parseBoolean(txtBesoinDeReparation.getText());
            float coutEstime = Float.parseFloat(txtCoutEstime.getText());
            int dureeEstimee = Integer.parseInt(txtDureeEstimee.getText());

           

            // Créer et ajouter le matériel à la liste
            ServiceMaintenance serviceMaintenance = new ServiceMaintenance(besoinDeReparation,dureeEstimee, (int) coutEstime,equipement);
            servicesList.add(serviceMaintenance);

            txtBesoinDeReparation.clear();
            txtCoutEstime.clear();
            txtDureeEstimee.clear();

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Les champs coût estimé et durée estimée doivent être des nombres valides.", Alert.AlertType.ERROR);
        } catch (IllegalArgumentException e) {
            showAlert("Erreur", e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    void supprimerservice(ActionEvent event) {
        // Récupérer l'élément sélectionné dans le tableau
        ServiceMaintenance selectedMaterial = tableServices.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            servicesList.remove(selectedMaterial);
        } else {
            showAlert("Erreur", "Veuillez sélectionner un matériel à supprimer.", Alert.AlertType.WARNING);
        }
        
    }

    // Fonction pour afficher des alertes
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }



     @FXML
     private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Acceuil.fxml"));
            Parent accueilPage = loader.load();
            Stage stage = (Stage) btnretourner.getScene().getWindow();
            Scene scene = new Scene(accueilPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }
    
}