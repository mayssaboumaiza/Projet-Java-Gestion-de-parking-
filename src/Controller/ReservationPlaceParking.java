package Controller;

import Model.gestion_utilisateur.Agent;
import Model.gestion_utilisateur.client;
import Model.gestion_reservation_et_paiement.Reservation;
import Model.gestion_reservation_et_paiement.ticket;
import gestion_de_parking.ValeurNegativeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.VBox;

public class ReservationPlaceParking {

    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField adresseMailTextField;
    @FXML
    private TextField numCINTextField;
    @FXML
    private TextField telTextField;
    @FXML
    private TextField identifiantTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField statutTextField;
    @FXML
    private TextField numReservationTextField;

    @FXML
    private Button ajouterClientButton;
    @FXML
    private Button supprimerClientButton;
    @FXML
    private Button buttonRetourner;
    @FXML
    private Button buttonvoirticket;
    
    @FXML
private Button modifierClientButton;

    @FXML
    private TableView<client> clientTableView;
    @FXML
    private TableColumn<client, String> nomColumn;
    @FXML
    private TableColumn<client, String> prenomColumn;
    @FXML
    private TableColumn<client, String> adresseMailColumn;
    @FXML
    private TableColumn<client, Integer> numCINColumn;
    @FXML
    private TableColumn<client, Integer> telColumn;
    @FXML
    private TableColumn<client, Integer> identifiantColumn;
    @FXML
    private TableColumn<client, String> typeColumn;
    @FXML
    private TableColumn<client, String> statutColumn;
    @FXML
    private TableColumn<client, Integer> numReservationColumn;
    

    private Agent agent;
    private ObservableList<client> clientList;

