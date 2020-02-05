import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfTestCases = scanner.nextInt();

    while (numberOfTestCases-- > 0) {
      int numberOfColleagues = scanner.nextInt();
      int[] chocolatesPerPerson = new int[numberOfColleagues];
      for (int i = 0; i < numberOfColleagues; i++) {
        chocolatesPerPerson[i] = scanner.nextInt();
      }
      int result = calculateTotal_minNumberOfOperations(chocolatesPerPerson);
      System.out.println(result);
    }
    scanner.close();
  }

  private static int calculateTotal_minNumberOfOperations(int[] chocolatesPerPerson) {
    int total_minOperations = Integer.MAX_VALUE;
    int min_initialChocolates = findMinimum_inititalChocolates(chocolatesPerPerson);
    int integer_toDecrease_minBase = 0;

    while (integer_toDecrease_minBase <= 4) {
      int operations_minBase = 0;
      int minBase = min_initialChocolates - integer_toDecrease_minBase;

      for (int i = 0; i < chocolatesPerPerson.length; i++) {
        int minSteps_perPerson =
            calculate_minSteps_perPerson_toReachMinBase(chocolatesPerPerson[i], minBase);
        operations_minBase = operations_minBase + minSteps_perPerson;
      }
      if (total_minOperations > operations_minBase) {
        total_minOperations = operations_minBase;
      }
      integer_toDecrease_minBase++;
    }
    return total_minOperations;
  }

  private static int findMinimum_inititalChocolates(int[] chocolatesPerPerson) {
    int minimumInititalChocolates = Integer.MAX_VALUE;
    for (int i = 0; i < chocolatesPerPerson.length; i++) {
      if (minimumInititalChocolates > chocolatesPerPerson[i]) {
        minimumInititalChocolates = chocolatesPerPerson[i];
      }
    }
    return minimumInititalChocolates;
  }

  private static int calculate_minSteps_perPerson_toReachMinBase(int chocolates, int minBase) {
    chocolates = chocolates - minBase;
    int total_steps_five = chocolates / 5;
    int total_steps_two = (chocolates % 5) / 2;
    int total_steps_one = ((chocolates % 5) % 2) / 1;
    return total_steps_five + total_steps_two + total_steps_one;
  }
}
