package sequenceur.Implementation;

import sequenceur.Interface.Event;

class Process {

	public Event evenement;
	/**
	 * temps du dernier reveille
	 */
	public long last_wake;
	public long periode;
	/**
	 * Getter of last_wake
	 */
	public long getLast_wake() {
	 	 return last_wake; 
	}
	/**
	 * Setter of last_wake
	 */
	public void setLast_wake(long last_wake) { 
		 this.last_wake = last_wake; 
	}
	/**
	 * Getter of periode
	 */
	public long getPeriode() {
	 	 return periode; 
	}
	/**
	 * Setter of periode
	 */
	public void setPeriode(long periode) { 
		 this.periode = periode; 
	}
	
	public Process(Event event, long periode){
		this.evenement = event;
		this.last_wake = 0;
		this.periode = periode;
	}
	/**
	 * @param t
	 */
	public void trigger(long t) {
		evenement.trigger(t);
	} 

}
