/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_vehicule;

/**
 *
 * @author hadil
 */
//record
public record Assurrance(int idf,boolean validite) {
    
        public String toString()
        {
            return "l'identifiant de cette assurance"+idf+"et sa validite est :"+validite;
        }
        
}