/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_reservation_et_paiement;

/**
 *
 * @author hadil
 */
//les classes qui implementent de l'interface
public final  class Tarif_Matin implements Tarif{
     private int horaire_debmat; 
     private int horaire_finmatin;
     private double prix_base1;
     

    // Constructeur qui reçoit le tarif
    public Tarif_Matin(int h,int g,double b) {
        this.horaire_debmat= h;
        this.horaire_finmatin=g;
        this.prix_base1=b;
    }
     public int gethoraire_debmat()
       {
           return horaire_debmat;
       }
     public int gethoraire_finmatin()
       {
           return horaire_finmatin;
       }
      public double getprix_base1()
       {
           return prix_base1;
       }
      public  void sethoraire_debmat(int h)
      { this.horaire_debmat=h;
      }
       public  void sethoraire_finmatin(int m)
      { this. horaire_finmatin=m;
      }
       public  void setprix_base1(double m)
      { this.prix_base1=m;
      }
    @Override
    public double calculerTarif(long duree) {
        return prix_base1* (duree / 60.0);
}
    public String toString()
    {
       return "les horaires de matin: "+horaire_debmat+" les horaires de fin matin "
               +horaire_finmatin+"avec un prix de base pour le matin"+prix_base1;
    }
}

