/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_vehicule;

/**
 *
 * @author hadil
 */

    import java.util.Comparator;

public class VoitureComparators {
    // Comparateur pour trier par consommation moyenne
    public static Comparator<voiture> parConsommationMoyenne = (voiture v1, voiture v2) -> Float.compare(v1.getConsoMoyenne(), 
            v2.getConsoMoyenne());

    // Comparateur pour trier par nombre de places
    public static Comparator<voiture> parNombrePlaces = (voiture v1, voiture v2) -> Integer.compare(v1.getNbrPlace(),
            v2.getNbrPlace());

    // Comparateur pour trier par type de carburant
    public static Comparator<voiture> parTypeCarburant = (voiture v1, voiture v2) -> v1.getTypeCarb().compareTo(v2.getTypeCarb());
}



