import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TestLogReg {
	
	private static double[] w;
	private static ArrayList<double[]> features = new ArrayList<double[]>();	
	private static int d;
	private static int n = 2115; // Hard-coded number of test features
	
	// Read the weight vector into instance variable w
	private static void readModel(Scanner in) {
		w = new double[d];
		
		for(int i = 0; i < d; i++) {
			w[i] = in.nextDouble();
		}
	}
	
	// Read the features into an ArrayList. Each feature is stored in an array.
	private static void readFeatures(Scanner in) {
		for(int i = 0; i < n; i++) {
			double[] feature = new double[d];
			
			for(int j = 0; j < d; j++) {
				feature[j] = in.nextInt();
			}
			
			features.add(feature);
		}
	}
	
	// Returns the dot product of two vectors of the same size
	private static double dotProduct(double[] u, double[] v) {
		double result = 0;
		
		for(int i = 0; i < u.length; i++) {
			result += u[i] * v[i];
		}
		
		return result;
	}
	
	// Apply the logistic regression model to predict labels and write them to the file, each on a new line
	private static void predictLabels(String fileName) {
		try {
			File file = new File(fileName);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < n; i++) {
				int label = dotProduct(w, features.get(i)) > 0 ? 1 : 0;
				bw.write(label + "\n");
			}
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Load the arguments
		String modelFile = args[0];
		String testFeatureFile = args[1];
		String predLabelFileName = args[2];
		d = Integer.parseInt(args[3]);

		// Read the weight vector from the model file
		Scanner modelReader = new Scanner(Paths.get(modelFile));
		readModel(modelReader);
		modelReader.close();
		
		// Read the features into a data structure
		Scanner featureReader = new Scanner(Paths.get(testFeatureFile));
		readFeatures(featureReader);
		featureReader.close();	
		
		// Apply logistic regression to predict the label of each test feature
		predictLabels(predLabelFileName);
		
		// Complete
		System.out.println("TestLogReg complete");
		System.exit(0);
	}
}
