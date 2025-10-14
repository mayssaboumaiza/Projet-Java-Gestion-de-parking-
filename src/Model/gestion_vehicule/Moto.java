/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_vehicule;

import java.util.Comparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hadil
 */
//classe moto qui héritent de vehicule
public final class Moto extends Vehicule{
    
    String type_moto;
    
    int puissance;
    //constructeur
    public Moto (int num_mat,String marque,  int dimension ,String type_moto,int puissance )
    {  
        super( num_mat, marque,  dimension);
        this.puissance=puissance;
        this.type_moto=type_moto;
        
    }
    public Moto(int num_matricule, String marque, int dimension) {
        super(num_matricule, marque, dimension);
       
        this.type_moto= "";
        
    }
    //affichage
    @Override
    public String toString()
    {
        return "le typpe de moto est"+type_moto + "et la puissance est"+puissance;
    }
    //compare to
    public static Comparator<Moto>puissanceComparator = new Comparator<Moto>() {
@Override
public int compare(Moto e1, Moto e2) {
return (int) (e1.getpuissance()- e2.getpuissance()); }};
 //les getters et les setters
    public String gettype_moto()
    {
        
        return type_moto;
    }
   public int getpuissance()
   {
       return puissance;
   }
   public void settype_moto(String s)
   {
       this.type_moto=s;
   }
   public void setpuissance(int s)
   {
       this.puissance=s;
   }
   
   
}
