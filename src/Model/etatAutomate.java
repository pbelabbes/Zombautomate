package Model;

public class etatAutomate {
	int etat_courant;
    caseAutomate square;
	//Constructeurs 
    
 /*   public setEtatCourant(int etat_courant){
    	this.etat_courant=etat_courant;
    }
    
    public getEtatCourant(int etat_courant){
    	this.etat_courant=etat_courant;
    } 
    */
    etatAutomate(int etat_courant,char direction,caseAutomate square){
    	this.etat_courant=etat_courant;
    	this.square=square;
    	
    }
}