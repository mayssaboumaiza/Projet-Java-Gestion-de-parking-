package Model.gestion_ressources;

public class Materiel {
    //attributs
    private String nomM;
    private StatutMateriel Statut; 
    private int heure_installation;
    //constructeur
     public Materiel(String nomM ,StatutMateriel  Statut,int heure_installation)
    { 
        this.nomM=nomM;
        this.Statut=Statut;
        this.heure_installation=heure_installation;
    }
     //les getters et les setters
     public String getNomM(){return nomM ;}
    public void setNomM (String type){this.nomM=nomM ;}
    
     public StatutMateriel  getStatut(){return Statut;}
    public void setStatut (StatutMateriel  Statut){this.Statut=Statut ;}
    
     public int getheure_installation(){return heure_installation;}
    public void setheure_installation (int heure_installation){this.heure_installation=heure_installation ;}
    //affichage des données
    @Override
public String toString() {
    return "Materiel {" +
           "Nom='" + nomM + '\'' +
           ", Statut='" + Statut + '\'' +
           ", Heure d'installation=" + heure_installation +
           '}';
}

}