/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_parking;

/**
 *
 * @author hadil
 */
public class Erreurvoiture extends Exception{
     public Erreurvoiture()
    {
        super("il n'existe pas cette voiture");
    }
}