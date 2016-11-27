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
	public int detecter(int niveauMin, int niveauMax, float positionActuelle, float distancesCapteurs){
		float distanceVersEtage;
		//on test pour tous les niveaux
		for (int niveau = niveauMin; niveau < niveauMax; niveau++){
			distanceVersEtage = positionActuelle - (distancesCapteurs * niveau);//calcule de la distance 
			if(distanceVersEtage <= 0.5f*distancesCapteurs && 0 <= distanceVersEtage){  //equivalent � un demi-�tage sup�rieur
				return niveau;
			}
		}
		return niveauMin-1;
	}
}
