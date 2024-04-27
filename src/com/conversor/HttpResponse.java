package com.conversor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpResponse {
    public static void conversion(String baseCurrency, String targetCurrency) {
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/0578064caf63dcd1d3dd26d6/latest/" + baseCurrency;

            URL url = new URL(direccion);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();
            float tasaCambio = obtenerTasaCambio(jsonResponse, targetCurrency);

            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese la cantidad a convertir: ");
            float cantidad = sc.nextFloat();

            float resultado = cantidad * tasaCambio;

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            System.out.printf("%.2f %s equivale a %.2f %s\n", cantidad, baseCurrency, resultado, targetCurrency);
            System.out.println("Fecha y hora de conversión: " + ahora.format(formatter));
        } catch (IOException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }

    private static float obtenerTasaCambio(String jsonResponse, String targetCurrency) {
        String[] parts = jsonResponse.split("\"conversion_rates\":\\{")[1].split("}")[0].split(",");
        for (String part : parts) {
            String[] keyValue = part.trim().split(":");
            String currency = keyValue[0].replaceAll("\"", "").trim();
            if (currency.equals(targetCurrency)) {
                return Float.parseFloat(keyValue[1].trim());
            }
        }
        throw new IllegalArgumentException("No se encontró la tasa de cambio para la moneda objetivo.");
    }
}
