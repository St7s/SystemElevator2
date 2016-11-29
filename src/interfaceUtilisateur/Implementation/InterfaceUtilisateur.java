package interfaceUtilisateur.Implementation;
import java.util.ArrayList;

import interfaceUtilisateur.Interface.IintefaceUtilisateur;
import interfaceUtilisateur.Interface.ObserverArretIU;
import interfaceUtilisateur.Interface.ObserverSurchargeIU;
import systemAscenseur.Implementation.Sens;
import systemControl.Interface.IU_SC;

public class InterfaceUtilisateur implements IintefaceUtilisateur {
	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	public Sens display_sens;
	public int display_pos;
	public IU_SC systemeControle;
	private ArrayList<ObserverArretIU> observeursArret;
	private ArrayList<ObserverSurchargeIU> observeursSurcharge;
	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public Sens getDisplay_sens() {
		return display_sens;
	}

	public void setDisplay_sens(Sens display_sens) {
		this.display_sens = display_sens;
	}

	public int getDisplay_pos() {
		return display_pos;
	}

	public void setDisplay_pos(int display_pos) {
		this.display_pos = display_pos;
	}
	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	public InterfaceUtilisateur(){
		this.display_sens = null;
		this.display_pos = 0;
		this.systemeControle = null;
		this.observeursArret = new ArrayList<ObserverArretIU>();
		this.observeursSurcharge = new ArrayList<ObserverSurchargeIU>();
	}
	/*
	 * =========================================================== 
	 * Methodes de classe 
	 * ===========================================================
	 */

	public void monterAscenseur(){}

	public void emettreAppel(Sens deplacement, int pos) {
		this.systemeControle.appel(pos, deplacement);
	}

	@Override
	public void notifierNiveau(int niveau) {
		if(this.display_pos == niveau)return;
		this.display_pos = niveau;
		System.out.println("IU >> L'ascenseur est au Niveau : " + niveau);
	}

	@Override
	public void addObserverSurcharge(ObserverSurchargeIU os) {
		this.observeursSurcharge.add(os)	;
	}

	@Override
	public void addObserverArret(ObserverArretIU oa) {
		this.observeursArret.add(oa);
	}

	@Override
	public void notifyObserverSurcharge() {
		for(ObserverSurchargeIU o :observeursSurcharge)
			o.surcharge();
	}

	@Override
	public void notifyObserverArret(int niveau) {
		for(ObserverArretIU o :observeursArret)
			o.arret(niveau);
	}

	@Override
	public void notifierSurcharge() {
		this.notifyObserverSurcharge();
	}

	@Override
	public void notifierArret(int niveau) {
		this.notifyObserverArret(niveau);	
	}

	@Override
	public void appel(int niveau, Sens sens) {
		this.systemeControle.appel(niveau, sens);
	}

	@Override
	public void deplacement(int niveau) {
		this.systemeControle.deplacement(niveau);
	}
	@Override
	public void link(IU_SC cn)	{
		this.systemeControle = cn;
	}
}
