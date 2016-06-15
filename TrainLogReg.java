import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainLogReg {

	private static ArrayList<double[]> features = new ArrayList<double[]>();
	private static ArrayList<Integer> labels = new ArrayList<Integer>();
	private static int d;
	private static int n;

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
	
	// Read the labels into an ArrayList
	private static void readLabels(Scanner in) {	
		for(int i = 0; i < n; i++) {
			labels.add(in.nextInt());
		}
	}
	
	// Writes each element of the vector to the file on a new line
	private static void writeVectorToFile(double[] v, String fileName) {
		try {
			File file = new File(fileName);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < v.length; i++) {
				bw.write(v[i] + "\n");
			}
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
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

	// Returns the vector multiplied by the scalar
	private static double[] scalarTimesVector(double s, double[] v) {
		for(int i = 0; i < v.length; i++) {
			v[i] *= s;
		}
		
		return v;
	}
	
	// Subtracts v from u. Vectors must be the same size.
	private static double[] subtractVectors(double[] u, double[] v) {
		for(int i = 0; i < u.length; i++) {
			u[i] -= v[i];
		}
		
		return u;
	}
	
	// Calculate the gradient L with respect to w
	private static double[] calculateGradient(double[] x, double y, double[] w) {
		if((int)y == 0) {
			y = -1;
		}
		
		double exp = Math.exp(-y * dotProduct(w, x));
		double[] gradient = scalarTimesVector((1 / (1 + exp)), scalarTimesVector(exp, scalarTimesVector(-y, x)));
		return gradient;
	}
	
	// Perform stochastic gradient descent with 1 iteration
	private static double[] stochasticGradientDescent() {
		// Initial values
		double c = .000001;
		double t = 0;
		double[] w = new double[d];
		
		// Algorithm for gradient descent
		for(int i = 0; i < n; i++) {
			t++;
			double[] gradient = calculateGradient(features.get(i), labels.get(i), w);
			w = subtractVectors(w, scalarTimesVector(c/t, gradient));
		}
		
		return w;
	}
	
	public static void main(String[] args) throws IOException {
		// Load the arguments
		String trainingFeatureFile = args[0];
		String trainingLabelFile = args[1];
		String modelFileName = args[2];
		d = Integer.parseInt(args[3]);
		n = Integer.parseInt(args[4]);
		
		// Read the features into a data structure
		Scanner featureReader = new Scanner(Paths.get(trainingFeatureFile));
		readFeatures(featureReader);
		featureReader.close();		
		
		// Read the labels into a data structure
		Scanner labelReader = new Scanner(Paths.get(trainingLabelFile));
		readLabels(labelReader);
		labelReader.close();
		
		// Calculate the weight vector using stochastic gradient descent with 1 iteration
		double[] weightVector = stochasticGradientDescent();
		
		// Write the weight vector to the model file
		writeVectorToFile(weightVector, modelFileName);
		
		// Complete
		System.out.println("TrainLogReg complete");
		System.exit(0);
	}
}
