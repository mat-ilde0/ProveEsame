package it.unibs.fp.MorraCinese;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileDati {

	public static void scritturaFile(Gioco gioco){
		
		File f = new File ("Statistiche.txt");
		
		try (PrintWriter pw = new PrintWriter (new BufferedWriter(new FileWriter ( f )))) {
				
			pw.println(gioco.toString());
		} catch (Exception e) {
			System.out.println("Errore nella scrittura!!");
		}
	}
}
