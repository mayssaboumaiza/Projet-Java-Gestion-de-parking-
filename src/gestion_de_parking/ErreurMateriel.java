/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_parking;

/**
 *
 * @author hadil
 */
public class ErreurMateriel extends Exception{
    public ErreurMateriel()
    {
        super("il n'existe pas ce materiel");
    }
}