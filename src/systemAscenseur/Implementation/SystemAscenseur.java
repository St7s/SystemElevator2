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
	private Moteur moteur;
	private CapteurNiveau capteurNiveau;
	private float distanceNiveaux;
	private long time1;
	private long time2;
	private ArrayList<ObserverArret> observersArret;
	private ArrayList<ObserverNiveau> observersNiveau;
	private ArrayList<ObserverSurcharge> observersSurcharge;

	//public ArrayList<Sens> test = new ArrayList<Sens>();
	
	/*
	 * =========================================================== 
	 * Getters - Setters
	 * ===========================================================
	 */
	public int getNiveauMax() {return niveauMax;}
	public void setNiveauMax(int niveauMax) {this.niveauMax = niveauMax;}

	public int getPosition() {return position;}
	public void setPosition(int position) {this.position = position;}

	public int getNiveauMin() {return niveauMin;}
	public void setNiveauMin(int niveauMin) {this.niveauMin = niveauMin;}

	public Etat getEtat() {return etat;}
	public void setEtat(Etat etat) {this.etat = etat;}

	public Moteur getMoteur() {return moteur;}
	public void setMoteur(Moteur moteur) {this.moteur = moteur;}

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
		this.moteur = moteur;
		this.distanceNiveaux = distanceNiveaux;
		this.capteurNiveau = capteurNiveau;
		this.time1 = 0;
		this.time2 = 0;
		
		this.setObserversArret(new ArrayList<ObserverArret>());
		this.setObserversNiveau(new ArrayList<ObserverNiveau>());
		this.setObserversSurcharge(new ArrayList<ObserverSurcharge>());
		/*
		test.add(Sens.UP);
		test.add(Sens.UP);
		test.add(Sens.UP);
		
		test.add(Sens.UP);
		test.add(Sens.UP);
		test.add(Sens.UP);
		
		test.add(Sens.DOWN);
		test.add(Sens.DOWN);
		test.add(Sens.DOWN);*/
	}

	/*
	 * ===========================================================
	 * Methodes de classe 
	 * ===========================================================
	 */
	/**
	 * Notifier tout les observeur du niveau de la position actuelles
	 */
	public void notifyAllNiveau(){
		for (ObserverNiveau observerNiveau : observersNiveau)
			observerNiveau.notifierNiveau(this.position);
	}
	
	/**
	 * Mettre a jour les variables de temps
	 * @param temps
	 */
	private void miseAjourTemps(long temps){
		this.time1 = this.time2;
		this.time2 = temps; 
	}

	/**
	 * 
	 * @param sens
	 */
	private void deplacement(Sens sens) {
		float positionAvantMouvement = this.getMoteur().getCabine().position;
		long tempsEcoule = this.time2 - this.time1;
		float vitesse = this.getMoteur().getVitesse();
		float deplacement = (vitesse*tempsEcoule)/1000;
		float positionApresMouvement = (sens == Sens.UP)?positionAvantMouvement+deplacement:positionAvantMouvement-deplacement;
		this.getMoteur().getCabine().setPosition(positionApresMouvement);
		int niveauAsc = this.capteurNiveau.detecter(this.niveauMax, positionApresMouvement, this.distanceNiveaux);
		
		
		this.notifyAllNiveau();//DEBUG
		System.out.println("deplacement:"+niveauAsc);//DEBUG
		if(niveauAsc != -1 && niveauAsc != this.position){
			this.position = niveauAsc;
			System.out.println("niveau : " +this.position);
			this.notifyAllNiveau();
		}
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
		System.out.println(sens);
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
			deplacement(sens);//
		}
	}
	/*
	 * ===========================================================
	 * Methodes Observer 
	 * ===========================================================
	 */
	@Override
	public void addObserverArret(ObserverArret obj){this.observersArret.add(obj);}
	@Override
	public void addObserverNiveau(ObserverNiveau obj){this.observersNiveau.add(obj);}
	@Override
	public void addObserverSurcharge(ObserverSurcharge obj){this.observersSurcharge.add(obj);}
	/*
	 * ===========================================================
	 * Methodes Event 
	 * ===========================================================
	 */
	
	@Override
	public void trigger(long t) {
		this.miseAjourTemps(t);
		//this.commande(test.get(0));
		//test.remove(0);
		/*for (ObserverNiveau observerNiveau : this.observersNiveau) {
			observerNiveau.notifierNiveau(this.position);
		}*/
	}
}