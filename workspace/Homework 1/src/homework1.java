import java.util.Random;
import java.util.Scanner;

public class homework1 {

	public static void main(String[] args) {
		System.out.println("Welcome to the birthday problem Simulator\n");
		String userAnswer="";
		Scanner stdIn = new Scanner(System.in);
		do {
			int [] userInput = promptAndRead(stdIn);
			double probability = compute(userInput[0], userInput[1]);

			// Print results
			System.out.println("For a group of " + userInput[1] + " people, the probability");
			System.out.print("that two people have the same birthday is\n");
			System.out.println(probability);

			System.out.print("\nDo you want to run another set of simulations(y/n)? :");

			//eat or skip empty line
			stdIn.nextLine();
			userAnswer = stdIn.nextLine();


		} while (userAnswer.equals("y"));

		System.out.println("Goodbye!");
		stdIn.close();
	}

	// Prompt user to provide the number of simulations and number of people and return them as an array
	public static int[] promptAndRead(Scanner stdIn) {
		int arry[] = new int[2];
		
		System.out.print("Please enter the number of simulations to do: ");
		int numOfSimulations = stdIn.nextInt();
		System.out.print("Please Enter the size of the group of people: ");
		int groupSize = stdIn.nextInt();
		
		arry[0] = numOfSimulations;
		arry[1] = groupSize;
		
		return arry;
	}

	
	// This is the method that actually does the calculations.
	public static double compute(int numOfSimulation, int numOfPeople) {
		double countSame = 0.0;
		double probs;
		Random rnd = new Random(1);
		int people[] = new int[numOfPeople];

		for (int i = 0; i < numOfSimulation; i++)//simulations
			{
			for (int j = 0; j < numOfPeople; j++) // create birthdays for the people of the group
				{
					people[j] = rnd.nextInt(365) + 1;;
					// Generates a random number between 1 and 365 inclusive for one simulation
					//System.out.println(people[j]);
				}
			for (int k = 0; k < people.length - 1; k++)
				//checking if the people have a same birthdays
				{
				for (int l = k + 1; l < people.length; l++)
					{
						if (people[k] == people[l])
						{
							countSame++;
						}
					}
				}
			}
		//System.out.println("\n" + countSame);
		probs = (countSame/numOfSimulation);
		
		return probs;
	}
}

