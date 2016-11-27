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
	public int detecter(int nbrNiveaux, float positionActuelle,float distancesCapteurs){
		float distanceVersEtage;
		for (int niveau = 0; niveau < nbrNiveaux; niveau++){
			distanceVersEtage = positionActuelle - (distancesCapteurs * niveau);
			if(distanceVersEtage <= 1.1 && distanceVersEtage >= -1.1)
				return niveau;
		}
		return -1;
	}
}
