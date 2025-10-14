/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_utilisateur;

/**
 *
 * @author hadil
 */
import Model.gestion_reservation_et_paiement.Reservation;
import Model.gestion_reservation_et_paiement.ticket;
import gestion_de_parking.ValeurNegativeException;
import java.util.Comparator;

/**
 *
 * @author hadil
 */
//classe qui heritent de personne
public  final class client extends personne implements Comparable<client>{
    private String statut_fidele;
    String type;
    Reservation num_res;
        private ticket ticket;

   
    //methode compare to
   @Override
    public int compareTo(client other) {
        return Integer.compare(this.getidf(), other.getidf());
    } 
 

    
 //constructeur
 public client(int identf,String nom,String prenom,int numtelephone,int CIN,String adressemail
         ,String statut_fidele,String type,Reservation num_res) throws ValeurNegativeException
   {
       super( identf, nom,prenom,numtelephone, CIN, adressemail);
       this.statut_fidele=statut_fidele;
       this.type=type;
       this.num_res=num_res;
       
       
      
   }
  //les getters et les setters
    public String getstatut_fidele()
    {
        return statut_fidele;
    }
    public String gettype()
    {
        return type;
    }
   
    public void  setstatut_fidele(String s)
    {
       this.statut_fidele=s;
    }
    public void  setTYPE(String s)
    {
       this.type=s;
    }
   
    
   public int getNumReservation() {
        return num_res.getnum(); // Assurez-vous que num_res n'est pas null
    }
   
   public void setNumReservation(int g) throws ValeurNegativeException {
       this.num_res.setnum(g);  // Assurez-vous que num_res n'est pas null
    }
   public ticket getTicket() { // Méthode pour obtenir le ticket
        return ticket;
    }

    public int getnum() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //affichage
     @Override
    public String toString()
    {
       return "le statut de fidelite est :"+statut_fidele;
    }
    
}

