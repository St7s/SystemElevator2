package systemAscenseur.Implementation;

import systemAscenseur.Interface.IU_SA;

class Cabine implements IU_SA {
	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	public EtatCabine etat;
	public float position;
	public CapteurPoids capteurPoids;

	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public EtatCabine getEtat() {
		return etat;
	}

	public void setEtat(EtatCabine etat) {
		this.etat = etat;
	}

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
		this.position = position;
	}

	public CapteurPoids getCapteurpoids() {
		return capteurPoids;
	}

	public void setCapteurpoids(CapteurPoids capteurpoids) {
		this.capteurPoids = capteurpoids;
	}

	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	public Cabine() {
		this.etat = EtatCabine.FERME;
		this.position = 0;
		this.capteurPoids = new CapteurPoids();
	}

	/*
	 * =========================================================== 
	 * Methodes de classe 
	 * ===========================================================
	 */
	/**
	 * 
	 */
	public void finMouvement() {
		// TODO Auto-generated method
	}

	/**
	 * Permet de déplacer la cabine
	 * @param vitesse
	 */
	public void miseEnMouvement(float vitesse) {
		// TODO Auto-generated method
	}
	
	/**
	 * 
	 */
	public void entre() {
		capteurPoids.ajouterPersonne();
	}

	/**
	 * 
	 */
	public void sortie() {
		capteurPoids.retirePersonne();
	}
}
