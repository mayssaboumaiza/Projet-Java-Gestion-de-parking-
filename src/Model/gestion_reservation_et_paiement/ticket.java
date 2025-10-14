/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_reservation_et_paiement;

/**
 *
 * @author hadil
 */
//classe record
public record ticket (int num_ticket,int h_emm,int h_exp) 
{
   
    
    
    public String toString()
    {
       return "le num de ticket :"+num_ticket+"et heure d'emmission :"+h_exp+"et l'heure de l'emmision est"+h_emm;
    }
}

