package sequenceur.Interface;

public interface ISequencer {

	/**
	 * 
	 * @param process 
	 */
	public void addProcess(Event event,long periode);
	/**
	 * 
	 * @param process 
	 */
	public long SimulationTime();
	/**
	 * @throws Throwable 
	 * 
	 */
	public void start() throws Throwable; 

}
