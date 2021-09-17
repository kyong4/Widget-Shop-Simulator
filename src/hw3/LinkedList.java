package hw3;

public class LinkedList {

    Node head;

    //inserts data onto nodes
    public void insert(int d, double costper) { //inserts node to end of list, or assigns int d to head if it is the beginning of the list

        Node node = new Node();// creates a new node, new node holds the value d in node.data
        node.data = d;
        node.costPer = costper;

        if (head == null) {
            head = node;
        } else {
            Node n = head;//try erase n, and directly putting "head" into whileloop, so while head.next!=null
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }//end insert

    public void show() { // shows all values
        Node node = head; //have to start from head, head references all the connecting nodes.

        while (node.next != null) {
            System.out.printf("Stock left: %d \t\t Original Purchase Price: $%.2f\n", node.data, node.costPer);
            node = node.next;
        }
        System.out.printf("Stock left: %d \t\t Original Purchase Price: $%.2f\n", node.data, node.costPer);
//For the last node, the node.next is null, so need to print it at the end instead.

    }// end show

    //takes amount sold and promotion given
    public void delete(int s, double promo) {

        double discounted = 1 - promo; //if 30% discount, then discounted will be .7    
        double sales = 0;
        double total = 0;
        double markup = .3; //30% markup
        int temp = 0;// used to hold the values of variables so that they can be printed at the end.

        Node n = head;

        while (n != null && s != 0) {//if null, check compare s and n.data

            if (s >= n.data) {//FIRST case
                s = s - n.data;
                sales = n.data * n.costPer;
                total += sales * discounted;

                temp = n.data;

                head = n.next;//

            } else if (s < n.data) { //SECOND case

                sales = s * n.costPer;
                total += sales * discounted;
                n.data = n.data - s;
                temp = s;
                s = 0;

            } //end if else

            System.out.printf("%d at $%.2f each ", temp, n.costPer);

            System.out.printf("\tSales: $%.2f\n", sales);
            if (n.next == null && s != 0) {
                System.out.println("Remainder of " + s + " Widgets not available");
            }

            n = n.next;

        }// end while loop
        if (discounted != 1) {
            System.out.printf("\t\t\tTotal(Promo Applied): $%.2f\n", total);
        } else {
            System.out.printf("\t\t\tTotal: $%.2f\n", total);
        }
        total = total + total * markup;
        System.out.printf("\t\t\tTotal after Markup: $%.2f\n", total);
        System.out.println();

    }//end delete

}//end LinkedList class
