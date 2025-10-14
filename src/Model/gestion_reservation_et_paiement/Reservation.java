/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_reservation_et_paiement;

/**
 *
 * @author hadil
 */

import Model.gestion_reservation_et_paiement.ticket;
import Model.gestion_utilisateur.client;
import gestion_de_parking.ValeurNegativeException;
    import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author hadil
 */
public class Reservation {
     int num_res;
     ticket num_ticket;
       public String clientName;
        private String dateReservation;
        //constructeur
     public Reservation(int num, ticket nume ,    String dateReservation,   String clientName) throws ValeurNegativeException
     {   
         this.num_res=num;
         this.num_ticket=nume;
         this.dateReservation=dateReservation;
         this.clientName=   clientName;
    
          if (num_res <= 0) {
            throw new ValeurNegativeException("Le num de reservation doit être positif.");
        }
         
     }
     //les getters et les setters
     public int  getnum()
     {
         return num_res;
     }
     public void setnum(int n) throws ValeurNegativeException
     {   if (n <= 0) {
            throw new ValeurNegativeException("Le num de reservation doit être positif.");}
         this.num_res=n;
     }
      
    public void setticket(ticket num_ticket) 
    {
        this.num_ticket=num_ticket;
         
    }
     public ticket getTicket() {
        return num_ticket; // Retourne l'objet ticket
    }
     public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }
    //affichage  des données
     public String toString()
     {
         return "le numero de reservation est:"+num_res+"num_ticket"+num_ticket+"le nom de client"+clientName;
     }
   
}


