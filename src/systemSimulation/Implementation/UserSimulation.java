package systemSimulation.Implementation;
 class UserSimulation extends User{
	private long tempsAppel;
	private long tempsEntre;
	private long tempsSortie;
	
	public UserSimulation(String nom, int niveau_initial, int niveau_final) {
		super(nom, niveau_initial, niveau_final);
	}
	
	public long getTempsAppel() {
		return tempsAppel;
	}
	public void setTempsAppel(long tempsAppel) {
		this.tempsAppel = tempsAppel;
	}
	
	public long getTempsSortie() {
		return tempsSortie;
	}
	public void setTempsSortie(long tempsSortie) {
		this.tempsSortie = tempsSortie;
	}

	public long getTempsEntre() {
		return tempsEntre;
	}

	public void setTempsEntre(long tempsEntre) {
		this.tempsEntre = tempsEntre;
	}
}
