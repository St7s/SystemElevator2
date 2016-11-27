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
	public void ajouterPersonne() {
		if(poidsActuel < getNbrPersonneMax()){
			poidsActuel++;		
		} else {
			//on peut pas ajouter ou on fait quand même
		}
	}

	public void retirePersonne() {
		if(poidsActuel > 0){
			poidsActuel--;
		} else {
			//on peut pas retirer
		}
	}
}
