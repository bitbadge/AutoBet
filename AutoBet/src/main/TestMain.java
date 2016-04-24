package main;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class TestMain {
	/*
	 * public static void main(String[] args) {
	 * 
	 * MatchUtility.printAllMatchesToFile();
	 * 
	 * 
	 * for (int i = 1; i < 7000; i++) {
	 * System.out.println(MatchUtility.populateMatch("http://csgo.guru/match/" +
	 * i)); }
	 * 
	 * 
	 * }
	 */

	/*public static void main(String[] args) throws Exception {

		// load CSV
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File("match information compiled - cleaned match information.csv"));
		Instances data = loader.getDataSet();

		// save ARFF
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File("match information compiled - cleaned match information.arff"));
		saver.writeBatch();
	}*/
}
