package systemControl.Implementation;

import systemAscenseur.Implementation.Sens;

public class Commande {

	public int niveau;
	public Sens sens;
	
	public Commande(){}
	
	public Commande(int niv, Sens s)
	{
		this.niveau=niv;
		this.sens=s;
	}
	/**
	 * Getter of niveau
	 */
	public int getNiveau() {
	 	 return niveau; 
	}
	/**
	 * Setter of niveau
	 */
	public void setNiveau(int niveau) { 
		 this.niveau = niveau; 
	}
	/**
	 * Getter of sens
	 */
	public Sens getSens() {
	 	 return sens; 
	}
	/**
	 * Setter of sens
	 */
	public void setSens(Sens sens) { 
		 this.sens = sens; 
	} 

	public Commande clone(){
		return new Commande(this.niveau, this.sens);
	}
}
