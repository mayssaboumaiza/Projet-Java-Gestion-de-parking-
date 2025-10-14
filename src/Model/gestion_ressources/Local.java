/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_ressources;
import Model.gestion_vehicule.PlaceParking;
import gestion_de_parking.ValeurNegativeException;
/**
 *
 * @author hadil
 */

public class Local {
    int num_loc;
    int nbr_niv;
    int capacite;
    PlaceParking statut;
   //constructeur
    public Local(int num_loc,int nbr_niv,int cap,PlaceParking statut) throws ValeurNegativeException
    {
        this.capacite=cap;
        this.nbr_niv=nbr_niv;
        this.num_loc=num_loc;
        this.statut=statut;
        
          if (capacite <= 0) {
            throw new ValeurNegativeException("Le num de reservation doit être positif.");
        }
           if (nbr_niv<= 0) {
            throw new ValeurNegativeException("Le num de reservation doit être positif.");
        }
           if (num_loc <= 0) {
            throw new ValeurNegativeException("Le num de reservation doit être positif.");
        }
           
          
    }
   //les getters et les setters
    public int getnum_loc()
    {
        return num_loc;
    }
    public PlaceParking getstatut()
    {
        return statut;
    }
    public int getnbr_niv()
    {
        return nbr_niv;
    }
    public int getcap()
    {
        return capacite;
    }
     public void  setnum_loc(int n)
    {
        this.num_loc=n;
    }
      public void  setstatut(PlaceParking n)
    {
        this.statut=n;
    }
      public void  setnbr_niv(int nb)
    {
        this.nbr_niv=nb;
    }
       public void  setcapacite(int cp)
    {
        this.capacite=cp;
    }
       //affichage
       public String toString()
       {
           return "le numero de parking "+num_loc+"et le nombre de niveaux de ce parking "+nbr_niv+"et la capacite"+capacite;
       }
     
      
    
}