    public ReservationPlaceParking() throws ValeurNegativeException  {
        this.agent = new Agent(1, "AgentNom", "AgentPrenom", 12345678, 11111111, "agent@mail.com", "Gestionnaire", true, "password");
        this.clientList = FXCollections.observableArrayList();
    }
    // Méthode pour récupérer la liste des clients
    public List<client> getclients() {
        return agent.getClients();// Récupérer les entrepreneurs de l'administrateur
    }
    @FXML
    public void initialize() throws ValeurNegativeException {
        // Configuration des colonnes
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getnom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getprenom()));
        adresseMailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getadressemail()));
        numCINColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCIN()).asObject());
        telColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getnumtlf()).asObject());
        identifiantColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getidf()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().gettype()));
        statutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getstatut_fidele()));
        
        numReservationColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNumReservation()));
    // Remplir la table avec les entrepreneurs
  clientTableView.setItems( clientList);
  chargerClients();

        // Action pour le bouton Ajouter Entrepreneur
       ajouterClientButton.setOnAction(event -> {
            try {
                ajouterClient();
            } catch (ValeurNegativeException ex) {
                Logger.getLogger(ReservationPlaceParking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Action pour le bouton Supprimer Entrepreneur
        supprimerClientButton.setOnAction(event -> supprimerClient());
        
    // Action pour le bouton Modifier Client
    modifierClientButton.setOnAction(event -> {
            try {
                modifierClient();
            } catch (ValeurNegativeException ex) {
                Logger.getLogger(ReservationPlaceParking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
  private void modifierClient() throws ValeurNegativeException {
    System.out.println("Méthode modifierClient appelée."); // Ajoutez ceci pour vérifier l'appel

    // Récupérer le client sélectionné
    client selectedClient = clientTableView.getSelectionModel().getSelectedItem();

    if (selectedClient == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un client à modifier.");
        alert.showAndWait();
        return;
    }

    // Créer un TextArea pour saisir les nouvelles informations
    TextArea textArea = new TextArea();
    textArea.setText("Nom: " + selectedClient.getnom() + "\n" +
                     "Prénom: " + selectedClient.getprenom() + "\n" +
                     "Adresse e-mail: " + selectedClient.getadressemail() + "\n" +
                     "numCIN: " + selectedClient.getCIN() + "\n" +
                     "Téléphone: " + selectedClient.getnumtlf()+ "\n" +
                     "Identifiant: " + selectedClient.getidf() + "\n" +
                     "Type: " + selectedClient.gettype()+ "\n" +
                     "Statut: " + selectedClient.getstatut_fidele() + "\n" +
                     "numReservation: " + selectedClient.getNumReservation() + "\n");
    textArea.setWrapText(true);

    // Créer une nouvelle fenêtre pour afficher le TextArea
    Stage modifierStage = new Stage();
    modifierStage.setTitle("Modifier Client");

    // Créer un bouton pour enregistrer les modifications
    Button saveButton = new Button("Enregistrer");
    saveButton.setOnAction(event -> {
        String[] lignes = textArea.getText().split("\n");
        if (lignes.length >= 9) { // Assurez-vous que toutes les lignes sont présentes
            try {
                String nouveauNom = lignes[0].replace("Nom: ", "").trim();
                String nouveauPrenom = lignes[1].replace("Prénom: ", "").trim();
                String nouvelleAdresseMail = lignes[2].replace("Adresse e-mail: ", "").trim();
                int nouveauNumCIN = Integer.parseInt(lignes[3].replace("numCIN: ", "").trim());
                int nouveauTel = Integer.parseInt(lignes[4].replace("Téléphone: ", "").trim());
                int nouvelIdentifiant = Integer.parseInt(lignes[5].replace("Identifiant: ", "").trim());
                String nouveauType = lignes[6].replace("Type: ", "").trim();
                String nouveauStatut = lignes[7].replace("Statut: ", "").trim();
                int nouveauNumReservation = Integer.parseInt(lignes[8].replace("numReservation: ", "").trim());

                // Créer un nouveau client avec les nouvelles informations
                ticket monTicket = new ticket(1, 10, 12);
                Reservation reservation = new Reservation(nouveauNumReservation, monTicket, "5/2/2021", "hadil");
                client updatedClient = new client(nouvelIdentifiant, nouveauNom, nouveauPrenom, nouveauTel, nouveauNumCIN, nouvelleAdresseMail, nouveauType, nouveauStatut, reservation);

                // Modifier le client dans l'agent
                client result = agent.modifierclient(nouvelIdentifiant, updatedClient);

                if (result == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun client trouvé avec cet identifiant.");
                    alert.showAndWait();
                } else {
                    // Mettre à jour la liste observable
                    int index = clientList.indexOf(selectedClient);
                    if (index != -1) {
                        clientList.set(index, updatedClient);
                    }
                    // Réinitialiser les champs de texte
                    clearFields();
                    modifierStage.close(); // Fermer la fenêtre de modification
                                        // Afficher un message de confirmation
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modification réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("Client modifié avec succès !");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Certains champs contiennent des valeurs non valides. Veuillez vérifier vos saisies.");
                alert.showAndWait();
            } catch (ValeurNegativeException ex) {
                Logger.getLogger(ReservationPlaceParking.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les informations requises.");
            alert.showAndWait();
        }
    });

    // Créer un layout pour le TextArea et le bouton
    VBox vbox = new VBox(textArea, saveButton);
    Scene scene = new Scene(vbox, 400, 300);
    modifierStage.setScene(scene);
    modifierStage.show();
}

// Méthode pour réinitialiser les champs de texte
  





    private void ajouterClient() throws ValeurNegativeException {
        try {
            // Récupérer les valeurs des champs de texte
            String nom = nomTextField.getText();
            String prenom = prenomTextField.getText();
            String adresseMail = adresseMailTextField.getText();
            int numCIN = Integer.parseInt(numCINTextField.getText());
            int tel = Integer.parseInt(telTextField.getText());
            int identifiant = Integer.parseInt(identifiantTextField.getText());
            String type = typeTextField.getText();
            String statut = statutTextField.getText();
            int numReservation = Integer.parseInt(numReservationTextField.getText());
           ticket monTicket = new ticket(1, 10, 12);

            Reservation reservation = new Reservation(numReservation, monTicket,"5/2/2021","hadil");

            // Créer un nouveau client
           
            client client = new client(identifiant, nom, prenom, tel, numCIN, adresseMail, type, statut, reservation);

            // Ajouter le client à l'agent
            agent.ajouterclient(reservation, client);

            // Ajouter le client à la liste observable
            clientList.add(client);

            // Réinitialiser les champs de texte
            clearFields();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez vérifier les valeurs saisies.");
            alert.showAndWait();
            
        }
        enregistrerClients();
    }
    

    private void supprimerClient() {
        // Récupérer le client sélectionné
        client selectedClient = clientTableView.getSelectionModel().getSelectedItem();

        if (selectedClient == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un client à supprimer.");
            alert.showAndWait();
            return;
        }

        // Supprimer le client de l'agent
        agent.supprimerclient(selectedClient.getNumReservation());

        // Supprimer le client de la liste observable
        clientList.remove(selectedClient);
        enregistrerClients();
    }
    private static final String FILE_PATH = "C:\\Users\\hadil\\OneDrive\\Bureau\\Gestion_de_Parking\\src\\clients.txt"; // Chemin du fichier

private void enregistrerClients() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (client c : clientList) {
            writer.write(c.getidf() + "," + c.getnom() + "," + c.getprenom() + "," + c.getCIN() + "," + c.getnumtlf() + "," + c.getadressemail() + "," + c.gettype() + "," + c.getstatut_fidele() + "," + c.getNumReservation());
            writer.newLine();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enregistrement réussi");
        alert.setHeaderText(null);
        alert.setContentText("Les données des clients ont été enregistrées avec succès !");
        alert.showAndWait();
    } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'enregistrement des clients.");
        alert.showAndWait();
    }
}
private void chargerClients() throws ValeurNegativeException {
    File file = new File(FILE_PATH);
    if (!file.exists()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier des clients n'existe pas.");
        alert.showAndWait();
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 9) { // Assurez-vous que vous avez 9 champs
                int identifiant = Integer.parseInt(data[0]);
                String nom = data[1];
                String prenom = data[2];
                int numCIN = Integer.parseInt(data[3]);
                int tel = Integer.parseInt(data[4]);
                String adresseMail = data[5];
                String type = data[6];
                String statut = data[7];
                int numReservation = Integer.parseInt(data[8]);

                // Créer un nouveau client
                ticket monTicket = new ticket(1, 10, 12);
                Reservation reservation = new Reservation(numReservation, monTicket, "5/2/2021", "hadil");
                client newClient = new client(identifiant, nom, prenom, tel, numCIN, adresseMail, type, statut, reservation);

                // Ajouter le client à la liste observable
                clientList.add(newClient);
            } else {
                System.out.println("Ligne invalide dans le fichier : " + line);
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chargement réussi");
        alert.setHeaderText(null);
        alert.setContentText("Les données des clients ont été chargées avec succès !");
        alert.showAndWait();
    } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors du chargement des clients.");
        alert.showAndWait();
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur de format dans le fichier des clients.");
        alert.showAndWait();
    }
}
    private void clearFields() {
        nomTextField.clear();
        prenomTextField.clear();
        adresseMailTextField.clear();
        numCINTextField.clear();
        telTextField.clear();
        identifiantTextField.clear();
        typeTextField.clear();
        statutTextField.clear();
        numReservationTextField.clear();
    }

     @FXML
    private void loadHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Acceuil.fxml"));
            Parent accueilPage = loader.load();
            Stage stage = (Stage) buttonRetourner.getScene().getWindow();
            Scene scene = new Scene(accueilPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }

// Méthode pour obtenir le numéro de réservation sélectionné
private int getSelectedReservationNumber() {
    client selectedReservation = clientTableView.getSelectionModel().getSelectedItem();
    if (selectedReservation != null) {
        int num = selectedReservation.getNumReservation(); // Assurez-vous que cette méthode existe dans votre classe client
        System.out.println("Numéro de réservation sélectionné : " + num); // Debug
        return num;
    }
    return -1; // Ou une autre valeur par défaut pour indiquer qu'aucune réservation n'est sélectionnée
}
@FXML
private void loadTicketPage(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ticket.fxml"));
        Parent ticketPage = loader.load();
        
        // Obtenir le contrôleur de la page Ticket
        TicketController ticketController = loader.getController();
        
        // Obtenez le numéro de réservation sélectionné
        int selectedReservationNumber = getSelectedReservationNumber();
        
        // Vérifiez si une réservation est sélectionnée
        if (selectedReservationNumber != -1) {
            ticketController.setNumReservation(selectedReservationNumber); // Passez le numéro de réservation au TicketController
        } else {
            // Gérer le cas où aucune réservation n'est sélectionnée (par exemple, afficher un message d'erreur)
            System.out.println("Aucune réservation sélectionnée.");
        }
        
        Stage stage = (Stage) buttonvoirticket.getScene().getWindow();
        Scene scene = new Scene(ticketPage);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Erreur lors du chargement de la page Ticket.");
    }
}
   

}


