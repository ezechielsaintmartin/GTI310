package gti310.tp2.audio;

import java.io.DataInputStream;
import java.io.IOException;

import gti310.tp2.io.FileSink;
import gti310.tp2.io.FileSource;

public class ConcreteAudioFilter implements AudioFilter {

	// Constante pour récupérer les entêtes
	private int SIZE = 44;

	private FileSource fichierSource;
	private FileSink fichierSink;
	private byte[] tableauByte;
	private DataInputStream InputSource = null;
	private int compteur = 0;
	private int frequence = 0;
	private int numChannel = 0;
	private int numBitEchantillon = 0;
	/** Variable pour parcourir le fichier */

	private int byte0, byte00, byte1, byte2, byte3, byte4, byte5, byte6 = 0;

	public ConcreteAudioFilter(FileSource fichierSource, FileSink fichierSink) {
		this.fichierSource = fichierSource;
		this.fichierSink = fichierSink;
	}

	public FileSource getFichierSource() {
		return fichierSource;
	}

	public void setFichierSource(FileSource fichierSource) {
		this.fichierSource = fichierSource;
	}

	public FileSink getFichierSink() {
		return fichierSink;
	}

	public void setFichierSink(FileSink fichierSink) {
		this.fichierSink = fichierSink;
	}

	@Override
	public void process() {

		// Se rendre jusqu'à l'octet de compression
		tableauByte = fichierSource.pop(8);
		tableauByte = fichierSource.pop(4);
		// InputSource = fichierSource.get_reader();

		// Vérification que le fichier est wav
		if (isThereChar(tableauByte, "WAVE")) {
			tableauByte = fichierSource.pop(10);
			// Vérification des 4 bytes de la fréquence
			tableauByte = fichierSource.pop(6);

			byte0 = Byte.toUnsignedInt(tableauByte[0]);
			byte00 = Byte.toUnsignedInt(tableauByte[1]);

			byte1 = Byte.toUnsignedInt(tableauByte[2]);
			byte2 = Byte.toUnsignedInt(tableauByte[3]);
			byte3 = Byte.toUnsignedInt(tableauByte[4]);
			byte4 = Byte.toUnsignedInt(tableauByte[5]);

			frequence = ((byte4 << 24) + (byte3 << 16) + (byte2 << 8) + (byte1 << 0));

			// System.out.println("À l'emplacement " + i +" du tableau nous avons = " + x);

			System.out.println("frequence " + frequence);
			// Si la fréquence est de 44100 KHZ
			if (frequence == 44100) {

				// Vérification du nombre de channels
				numChannel = ((byte00 << 8) + (byte0 << 0));
				System.out.println("Le nombre de channel est" + numChannel);
				tableauByte = fichierSource.pop(8);
				numBitEchantillon = Byte.toUnsignedInt(tableauByte[6]);

				// Si le fichier est mono
				if (numChannel == 1) {
					// Vérification que le fichier est mono de 8bits
					if (numBitEchantillon == 8) {
 
						
					}

					// Vérification que le fichier est mono de 16bits
					if (numBitEchantillon == 16) {

					}

				}

				// Si le fichier est stéréo
				if (numChannel == 2) {

					// Vérification que le fichier est mono de 8bits
					if (numBitEchantillon == 8) {

					}

					// Vérification que le fichier est mono de 16bits
					if (numBitEchantillon == 16) {

					}
				}

			}

		}

	}

	/**
	 * Methode qui Comparele string fournit avec les valeurs des charactères d'un
	 * tableau de byte
	 * 
	 * @return true ou false
	 * 
	 */

	public boolean isThereChar(byte[] chaArray, String chr) {
		boolean bool = false;
		for (int i = 0; i < chaArray.length; i++) {
			char x = (char) chaArray[i];

			if (chr.charAt(i) == x) {

				bool = true;
				compteur++;

			}
		}
		if (compteur == chaArray.length)
			return true;

		return false;
	}

	public void skipBytes(int nombreByte) {
		try {
			InputSource.skipBytes(nombreByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
