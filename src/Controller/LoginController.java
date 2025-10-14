
package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;

public class LoginController {

    @FXML
    private TextField identifiantField;

    @FXML
    private TextField motDePasseField;

    @FXML
    private CheckBox robotCheckBox;

   @FXML
private Button connexionButton; // Assurez-vous que c'est bien un Button

    @FXML
    private Hyperlink motDePasseOublieLink;

    @FXML
    private Hyperlink creerCompteLink;

    @FXML
    private ImageView imageLogoPersonne;

    @FXML
    private ImageView logoOeilBarre;

    @FXML
    private ImageView logoRobot;

    @FXML
    public void initialize() {
           if (identifiantField == null) {
       System.out.println("identifiantField n'a pas été initialisé !");
    }
    if (motDePasseField == null) {
        System.out.println("motDePasseField n'a pas été initialisé !");
    }
        // Désactiver le bouton de connexion au démarrage
        connexionButton.setDisable(true);

        // Activer/désactiver le bouton de connexion en fonction de la case à cocher
        robotCheckBox.setOnAction(event -> {
            connexionButton.setDisable(!robotCheckBox.isSelected());
        });

        // Gérer l'action du bouton de connexion
        connexionButton.setOnAction(event -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String identifiant = identifiantField.getText();
        String motDePasse = motDePasseField.getText();

        if (identifiant.isEmpty() || motDePasse.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs.");
            return;
        }

        // Exemple de logique de connexion identifiant.equals("admin") &&
        if (motDePasse.equals("admin123")) {
            System.out.println("Connexion réussie !");
            // Ici, vous pouvez charger une nouvelle scène ou faire autre chose
            loadHomePage();
        } else {
            System.out.println("Identifiant ou mot de passe incorrect !");
        }
    }

    @FXML
    private void handleMotDePasseOublie() {
        System.out.println("Lien 'Mot de passe oublié' cliqué.");
        // Logique pour gérer le mot de passe oublié
    }

    @FXML
    private void handleCreerCompte() {
        System.out.println("Lien 'Créer mon compte' cliqué.");
        // Logique pour gérer la création de compte
    }

    // Méthode pour charger la page d'accueil (exemple)
    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Acceuil.fxml"));
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
    
}