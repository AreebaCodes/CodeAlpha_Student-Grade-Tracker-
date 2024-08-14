import java.util.ArrayList;
import java.util.Scanner;

class AcademicEvaluator{
    private ArrayList<Double> scores;

    public AcademicEvaluator() {
        scores = new ArrayList<>();
    }

    public void recordScore(double mark) {
        scores.add(mark);
    }

    public double computeMeanScore() {
        return scores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double findExtremeScore(boolean isMax) {
        double extreme = scores.get(0);
        for (double score : scores) {
            if (isMax && score > extreme) {
                extreme = score;
            } else if (!isMax && score < extreme) {
                extreme = score;
            }
        }
        return extreme;
    }

    public double getTopScore() {
        return findExtremeScore(true);
    }

    public double getLowestScore() {
        return findExtremeScore(false);
    }

    public static void main(String[] args) {
        AcademicEvaluator evaluator = new AcademicEvaluator();
        Scanner inputReader = new Scanner(System.in);

        System.out.println("\t+-------------------------------------------------------------------+");
        System.out.println("\t|\t\t\tACADEMIC PERFORMANCE TRACKER\t\t\t    |");
        System.out.println("\t+-------------------------------------------------------------------+");

        System.out.print("\n\tEnter the number of students in the class : ");
        int studentCount = inputReader.nextInt();

        if (studentCount <= 0) {
            System.out.println("Invalid number of students.");
            return;
        }

        System.out.println("\n\tEnter score for,");

        for (int i = 0; i < studentCount; i++) {
            System.out.print("\tStudent " + (i + 1) + " : ");
            double score = inputReader.nextDouble();
            if (score < 0 || score > 100) {
                System.out.println("Invalid score. Please enter a value between 0 and 100.");
                i--; // retry
                continue;
            }
            evaluator.recordScore(score);
        }

        System.out.println("\n\tMean Score : " + evaluator.computeMeanScore());
        System.out.println("\tTop Score : " + evaluator.getTopScore());
        System.out.println("\tLowest Score  : " + evaluator.getLowestScore());
    }
}