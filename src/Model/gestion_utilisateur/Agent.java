/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_utilisateur;
import Model.gestion_reservation_et_paiement.Reservation;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import Model.gestion_ressources.Materiel;
import Model.gestion_ressources.StatutMateriel;
import Model.gestion_vehicule.Moto;
import Model.gestion_vehicule.PlaceParking;
import Model.gestion_vehicule.Vehicule;
import Model.gestion_vehicule.voiture;
import gestion_de_parking.ErreurMateriel;
import gestion_de_parking.ErreurMoto;
import gestion_de_parking.Erreurvoiture;
import gestion_de_parking.ValeurNegativeException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hadil
 */
//classe admin
    public  final   class Agent extends personne {
            String specialite;
           boolean disponib;
           String mot_de_passe;
           private List<Materiel>ListMateriel;//liste de materiel
                private Map<Integer,client>Mapclt;//map de client
                private Map<PlaceParking,voiture>Mapvoiture;//map de voiture avec le clé placeparking
                     private Map<PlaceParking,Moto>MapMoto;//map de moto avec le clé placeparking

                
//constructeur
           public   Agent(int identf,String nom,String prenom,int numtelephone,int CIN,String adressemail,
                   String spec,boolean dispo,String mdp) throws ValeurNegativeException
           {
               super( identf, nom, prenom, numtelephone, CIN, adressemail);
               this.disponib=dispo;
               this.specialite=spec;
               this.mot_de_passe=mdp;
               ListMateriel=new ArrayList<Materiel>();
                    Mapclt= new HashMap<>();
                   Mapvoiture= new HashMap<>();
                     MapMoto= new HashMap<>();}

                   
 
           
            //les getters et les setters
                 public Integer getReservationNumByClient(client c) {
    for (Map.Entry<Integer, client> entry : Mapclt.entrySet()) {
        if (entry.getValue().equals(c)) {
            return entry.getKey();
        }
    }
    return null;
}    
public String getmotdepasse()
    {
        return mot_de_passe;
    }
                public void setmotdepasse(String h)
    {
        this.mot_de_passe=h;
    }
            public String getspecialite()
    {
        return specialite;
    }
             public boolean getdispo()
    {
        return disponib;
    }
             public void  setspecialite(String s)
    {
       this.specialite=s;
    }
             public void  setdisponibilite(boolean m)
    {
       this.disponib=m;
    }
             
       // Méthode pour récupérer la map des clients
    public List<client> getClients() {
        return new ArrayList<>(Mapclt.values());
    }
    // Méthode pour récupérer la la map de voitures
    public List<voiture> getvoitures() {
        return new ArrayList<>(Mapvoiture.values());
    }
    // Méthode pour récupérer la la map de motos 
     public List<Moto> getmotos() {
        return new ArrayList<>(MapMoto.values());
    }
     
     
     
     //methodes de la map de voitures
      public void ajouterVoiture(PlaceParking place, voiture voiture) throws Erreurvoiture {
    if (Mapvoiture.containsKey(place.OCCUPE))
            {
        throw new Erreurvoiture();
    }
    Mapvoiture.put(place, voiture);
}
      public void supprimerVoiture(PlaceParking place) throws Erreurvoiture {
    // Vérifier si la place de parking existe dans la Map
    if (Mapvoiture.containsKey(place.OCCUPE)) {
        // Si la place de parking n'existe pas, lancer une exception ou afficher un message d'erreur
        throw new Erreurvoiture();  // Vous pouvez personnaliser l'exception en fonction de vos besoins
    }

    // Supprimer l'élément de la Map en utilisant la place de parking comme clé
    Mapvoiture.remove(place);

    System.out.println("Voiture supprimée de la place de parking : " + place);
}
      public voiture modifierVoiture(voiture V, String nouveauTypeCarburant) {
    // Parcourir les entrées de la Map (clé = PlaceParking, valeur = Voiture)
    for (Map.Entry<PlaceParking, voiture> entry : Mapvoiture.entrySet()) {
        voiture voiture = entry.getValue();  // Récupérer la voiture associée à la place de parking
        
        // Vérifier si la voiture correspond à l'objet V que l'on souhaite modifier
        if (voiture.equals(V)) {
            // Modifier le type de carburant de la voiture
            voiture.settypecarb(nouveauTypeCarburant);
            System.out.println("Le type de carburant de la voiture a été modifié.");
            return voiture;  // Retourner la voiture modifiée
        }
    }
    // Si la voiture n'est pas trouvée, renvoyer null
    System.out.println("Voiture non trouvée.");
    return null;
}
      
      
      
      //methodes de la map de clients
      public void ajouterclient(Reservation  r,client c)
     {
         Mapclt.put(r.getnum(), c);
     }
     public void supprimerclient(int cle)
     {
         Mapclt.remove(cle);
     }
     
     public client modifierclient(int cle,client c)
     {
         if (Mapclt.containsKey(cle)){
             System.out.println("aucun client trouvé a cet indice");
         }
         else
         { Mapclt.replace(cle, c);}
         return c;
     }
     
     
     
     
     
     //methodes de la map de moto 
       public void ajoutermoto(PlaceParking place, Moto m) throws ErreurMoto {
    if (MapMoto.containsKey(place.OCCUPE))
            {
        throw new ErreurMoto();
    }
    MapMoto.put(place, m);
}
        public void supprimerMoto(PlaceParking place) throws ErreurMoto {
    // Vérifier si la place de parking existe dans la Map
    if (MapMoto.containsKey(place.OCCUPE)) {
        // Si la place de parking n'existe pas, lancer une exception ou afficher un message d'erreur
        throw new ErreurMoto();  // Vous pouvez personnaliser l'exception en fonction de vos besoins
    }

    // Supprimer l'élément de la Map en utilisant la place de parking comme clé
    MapMoto.remove(place);

    System.out.println("Moto supprimée de la place de parking : " + place);
}
         public Moto modifierMoto(Moto V, int nouveaupuis) {
    // Parcourir les entrées de la Map (clé = PlaceParking, valeur = Voiture)
    for (Map.Entry<PlaceParking,Moto> entry : MapMoto.entrySet()) {
        Moto m= entry.getValue();  // Récupérer la moto associée à la place de parking
        
        // Vérifier si la moto correspond à l'objet V que l'on souhaite modifier
        if (m.equals(V)) {
            // Modifier le type de carburant de la voiture
            m.setpuissance(nouveaupuis);
            System.out.println("Le puissance de la moto a été modifié.");
            return m;  // Retourner la moto modifiée
        }
    }
    // Si la moto n'est pas trouvée, renvoyer null
    System.out.println("moto non trouvée.");
    return null;
}
         
         
         
    

         
     //methodes de la liste de materiels
    public void ajoutermateriel(Materiel m)
    {
        ListMateriel.add(m);
    }
   
    public void suprimerMateriel(int rang) throws ErreurMateriel
    {
        {if (rang<0 || rang>=ListMateriel.size())
        {throw new ErreurMateriel();}
            ListMateriel.remove(rang);
        } }
    public Materiel ModifierMateriel(Materiel m,String nouv_nom)
    {
        for (Materiel ma :ListMateriel){
            if(ma.equals(m)){
                m.setNomM(nouv_nom);
                return ma;
            }
        }
        return null;
    }
     public Materiel rechercher (Materiel m,String T)
    {
        for(Materiel mm :ListMateriel){
            if(mm.equals(mm)){
                return (mm);
            }
        }
                return null;
    }
    
   

//stream pour map de voitures
    public List<Integer> obtenirNumMatriculeVoituresTriees() {
    return Mapvoiture.values().stream()  // Récupérer toutes les voitures de la Map
            .map(voiture::getNumMatricule)  // Récupérer les numéros de matricule
            .sorted()  // Trier les numéros de matricule
            .toList();  // Retourner la liste triée
}
public List<voiture> rechercherNumMatriculeVoituresParTypeCarburant(String typeCarburant) {
    return Mapvoiture.values().stream()  // Récupérer toutes les voitures de la Map
            .filter(voiture -> voiture.getTypeCarb().toLowerCase().equals(typeCarburant.toLowerCase()))  // Filtrer par type de carburant
            .toList();  // Retourner la liste filtrée
}

public void afficherVoitures() {
    Mapvoiture.values().forEach(voiture -> System.out.println(voiture));  // Parcourir et afficher chaque voiture
}
    
     
      // la partie de Stream sur list materiel
      public  List<String> obtenirNomMaterieltrier()
      {
          return ListMateriel.stream().map(Materiel::getNomM).sorted().toList();
      }
      public  List<Materiel> rechercherMaterielParNom(String nomMateriel)
              {
                  
                return ListMateriel.stream().filter(materiel->{
                    return materiel.getNomM().toLowerCase().contains(nomMateriel.toLowerCase());
                }).toList();
                  
              }
   
              


    
    
    //stringbuffer
    
    @Override
public  String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Agent Information:\n");
    sb.append("Specialité: ").append(specialite).append("\n");
    sb.append("Disponibilité: ").append(disponib ? "Disponible" : "Non disponible").append("\n");
    sb.append("Mot de passe: ").append(mot_de_passe).append("\n"); // Note: En général, il n'est pas recommandé d'afficher le mot de passe.
    
    // Liste des matériels
    sb.append("Liste des matériels:\n");
    if (ListMateriel.isEmpty()) {
        sb.append("Aucun matériel disponible.\n");
    } else {
        for (Materiel materiel : ListMateriel) {
            sb.append(materiel.toString()).append("\n");
        }
    }

    // Map des motos
    sb.append("Map des motos:\n");
    if (MapMoto.isEmpty()) {
        sb.append("Aucune moto enregistrée.\n");
    } else {
        for (Map.Entry<PlaceParking, Moto> entry : MapMoto.entrySet()) {
            sb.append("Place de parking: ").append(entry.getKey())
              .append(", Moto: ").append(entry.getValue().toString()).append("\n");
        }
    }

    // Map des voitures
    sb.append("Map des voitures:\n");
    if (Mapvoiture.isEmpty()) {
        sb.append("Aucune voiture enregistrée.\n");
    } else {
        for (Map.Entry<PlaceParking, voiture> entry : Mapvoiture.entrySet()) {
            sb.append("Place de parking: ").append(entry.getKey())
              .append(", Voiture: ").append(entry.getValue().toString()).append("\n");
        }
    }

    // Map des clients
    sb.append("Map des clients:\n");
    if (Mapclt.isEmpty()) {
        sb.append("Aucun client enregistré.\n");
    } else {
        for (Map.Entry<Integer, client> entry : Mapclt.entrySet()) {
            sb.append("Numéro de réservation: ").append(entry.getKey())
              .append(", Client: ").append(entry.getValue().toString()).append("\n");
        }
    }

    return sb.toString();

}
 public PlaceParking getPlaceParking(Vehicule vehicule) {
        // Si le véhicule est une voiture
        if (vehicule instanceof voiture) {
            // Chercher dans la map des voitures
            for (Map.Entry<PlaceParking, voiture> entry : Mapvoiture.entrySet()) {
                if (entry.getValue().equals(vehicule)) {
                    return entry.getKey();  // Retourner la place de parking (clé de la map)
                }
            }
        }
        // Si le véhicule est une moto
        else if (vehicule instanceof Moto) {
            // Chercher dans la map des motos
            for (Map.Entry<PlaceParking, Moto> entry : MapMoto.entrySet()) {
                if (entry.getValue().equals(vehicule)) {
                    return entry.getKey();  // Retourner la place de parking (clé de la map)
                }
            }
        }

        // Si le véhicule n'a pas de place de parking, retourner null
        return null;
    }
 

}

     



