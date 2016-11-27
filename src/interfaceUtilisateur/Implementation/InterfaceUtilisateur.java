package interfaceUtilisateur.Implementation;
import java.util.ArrayList;

import interfaceUtilisateur.Interface.ObservableIU;
import interfaceUtilisateur.Interface.ObserverArretIU;
import interfaceUtilisateur.Interface.ObserverSurchargeIU;
import interfaceUtilisateur.Interface.SystemControl_IU;
import interfaceUtilisateur.Interface.UserInterface;
import systemAscenseur.Implementation.Sens;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;
import systemControl.Interface.IU_SC;

public class InterfaceUtilisateur implements SystemControl_IU, ObserverNiveau, UserInterface,ObservableIU,ObserverArret,ObserverSurcharge {
	public Sens display_sens;
	public int display_pos;
	public IU_SC systemeControle;
	private ArrayList<ObserverArretIU> oa;
	private ArrayList<ObserverSurchargeIU> os;
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

	public IU_SC getSystemeControle() {
		return systemeControle;
	}

	public void setSystemeControle(IU_SC systemeControle) {
		this.systemeControle = systemeControle;
	}

	public void appelServi(int niveau) {
		System.out.println("Vous etes au niveau : " + niveau);
	}

	public void demandeTraite(int niveau) {
		this.systemeControle.appel(niveau, null);
	}

	public void deplacement(int niveau) {
		// TODO Auto-generated method
	}

	public void monterAscenseur() {
		// TODO Auto-generated method
	}

	public void appel(Sens deplacement, int pos) {
		this.systemeControle.appel(pos, deplacement);
	
	}

	@Override
	public void notifierNiveau(int niveau) {
		System.out.println("L'ascenseur est au Niveau : " + niveau);
	}

	@Override
	public void addObserverSurcharge(ObserverSurchargeIU os) {
		// TODO Auto-generated method stub
	this.os.add(os)	;
	}

	@Override
	public void addObserverArret(ObserverArretIU oa) {
		// TODO Auto-generated method stub
		this.oa.add(oa);
	}

	@Override
	public void notifyObserverSurcharge() {
		// TODO Auto-generated method stub
		for(ObserverSurchargeIU o :os)
			o.surcharge();
	}

	@Override
	public void notifyObserverArret(int niveau) {
		// TODO Auto-generated method stub
		for(ObserverArretIU o :oa)
			o.arret(niveau);
	}

	@Override
	public void notifierSurcharge() {
		// TODO Auto-generated method stub
		this.notifyObserverSurcharge();
	}

	@Override
	public void notifierArret(int niveau) {
		// TODO Auto-generated method stub
	this.notifyObserverArret(niveau);	
	}


	
}
