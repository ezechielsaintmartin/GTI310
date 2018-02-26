package gti310.tp2;

import java.io.FileNotFoundException;

import gti310.tp2.audio.ConcreteAudioFilter;
import gti310.tp2.io.FileSink;
import gti310.tp2.io.FileSource;

public class Application {

	/**
	 * Launch the application
	 * @param args This parameter is ignored
	 */
	private static FileSource fichierSource;
	private static FileSink fichierSortie;
	static ConcreteAudioFilter filtreAudio;


	public static void main(String args[]) {
		System.out.println("Audio Resample project!");
		
		
		init();
		
	}

	private static void init() {
		// TODO Auto-generated method stub
		try {
			
			//Instanciation du fichier d'entrée
			fichierSource= new FileSource("C:/Users/ÉzéchielSaint-Martin/Downloads/media-TP2/media/App1Test1Stereo16bits.wav ");
			//Instanciation du fichier de sortie
			fichierSortie = new FileSink("C:/Users/ÉzéchielSaint-Martin/Downloads/media-TP2/media/SORTIE.wav");
			//Instanciation du filtre
			filtreAudio = new ConcreteAudioFilter(fichierSource,fichierSortie);
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		filtreAudio.process();
	}
}
