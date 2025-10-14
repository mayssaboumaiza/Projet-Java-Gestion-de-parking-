/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author hadil
 */

    

package Model.gestion_vehicule;

import java.util.Comparator;

/**
 *
 * @author hadil
 */
public final class voiture extends Vehicule {
    
    private  String type_carb;
    private  String type_boitevit;
    private int nbr_place;
    private  float conso_moy;

    public voiture(int num_matricule, String marque, int dimension, String type_carb, 
            String type_boitevit, int nbr_place, float conso_moy) {
        super( num_matricule, marque,  dimension);
        this.type_carb = type_carb;
        this.type_boitevit = type_boitevit;
        
        this.conso_moy = conso_moy;
    }

    public voiture(int num_matricule, String marque, int dimension) {
        super(num_matricule, marque, dimension);
        this.type_carb = "";
        this.type_boitevit = "";
        
        this.conso_moy = 0;
    }

    // Comparator pour trier la liste ou le tableau des voitures en fonction du nbr de place
    public static Comparator<voiture> nbrplaceComparator = new Comparator<voiture>() {
        @Override
        public int compare(voiture e1, voiture e2) {
            return Integer.compare(e1.getNbrPlace(), e2.getNbrPlace());
        }
    };

    public String getTypeCarb() {
        return type_carb;
    }

    public String getTypeBoiteVitesse() {
        return type_boitevit;
    }

    public int getNbrPlace() {
        return nbr_place;
    }

    public float getConsoMoyenne() {
        return conso_moy;
    }

    public void setNbrPlace(int nc) {
        this.nbr_place=nc;
    }

    // Override toString
    @Override
    public String toString() {
        return "Le type de carburant est : " + type_carb + ", la consommation moyenne est : " 
                + conso_moy + ", le type de boite : " + type_boitevit;
    }

    public void settypecarb(String nouveauTypeCarburant) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
