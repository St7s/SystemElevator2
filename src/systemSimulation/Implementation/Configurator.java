package systemSimulation.Implementation;
import sequenceur.Interface.ISequencer;
import sequenceur.Interface.SequenceurFactory;
import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.ObserverNiveau;
import systemControl.Interface.ISystemControl;

 class Configurator {

	static ISequencer seq;
	/**
	 * 
	 */
	public static void main(String args[]) throws Throwable{
		/*
		 * =========================================================== 
		 * Valeurs Config
		 * ===========================================================
		 */
		/**Configuration du Sequenceur**/
		long temps_debut = 0;
		float coefficient_temps = 1;
		long temps_execution = 9000;
		
		/**Configuration du SystemAscenseur**/
		float vitesseMoteur = 1;
		int niveauMin = 0;
		int niveauMax = 10;
		float distanceNiveaux = 3;
		/*
		 * =========================================================== 
		 * Creation des objets de la simulation
		 * ===========================================================
		 */
		
		//On cree le sequenceur
		 seq = SequenceurFactory.create(temps_execution, temps_debut, coefficient_temps);

		//On cree le systeme de controle
		ISystemControl sysControle = systemControl.Interface.SystemControlFactory.create();
		
		//On cree le systemeAscenseur
		ISystemAscenseur sa = systemAscenseur.Interface.SystemAscenseurFactory.create(vitesseMoteur, niveauMin, niveauMax, distanceNiveaux);

		//On donne au systemControle l'interface pour commander le systemAscenseur
		sysControle.link(sa);

		seq.addProcess(sa,12);
		
	
		//On demande au systemControle d'observer le niveau du systemAscenseur
		sa.addObserverNiveau((ObserverNiveau) sysControle);

		seq.start();

	} 

}
