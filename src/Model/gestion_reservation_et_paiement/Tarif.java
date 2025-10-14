/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_reservation_et_paiement;

/**
 *
 * @author hadil
 */
//interface fonctionnelle

       @FunctionalInterface
public  interface Tarif {
    double calculerTarif(long duree);
}

