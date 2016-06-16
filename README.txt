Handwritten Digit Recognizer trains a model to distinguish between images of handwritten digits '0' and '1'. This project utilizes stochastic gradient descent to train the model and uses logistic regression to predict the digits. Images of handwritten digits are provided as vector representations in the data files. Conversion from images to vector representations is not included in this project. 

Compile project using the following commands:
javac TrainLogReg.java
javac TestLogReg.java
javac Accuracy.java

Train the model by running TrainLogReg on an input set of handwritten digits (features) and their known values (labels).

java TrainLogReg trainingFeatureFile trainingLabelFile modelFile 785 12665

Where modelFile is the location for the model file that will be generated. 785 is the dimension of each feature in the provided sample training data. 12665 is the number of features in the provided sample training data. 

Use the generated model to predict any future sets of handritten digit vector files using TestLogReg.

java TestLogReg modelFile testFeatureFile predLabelFile 785

Where predLabelFile is the location for the file of predicted labels that will be generated. 785 is the dimension of each feature in the provided sample test data.

Test the accuracy of generated predicted labels against known labels using Accuracy.

java Accuracy predLabelFile testLabelFile

Note that testLabelFile was not provided for this project so that the project could be graded by the professor. This submission recieved full credit for the assignment. If desired, use TestLogReg to predict labels for the trainingFeature file, then use Accuracy to test the predicted labels against the known labels. This results in 99.62% accuracy. 

java TestLogReg modelFile trainingFeatureFile predLabelFile 785
java Accuracy predLabelFile trainingLabelFile
