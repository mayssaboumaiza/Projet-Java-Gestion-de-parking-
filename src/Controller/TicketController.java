/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;
import Model.gestion_reservation_et_paiement.ticket;
import Model.gestion_utilisateur.client;
import gestion_de_parking.ValeurNegativeException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TicketController {

    @FXML
    private TextField hEmissionField;
    
    @FXML
    private TextField hExpirationField;
    
    @FXML
    private TextField numTicketField;
    
   
    
     @FXML
    private ComboBox<Integer> comboxRes;
    
    @FXML
    private Label ticketLabel; // Supposons que vous avez un Label pour afficher le numéro de ticket

    @FXML
    private Button ticketButton;
   
// Ajoutez ces champs à votre classe
@FXML
private CheckBox prixNuitCheckBox;

@FXML
private CheckBox prixJourCheckBox;

@FXML
private Label prixTicketLabel;
 // Méthode pour définir le numéro de ticket
    /*public void setNumTicket(int numTicket) {
        // Mettre à jour le Label avec le numéro de ticket
        ticketLabel.setText("Numéro de ticket : " + numTicket);
    }*/
    @FXML
    public void initialize() {
        // Initialisation si nécessaire
        ticketButton.setOnAction(event -> printTicket());
        prixJourCheckBox.setOnAction(event ->handlePrixJourCheckBox(event));
        prixNuitCheckBox.setOnAction(event ->handlePrixNuitCheckBox(event));
    }

    private void printTicket() {
        // Récupérer les valeurs des champs de texte
        String hEmission = hEmissionField.getText();
        String hExpiration = hExpirationField.getText();
       String numTicket = comboxRes.getValue().toString();
      
        
        // Logique pour imprimer le ticket ou afficher un message
        if (hEmission.isEmpty() || hExpiration.isEmpty() || numTicket.isEmpty() ) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
        } else {
            // Ici, vous pouvez ajouter la logique pour imprimer le ticket
            // Par exemple, générer un PDF, afficher un message de confirmation, etc.
            showAlert("Succès", "Ticket imprimé avec succès !");
        }
    }
    // Méthode pour gérer le prix nuit
@FXML
private void handlePrixNuitCheckBox(ActionEvent event) {
    if (prixNuitCheckBox.isSelected()) {
        prixJourCheckBox.setSelected(false); // Désélectionner l'autre case
        prixTicketLabel.setText("Prix Ticket: 5000");
    } else {
        updatePrixTicket();
    }
}

// Méthode pour gérer le prix jour
@FXML
private void handlePrixJourCheckBox(ActionEvent event) {
    if (prixJourCheckBox.isSelected()) {
        prixNuitCheckBox.setSelected(false); // Désélectionner l'autre case
        prixTicketLabel.setText("Prix Ticket: 8000");
    } else {
        updatePrixTicket();
    }
}

// Méthode pour mettre à jour le prix du ticket
private void updatePrixTicket() {
    if (prixNuitCheckBox.isSelected()) {
        prixTicketLabel.setText("Prix Ticket: 5000");
    } else if (prixJourCheckBox.isSelected()) {
        prixTicketLabel.setText("Prix Ticket: 8000");
    } else {
        prixTicketLabel.setText("Prix Ticket: 0");
    }
}
    public void setTicketDetails(ticket ticketDetails) {
    // Remplir les champs de texte avec les détails du ticket
   hEmissionField.setText(String.valueOf(ticketDetails.h_emm()));
    hExpirationField.setText(String.valueOf(ticketDetails.h_exp()));
    numTicketField.setText(String.valueOf(ticketDetails.num_ticket()));
    // Si vous avez des prix, vous devez les ajouter à votre record ou les gérer différemment
}

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     @FXML
    private void loadHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Acceuil.fxml"));
            Parent accueilPage = loader.load();
            Stage stage = (Stage) ticketButton.getScene().getWindow();
            Scene scene = new Scene(accueilPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }

    void setClient(client client) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
// Méthode pour définir le numéro de réservation dans le ComboBox
    public void setNumReservation(int numReservation) {
        comboxRes.getItems().add(numReservation); // Ajoute le numéro de réservation au ComboBox
        comboxRes.setValue(numReservation); // Définit le numéro de réservation comme valeur sélectionnée
        System.out.println( numReservation); // Debug
    }
  
}