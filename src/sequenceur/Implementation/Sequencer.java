package sequenceur.Implementation;

import java.util.ArrayList;


import sequenceur.Interface.Event;
import sequenceur.Interface.ISequencer;
 class Sequencer implements ISequencer {
	
	
	/**
	 * 
	 */
	private long temps_debut;
	/**
	 * 
	 */
	private long simulationTime;
	/**
	 * 
	 */
	private float coefficient_temps;
	/**
	 * 
	 */
	public ArrayList<Process> process;
	/**
	 * 
	 */
	private long temps_execution;

	/**
	 * 
	 * @param temps_execution
	 * @param temps_debut
	 * @param coefficient_temps
	 */
	public Sequencer(long temps_execution, long temps_debut, float coefficient_temps) {
		setCoefficient_temps(coefficient_temps);
		setTemps_debut(temps_debut);
		setTemps_execution(temps_execution);
		process = new ArrayList<Process>();
	}
	/**
	 * Getter of temps_debut
	 */
	public long getTemps_debut() {
	 	 return temps_debut; 
	}
	/**
	 * Setter of temps_debut
	 */
	public void setTemps_debut(long temps_debut) { 
		 this.temps_debut = temps_debut; 
	}
	/**
	 * Getter of coefficient_temps
	 */
	public float getCoefficient_temps() {
	 	 return coefficient_temps; 
	}
	/**
	 * Setter of coefficient_temps
	 */
	public void setCoefficient_temps(float coefficient_temps) { 
		 this.coefficient_temps = coefficient_temps; 
	}
	/**
	 * Getter of temps_execution
	 */
	public long getTemps_execution() {
	 	 return temps_execution; 
	}
	/**
	 * Setter of temps_execution
	 */
	public void setTemps_execution(long temps_execution) { 
		 this.temps_execution = temps_execution; 
	}
	/**
	 * 
	 */
	@Override
	public void start() throws Throwable{ 
		long debut = System.currentTimeMillis();
		long tempsActuel = debut;
		long periode = 100;
		while((tempsActuel - debut) < getTemps_execution())
		{
			for(Process processus : process)
			{
				if((tempsActuel - processus.getLast_wake() )>= processus.getPeriode())
				{
					simulationTime = ((long) (temps_debut+ ((tempsActuel - debut) )));
					processus.trigger(simulationTime);
					processus.setLast_wake(simulationTime);
				}
			}
			Thread.sleep((long) (periode/getCoefficient_temps()));
			tempsActuel +=periode;;
		}
	 }
	/**
	 * 
	 */
	@Override
	public void addProcess(Event event,long periode) {
		this.process.add(new Process(event,periode));
	}
	@Override
	public long SimulationTime() {
		return simulationTime;
	}

}
