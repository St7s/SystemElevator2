package systemAscenseur.Implementation;

class CapteurPoids {
	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private int poidsActuel;
	private int nbrPersonneMax;
	
	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public int getPoidsActuel() {
		return poidsActuel;
	}

	public void setPoidsActuel(int poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	public int getNbrPersonneMax() {
		return nbrPersonneMax;
	}

	public void setNbrPersonneMax(int nbrPersonneMax) {
		this.nbrPersonneMax = nbrPersonneMax;
	}

	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	
	/**
	 * Constructeur par defaut
	 */
	public CapteurPoids() {
		this.poidsActuel = 0;
		this.nbrPersonneMax = 0;
	}

	/*
	 * =========================================================== 
	 * Methodes de classe 
	 * ===========================================================
	 */
	public boolean ajouterPersonne() {
		if(poidsActuel < getNbrPersonneMax()){
			poidsActuel++;		
			return true;
		}
		return false;
	}

	public boolean retirePersonne() {
		if(poidsActuel > 0){
			poidsActuel--;
			return true;
		}
		return false;
	}
}
