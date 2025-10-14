package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AcceuilController {

    @FXML
    private Button reservationButton; // Déclaration du bouton Réservation
    @FXML
    private Button ticketButton; // Déclaration du bouton Ticket
    @FXML
    private Button materielButton; // Déclaration du bouton Matériel
    @FXML
    private Button placeParkingButton; // Déclaration du bouton Place Parking
    @FXML
    private Button  serviceButton;
   
    @FXML
    private Text bienvenueText; // Déclaration du texte de bienvenue
     @FXML
private Button connexionButton; 
    @FXML
    private void initialize() {
        // Initialisation des événements des boutons
        reservationButton.setOnAction(event -> handleReservationClick());
        ticketButton.setOnAction(event -> handleTicketClick());
        materielButton.setOnAction(event -> handleMaterielClick());
        placeParkingButton.setOnAction(event -> handlePlaceParkingClick());
         serviceButton.setOnAction(event -> handleserviceClick());

    }

    private void handleReservationClick() {
        changeScene("/View/reservation.fxml");
    }

    private void handleTicketClick() {
        changeScene("/View/ticket.fxml"); // Assurez-vous de créer ce fichier FXML
    }

    private void handleMaterielClick() {
        changeScene("/View/Materiel.fxml"); // Assurez-vous de créer ce fichier FXML
    }
    private void handleserviceClick() {
        changeScene("/View/Service.fxml"); // Assurez-vous de créer ce fichier FXML
    }

    private void handlePlaceParkingClick() {
        changeScene("/View/ConsultetPlacesVides.fxml"); // Assurez-vous de créer ce fichier FXML
    }

    private void changeScene(String fxmlFile) {
        try {
            // Charger le fichier FXML correspondant
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Obtenir la scène actuelle et changer de scène
            Stage stage = (Stage) reservationButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void loadreservation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/reservation.fxml"));
            Parent accueilPage = loader.load();
            Stage stage = (Stage) connexionButton.getScene().getWindow();
            Scene scene = new Scene(accueilPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }
    @FXML
    private void loadTICKET() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ticket.fxml"));
            Parent ticketPage = loader.load();
            Stage stage = (Stage) connexionButton.getScene().getWindow();
            Scene scene = new Scene(ticketPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }
        @FXML
    private void loadPlaceParking() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ConsultetPlacesVides.fxml"));
            Parent placeparkingPage = loader.load();
            Stage stage = (Stage) connexionButton.getScene().getWindow();
            Scene scene = new Scene(placeparkingPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }
        @FXML
    private void loadService() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Service.fxml"));
            Parent servicePage = loader.load();
            Stage stage = (Stage) connexionButton.getScene().getWindow();
            Scene scene = new Scene(servicePage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page de service.");
        }
    }

}