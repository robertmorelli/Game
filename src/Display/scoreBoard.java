package Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

	// Insert the final score as the parameter.
	public void endGame(int finalScore) throws IOException {
		List<String> scores = retrieveScores();
		addNewScore(finalScore, scores);
		writeScores(scores);
		showLeaderBoard(scores);
	}

	// Retrieves scores from score.txt file
	private List<String> retrieveScores() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/resources/score.txt"));
		String scoreLine = reader.readLine(); // read line that contains scores
		List<String> scores = new ArrayList<>();
		if (scoreLine != null) { // in case of first game
			String[] tempScore = scoreLine.split(", ");
			scores = new ArrayList<>(Arrays.asList(tempScore));
		}
		reader.close();
		return scores;
	}

	// Adds the new scores to the scores list
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
	private void writeScores(List<String> scores) throws IOException {
		FileWriter writer = new FileWriter("src/resources/score.txt");
		String outputScores = scores.toString();
		outputScores = outputScores.replace("[", "");
		outputScores = outputScores.replace("]", "");
		writer.write(outputScores);
		writer.close();
	}

	// Shows the leader board at the end of the game
	private void showLeaderBoard(List<String> scores) {
		System.out.println("TOP 5 LEADERBOARD");
		int i = 0;
		while (i < 5 && i < scores.size()) {
			System.out.println(scores.get(i));
			i++;
		}
	}

}
