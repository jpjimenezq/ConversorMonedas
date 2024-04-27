package com.conversor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            Menu.mostrarMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    HttpResponse.conversion("USD", "ARS");
                    break;
                case 2:
                    HttpResponse.conversion("ARS", "USD");
                    break;
                case 3:
                    HttpResponse.conversion("USD", "BRL");
                    break;
                case 4:
                    HttpResponse.conversion("BRL", "USD");
                    break;
                case 5:
                    HttpResponse.conversion("USD", "COP");
                    break;
                case 6:
                    HttpResponse.conversion("COP", "USD");
                    break;
                case 7:
                    System.out.println("*******************************************");
                    System.out.print("Ingrese la moneda base: ");
                    String baseCurrency = sc.next();
                    System.out.print("Ingrese la moneda a convertir: ");
                    String targetCurrency = sc.next();
                    System.out.println("*******************************************");
                    HttpResponse.conversion(baseCurrency, targetCurrency);
                    break;
                case 8:
                    System.out.println("Gracias por usar el Conversor de Moneda. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Escoja una opción válida");
                    break;
            }
        } while (opcion != 8);
    }
}
