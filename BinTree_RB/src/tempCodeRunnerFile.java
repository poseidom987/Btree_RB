import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Main {
    public static void main(String[] args) {
        Btree a = new Btree();
        

        String texto = "texto.txt";  // Arquivo com os números
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(texto));
            String linha;

            // Lê o arquivo linha por linha
            while ((linha = br.readLine()) != null) {
                // Divide a linha pelos números separados por vírgula
                String[] numeros = linha.split(",");
                
                // Para cada número, converte para inteiro e adiciona à árvore
                for (String numero : numeros) {
                    try {
                        int valor = Integer.parseInt(numero.trim());
                        a.add(valor);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido: " + numero);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close(); 
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }
/*
        a.add(42);
        a.add(51);
        a.add(19);
        a.add(37);
        a.add(42);
        a.add(86);
        a.add(71);
        a.add(10);
        a.add(75);
        a.add(22);
        a.add(31);
        a.add(42);
*/
        a.show();

    }
}

