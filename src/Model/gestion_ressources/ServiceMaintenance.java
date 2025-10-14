/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.gestion_ressources;
//classe qui implementent l'interface fonctionnelle service
public final class ServiceMaintenance implements Service {
    private  boolean besoinDeReparation;  // Si l'équipement a besoin de réparation
    private  int dureeEstimee;
    private  int coutEstime;
    private  String equipement;

    // Constructeur
    public ServiceMaintenance(boolean besoinDeReparation, int dureeEstimee, int coutEstime, String equipement) {
        this.besoinDeReparation =besoinDeReparation;
        this.dureeEstimee =dureeEstimee;
        this.coutEstime =coutEstime;
        this.equipement =  equipement;
    }

    // Getters
    public boolean getBesoinDeReparation() {
        return besoinDeReparation;
    }

   public void setisBesoinDeReparation(boolean m)
   { this.besoinDeReparation=m;
   }

    public int getDureeEstimee() {
        return dureeEstimee;
    }

      public void setDureeEstimee(int m)
   { this.dureeEstimee=m;
   }

    public int getCoutEstime() {
        return coutEstime;
    }
      public void setcoutEstime(int m)
   { this.coutEstime=m;
   }

   
    public String getEquipement() {
        return equipement;
    }
    public void setEquipemente(String m)
   { this.equipement=m;
   }
  

    @Override
    public void effectuerService() {
        if (getBesoinDeReparation()) {
            System.out.println("Maintenance du " + getEquipement() + " en cours (réparation nécessaire).");
        } else {
            System.out.println("Maintenance du " + getEquipement() + " en cours (pas de réparation nécessaire).");
        }
    }
}