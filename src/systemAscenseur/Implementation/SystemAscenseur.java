package systemAscenseur.Implementation;

import java.util.ArrayList;

import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;
import systemAscenseur.Interface.SystemAscenseurFactory;


public class SystemAscenseur extends SystemAscenseurFactory implements ISystemAscenseur{

	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private int niveauMax;
	private int niveauMin;
	private int position;
	private Etat etat;
	private Sens sensDeplacement;
	private Moteur moteur;
	private CapteurNiveau capteurNiveau;
	private float distanceNiveaux;
	private long time1;
	private long time2;
	private ArrayList<ObserverArret> observersArret;
	private ArrayList<ObserverNiveau> observersNiveau;
	private ArrayList<ObserverSurcharge> observersSurcharge;

	/*
	 * =========================================================== 
	 * Getters - Setters
	 * ===========================================================
	 */
	public int getNiveauMax() {return niveauMax;}
	public void setNiveauMax(int niveauMax) {this.niveauMax = niveauMax;}

	public int getPosition() {return position;}
	public void setPosition(int position) {this.position = position;this.notifyAllNiveau();}

	public int getNiveauMin() {return niveauMin;}
	public void setNiveauMin(int niveauMin) {this.niveauMin = niveauMin;}

	public Etat getEtat() {return etat;}
	public void setEtat(Etat etat) {this.etat = etat;}

	public Moteur getMoteur() {return moteur;}
	public void setMoteur(Moteur moteur) {this.moteur = moteur;}

	public Sens getSensDeplacementCourant() {return sensDeplacement;}
	public void setSensDeplacementCourant(Sens sensDeplacementCourant) {this.sensDeplacement = sensDeplacementCourant;}

	public CapteurNiveau getCapteurniveau() {return capteurNiveau;}
	public void setCapteurniveau(CapteurNiveau capteurniveau) {this.capteurNiveau = capteurniveau;}

	public float getDistanceNiveaux() {return distanceNiveaux;}
	public void setDistanceNiveaux(float distanceNiveaux) {this.distanceNiveaux = distanceNiveaux;}

	public long getTempsArret() {return time1;}
	public void setTempsArret(long time1) {this.time1 = time1;}

	public ArrayList<ObserverArret> getObserversArret() {return observersArret;}
	public void setObserversArret(ArrayList<ObserverArret> observerArret) {this.observersArret = observerArret;}

	public ArrayList<ObserverNiveau> getObserversNiveau() {return observersNiveau;}
	public void setObserversNiveau(ArrayList<ObserverNiveau> observerNiveau) {this.observersNiveau = observerNiveau;}

	public ArrayList<ObserverSurcharge> getObserversSurcharge() {return observersSurcharge;}
	public void setObserversSurcharge(ArrayList<ObserverSurcharge> observerSurcharge) {this.observersSurcharge = observerSurcharge;}
	/*
	 * ===========================================================
	 * Constructeur
	 * ===========================================================
	 */
	/**
	 * Constructeurs avec parametres
	 * @param niveauMin
	 * @param niveauMax
	 * @param distanceNiveaux
	 * @param moteur
	 * @param capteurNiveau
	 */
	public SystemAscenseur(int niveauMin, int niveauMax, float distanceNiveaux, Moteur moteur, CapteurNiveau capteurNiveau) {
		this.niveauMin = niveauMin;
		this.niveauMax = niveauMax;
		this.position = 0;
		this.etat = Etat.REPOS;
		this.sensDeplacement = null;
		this.moteur = moteur;
		this.distanceNiveaux = distanceNiveaux;
		this.capteurNiveau = capteurNiveau;
		this.time1 = -1;
		this.time2 = 0;

		this.setObserversArret(new ArrayList<ObserverArret>());
		this.setObserversNiveau(new ArrayList<ObserverNiveau>());
		this.setObserversSurcharge(new ArrayList<ObserverSurcharge>());
	}

	/*
	 * ===========================================================
	 * Methodes de classe 
	 * ===========================================================
	 */	
	/**
	 * Mettre a jour les variables de temps
	 * @param temps
	 */
	private void miseAjourTemps(long temps){
		if(time1 == -1){
			time1 = temps;
			time2 = time1;
		}else{	
			this.time1 = this.time2;
			this.time2 = temps;
		}
	}

	/**
	 * 
	 * @param sens
	 */
	private void deplacement(Sens sens) {
		this.sensDeplacement = sens;
	}

