package fa.training.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Validate {

    public static Scanner sc = new Scanner(System.in);

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                System.out.println();
                System.out.print("Enter Integer: ");
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Number must between [" + min + "; " + max + "].");
            }
        }
    }

    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Must be input integer.");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String inputString(String message) throws IOException {
        String str;
        System.out.print(message);
        str = in.readLine().trim().replaceAll("\\s+", " ");
        return str;
    }

    public static double inputPositiveDouble(String message) {
        double n;
        while (true) {
            try {
                System.out.print(message);
                n = Double.parseDouble(in.readLine());
                if (n >= 0){
                    return n;
                }
            } catch (Exception e) {
                System.out.println("Please enter as a positive number!");
            }

        }
    }

    public static String inputStringRegex(String message, String regex) throws IOException {
        String str;
        while (true) {
            System.out.print(message);
            str = in.readLine().trim();
            if (str.matches(regex)) {
                break;
            } else {
                System.out.println("Re-input!");
            }
        }
        return str;
    }

    public static int inputPositiveInt(String message) {
        while (true) {
            int i = checkInputInt();
            if (i > 0) {
                return i;
            } else {
                System.out.println("Please enter integer number > 0");
            }
        }
    }


}
