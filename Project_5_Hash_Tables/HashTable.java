/**
 * HashTable class
 * <p>
 * The HashTable class provides methods to implement a separate-chained hash table.
 * HashTables are implemented by an array of LinkedList.
 * Methods provide by the class are:
 * hashFunc, insert, delete, find, display, and printFreeAndCollisions.
 * <p>
 * @author Luke Sanchez
 * @version Dec. 4, 2020>
 */


public class HashTable {

    public LinkedList[] hashArray;
    int arraySize = 307;


    public HashTable() { //HashTable Constructor
        hashArray = new LinkedList[arraySize];
        for (int i = 0; i < arraySize; i++) {// creates a empty linked list
            hashArray[i] = new LinkedList(); // for every hashTable index
        }
    }


    /**
     * The hashFunc method returns a hash value from the provided inputs.
     * The function takes every Unicode value of character in the country
     * and capital strings and sums them together.
     * The sum is then modulated by the size of the array to get the final hash value.
     * @param country String of nation
     * @param capital String of nation
     * @return int hash value of the given strings
     */
    public int hashFunc(String country, String capital){
        String combinedString = country + capital;
        int nameSum = 0;

        for(int i = 0; i < combinedString.length(); i++)
            nameSum += combinedString.charAt(i);

        return nameSum % arraySize;
    }//end hashFunc


    /**
     * The insert method inserts a node into the correct hashTable index based on it's hash value.
     * First the hash value is generated from the country name and capital.
     * Then a new node object is created using the country's name, capital, and CFR.
     * Finally that node is inserted into the linked list at the generated hashed index.
     * @param country String from CSV
     * @param capital String from CSV
     * @param CFR Double from CSV
     */
    public void insert(String country, String capital, double CFR){
        int hashVal = hashFunc(country, capital);
        Node newNode = new Node(country, capital, CFR);

        hashArray[hashVal].insertNode(newNode);
    }//end insert


    /**
     * The delete method remove a specified country node from the hash table.
     * The hash value is first generated to locate the correct index.
     * With the proper hash table index, the node to delete is searched for in the linked list.
     * If a matching country node is found then it is deleted.
     * @param country String to delete
     * @param capital String to delete
     */
    public void delete(String country, String capital){
        int hashVal = hashFunc(country, capital);
        hashArray[hashVal].deleteNode(country);
    }//end delete


    /**
     * The find method tries to find a country node given the name and capital.
     * If a matching country node is found then a message is printed and the CFR is returned.
     * If a node is not found then a message is printed and -1.0 value is returned.
     * @param country String you're trying to find
     * @param capital String you're trying to find
     * @return double CFR or -1.0 if not found
     */
    public double find(String country, String capital) {
        int hashVal = hashFunc(country, capital);

        Node foundNode = hashArray[hashVal].findNode(country);

        if (foundNode != null){
            System.out.printf("%s is found with a CFR of %f\n", country, foundNode.CFR);
            return foundNode.CFR;
        }
        else{
            System.out.printf("%s is not found\n", country);
            return -1.0;
        }
    }//end find


    /**
     * The display method prints out the contents of the hash Table.
     * The method displays the index of hash table.
     * If a node resides at the index, the entire linked list is printed.
     * else if the linked list at the hash table index is empty, then 'empty' is printed.
     */
    public void display() {
        System.out.println("\nHash Table Content:\n");
        System.out.printf("\t  %-40s %-20s %-30s\n", "Name", "Capital", "CFR");
        System.out.println("-----------------------------------------------------------------------------------");
        for(int j = 0; j < arraySize; j++) {
            if(hashArray[j].first != null) {
                System.out.printf("%3d.  ", j);
                hashArray[j].displayList();
            }
            else
                System.out.printf("%3d.  Empty \n", j);
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }//end display


    /**
     * The printFreeAndCollisions method display the number of free spaces and collisions in the hash table.
     */
    public void printFreeAndCollisions(){
        int free = 0;
        int collisions = 0;

        for(int i = 0; i < arraySize; i++){
            if(hashArray[i].first == null)
                free += 1;
            else if(hashArray[i].first.nextNode != null)
                collisions += 1;
        }
        System.out.printf("Hash table has %d empty spaces and %d collisions.\n", free, collisions);
    }//end printFreeAndCollisions


     private class Node {
        String name;
        String capital;
        double CFR;
        Node nextNode;
        public Node(String name, String capital, double CFR) {
            this.name = name;
            this.capital = capital;
            this.CFR = CFR;
        }
        public void printNode() {
            System.out.printf("%-40s %-20s %-30.6f\n", name, capital, CFR);
        }
    }


    private class LinkedList {
        private Node first;
        private Node last;

        private LinkedList() { // constructor
            first = null;
            last = null;
        }

        private void insertNode(Node nation) {
            Node previous = null;
            Node current = first;

            while (current != null) {
                previous = current;
                current = current.nextNode;
            }
            if(previous == null) {
                first = nation;
            }
            else
                previous.nextNode = nation;
            nation.nextNode = current;
        }

        private Node findNode(String name){
            Node current = first;

            while (current != null && name.equalsIgnoreCase(current.name)){
                if(name.equalsIgnoreCase(current.name))
                    return current;
                current = current.nextNode;
            }
            return null;
        }

        private void deleteNode(String name){
            Node previous = null;
            Node current = first;

            while(current != null && !name.equalsIgnoreCase(current.name)){
                previous = current;
                current = current.nextNode;
            }

            if(previous == null){
                first = first.nextNode;
            }
            else
                previous.nextNode = current.nextNode;
            System.out.printf("%s has been deleted from the hash table\n", name);
        }

        private void displayList() {
            Node current = first;
            while (current != null){
                if(current != first)
                    System.out.print("\t  ");
                current.printNode();
                current = current.nextNode;
            }
        }
    }// end LinkedList

}//end HashTable
