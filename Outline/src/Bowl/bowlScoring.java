package Bowl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class bowlScoring {
	
	final static int maxFrames = 10;
	
	public static void main(String[] args) {
		Score();
	}
	
	public static boolean spare(int first, int second) {		
		return (first + second == 10);
	}
	
	public static String outputScore(String finalScore, int totalScore) {
//		finalScore + " " + totalScore + "    ";
/*	length can be single
 * 					double
 * 					triple
 * 					in score length
 */
		if (String.valueOf(totalScore).length() == 3) {
			finalScore += totalScore + "     ";
		}
		else if (String.valueOf(totalScore).length() == 2) {
			finalScore += totalScore + "      ";
		}
		else {
			finalScore += totalScore + "       ";
		}

		return finalScore;
	}
	
	public static void Score( ) {
		
		int firstThrow = 0;
		int secondThrow = 0;
		int prevFrame = 0;
		int prevFrameTwo = 0;
		int totalScore = 0;
		
		// Keep track of throws
		String scores = "       *** ";

		// Keeping track of score
		String finalScore = "       *** ";
		boolean spare = false;
		boolean strike = false;
		boolean strikeTwo = false;
		
		Scanner reader = new Scanner(System.in);

		BufferedReader br = null;
		
			try {
				
				br = new BufferedReader(new InputStreamReader(System.in));
				
				for (int i = 1; i <= maxFrames; i++) {
					
					System.out.print("enter your 1st throw score: ");
				
					String first = br.readLine();
				

					if (first.equals("x")) {
						scores+= " X      ";

						// If bowler throws a turkey ( 3 X's) add 30
						if (strikeTwo) {
							prevFrameTwo = 30;
							totalScore += prevFrameTwo;
							finalScore = outputScore(finalScore, totalScore);
						}
						// If bowler throws a double (2 X's) add 20
						else if (strike) {
							prevFrameTwo = 20;
							strikeTwo = true;
						}
						// If bowler throws a strike (which does not follow another strike)
						else {
							firstThrow = 10;
							secondThrow = 0;
							strike = true;
						}
					}
					else {
						firstThrow = Integer.parseInt(first);
						scores += firstThrow;
					}
					
					
					if (spare) {
						prevFrame = 10 + firstThrow;
						spare = false;
						totalScore += prevFrame;
						finalScore = outputScore(finalScore, totalScore);
					}
					
				
					if (firstThrow < 10) {
						System.out.print("enter your 2nd throw score: ");
						
						secondThrow = reader.nextInt();
						
						// following a double (2 X's)
						if (strikeTwo) {
							prevFrameTwo = 20 + firstThrow;
							totalScore += prevFrameTwo;
							finalScore = outputScore(finalScore, totalScore);
							strikeTwo = false;
							strike = false;
							
							// a spare is made following a double (2 X's)
							if (spare(firstThrow, secondThrow)) {
								spare = true;
								scores = scores + "/      ";
								totalScore += 20;
								finalScore = outputScore(finalScore, totalScore);
							}
							
							// Open frame following a double (2 X's)
							else {
								totalScore += 10 + firstThrow + secondThrow;
								scores = scores + " " + secondThrow + "     ";
								finalScore = outputScore(finalScore, totalScore);
								
								totalScore += firstThrow + secondThrow;
								finalScore = outputScore(finalScore, totalScore);
							}
						}
						//made a spare following a strike
						else if (spare(firstThrow, secondThrow) && strike) {
							strike = false;
							spare = true;
							prevFrame = 20;
							scores = scores + "/      ";
							totalScore += prevFrame;
							finalScore = outputScore(finalScore, totalScore);
						}
						else if (spare(firstThrow, secondThrow)) {
							spare = true;
							scores = scores + "/      ";
						}
						else {
							totalScore += firstThrow + secondThrow;
							scores = scores + " " + secondThrow + "     ";
							finalScore = outputScore(finalScore, totalScore);
						}					

						
						/* 	think of all of the different possible scenarios
							following a spare
							following a strike
							following a double strike
							
							what happens if a spare is made?
							what happens if a spare is not made?
						*/
				
					}
					
					
					// Tenth frame
					if (i == 10 && (strike || spare(firstThrow, secondThrow))) {
						System.out.println("Inside of Frame 10");
						
						if (firstThrow == 10)
							System.out.print("enter your 2nd throw score: ");
						else
							System.out.println("enter your 3rd throw score: ");
						
						String secondTenth = br.readLine();
						int firstTenth = 0;

						if (secondTenth.equals("x")) {
							int pos = scores.lastIndexOf("X");
							scores = scores.substring(0, pos + 1);
							scores+= "X";

							// If bowler throws a turkey ( 3 X's) add 30
							if (strikeTwo) {
								prevFrameTwo = 30;
								totalScore += prevFrameTwo;
								finalScore = " " + outputScore(finalScore,totalScore);
							}

						}
						else {
							firstTenth = Integer.parseInt(first);
							scores += firstThrow;
						}

						if (secondTenth.equals("x")) {
							System.out.print("enter your 3rd throw score: ");

							String thirdTenth = br.readLine();
							
							if (thirdTenth.equals("x")) {
								scores+= "X";
								
								if (strikeTwo) {
									prevFrameTwo = 30;
									totalScore += prevFrameTwo;
									finalScore = outputScore(finalScore, totalScore);
								}
							}

						}

					}

					
					System.out.println("\n" + "For frame " + i + " you threw: " + firstThrow +  " " + secondThrow);
					System.out.println("*******************************************************************************************");
					System.out.println("Frame  ***  1  ***  2  ***  3  ***  4  ***  5  ***  6  ***  7  ***  8  ***  9  ***  10  ***");
					System.out.println(scores);
					System.out.println(finalScore);
					System.out.println("Your total score is: " + totalScore + "\n" + "\n");
					System.out.println("\n" + "\n" + "Player *** " + totalScore);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
	}
}

