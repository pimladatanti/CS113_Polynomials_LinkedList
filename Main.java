import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String userPolynomial;
        char menuChoice;
        int choice;

         do {

             System.out.print("---Menu--- \n'1' to Add/Edit a Polynomial \n'2' Clear a Polynomial \n'3' Add Polynomials 1 & 2 \n'Q' to quit");
             System.out.print("\nEnter a menu option: ");
             menuChoice = keyboard.nextLine().charAt(0);

             Polynomial polynomial1 = new Polynomial();
             Polynomial polynomial2 = new Polynomial();

             switch (menuChoice) {
                 case '1' -> {
                     System.out.println("Which polynomial would you like to add/edit? (1 or 2)");
                     choice = keyboard.nextInt();
                     System.out.println(choice);
                     keyboard.nextLine();
                     System.out.println("Enter a polynomial or term(s)");
                     userPolynomial = keyboard.nextLine();
                     System.out.println(userPolynomial);
                     switch (choice) {
                         case 1 -> {
                         polynomial1.order();
                         Polynomial user = new Polynomial(userPolynomial);
                         user.order();
                         polynomial1.add(user);
                         System.out.println(polynomial1);
                         }
                         case 2 -> {
                             polynomial2.order();
                             Polynomial user = new Polynomial(userPolynomial);
                             user.order();
                             polynomial2.add(user);
                             System.out.println(polynomial2);
                         }
                     }
                 }
                 case '2' -> {
                     System.out.println("Which polynomial would you like to clear? (1 or 2)");
                     choice = keyboard.nextInt();
                     keyboard.nextLine();
                     switch (choice) {
                         case 1 -> {
                         polynomial1.clear();
                         System.out.println(polynomial1);
                         }
                         case 2 -> {
                         polynomial2.clear();
                         System.out.println(polynomial2);
                         }
                     }
                 }
                 case '3' -> {
                 System.out.println("test");
                 Polynomial copy = new Polynomial(polynomial1);
                 System.out.println("copy:" + copy);
                 copy.add(polynomial2);
                 System.out.println(copy);
                 }

                 case '4' -> {
                 Polynomial polynomial3 = new Polynomial("6x^10+5x^12+9x+3x");
                 System.out.println(polynomial3);
                 polynomial3.order();
                 System.out.println(polynomial3);
                 }
             }

         } while(menuChoice != 'Q' && menuChoice != 'q');



         }
}

