package Controller;

import Model.gestion_ressources.StatutMateriel;
import Model.gestion_utilisateur.Agent;
import gestion_de_parking.ValeurNegativeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MaterielController {

    @FXML
    private TextField nomMField;

    @FXML
    private TextField statutField;

    @FXML
    private TextField heureInstallationField;

    @FXML
    private TableView<Materiel> materielTable;

    @FXML
    private TableColumn<Materiel, String> nomMColumn;

    @FXML
    private TableColumn<Materiel, String> statutColumn;

    @FXML
    private TableColumn<Materiel, String> heureInstallationColumn;

    @FXML
    private Button ajouterButton;

    @FXML
    private Button supprimerButton;

    @FXML
    private Button retournerButton;

    // Liste observable pour stocker les données des matériels
    private ObservableList<Materiel> materielList;
    private Agent agent;
      public MaterielController() throws ValeurNegativeException  {
        this.agent = new Agent(1, "AgentNom", "AgentPrenom", 12345678, 11111111, "agent@mail.com", "Gestionnaire", true, "password");
        this.materielList = FXCollections.observableArrayList();
    }
    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        nomMColumn.setCellValueFactory(new PropertyValueFactory<>("nomM"));
        statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));
        heureInstallationColumn.setCellValueFactory(new PropertyValueFactory<>("heureInstallation"));

        // Initialisation de la liste observable
        materielList = FXCollections.observableArrayList();
        materielTable.setItems(materielList);
        chargerMateriels();
        // Action pour le bouton Supprimer Entrepreneur
        supprimerButton.setOnAction(event ->supprimerMateriel(event));
              ajouterButton.setOnAction(event ->ajouterMateriel(event));
         retournerButton.setOnAction(event ->loadHomePage(event));

    }

    @FXML
    private void ajouterMateriel(ActionEvent event) {
        // Récupérer les données des champs de texte
        String nomM = nomMField.getText();
        String statut = statutField.getText();
        String heureInstallation = heureInstallationField.getText();

        if (!nomM.isEmpty() && !statut.isEmpty() && !heureInstallation.isEmpty()) {
            // Ajouter un nouvel objet Materiel à la liste
            Materiel materiel = new Materiel(nomM, statut, heureInstallation);
            materielList.add(materiel);

            // Réinitialiser les champs de texte
            nomMField.clear();
            statutField.clear();
            heureInstallationField.clear();
            enregistrerMateriels();
        }
    }

    @FXML
    private void supprimerMateriel(ActionEvent event) {
        // Supprimer l'élément sélectionné dans le TableView
        Materiel selectedMateriel = materielTable.getSelectionModel().getSelectedItem();
        if (selectedMateriel != null) {
            materielList.remove(selectedMateriel);
        }
        enregistrerMateriels();
    }
  
    @FXML
    private void loadHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Acceuil.fxml"));
            Parent accueilPage = loader.load();
            Stage stage = (Stage) retournerButton.getScene().getWindow();
            Scene scene = new Scene(accueilPage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la page d'accueil.");
        }
    }

    // Classe interne pour représenter les données des matériels
    public static class Materiel {
        private String nomM;
        private String statut;
        private String heureInstallation;

        public Materiel(String nomM, String statut, String heureInstallation) {
            this.nomM = nomM;
            this.statut = statut;
            this.heureInstallation = heureInstallation;
        }

        public String getNomM() {
            return nomM;
        }

        public void setNomM(String nomM) {
            this.nomM = nomM;
        }

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public String getHeureInstallation() {
            return heureInstallation;
        }

        public void setHeureInstallation(String heureInstallation) {
            this.heureInstallation = heureInstallation;
        }
    }
    private static final String FILE_PATH = "C:\\Users\\hadil\\OneDrive\\Bureau\\Gestion_de_Parking\\src\\materiel.txt";
    private void enregistrerMateriels() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Materiel materiel : materielList) {
                writer.write(materiel.getNomM() + "," + materiel.getStatut() + "," + materiel.getHeureInstallation());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement des matériels.");
        }
    }

    private void chargerMateriels() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Materiel materiel = new Materiel(data[0], data[1], data[2]);
                    materielList.add(materiel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des matériels.");
        }
    }
}
