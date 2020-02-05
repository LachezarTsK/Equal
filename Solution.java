import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfTestCases = scanner.nextInt();

    while (numberOfTestCases-- > 0) {
      int numberOfPeople = scanner.nextInt();
      int[] chocolatesPerPerson = new int[numberOfPeople];
      for (int i = 0; i < numberOfPeople; i++) {
        chocolatesPerPerson[i] = scanner.nextInt();
      }
      int result = calculateTotal_minNumberOfOperations(chocolatesPerPerson);
      System.out.println(result);
    }
    scanner.close();
  }

/**
  * The method calculates the total number of operations (rounds) necessary to 
  * equalize the number of chocolates per person. 
  *
  * In each operation, every person except one, must receive chocolates. The people who 
  * receive chocolates in the current round, must receive the same number of chocolates.
  * In each round, the number of distributed chocolates per person can be 1, 2 or 5.
  * 
  * Distributing a fixed amount of chocolates (1, 2, or 5) to everyone, except one person, 
  * per round, until the chocolates in possession are equalized, is the equivalent of finding 
  * how many total steps of 1, 2 and 5, each person needs to subtract from its initial number 
  * of chocolates, in order to reach the minimum chocolates in possession of a person i.e.
  * to reach minBase. 
  *
  * Since the possible steps are 1, 2 or 5, the minBase could be 
  * "minimum initial chocolates in possession of a person" minus 0, 1, 2, 3 or 4.
  *
  * By summing up the minimum steps of each person for a given minBase, and then
  * selecting the minumum of these sums, we can find the minimum number of operations
  * to equalize the chocolates, as per the conditions described in the challenge.
  *
  * @return An integer, representing the total number of operations (rounds) necessary to 
  * equalize the number of chocolates per person.
  */
  private static int calculateTotal_minNumberOfOperations(int[] chocolatesPerPerson) {
    int total_minOperations = Integer.MAX_VALUE;
    int min_initialChocolates = findMinimum_inititalChocolates(chocolatesPerPerson);
    int integer_toDecrease_minBase = 0;

    while (integer_toDecrease_minBase <= 4) {
      int operations_minBase = 0;
      int minBase = min_initialChocolates - integer_toDecrease_minBase;

      for (int i = 0; i < chocolatesPerPerson.length; i++) {
        int minNumberOfSteps_perPerson =
            calculate_minNumberOfSteps_perPerson_toReachMinBase(chocolatesPerPerson[i], minBase);
        operations_minBase += minNumberOfSteps_perPerson;
      }
      if (total_minOperations > operations_minBase) {
        total_minOperations = operations_minBase;
      }
      integer_toDecrease_minBase++;
    }
    return total_minOperations;
  }

/**
  * The method finds the minimum initial chocolates in possession of a person.
  * This initial minimum serves as the start value for minBase.
  * 
  * @return An integer, representing the minimum initial chocolates received by a person.
  */
  private static int findMinimum_inititalChocolates(int[] chocolatesPerPerson) {
    int minimumInititalChocolates = Integer.MAX_VALUE;
    for (int i = 0; i < chocolatesPerPerson.length; i++) {
      if (minimumInititalChocolates > chocolatesPerPerson[i]) {
        minimumInititalChocolates = chocolatesPerPerson[i];
      }
    }
    return minimumInititalChocolates;
  }

/**
  * The method calculates the minimum number of steps per person to reach current minimum base.
  * Each step can be only of 1, 2 or 5 chocolate(s).
  * 
  * @return An integer, representing the above-mentioned minimum value.
  */
  private static int calculate_minNumberOfSteps_perPerson_toReachMinBase(int chocolates, int minBase) {
    chocolates -= minBase;
    int total_steps_five = chocolates / 5;
    int total_steps_two = (chocolates % 5) / 2;
    int total_steps_one = ((chocolates % 5) % 2) / 1;
    return total_steps_five + total_steps_two + total_steps_one;
  }
}
