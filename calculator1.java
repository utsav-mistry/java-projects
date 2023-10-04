import java.util.Scanner;

public class calculator1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a ,b ,c ,d ,e ,f ,g ,h ,i ,j;
        System.out.println("Enter the arithmetic operation you want to perform (+,-,/,*,power,root,modulus,x-percentage,conversion): ");
        String inp, con; 
        inp = sc.nextLine();
        char inp1 = inp.charAt(0);
        switch (inp1) {
            case '+':
                System.out.println("Enter the first number: ");
                a = sc.nextDouble();
                System.out.println("Enter the second number: ");
                b = sc.nextDouble();
                c = a+b;
                System.out.println("The addition of two numbers is: "+c);
            break;
            case '-':
                System.out.println("Enter the first number: ");
                a = sc.nextDouble();
                System.out.println("Enter the second number: ");
                b = sc.nextDouble();
                d = a-b;
                System.out.println("The difference of two numbers is: "+d);
            break;
            case '*':
                System.out.println("Enter the first number: ");
                a = sc.nextDouble();
                System.out.println("Enter the second number: ");
                b = sc.nextDouble();
                e = a*b;
                System.out.println("The product of two numbers is: "+e);
            break;
            case '/':
                System.out.println("Enter the first number: ");
                a = sc.nextDouble();
                System.out.println("Enter the second number: ");
                b = sc.nextDouble();
                f = a/b;
                System.out.println("The quotient of two numbers is: "+f);
            break;
            case 'p':
                System.out.println("Enter the base: ");
                a = sc.nextDouble();
                System.out.println("Enter the power: ");
                b = sc.nextDouble();
                g = Math.pow(a, b);
                System.out.println(a+" power "+b+" is: "+g);
            break;
            case 'r':
                System.out.println("Enter the base: ");
                a = sc.nextDouble();
                System.out.println("Enter the nth root: ");
                b = sc.nextDouble();
                h = Math.pow(a,1/b);
                System.out.println("The "+b+"th root of "+a+" is: "+h);
            break;
            case 'm':
                System.out.println("Enter the first number: ");
                a = sc.nextDouble();
                System.out.println("Enter the second number: ");
                b = sc.nextDouble();
                i = a%b;
                System.out.println("The modulus of two numbers is: "+i);
            break;
            case 'x':
                System.out.println("Enter the number: ");
                a = sc.nextDouble();
                System.out.println("Enter the total: ");
                b = sc.nextDouble();
                j = (a/b)*100;
                System.out.println("The percentage of two numbers is: "+j+"%");
            break;
            case 'c':
                System.out.println("Enter the quantity you want to convert x,y(x = temperature, y = units): ");
                con = sc.next();
                char con1 = con.charAt(0);
                {
                    switch (con1) {
                        case 'x':
                    
                        System.out.println("Enter the unit of your input C,F(C = celsius, F = fahrenhit): ");
                        String temp = sc.next();
                        char temp1 = temp.charAt(0);
                        if (temp1 == 'C') {
                            System.out.println("Enter your value in degree celsius: ");
                            double k = sc.nextDouble();
                            double l = ((9*k)/5)+32;
                            System.out.println("The value of "+k+" in fahrenhit is: "+l);
                        }
                        else if (temp1 == 'F') {
                            System.out.println("Enter your value in fahrenhit: ");
                            double m = sc.nextDouble();
                            double n = ((m-32)*5)/9;
                            System.out.println("The value of "+m+" in fahrenhit is: "+n);
                        }
                        break;
                        case 'y': {
                            Scanner op = new Scanner(System.in);
                            System.out.print("Enter length: ");
                            double length = op.nextDouble();
                            System.out.println("Select input unit:");
                            System.out.println("1. Millimeter");
                            System.out.println("2. Centimeter");
                            System.out.println("3. Decimeter");
                            System.out.println("4. Meter");
                            System.out.println("5. Decameter");
                            System.out.println("6. Hectometer");
                            System.out.println("7. Kilometer");
                            int inputChoice = op.nextInt();
                            System.out.println("Select output unit:");
                            System.out.println("1. Millimeter");
                            System.out.println("2. Centimeter");
                            System.out.println("3. Decimeter");
                            System.out.println("4. Meter");
                            System.out.println("5. Decameter");
                            System.out.println("6. Hectometer");
                            System.out.println("7. Kilometer");
                            int outputChoice = op.nextInt();
                            double result;
                                switch (inputChoice) {
                                    case 1:  
                                        length /= 1000;
                                    break;
                                    case 2:  
                                        length /= 100;
                                    break;
                                    case 3:  
                                        length /= 10;
                                    break;
                                    case 4:  
                                     break;
                                    case 5:  
                                        length *= 10;
                                    break;
                                    case 6:  
                                        length *= 100;
                                    break;
                                    case 7:  
                                        length *= 1000;
                                    break;
                                    default:
                                        System.out.println("Invalid input unit choice.");
                                    }

                                switch (outputChoice) {
                                    case 1: 
                                        result = length * 1000;
                                        break;
                                    case 2: 
                                        result = length * 100;
                                        break;
                                    case 3:  
                                        result = length * 10;
                                        break;
                                    case 4: 
                                        result = length;
                                        break;
                                    case 5:  
                                        result = length / 10;
                                        break;
                                    case 6:  
                                        result = length / 100;
                                        break;
                                    case 7:  
                                        result = length / 1000;
                                        break;
                                    default:
                                        System.out.println("Invalid output unit choice.");
                                        return;
                                }
                                System.out.println("Converted length: " + result + " units");
                                op.close();
                            }
                        }
                    }
            break;
            default:
                System.out.println("Invalid Input/Syntax Error/Math Error");
                break;
            }
        sc.close();
    }
}