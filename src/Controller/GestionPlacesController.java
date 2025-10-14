package Controller;
import Model.gestion_utilisateur.Agent;
import Model.gestion_vehicule.Moto;
import Model.gestion_vehicule.PlaceParking;
import Model.gestion_vehicule.Vehicule;
import Model.gestion_vehicule.voiture;
import gestion_de_parking.ErreurMoto;
import gestion_de_parking.Erreurvoiture;
import gestion_de_parking.ValeurNegativeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TableView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

    public class GestionPlacesController {

        // Déclaration des éléments FXML
        @FXML
        private TableView<Vehicule> tablePlaces;
        @FXML
        private TableColumn<Vehicule,Integer> colPlaceId;
        @FXML
        private TableColumn<Vehicule, String> colTypeVehicule;
        @FXML
        private TableColumn<Vehicule, Integer> coldimension;
       

        @FXML
        private CheckBox voitureCheckBox;
        @FXML
        private CheckBox motoCheckBox;
         @FXML
        private Button retournerButton;
        @FXML
        private Button retourButton;
        @FXML
        private Button actualiserButton;

        @FXML
        private GridPane parkingGrid;
        
        @FXML
         private ObservableList<Vehicule> vehicules; 
    @FXML
    private TextField numMatriculeField; // Correspond au TextField pour le numéro de matricule

    @FXML
    private TextField dimensionsField; // Correspond au TextField pour les dimensions

   

    @FXML
    private TextField marqueField; // Correspond au TextField pour la validité

    @FXML
    private TextField numplaceParkingField; // Correspond au TextField pour la place de parking

    @FXML
    private Button pageAccueilButton; // Correspond au bouton "PageAcceuil"


        // Attributs du contrôleur
        private Agent agent;
        private String[] places = new String[6];  // Statut des places de parking
        private int currentPlaceIndex = 0;  // Indice de la place de parking actuellement sélectionnée

        public GestionPlacesController () throws ValeurNegativeException {
            // Initialisation
            this.agent = new Agent(12,"sana", "hhh",54111222,14425959,"jjjjjj","reparateur",true,"44444");
        }

        // Méthode pour initialiser l'affichage des places de parking
        @FXML
        public void initialize() {
            // Initialiser les places de parking
            for (int i = 0; i < places.length; i++) {
                places[i] = "libre";  // Par défaut, toutes les places sont libres
            }

            // Ajouter des rectangles pour les places de parking visuelles
            for (int i = 0; i < places.length; i++) {
                Rectangle rectangle = new Rectangle(150, 91);
                rectangle.setArcHeight(5.0);
                rectangle.setArcWidth(5.0);
                rectangle.setFill(Color.LIGHTGREEN);
                rectangle.setStroke(Color.BLACK);
                    rectangle.setId("rectanglePlace" + (i + 1)); // ID unique pour chaque rectangle

                //final int placeIndex = i;  // Utilisé dans l'event handler
                rectangle.setOnMouseClicked(event -> afficherDetailsPlace(event));

                parkingGrid.add(rectangle, i % 2, i / 2);
               
            }

            // Ajouter des labels pour les places
            for (int i = 0; i < places.length; i++) {
                Label label = new Label("Place " + (i + 1));
            label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
             parkingGrid.add(label, i % 2, i / 2);
            }
             // Initialisation de l'ObservableList
        vehicules = FXCollections.observableArrayList();
        
        // Liaison de l'ObservableList à la TableView
        tablePlaces.setItems(vehicules);
         // Action pour le bouton Supprimer Entrepreneur
        retourButton.setOnAction(event ->handleRetourClick());
        actualiserButton.setOnAction(event ->{
                try {
                    actualiser();
                } catch (ValeurNegativeException ex) {
                    Logger.getLogger(GestionPlacesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Erreurvoiture ex) {
                    Logger.getLogger(GestionPlacesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ErreurMoto ex) {
                    Logger.getLogger(GestionPlacesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
}

     
     
@FXML
        // Méthode pour obtenir une place de parking disponible
        private PlaceParking getPlaceDisponible() {
            for (int i = 0; i < places.length; i++) {
                if (places[i].equals("libre")) {
                    return PlaceParking.values()[i];  // Retourner la place libre
                }
            }
            return null;  // Si aucune place libre n'est trouvée
        }

        // Méthode pour obtenir l'indice d'une place de parking dans le tableau des places
        private int getPlaceIndex(PlaceParking place) {
            return place.ordinal();  // Utiliser l'ordinal pour obtenir l'indice de la place
        }
       

  @FXML
private void actualiser() throws ValeurNegativeException, Erreurvoiture, ErreurMoto {
    try {
        // Récupérer les valeurs des champs de texte
        int numMatricule = Integer.parseInt(numMatriculeField.getText());
        String marque = marqueField.getText();
        int dimension = Integer.parseInt(dimensionsField.getText());

        // Vérifiez si une place est disponible
        PlaceParking placeDisponible = getPlaceDisponible(); // Obtenez une place disponible
        if (placeDisponible == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aucune Place Disponible");
            alert.setHeaderText(null);
            alert.setContentText("Aucune place de parking disponible.");
            alert.showAndWait();
            return; // Sortir de la méthode si aucune place n'est disponible
        }

        int placeIndex = getPlaceIndex(placeDisponible); // Obtenez l'indice de la place

        // Vérifiez si la place est déjà occupée
        if (placeIndex >= 0 && placeIndex < places.length && !places[placeIndex].equals("libre")) {
            // Afficher un message d'alerte si la place est réservée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Place Occupée");
            alert.setHeaderText(null);
            alert.setContentText("La place est déjà occupée.");
            alert.showAndWait();
            return; // Sortir de la méthode si la place est occupée
        }

        // Vérifiez quel type de véhicule est sélectionné
        if (voitureCheckBox.isSelected()) {
            // Créer un nouveau véhicule
            voiture v = new voiture(numMatricule, marque, dimension);
            v.setPlaceParking(placeDisponible); // Affecter la place de parking au véhicule
            // Ajouter le véhicule à l'agent
            agent.ajouterVoiture(PlaceParking.LIBRE, v);
            places[placeIndex] = "occupée"; // Marquer la place comme occupée

            // Ajouter le véhicule à la liste observable
            vehicules.add(v);
        } else if (motoCheckBox.isSelected()) {
            Moto m = new Moto(numMatricule, marque, dimension);
            m.setPlaceParking(placeDisponible); // Affecter la place de parking au véhicule
            agent.ajoutermoto(placeDisponible.LIBRE, m);
            places[placeIndex] = "occupée"; // Marquer la place comme occupée
            vehicules.add(m);
        } else {
                       // Type de véhicule non sélectionné
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Type de Véhicule Non Sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un type de véhicule (voiture ou moto).");
            alert.showAndWait();
            return; // Sortir de la méthode si aucun type n'est sélectionné
        }

        // Mettre à jour le statut de la place après avoir ajouté un véhicule
        updatePlaceStatus(); // Mettre à jour l'affichage des places

        // Mettre à jour la table des véhicules
        colPlaceId.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("numMatricule"));
        colTypeVehicule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        coldimension.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("dimensions"));
        tablePlaces.setItems(vehicules);

    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de Format");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer des valeurs valides pour le numéro de matricule et les dimensions.");
        alert.showAndWait();
    } catch (Erreurvoiture | ErreurMoto e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur Inattendue");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur inattendue s'est produite : " + e.getMessage());
        alert.showAndWait();
    }
   
}   
@FXML
private void updatePlaceStatus() {
    for (int i = 0; i < places.length; i++) {
        // Vérifiez si l'enfant est un Rectangle avant de le caster
        if (i * 2 < parkingGrid.getChildren().size()) { // Vérifiez que l'index est valide
            Node child = parkingGrid.getChildren().get(i * 2); // Récupérer le rectangle
            if (child instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) child; // Cast sécurisé
                if (places[i].equals("libre")) {
                    rectangle.setFill(Color.GREEN);
                } else {
                    rectangle.setFill(Color.RED);
                }
            } else {
                System.out.println("L'enfant à l'indice " + (i * 2) + " n'est pas un Rectangle.");
            }
        } else {
            System.out.println("Index " + (i * 2) + " est hors limites pour le parkingGrid.");
        }
    }
}
        // Méthode pour gérer le clic sur une place de parking
@FXML
private void afficherDetailsPlace(MouseEvent event) {
    Rectangle clickedRectangle = (Rectangle) event.getSource();
      // Vérifier si l'ID est null
    String rectangleId = clickedRectangle.getId();
    if (rectangleId == null) {
        System.out.println("L'ID du rectangle est null !");
        return; // Sortir de la méthode si l'ID est null
    }
        int placeIndex = Integer.parseInt(clickedRectangle.getId().replace("rectanglePlace", "")) - 1;

    // Logique pour afficher les détails de la place
    if (places[placeIndex].equals("libre")) {
        clickedRectangle.setFill(Color.GREEN);
        System.out.println("Détails de la place " + (placeIndex + 1) + ": Libre");
    } else {
        clickedRectangle.setFill(Color.RED);
        System.out.println("Détails de la place " + (placeIndex + 1) + ": Occupée");
    }
}
@FXML
private void toSting(MouseEvent event) {
    System.out.println("Rectangle 1 clicked!");
    // Ajoutez ici la logique que vous souhaitez exécuter lors du clic
}

   @FXML
private void handleRetourClick() {
    // Récupérer le véhicule sélectionné
    Vehicule selectedVehicule = tablePlaces.getSelectionModel().getSelectedItem();

    if (selectedVehicule == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un véhicule à supprimer.");
        alert.showAndWait();
        return;
    }

    // Récupérer la place de parking du véhicule sélectionné
    PlaceParking place = selectedVehicule.getPlaceParking(); // Assurez-vous que cette méthode existe dans votre classe Vehicule

    try {
        // Supprimer le véhicule de l'agent
        agent.supprimerVoiture(place);
        
        // Supprimer le véhicule de la liste observable
        tablePlaces.getItems().remove(selectedVehicule); // Utilisez getItems() pour accéder à la liste observable
    } catch (Erreurvoiture e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de la suppression du véhicule : " + e.getMessage());
        alert.showAndWait();
    }
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
  
    }

