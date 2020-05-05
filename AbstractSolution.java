package de.unidue.iem.tdr.nis.client;

import de.unidue.iem.tdr.nis.client.solutions.Helper;

public abstract class AbstractSolution extends Helper {
	protected Connection con;
	protected TaskObject task;
	
	public AbstractSolution(Connection con, TaskObject task) {
		super();
		this.con = con;
		this.task = task;
	}
	
	/** In jeder Lösung zu implementierende Methode,
	 * die bei der Lösungsüberprüfung aufgerufen wird.
	 * @return String Die Lösung der Aufgabe */
	public abstract String run();
	
	/**
	 * In manchen Aufgaben zu überschreibende Methode um den Verschlüsselungskey
	 * der Aufgabe zu definieren.
	 * @return String[] Ein Array, welches den Verschlüsselungskey der Aufgabe enthält
	 */
	public String[] getKey() {
		return null;
	}
	
	/** Vereinfachte Methode um zusätzliche Lösungsparamter für eine Aufgabe zu verschicken
	 * @param params Die zu verschickenden Paramter
	 */
	protected final void sendMoreParams(String[] params) {
		this.con.sendMoreParams(this.task, params);
	}
	
	/**
	 * Setter Methode für das TaskObject zur Laufzeit
	 * @param task Das neue TaskObject für die aktuelle Lösung
	 */
	protected final void setTask(TaskObject task) {
		this.task = task;
	}
}
