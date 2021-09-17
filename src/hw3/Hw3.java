package hw3;

import java.util.Scanner;
import java.io.File;

/**
 *
 * @author Kevin Yong
 * @since 6/3/2020
 * @description Assignment using linked list
 *
 * Widget store receives widgets at diff. prices and amounts. insert to linked list
 * Widget store sells widgets. delete from linked list
 */
public class Hw3 {

    public static void main(String[] args) throws Exception {

        readInput();

    } //end main

    //reads input, see if it is R, S, or P, and actions depending on which letter
    //will also make actions to linked list such as insert and delete
    public static void readInput() throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        LinkedList list = new LinkedList();

        int times = 0; //times promo was promoted
        double promoNum = 0;

        while (sc.hasNext()) {
            String letter = sc.next();

            if (letter.contains("R")) {
                int quantity = sc.nextInt();

                String costeaString = sc.next();
                costeaString = costeaString.replace("$", ""); //originally used split,
                //as shown in if letter is P. But split did not work as expected since
                //split[0] resulted in $1.00. But I learned that I needed to just escape $.
                double costea = Double.parseDouble(costeaString);

                System.out.printf ("%d Widgets received. Priced at $%.2f each\n", quantity,costea);
                list.insert(quantity, costea);
            }// end R

            if (letter.contains("S")) {
                System.out.println();
                System.out.println();
                System.out.println();
                int sold = sc.nextInt();
                if (times != 0) {

                    times--;
                    System.out.println("Discount Applied:"+promoNum*100+"%");
                    System.out.println( sold + " Widgets sold ");
                    list.delete(sold, promoNum);

                } else {

                    System.out.println(sold + " Widgets sold ");
                    list.delete(sold, 0);
                }
                System.out.println();
                System.out.println();
            } //end S

            if (letter.contains("P")) {
                times = 2; //promo applies to the next "2" sales
                String promo = sc.next();
                System.out.println();
                System.out.println("Next two customer will receive discount of " + promo);

                String[] split = promo.split("%"); //used split, but using substring from 0 to string-1 works too
                promoNum = Double.parseDouble(split[0]);
                promoNum /= 100;

            }// end P

            sc.nextLine();

        }// end while

        list.show();
    }//end readInput

}//end class
