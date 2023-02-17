import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
    void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver {
    SingleLinkedList listExpA = new SingleLinkedList();
    SingleLinkedList listCoA = new SingleLinkedList();
    SingleLinkedList listExpB = new SingleLinkedList();
    SingleLinkedList listCoB = new SingleLinkedList();
    SingleLinkedList listExpC = new SingleLinkedList();
    SingleLinkedList listCoC = new SingleLinkedList();
    SingleLinkedList listExpR = new SingleLinkedList();
    SingleLinkedList listCoR = new SingleLinkedList();
    SingleLinkedList listExp1 = new SingleLinkedList();
    SingleLinkedList listCo1 = new SingleLinkedList();
    SingleLinkedList listExp2 = new SingleLinkedList();
    SingleLinkedList listCo2 = new SingleLinkedList();

    public static boolean flagA = false;
    public static boolean flagB = false;
    public static boolean flagC = false;

    public int[][] scan(String st) {
        int[][] terms = new int[100][2];
        String[] s = st.split(",");
        if (!(s.length == 1 && s[0].isEmpty())) {
            int exp = s.length - 1;
            for (int i = 0; i < s.length; i++) {
                terms[i][1] = Integer.parseInt(s[i]);
                terms[i][0] = exp--;
            }
            return terms;
        } 
        else
            return null;
    }

    public static boolean valid(char poly) {
        switch (poly) {
        case 'A':
            return flagA;
        case 'B':
            return flagB;
        case 'C':
            return flagC;
        default:
            return false;
        }
    }

    public void setPolynomial(char poly, int[][] terms) {
        switch (poly) {
        case 'A':
            flagA = true;
            for (int i = 0; i <= terms[0][0]; i++) {
                listExpA.add(terms[i][0]);
                listCoA.add(terms[i][1]);
            }
            break;
        case 'B':
            flagB = true;
            for (int i = 0; i <= terms[0][0]; i++) {
                listExpB.add(terms[i][0]);
                listCoB.add(terms[i][1]);
            }
            break;
        case 'C':
            flagC = true;
            for (int i = 0; i <= terms[0][0]; i++) {
                listExpC.add(terms[i][0]);
                listCoC.add(terms[i][1]);
            }
            break;
        case 'R':
            listExpR.clear();
            listCoR.clear();
            for (int i = 0; i <= terms[0][0]; i++) {
                listExpR.add(terms[i][0]);
                listCoR.add(terms[i][1]);
            }
        }
    }
    public String print(char poly) {
        String polyString = "";
        SingleLinkedList tempExp = new SingleLinkedList();
        SingleLinkedList tempCo = new SingleLinkedList();
        switch (poly) {
        case 'A':
            tempExp = listExpA;
            tempCo = listCoA;
            break;
        case 'B':
            tempExp = listExpB;
            tempCo = listCoB;
            break;
        case 'C':
            tempExp = listExpC;
            tempCo = listCoC;
            break;
        case 'R':
            tempExp = listExpR;
            tempCo = listCoR;
            break;
        }
        for (int i = 0; i < tempCo.length; i++) {
            if ((int) tempCo.get(i) == 0) {
                continue;
            }
            if ((int) tempExp.get(i) > 1) {
                if ((int) tempCo.get(i) == 1) {
                    polyString += "x^" + tempExp.get(i);
                }
                else {
                    polyString += tempCo.get(i) + "x^" + tempExp.get(i);
                }
                if ((int) tempCo.get(i + 1) > 0) {
                    polyString += "+";
                }
            }
            if ((int) tempExp.get(i) == 1) {
                if ((int) tempCo.get(i) == 1) {
                    polyString += "x";
                } 
                else {
                    polyString += tempCo.get(i) + "x";
                }
                if ((int) tempCo.get(i + 1) > 0) {
                    polyString += "+";
                }
            }
            if ((int) tempExp.get(i) == 0) {
                polyString += tempCo.get(i);
            }
        }
        if(Objects.equals(polyString, "")) {
            return "0";
        }
        return polyString;
    }

    public void clearPolynomial(char poly) {
        switch (poly) {
        case 'A':
            flagA = false;
            listCoA.clear();
            listExpA.clear();
            break;
        case 'B':
            flagB = false;
            listCoB.clear();
            listExpB.clear();
            break;
        case 'C':
            flagC = false;
            listCoC.clear();
            listExpC.clear();
            break;
        }
    }

    public float evaluatePolynomial(char poly, float value) {
        float ans = 0.0F;
        SingleLinkedList tempExp = new SingleLinkedList();
        SingleLinkedList tempCo = new SingleLinkedList();
        switch (poly) {
        case 'A':
            tempExp = listExpA;
            tempCo = listCoA;
            break;
        case 'B':
            tempExp = listExpB;
            tempCo = listCoB;
            break;
        case 'C':
            tempExp = listExpC;
            tempCo = listCoC;
            break;
        }
        for(int i = 0; i < tempCo.length; i++) {
            if(i == tempCo.length - 1) {
                ans += (int)tempCo.get(i);
            }
            else {
                ans += (int)tempCo.get(i) * Math.pow(value, (int)tempExp.get(i));
            }
        }
        return ans;
    }

    public int[][] add(char poly1, char poly2) {
        switch (poly1) {
            case 'A':
                listExp1 = listExpA;
                listCo1 = listCoA;
                break;
            case 'B':
                listExp1 = listExpB;
                listCo1 = listCoB;
                break;
            case 'C':
                listExp1 = listExpC;
                listCo1 = listCoC;
                break;
        }
        switch (poly2) {
            case 'A':
                listExp2 = listExpA;
                listCo2 = listCoA;
                break;
            case 'B':
                listExp2 = listExpB;
                listCo2 = listCoB;
                break;
            case 'C':
                listExp2 = listExpC;
                listCo2 = listCoC;
                break;
        }
        int index1 = 0, index2 = 0, index3 = 0;
        int[][] Rterms = new int[100][2];

        while ((int) listExp1.get(index1) > (int) listExp2.get(index2)) {
            listExpR.add(listExp1.get(index1));
            listCoR.add(listCo1.get(index1));
            Rterms[index3][0] = (int) listExp1.get(index1);
            Rterms[index3][1] = (int) listCo1.get(index1);
            index1++;
            index3++;
        }
        while ((int) listExp1.get(index1) < (int) listExp2.get(index2)) {
            listExpR.add(listExp2.get(index2));
            listCoR.add(listCo2.get(index2));
            Rterms[index3][0] = (int) listExp2.get(index2);
            Rterms[index3][1] = (int) listCo2.get(index2);
            index2++;
            index3++;
        }

        while (listCoR.size() < listCo1.size() || listCoR.size() < listCo2.size()) {
            listExpR.add(listExp1.get(index1));
            listCoR.add((int) listCo1.get(index1) + (int) listCo2.get(index2));
            Rterms[index3][0] = (int) listExp1.get(index1);
            Rterms[index3][1] = (int) listCo1.get(index1) + (int) listCo2.get(index2);
            index1++;
            index2++;
            index3++;
        }
        return Rterms;
    }

    public int[][] subtract(char poly1, char poly2) {
        switch (poly1) {
            case 'A':
                listExp1 = listExpA;
                listCo1 = listCoA;
                break;
            case 'B':
                listExp1 = listExpB;
                listCo1 = listCoB;
                break;
            case 'C':
                listExp1 = listExpC;
                listCo1 = listCoC;
                break;
        }
        switch (poly2) {
            case 'A':
                listExp2 = listExpA;
                listCo2 = listCoA;
                break;
            case 'B':
                listExp2 = listExpB;
                listCo2 = listCoB;
                break;
            case 'C':
                listExp2 = listExpC;
                listCo2 = listCoC;
                break;
        }
        int index1 = 0, index2 = 0, index3 = 0;
        int[][] Rterms = new int[100][2];

        while ((int) listExp1.get(index1) > (int) listExp2.get(index2)) {
            listExpR.add(listExp1.get(index1));
            listCoR.add(listCo1.get(index1));
            Rterms[index3][0] = (int) listExp1.get(index1);
            Rterms[index3][1] = (int) listCo1.get(index1);
            index1++;
            index3++;
        }
        while ((int) listExp1.get(index1) < (int) listExp2.get(index2)) {
            listExpR.add(listExp2.get(index2));
            listCoR.add(listCo2.get(index2));
            Rterms[index3][0] = (int) listExp2.get(index2);
            Rterms[index3][1] = (int) listCo2.get(index2);
            index2++;
            index3++;
        }

        while (listCoR.size() < listCo1.size() || listCoR.size() < listCo2.size()) {
            listExpR.add(listExp1.get(index1));
            listCoR.add((int) listCo1.get(index1) - (int) listCo2.get(index2));
            Rterms[index3][0] = (int) listExp1.get(index1);
            Rterms[index3][1] = (int) listCo1.get(index1) - (int) listCo2.get(index2);
            index1++;
            index2++;
            index3++;
        }
        return Rterms;
    }

    public int[][] multiply(char poly1, char poly2) {
        switch (poly1) {
            case 'A':
                listExp1 = listExpA;
                listCo1 = listCoA;
                break;
            case 'B':
                listExp1 = listExpB;
                listCo1 = listCoB;
                break;
            case 'C':
                listExp1 = listExpC;
                listCo1 = listCoC;
                break;
            default:
                System.out.println("Error");
                break;
        }
        switch (poly2) {
            case 'A':
                listExp2 = listExpA;
                listCo2 = listCoA;
                break;
            case 'B':
                listExp2 = listExpB;
                listCo2 = listCoB;
                break;
            case 'C':
                listExp2 = listExpC;
                listCo2 = listCoC;
                break;
            default:
                System.out.println("Error");
                break;
        }
        int bigEx = (int) listExp1.get(0) + (int) listExp2.get(0);
        int[][] Rterms = new int[100][2];
        for (int i = bigEx; i >= 0; i--) {
            listExpR.add(i);
        }
        int col = 0;
        int[] mult = new int[bigEx + 1];
        for (int i = 0; i < listExp1.size(); i++) {
            for (int j = 0; j < listExp2.size(); j++) {
                mult[j + col] = mult[j + col] + ((int) listCo1.get(i) * (int) listCo2.get(j));
            }
            col++;
        }
        for (int i = 0; i < bigEx + 1; i++) {
            Rterms[i][0] = (int) listExpR.get(i);
            Rterms[i][1] = mult[i];
        }
        return Rterms;
    }

    public static void main(String[] args) {
        int[][] termsA = new int[100][2];
        int[][] termsB = new int[100][2];
        int[][] termsC = new int[100][2];
        char poly1, poly2, poly3;
        char res = 'R';
        PolynomialSolver polynomial = new PolynomialSolver();
        Scanner sc = new Scanner(System.in);
        String st;
        String op = sc.nextLine();
        if (!Objects.equals(op, "set")) {
            System.out.println("Error");
            return;
        }
        poly1 = sc.nextLine().charAt(0);
        switch (poly1) {
        case 'A':
            st = sc.nextLine().replaceAll("\\[|\\]", "");
            termsA = polynomial.scan(st);
            if(termsA == null) {
                System.out.println("Error");
                return;
            }
            else {
                flagA = true;
                polynomial.setPolynomial(poly1, termsA);
            }
            break;
        case 'B':
            st = sc.nextLine().replaceAll("\\[|\\]", "");
            termsB = polynomial.scan(st);
            if(termsB == null) {
                System.out.println("Error");
                return;
            }
            else {
                flagB = true;
                polynomial.setPolynomial(poly1, termsB);
            }
            break;
        case 'C':
            st = sc.nextLine().replaceAll("\\[|\\]", "");
            termsC = polynomial.scan(st);
            if(termsC == null) {
                System.out.println("Error");
                return;
            }
            else {
                flagC = true;
                polynomial.setPolynomial(poly1, termsC);
            }
            break;

        default:
            System.out.println("Error");
            return;
        }
        while (true) {
            if(!sc.hasNextLine()) {
                break;
            }
            op = sc.nextLine();
            if (Objects.equals(op, "set")) {
                poly2 = sc.nextLine().charAt(0);
                switch (poly2) {
                case 'A':
                    if(flagA) {
                        polynomial.clearPolynomial(poly2);
                    }
                    st = sc.nextLine().replaceAll("\\[|\\]", "");
                    termsA = polynomial.scan(st);
                    if(termsA == null) {
                         System.out.println("Error");
                         return;
                    }
                    else {
                        flagA = true;
                        polynomial.setPolynomial(poly2, termsA);
                    }
                    break;
                case 'B':
                    if(flagB) {
                        polynomial.clearPolynomial(poly2);
                    }
                    st = sc.nextLine().replaceAll("\\[|\\]", "");
                    termsB = polynomial.scan(st);
                    if(termsB == null) {
                         System.out.println("Error");
                         return;
                    }
                    else {
                        flagB = true;
                        polynomial.setPolynomial(poly2, termsB);
                    }
                    break;
                case 'C':
                    if(flagC) {
                        polynomial.clearPolynomial(poly2);
                    }
                    st = sc.nextLine().replaceAll("\\[|\\]", "");
                    termsC = polynomial.scan(st);
                    if(termsC == null) {
                         System.out.println("Error");
                         return;
                    }
                    else {
                        flagC = true;
                        polynomial.setPolynomial(poly2, termsC);
                    }
                    break;

                default:
                    System.out.println("Error");
                    return;
                }
            }
            else if (Objects.equals(op, "print")) {
                poly2 = sc.nextLine().charAt(0);
                if(valid(poly2) == false) {
                    System.out.println("Error");
                    break;
                }
                System.out.println(polynomial.print(poly2));
            }
            else if (Objects.equals(op, "add")) {
                poly2 = sc.nextLine().charAt(0);
                poly3 = sc.nextLine().charAt(0);
                if (!(valid(poly2) && valid(poly3))) {
                    System.out.println("Error");
                    break;
                }
                else {
                    polynomial.setPolynomial(res, polynomial.add(poly2, poly3));
                    System.out.println(polynomial.print(res));
                }
            }
            else if (Objects.equals(op, "sub")) {
                poly2 = sc.nextLine().charAt(0);
                poly3 = sc.nextLine().charAt(0);
                if (!(valid(poly2) && valid(poly3))) {
                    System.out.println("Error");
                    break;
                }
                else {
                    polynomial.setPolynomial(res, polynomial.subtract(poly2, poly3));
                    System.out.println(polynomial.print(res));
                }
            }
            else if (Objects.equals(op, "mult")) {
                poly2 = sc.nextLine().charAt(0);
                poly3 = sc.nextLine().charAt(0);
                if (!(valid(poly2) && valid(poly3))) {
                    System.out.println("Error");
                    break;
                }
                else {
                    polynomial.setPolynomial(res, polynomial.multiply(poly2, poly3));
                    System.out.println(polynomial.print(res));
                }
            }
            else if (Objects.equals(op, "clear")) {
                poly2 = sc.nextLine().charAt(0);
                if (!valid(poly2)) {
                    System.out.println("Error");
                    break;
                }
                else {
                    polynomial.clearPolynomial(poly2);
                    System.out.println("[]");
                }
            }
            else if (Objects.equals(op, "eval")) {
                poly2 = sc.nextLine().charAt(0);
                float value = sc.nextFloat();
                if (!valid(poly2)) {
                    System.out.println("Error");
                    break;
                }
                else {
                    float ans = polynomial.evaluatePolynomial(poly2, value);
                    if(ans == (int) ans){
                        System.out.println((int)ans);
                    }
                    else{
                        System.out.println(ans);
                    }
                }
            }
            else {
                System.out.println("Error");
                break;
            }
        }
    }
}
interface ILinkedList {
public void add(int index, Object element);
public void add(Object element);
public Object get(int index);
public void set(int index, Object element);
public void clear();
public boolean isEmpty();
public void remove(int index);
public int size();
public ILinkedList sublist(int fromIndex, int toIndex);
public boolean contains(Object o);
public void printList();
}

