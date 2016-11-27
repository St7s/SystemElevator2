package systemAscenseur.Implementation;

class Moteur {

	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private float vitesse;
	private EtatMoteur etat;
	private Cabine cabine; 

	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public EtatMoteur getEtat() {
		return etat;
	}

	public void setEtat(EtatMoteur etat) {
		this.etat = etat;
	}

	public Cabine getCabine() {
		return cabine;
	}

	public void setCabine(Cabine cabine) {
		this.cabine = cabine;
	}

	/*
	 * =========================================================== 
	 * Constructeurs
	 * ===========================================================
	 */
	/**
	 * Constructeur par defaut
	 */
	public Moteur() {
		this.vitesse = 0;
		this.etat = EtatMoteur.ARRET;
	}

	/**
	 * Constructeur avec parametre
	 * 
	 * @param vitesse
	 */
	public Moteur(float vitesse, Cabine cabine) {
		this.vitesse = vitesse;
		this.etat = EtatMoteur.ARRET;
		this.cabine = cabine;
	}

	/*
	 * =========================================================== 
	 * Methodes de classe
	 * ===========================================================
	 */
	public void marche(Sens sens) {
		if (this.getEtat() == EtatMoteur.ARRET){
			etat = EtatMoteur.MARCHE;
			this.getCabine().miseEnMouvement(this.getVitesse());
		} else {
			//System.out.println("DEBUG : le moteur est deja en marche");
		}
	}

	public void arretImmediat() {
		if (this.getEtat() == EtatMoteur.MARCHE){
			etat = EtatMoteur.ARRET;
		} else {
			//System.out.println("DEBUG : le moteur est deja arreter");
		}
	}

}