package Display;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to print out the score board for the top 5 scores.
 * 
 * @author Kody Berry
 * @author Robert Morelli
 *
 */
public class scoreBoard {
	// scoreBoard.getlass
	// public String
	// nameURL=getClass().getClassLoader().getResource("names.txt").toString();
	// public String
	// scoreURL=getClass().getClassLoader().getResource("score.txt").toString();
	// Insert the final score as the parameter.
	public void endGame(int finalScore) throws IOException {

		// List<String> scores = retrieveScores();
		// addNewScore(finalScore, scores);
		// writeScores(scores);
		try {
			writeAndNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// showLeaderBoard(scores);

	}

	// Retrieves scores from score.txt file
	public List<String> retrieveScores() throws IOException, URISyntaxException {
		// System.out.println(getClass().getClassLoader(). );

		// File file = new File(getClass().getResource("/score.txt").toURI());

		// BufferedReader reader = new BufferedReader(new FileReader(file));
		//BufferedReader reader = new BufferedReader(
		//		new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("score.txt")));
		BufferedReader reader = new BufferedReader(new FileReader("score.txt"));

		String scoreLine = reader.readLine(); // read line that contains scores
		List<String> scores = new ArrayList<>();
		if (scoreLine != null) { // in case of first game
			String[] tempScore = scoreLine.split(", ");
			scores = new ArrayList<>(Arrays.asList(tempScore));
		}
		ScoreBoardDisplay.leaderScores = new int[scores.size()];
		int index = 0;
		for (String score : scores) {
			ScoreBoardDisplay.leaderScores[index++] = Integer.parseInt(score);
		}
		reader.close();
		return null;
	}

	public List<String> retrieveNames() throws IOException, URISyntaxException {
		//FileOutputStream scorefile = new OStreamReader(""+ClassLoader.getSystemClassLoader().getResource("names.txt"));//
		//BufferedReader reader = new BufferedReader(
		//		new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("names.txt")));
		BufferedReader reader = new BufferedReader(new FileReader("name.txt"));
		String namesLine = reader.readLine(); // read line that contains scores
//System.out.println(reader.getClass().getClassLoader().getResource("names.txt"));
		List<String> names = new ArrayList<>();
		if (namesLine != null) { // in case of first game
			String[] tempScore = namesLine.split(", ");
			names = new ArrayList<>(Arrays.asList(tempScore));
		}
		ScoreBoardDisplay.leaderNames = new String[names.size()];
		int index = 0;
		for (String name : names) {
			ScoreBoardDisplay.leaderNames[index++] = name;
		}
		reader.close();
		return names;
	}

	// Adds the new scores to the scores list
	@SuppressWarnings("unused")
	private void addNewScore(int finalScore, List<String> scores) {
		boolean foundSpotForNewScore = false;
		int i = 0;
		while (!foundSpotForNewScore && i < scores.size()) {
			if (finalScore <= Integer.parseInt(scores.get(i))) {
				foundSpotForNewScore = true;
			}
			i++;
		}
		scores.add(i, String.valueOf(finalScore));
	}

	// Writes scores to File
	@SuppressWarnings("unused")
	private void writeScores(List<String> scores) throws IOException {
		// FileWriter writer = new FileWriter(scoreURL);
		String outputScores = scores.toString();
		outputScores = outputScores.replace("[", "");
		outputScores = outputScores.replace("]", "");
		// writer.write(outputScores);
		// writer.close();
	}

	public void writeAndNames() throws IOException, URISyntaxException {
		
			FileWriter scorefile = new FileWriter("score.txt");
			//FileWriter scorefile = new FileWriter(
			//		new File(ClassLoader.getSystemClassLoader().getResource("score.txt").getPath()));
			FileWriter namefile = new FileWriter("name.txt");
			// File file1 = new File(getClass().getResource("/score.txt").toURI());
			// System.out.println(file.getPath());
			// File file2 = new File(getClass().getResource("/names.txt").toURI());
			// System.out.println(file.getPath());
			// FileWriter namefile = new FileWriter(file1);
			// FileWriter scorefile = new FileWriter(file2);

			namefile.write(String.join(", ", Arrays.asList(ScoreBoardDisplay.leaderNames)));

			List<String> scores = new ArrayList<>(ScoreBoardDisplay.leaderScores.length);
			for (int i : ScoreBoardDisplay.leaderScores) {
				scores.add(i + "");
			}
			scorefile.write(String.join(", ", scores));
			scorefile.write(", " + Game.mainChar.score);
			String dific=Game.dif==difficulties.easy?"easy":"";
			dific=Game.dif==difficulties.peaceful?"peaceful":dific;
			dific=Game.dif==difficulties.impossible?"impossible":dific;
			namefile.write(", " + ScoreBoardDisplay.myName+"("+dific+")");
			namefile.close();
			scorefile.close();
		
	}

	// Shows the leader board at the end of the game
	@SuppressWarnings("unused")
	private void showLeaderBoard(List<String> scores) {
		System.out.println("TOP 5 LEADERBOARD");
		int i = 0;
		while (i < 5 && i < scores.size()) {
			System.out.println(scores.get(i));
			i++;
		}
	}

}