	private void ouverturePorte() {
		this.getMoteur().getCabine().setEtat(EtatCabine.OUVERT);
	}

	private void arretNiveau() {
		this.moteur.arretImmediat();
		this.ouverturePorte();
	}
	/*
	 * ===========================================================
	 * Methodes SystemControl_SA 
	 * ===========================================================
	 */
	/**
	 * 
	 * @param sens
	 */
	@Override
	public void commande(Sens sens) {		
		System.out.println("Reception de commande : " + sens + "("+this.getMoteur().getCabine().getPosition()+" m)");
		//Demande d'arret
		if (sens == null){
			if(this.getEtat() == Etat.DEPLACEMENT)
				this.setEtat(Etat.TRANSITOIRE);
			else
				this.setEtat(Etat.REPOS);
			this.arretNiveau();
		}
		//Demande de monter
		else if(sens == Sens.DOWN){
			this.setEtat(Etat.DEPLACEMENT);
			this.getMoteur().marche(Sens.DOWN);
			deplacement(sens);
		}
		//Demande de descendre
		else if(sens == Sens.UP){
			this.setEtat(Etat.DEPLACEMENT);
			this.getMoteur().marche(Sens.UP);
			deplacement(sens);
		}
	}
	/*
	 * ===========================================================
	 * Methodes Observable 
	 * ===========================================================
	 */
	@Override
	public void addObserverArret(ObserverArret obj){this.observersArret.add(obj);}
	@Override
	public void addObserverNiveau(ObserverNiveau obj){this.observersNiveau.add(obj);}
	@Override
	public void addObserverSurcharge(ObserverSurcharge obj){this.observersSurcharge.add(obj);}

	@Override
	public void notifyAllNiveau(){
		for (ObserverNiveau observerNiveau : observersNiveau)
			observerNiveau.notifierNiveau(this.position);
	}


	@Override
	public void notifyAllArret(){
		for (ObserverArret observerArret : observersArret)
			observerArret.notifierArret(this.position);
	}

	@Override
	public void notifyAllSurcharge() {
		for (ObserverSurcharge observerSurcharge : observersSurcharge)
			observerSurcharge.notifierSurcharge();
	}
	/*
	 * ===========================================================
	 * Methodes Event 
	 * ===========================================================
	 */

	@Override
	public void trigger(long t) {
		System.out.println("\n==========================\ntriggered "+t);
		this.miseAjourTemps(t);
		
		//Si on est au repos on demande ce qu'on fait en notifiant de notre position
		if(this.getEtat() == Etat.REPOS)
			this.notifyAllNiveau();
		//Si on est en mouvement on effectue le mouvement
		else{
			//On calcul la distance parcourue
			float distanceParcourue = (this.getMoteur().getVitesse()*(this.time2-this.time1))/1000;
			if(this.sensDeplacement == Sens.DOWN) distanceParcourue = distanceParcourue * -1;

			//On met a jour la nouvelle position de la cabine
			float positionApresMouvement = this.getMoteur().getCabine().getPosition()+distanceParcourue;
			this.getMoteur().getCabine().setPosition(positionApresMouvement);
			int niveauAsc = this.capteurNiveau.detecter(this.niveauMin, this.niveauMax, this.getMoteur().getCabine().getPosition(), this.distanceNiveaux);
			
			System.out.println("position reelle : "+ this.getMoteur().getCabine().getPosition() + " metres ou "+this.getMoteur().getCabine().getPosition()/this.getDistanceNiveaux() );

			
			//Si la cabine atteint un nouveau niveau on fait varier cette valeur et on notifie tout les observeurs
			if(niveauAsc != -1 && niveauAsc != this.position)
				this.setPosition(niveauAsc);
			System.out.println("position ascenseur : "+ this.getPosition());
		}
	}
	/*
	 * ===========================================================
	 * Methodes ObserverEntree 
	 * ===========================================================
	 */

	@Override
	public void entre() {
		if(!this.getMoteur().getCabine().getCapteurpoids().ajouterPersonne()){
			this.notifyAllSurcharge();
		}
	}
	/*
	 * ===========================================================
	 * Methodes ObserverSortie 
	 * ===========================================================
	 */
	@Override
	public void sortie() {
		this.getMoteur().getCabine().getCapteurpoids().retirePersonne();
	}
}