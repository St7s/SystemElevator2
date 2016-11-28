package systemAscenseur.Implementation;

class CapteurNiveau {

	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private boolean detection;

	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	/**
	 * Constructeur par defaut
	 */
	public CapteurNiveau() {
		this.detection = false;
	}

	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public boolean getDetection() {
		return detection;
	}

	public void setDetection(boolean detection) {
		this.detection = detection;
	}
	/*
	 * =========================================================== 
	 * Methode
	 * ===========================================================
	 */
	public int detecter(int niveauMin, int niveauMax, float positionActuelle, float distancesCapteurs, float vitesseDeplacement){
		float distanceVersEtage;
		//on test pour tous les niveaux
		for (int niveau = niveauMin; niveau < niveauMax; niveau++){
			distanceVersEtage = positionActuelle - (distancesCapteurs * niveau);//calcule de la distance 
			//TODO MODIFIER marche pas avec 1.1
			if(distanceVersEtage <= vitesseDeplacement && 0 <= distanceVersEtage){  //equivalent à un demi-étage supérieur
				return niveau;
			}
		}
		return niveauMin-1;
	}
}
