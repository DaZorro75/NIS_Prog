package de.unidue.iem.tdr.nis.client;

import de.unidue.iem.tdr.nis.client.solutions.*;

/**
 * Diese Klasse ermöglicht das Abrufen von Aufgaben vom Server und die
 * Implementierung der dazugehörigen Lösungen.
 * <p>
 * Nähere Informationen zu den anderen Klassen und den einzelnen Aufgabentypen
 * entnehmen Sie bitte der entsprechenden Dokumentation im TMT und den Javadocs
 * zu den anderen Klassen.
 *
 * @see Connection
 * @see TaskObject
 */
public class Client implements TaskDefs {
    private Connection con;

    /* Tragen Sie hier ihr Token ein um Ihre Lösungen testen zu können */
    private static final String token = "8e5a543820940372353fcfdf74cca8d5781887af3216cb4ccbebad11311a2504";
    /* !Bearbeiten Sie nichts anderes in dieser Datei! */

    /* Aufgaben, die bearbeitet werden sollen */
    private final int[] tasks = {TASK_CLEARTEXT, TASK_XOR, TASK_MODULO,
            TASK_FACTORIZATION, TASK_VIGENERE, TASK_DES_KEYSCHEDULE,
            TASK_DES_RBLOCK, TASK_DES_FEISTEL, TASK_DES_ROUND, TASK_AES_GF8,
            TASK_AES_KEYEXPANSION, TASK_AES_MIXCOLUMNS,
            TASK_AES_TRANSFORMATION, TASK_AES_3ROUNDS, TASK_RC4_LOOP,
            TASK_RC4_KEYSCHEDULE, TASK_RC4_ENCRYPTION, TASK_DIFFIEHELLMAN,
            TASK_RSA_ENCRYPTION, TASK_RSA_DECRYPTION, TASK_ELGAMAL_ENCRYPTION,
            TASK_ELGAMAL_DECRYPTION};

    /**
     * Klassenkonstruktor. Baut die Verbindung zum Server auf.
     */
    public Client() {
        con = new Connection();
        if (con.auth(token))
            System.out.println("Anmeldung erfolgreich.");
        else
            System.out.println("Anmeldung nicht erfolgreich.");
    }

    /**
     * Durchläuft die Liste von Aufgaben und fordert diese vom Server an.
     */
    public void taskLoop() {
        TaskObject currentTask;
        AbstractSolution currentSolution;
        String[] keyArr = new String[1];
        for (int task : tasks) {
            String solution = null;
            try {
	            switch (task) {
	                default: case TASK_CLEARTEXT:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution01(con, currentTask);
	                    break;
	                case TASK_XOR:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution02(con, currentTask);
	                    break;
	                case TASK_MODULO:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution03(con, currentTask);
	                	break;
	                case TASK_FACTORIZATION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution04(con, currentTask);
	                	break;
	                case TASK_VIGENERE:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution05(con, currentTask);
	                	break;
	                case TASK_DES_KEYSCHEDULE:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution06(con, currentTask);
	                	break;
	                case TASK_DES_RBLOCK:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution07(con, currentTask);
	                	break;
	                case TASK_DES_FEISTEL:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution08(con, currentTask);
	                	break;
	                case TASK_DES_ROUND:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution09(con, currentTask);
	                	break;
	                case TASK_AES_GF8:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution10(con, currentTask);
	                	break;
	                case TASK_AES_KEYEXPANSION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution11(con, currentTask);
	                	break;
	                case TASK_AES_MIXCOLUMNS:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution12(con, currentTask);
	                	break;
	                case TASK_AES_TRANSFORMATION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution13(con, currentTask);
	                	break;
	                case TASK_AES_3ROUNDS:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution14(con, currentTask);
	                	break;
	                case TASK_RC4_LOOP:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution15(con, currentTask);
	                	break;
	                case TASK_RC4_KEYSCHEDULE:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution16(con, currentTask);
	                	break;
	                case TASK_RC4_ENCRYPTION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution17(con, currentTask);
	                	break;
	                case TASK_DIFFIEHELLMAN:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution18(con, currentTask);
	                	break;
	                case TASK_RSA_ENCRYPTION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution19(con, currentTask);
	                	break;
	                case TASK_RSA_DECRYPTION:
	                	currentSolution = new Solution20(con, null);
	                	keyArr = currentSolution.getKey();
	                	if(keyArr == null) throw new RuntimeException("Es wurde kein Key gesetzt!");
	                	currentTask = con.getTask(task, keyArr);
	                	currentSolution.setTask(currentTask);
	                	break;
	                case TASK_ELGAMAL_ENCRYPTION:
	                	currentTask = con.getTask(task);
	                	currentSolution = new Solution21(con, currentTask);
	                	break;
	                case TASK_ELGAMAL_DECRYPTION:
	                	currentSolution = new Solution22(con, null);
	                	keyArr = currentSolution.getKey();
	                	if(keyArr == null) throw new RuntimeException("Es wurde kein Key gesetzt!");
	                	currentTask = con.getTask(task, keyArr);
	                	currentSolution.setTask(currentTask);
	                	break;
	            }
	            
	            solution = currentSolution.run();
	            
	            if(solution == null) {
	            	System.out.println("Aufgabe "+task+": Nicht implementiert!");
	            	continue;
	            }
	            
	            if (con.sendSolution(solution))
	                System.out.println("Aufgabe " + task + ": Lösung korrekt");
	            else
	                System.out.println("Aufgabe " + task + ": Lösung falsch");
            } catch(Exception e) {
            	System.out.println("Aufgabe "+task+": fehlerhaft");
            	System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        if (c.con.isReady())
            c.taskLoop();
        c.con.close();
    }
}
