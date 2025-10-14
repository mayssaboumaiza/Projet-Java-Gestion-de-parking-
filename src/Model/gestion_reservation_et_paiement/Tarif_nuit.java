/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_reservation_et_paiement;

/**
 *
 * @author hadil
 */
//les classes qui implementent interface fonctionnelle tarif
public final  class Tarif_nuit implements Tarif{
     private double prix_base2;  
     private int hor_deb_nuit;
     private int hor_fin_nuit;

    // Constructeur qui reçoit le tarif
    public Tarif_nuit(double p,int h,int l) {
        this.hor_deb_nuit = h;
        this.hor_fin_nuit=l;
        this.prix_base2=p;
    }
     public int gethoraire_debnuit()
       {
           return hor_deb_nuit;
       }
     public int gethoraire_finnuit()
       {
           return  hor_fin_nuit;
       }
      public double getprix_base2()
       {
           return prix_base2;
       }
      public  void sethoraire_debnuit(int h)
      { this.hor_deb_nuit=h;
      }
       public  void sethoraire_finnuit(int m)
      { this.hor_fin_nuit=m;
      }
       public  void setprix_base2(double m)
      { this.prix_base2=m;
      }
        public String toString()
    { 
       return "les horaires de debut de nuit: "+hor_deb_nuit+" les horaires de fin nuit"+
               hor_fin_nuit+"avec un prix de base pour le nuit"+prix_base2;
    }
    @Override
    public double calculerTarif(long duree) {
        return prix_base2 * (duree / 60.0);
}
}    

