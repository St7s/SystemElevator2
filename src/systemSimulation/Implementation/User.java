package systemSimulation.Implementation;

import java.util.ArrayList;
import utilisateur.IUser;
import utilisateur.ObserverAppel;
import utilisateur.ObserverDeplacement;
import utilisateur.ObserverEntre;
import utilisateur.ObserverSortie;
  class User implements IUser{
	private ArrayList<ObserverAppel> oa; 
	private ArrayList<ObserverDeplacement> od; 
	private ArrayList<ObserverEntre> oe; 
	private ArrayList<ObserverSortie> os; 
	/**
	 * l'utilisateur est dans la cabine ou pas
	 */
	public boolean inLift;
	/**
	 * nom de l'utilisateur
	 */
	public String nom;
	/**
	 * niveau d'ou le user fait l'appel
	 */
	public int niveau_initial;
	/**interfaceUtilisateur.Interface
	 * niveau ou le user veut aller
	 */
	public int niveau_final;
	/**
	 * Getter of inLift
	 */
	public boolean getInLift() {
	 	 return inLift; 
	}
	/**
	 * Setter of inLift
	 */
	public void setInLift(boolean inLift) { 
		 this.inLift = inLift; 
	}
	/**
	 * Getter of nom
	 */
	public String getNom() {
	 	 return nom; 
	}
	/**
	 * Setter of nom
	 */
	public void setNom(String nom) { 
		 this.nom = nom; 
	}
	/**
	 * Getter of niveau_initial
	 */
	public int getNiveau_initial() {
	 	 return niveau_initial; 
	}
	/**
	 * Setter of niveau_initial
	 */
	public void setNiveau_initial(int niveau_initial) { 
		 this.niveau_initial = niveau_initial; 
	}
	/**
	 * Getter of niveau_final
	 */
	public int getNiveau_final() {
	 	 return niveau_final; 
	}
	/**
	 * Setter of niveau_final
	 */
	public void setNiveau_final(int niveau_final) { 
		 this.niveau_final = niveau_final; 
	}
	/**
	 * 
	 * @param nom 
	 * @param niveau_initial 
	 * @param niveau_final 
	 */
	public User(String nom, int niveau_initial, int niveau_final) { 
		setInLift(false);
		setNom(nom);
		setNiveau_initial(niveau_initial);
		setNiveau_final(niveau_final);
	 }
	@Override
	public void addObserverAppel(ObserverAppel oa) {
		// TODO Auto-generated method stub
		this.oa.add(oa);
	}
	@Override
	public void addObserverDeplacement(ObserverDeplacement od) {
		// TODO Auto-generated method stub
		this.od.add(od);
	}
	@Override
	public void appel() {
		// TODO Auto-generated method stub
		for(ObserverAppel o : this.oa)
			o.appel(this.niveau_initial);
	}
	@Override
	public void deplacement() {
		// TODO Auto-generated method stub
		for(ObserverDeplacement o : this.od)
			o.deplacement(this.niveau_final);
		
	}
	@Override
	public void addObserverEntre(ObserverEntre oe) {
		// TODO Auto-generated method stub
		this.oe.add(oe);
	}
	@Override
	public void addObserverSortie(ObserverSortie os) {
		// TODO Auto-generated method stub
		this.os.add(os);
	}
	@Override
	public void entre() {
		// TODO Auto-generated method stub
		for(ObserverEntre o : oe)
			o.entre();
	}
	@Override
	public void sortie() {
		// TODO Auto-generated method stub
		for(ObserverSortie o : os)
			o.sortie();
		
	}

}
