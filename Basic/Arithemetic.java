import java.util.Scanner;

public class Arithemetic{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a,b;
        System.out.println("Enter The numbers :");
        a= sc.nextInt();
        b= sc.nextInt();
        System.out.println("Result :"+ "Sum: "+ (a+b) +"Subtraction :"+ (a-b) +"Multiplication :"+(a*b) +"Divison"+ (a/b));
    }
}