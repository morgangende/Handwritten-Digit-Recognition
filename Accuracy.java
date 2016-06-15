import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Accuracy {

	public static void main(String[] args) throws IOException {
		// Load the arguments
		String predLabelFile = args[0];
		String trueLabelFile = args[1];
		
		Scanner predReader = new Scanner(Paths.get(predLabelFile));
		Scanner trueReader = new Scanner(Paths.get(trueLabelFile));
		
		// If the predicted label matches the true label, add to the sum of correct predictions
		int sum = 0, n = 0;
		while(predReader.hasNext() && trueReader.hasNext()) {
			if(predReader.nextInt() == trueReader.nextInt()) {
				sum++;
			}
			n++;
		}
		
		// Take the average to determine the accuracy
		double accuracy = (double)sum / n;
		
		// Complete - print the accuracy
		System.out.println("Accuracy: " + accuracy);
	}

}
