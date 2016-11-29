package systemSimulation.Implementation;
import interfaceUtilisateur.Interface.IintefaceUtilisateur;
import interfaceUtilisateur.Interface.SystemIUFactory;
import sequenceur.Interface.ISequencer;
import sequenceur.Interface.SequenceurFactory;
import systemAscenseur.Interface.ISystemAscenseur;
import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;
import systemControl.Interface.ISystemControl;
import utilisateur.ObserverAppelUser;
import utilisateur.ObserverDeplacementUser;

class Configurator {

	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	protected static ISequencer seq;
	/**Configuration du Sequenceur**/
	protected long temps_debut;
	protected float coefficient;
	protected long temps_execution;

	/**Configuration du SystemAscenseur**/
	protected float vitesseMoteur;
	protected int niveauMin;
	protected int niveauMax;
	protected float distanceNiveaux;

	protected ISystemControl sysControle;
	protected Flow flow;
	protected String flowFileName;
	protected ISystemAscenseur sa;
	protected IintefaceUtilisateur ui;
	/*
	 * =========================================================== 
	 * Getters - Setters 
	 * ===========================================================
	 */
	public static ISequencer getSeq() {return seq;}
	public static void setSeq(ISequencer seq) {Configurator.seq = seq;}

	public long getTemps_debut() {return temps_debut;}
	public void setTemps_debut(long temps_debut) {this.temps_debut = temps_debut;}

	public float getCoefficient() {return coefficient;}
	public void setCoefficient(float coefficient) {this.coefficient = coefficient;}

	public long getTemps_execution() {return temps_execution;}
	public void setTemps_execution(long temps_execution) {this.temps_execution = temps_execution;}

	public float getVitesseMoteur() {return vitesseMoteur;}
	public void setVitesseMoteur(float vitesseMoteur) {this.vitesseMoteur = vitesseMoteur;}

	public int getNiveauMin() {return niveauMin;}
	public void setNiveauMin(int niveauMin) {this.niveauMin = niveauMin;}

	public int getNiveauMax() {return niveauMax;}
	public void setNiveauMax(int niveauMax) {this.niveauMax = niveauMax;}

	public float getDistanceNiveaux() {return distanceNiveaux;}
	public void setDistanceNiveaux(float distanceNiveaux) {this.distanceNiveaux = distanceNiveaux;}

	public ISystemControl getSysControle() {return sysControle;}
	public void setSysControle(ISystemControl sysControle) {this.sysControle = sysControle;}

	public Flow getFlow() {return flow;}
	public void setFlow(Flow flow) {this.flow = flow;}

	public String getFlowFileName() {return flowFileName;}
	public void setFlowFileName(String flowFileName) {this.flowFileName = flowFileName;}

	public IintefaceUtilisateur getUi() {return ui;}
	public void setUi(IintefaceUtilisateur ui) {this.ui = ui;}

	public ISystemAscenseur getSa() {return sa;}
	public void setSa(ISystemAscenseur sa) {this.sa = sa;}
	/*
	 * =========================================================== 
	 * Construcuteurs
	 * ===========================================================
	 */
	/**
	 * Constructeur par defaut
	 */
	public Configurator() throws Throwable{
		this.temps_debut = 0;
		this.coefficient = 100;
		this.temps_execution = 100000;

		this.vitesseMoteur = 1;
		this.niveauMin = 0;
		this.niveauMax = 10;
		this.distanceNiveaux = 3;
		this.flowFileName = "users";

		this.flow = Flow.creatFlow();
		//On cree le sequenceur
		seq = SequenceurFactory.create(this.temps_execution, this.temps_debut, this.coefficient);

		//On cree le systeme de controle
		this.sysControle = systemControl.Interface.SystemControlFactory.create();

		//On cree le systemeAscenseur
		this.sa = systemAscenseur.Interface.SystemAscenseurFactory.create(this.vitesseMoteur, this.niveauMin, this.niveauMax, this.distanceNiveaux);

		//On donne au systemControle l'interface pour commander le systemAscenseur
		this.sysControle.link(this.sa);

		seq.addProcess(this.sa,12);

		this.ui = SystemIUFactory.createInstance();
		this.ui.link(sysControle);

		//On cree le Flow
		this.flow = Flow.creatFlow();

		//On initialise les utilisateurs systeme a partir d un fichier flow
		this.flow.addFichier(this.flowFileName);

		//On ratache l'interface Utilisateur aux utilisateurs
		this.flow.addObserveurDeplacement((ObserverDeplacementUser) ui);

		//On ratache l'interface Utilisateur aux utilisateurs
		this.flow.addObserveurAppel((ObserverAppelUser) ui);

		this.ui.addObserverArret(flow);
		this.ui.addObserverSurcharge(flow);
		//On demande au systemControle d'observer le niveau du systemAscenseur
		this.sa.addObserverNiveau((ObserverNiveau) sysControle);
		this.sa.addObserverNiveau(ui);
		this.sa.addObserverArret(ui);
		this.sa.addObserverSurcharge(ui);
		seq.addProcess(flow,12);
	}

