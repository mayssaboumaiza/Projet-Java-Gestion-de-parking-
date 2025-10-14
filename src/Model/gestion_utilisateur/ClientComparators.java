/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_utilisateur;

/**
 *
 * @author hadil
 */
import java.util.Comparator;

public class ClientComparators {
    public static Comparator<client> parNom = (client c1, client c2) -> c1.getnom().compareTo(c2.getnom());

    public static Comparator<client> parPrenom = (client c1, client c2) -> c1.getprenom().compareTo(c2.getprenom());
}