class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
    public int length = 0;
    public static boolean flag;

    public class Node {
        Object item;
        Node next;
    }

    public Node head = null;
    public Node tail = null;
    
    public void printList() {
        Node current = head;
        if(current == null) {
            System.out.println("[]");
        }
        else {
            System.out.print("[" + current.item);
            current = current.next;
            while (current != null) {
                System.out.print(", " + current.item);
                current = current.next;
            }
            System.out.println("]");
        }
    }

    public void insertFirst(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            newNode.next = null;
            head = tail = newNode;
        } 
        else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public void add(int index, Object element) {
        flag = true;
        if(index < 0 || index > length) {
            flag = false;
        }
        else {
            Node newNode = new Node();
            newNode.item = element;
            if(index == 0) {
                insertFirst(element);
            }
            else if (index == length) {
                add(element);
            }
            else {
                Node current = head;
                for(int i = 1; i < index; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
                length++;
            }
        }
    }

    public void add(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            newNode.next = null;
            head = tail = newNode;
        } 
        else {
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Object get(int index) {
        if(index < 0 || index >= length) {
            return 0;
        }
        else if(index == 0) {
            return head.item;
        }
        else if(index == length - 1) {
            return tail.item;
        }
        else {
            Node current = head;
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

    public void set(int index, Object element) {
        Node current = head;
        flag = true;
        if(index < 0 || index >= length) {
            flag = false;
        }
        else if(index == 0) {
            head.item = element;
        }
        else if(index == length - 1) {
            tail.item = element;
        }
        else {
            for(int i = 0; i < index; i++) {
                current = current.next;
            }
            current.item = element;
        }
    }

    public void clear() {
        head = tail = null;
        length = 0;
    }

    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void remove(int index) {
        flag = true;
        Node current = head;
        if(index < 0 || index >= length) {
            flag = false;
        }
        else if(length == 1) {
            head = tail = null;
            length--;
        }
        else if (index == 0) {
            head = head.next;
            length--;
        }
        else if (index == length - 1) {
            for(int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            length--;
        }
        else {
            for(int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            length--;
        }
    }

    public int size() {
        return length;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {

        ILinkedList sub = new SingleLinkedList();
        if(fromIndex < 0 || fromIndex >= length || toIndex < 0 || toIndex >= length || fromIndex > toIndex || isEmpty()){
            return null;
        }
        else {
            for (int i = fromIndex; i <= toIndex; i++) {
                sub.add(get(i));
            }
            return sub;
        }
    }

    public boolean contains(Object o) {
        Node current = head;
        while(current != null) {
            if(current.item == o) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        SingleLinkedList list = new SingleLinkedList();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = st.split(", ");
        if (!(s.length == 1 && s[0].isEmpty())){
            for(int i=0; i < s.length; i++){
                Object o = Integer.parseInt(s[i]);
                list.add(o);
            }
        }
        String keyword = sc.nextLine();
        if(Objects.equals(keyword, "add")){
            int element = sc.nextInt();
            list.add(element);
            list.printList();
        }
        if(Objects.equals(keyword, "addToIndex")){
            int index = sc.nextInt();
            int element =sc.nextInt();
            list.add(index, element);
            if(flag == true) {
                list.printList();
            }
            else {
                System.out.println("Error");
            }
        }
        if(Objects.equals(keyword, "get")){
            int index = sc.nextInt();
            Object res = list.get(index);
            if((int)res == 0) {
                System.out.println("Error");
            }
            else {
                System.out.println(res);
            }
        }
        if(Objects.equals(keyword, "set")) {
            int index = sc.nextInt();
            int element =sc.nextInt();
            list.set(index, element);
            if(flag == false){
                System.out.println("Error");
            }
            else {
                list.printList();
            }
        }
        if(Objects.equals(keyword, "clear")) {
            list.clear();
            list.printList();
        }
        if(Objects.equals(keyword, "isEmpty")) {
            boolean res = list.isEmpty();
            if(res == true) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }
        if(Objects.equals(keyword, "remove")) {
            int index = sc.nextInt();
            list.remove(index);
            if(flag == false) {
                System.out.println("Error");
            }
            else {
                list.printList();
            }
        }
        if(Objects.equals(keyword, "size")){
            System.out.println(list.size());
        }
        if(Objects.equals(keyword, "sublist")) {
            int fromIndex = sc.nextInt();
            int toIndex = sc.nextInt();
            ILinkedList sub = list.sublist(fromIndex, toIndex);
            if(sub == null){
                System.out.println("Error");
            }
            else{
                sub.printList();
            }
        }
        if(Objects.equals(keyword, "contains")) {
            int element =sc.nextInt();
            boolean res = list.contains(element);
            if(res == true) {
                System.out.println("True");
            }
            else {
                System.out.println("False");
            }
        }
    }
}