	/**
	 * Constructeurs avec tout les parametres
	 * @param temps_debut
	 * @param coefficient
	 * @param temps_execution
	 * @param vitesseMoteur
	 * @param niveauMin
	 * @param niveauMax
	 * @param distanceNiveaux
	 * @param flowFileName 
	 */
	public Configurator(long temps_debut, float coefficient, long temps_execution, float vitesseMoteur, int niveauMin, int niveauMax, float distanceNiveaux, String flowFileName) throws Throwable{
		this.temps_debut = temps_debut;
		this.coefficient = coefficient;
		this.temps_execution = temps_execution;

		this.vitesseMoteur = vitesseMoteur;
		this.niveauMin = niveauMin;
		this.niveauMax = niveauMax;
		this.distanceNiveaux = distanceNiveaux;
		this.flowFileName = flowFileName;
		this.flow = Flow.creatFlow();

		//On cree le sequenceur
		seq = SequenceurFactory.create(this.temps_execution, this.temps_debut, this.coefficient);

		//On cree le systeme de controle
		this.sysControle = systemControl.Interface.SystemControlFactory.create();

		//On cree le systemeAscenseur
		this.sa = systemAscenseur.Interface.SystemAscenseurFactory.create(this.vitesseMoteur, this.niveauMin, this.niveauMax, this.distanceNiveaux);

		//On donne au systemControle l'interface pour commander le systemAscenseur
		this.sysControle.link(this.sa);

		seq.addProcess(this.sa,12);

		this.ui = SystemIUFactory.createInstance();
		this.ui.link(sysControle);

		//On cree le Flow
		this.flow = Flow.creatFlow();

		//On initialise les utilisateurs systeme a partir d un fichier flow
		this.flow.addFichier(this.flowFileName);

		//On ratache l'interface Utilisateur aux utilisateurs
		this.flow.addObserveurDeplacement((ObserverDeplacementUser) ui);

		//On ratache l'interface Utilisateur aux utilisateurs
		this.flow.addObserveurAppel((ObserverAppelUser) ui);

		this.ui.addObserverArret(flow);
		this.ui.addObserverSurcharge(flow);
		//On demande au systemControle d'observer le niveau du systemAscenseur
		this.sa.addObserverNiveau((ObserverNiveau) sysControle);
		this.sa.addObserverNiveau(ui);
		this.sa.addObserverArret(ui);
		this.sa.addObserverSurcharge(ui);
		seq.addProcess(flow,12);
	}
	/*
	 * =========================================================== 
	 * Methodes
	 * ===========================================================
	 */
	public void demarer()throws Throwable{
		/*
		 * =========================================================== 
		 * Creation des objets de la simulation
		 * ===========================================================
		 */


		seq.start();
		this.flow.tempDeplacementUser();
		this.flow.temps_attente();
		this.flow.temps_deplacement();
	}

	public void addObserverNiveau(ObserverNiveau obj){
		this.sa.addObserverNiveau(obj);
	}

	public void addObserverArret(ObserverArret obj){
		this.sa.addObserverArret(obj);
	}

	public void addObserverSurcharge(ObserverSurcharge obj){
		this.sa.addObserverSurcharge(obj);
	}

	public static void main(String args[]) throws Throwable{
		Configurator c = new Configurator(0, 100, 100000, 1, 0, 10, 3, "users");
		c.demarer();
	} 

}
