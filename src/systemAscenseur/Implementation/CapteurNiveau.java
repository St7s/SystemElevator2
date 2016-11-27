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
		for (int niveau = 0; niveau < nbrNiveaux; niveau++)
			if(positionActuelle - (distancesCapteurs * niveau) <= distancesCapteurs / 3)
				return niveau;
		return -1;
	}
}
