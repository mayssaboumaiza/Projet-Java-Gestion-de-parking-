/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_utilisateur;

import gestion_de_parking.ValeurNegativeException;

/**
 *
 * @author hadil
 */
//classe scellée classe mére
public sealed class personne permits client,Agent{
    private int identf;
    private String nom;
    private String prenom;
    private int numtelephone;
    private int CIN;
    private String adressemail;
    //constructeur
    public  personne(int identf,String nom,String prenom,int numtelephone,int CIN,String adressemail) throws ValeurNegativeException    
    {
        this.adressemail=adressemail;
        this.CIN=CIN;
        this.identf=identf;
        this.numtelephone=numtelephone;
        this.prenom=prenom;
        this.nom=nom;
       
        
         if (identf <= 0) {
            throw new ValeurNegativeException("Le ident de reservation doit être positif.");
        }
         if (String.valueOf(CIN).length() !=8) {
            throw new ValeurNegativeException("Le le longeur de cin doit etre egale a 8");
        }
          if (String.valueOf(numtelephone).length() !=8) {
            throw new ValeurNegativeException("Le le longeur de num telephone doit etre egale a 8");
        }
         if (CIN <= 0) {
              throw new ValeurNegativeException("Le cin  doit être positif.");
        }
         if (numtelephone <= 0) {
              throw new ValeurNegativeException("Le num de telephone  doit être positif.");
        }
               
    }
    //les getters et les setters
     public int getCIN()
    {
        return CIN;
    
    }
   
      public void setCIN(int C)
     {   
         this.CIN=C;
         
     }
    public int getidf()
    {
        return identf;
    
    }
     public String  getnom()
    {
        return nom;
    
    }
       public String getprenom()
    {
        return prenom;
    
    }
      public int getnumtlf()
    {
        return numtelephone;
    
    }
       public String getadressemail()
    {
        return adressemail;
    
    }
     public void setnom(String nom)
     {   
         this.nom=nom;
         
     }
     public void setprenom(String prenom)
     {    
         this.prenom=prenom;
         
     }
     public void setadrmail(String adrmail)
     {   
         this.adressemail=adrmail;
         
     }
     public void setid(int id)
     {    
         this.identf=id;
         
     }
     public void setnumtlf(int tlf)
     {    
         this.numtelephone=tlf;
         
     }
     //affichage
      @Override
    public String toString()
    {
       return "identifiant : "+identf+" nom: "+nom+"prenom:"+prenom+"numtelephone:"+numtelephone+"le numero de cin:"+CIN+
               "son adresse est :"+adressemail;
    }
    
     
       
    
}

