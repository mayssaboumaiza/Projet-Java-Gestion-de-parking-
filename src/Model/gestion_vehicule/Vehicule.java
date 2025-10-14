/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_vehicule;

/**
 *
 * @author hadil
 */

public sealed class Vehicule permits voiture, Moto {
    private int numMatricule ;
    private String marque ;
    private int dimensions ;
  ;
    private PlaceParking place;

    public Vehicule(int numMatricule, String marque, int dimension) {
        this.numMatricule=numMatricule;
        this.marque=marque;
        this.dimensions=dimension;
        
    }

   

    // Getter pour dimensions
    public int getDimensions() {
        return dimensions;
    }

    // Setter pour dimensions
    public void setDimensions(int m) {
        this.dimensions=m;
    }

    // Getter pour num_mat
    public int getNumMatricule() {
        return numMatricule;
    }

    // Setter pour num_mat
    public void setNumMatricule(int m) {
        this.numMatricule=m;
    }

    // Getter pour marque
    public String getMarque() {
        return marque;
    }

    // Setter pour marque
    public void setMarque(String m) {
        this.marque=m;
    }

    @Override
    public String toString() {
        return "Le numéro de matricule : " + getNumMatricule()  + ", Dimension : " + getDimensions() + ", Marque : " + getMarque();
    }

    public void setPlaceParking(PlaceParking placeDisponible) {
        this.place = placeDisponible; // Assignez la place de parking
    }

    public PlaceParking getPlaceParking() {
        return this.place; // Retournez la place de parking
    }
}   

