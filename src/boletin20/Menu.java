package boletin20;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Menu {

    ArrayList<Libro> exemplares = new ArrayList();
    Scanner sc;
    File ficheiro;
    PrintWriter escribir;

    public void menu() throws IOException {
        System.out.println("*******MENU******* \n"
                + "1)Engade un libro\n"
                + "2)Visualiza o precio dun libro\n"
                + "3)Visualiza todos os datos do ficheiro\n"
                + "4)Borra os libros con 0 unidades\n"
                + "5)Modifica o precio dun libro determinado\n"
                + "6)Salir");

        int op = Integer.parseInt(JOptionPane.showInputDialog("Escolle unha opción:"));

        while (op != 6) {
            switch (op) {
                case 1:
                    engadirLibros();
                    break;
                case 2:
                    consultarLibro();
                    break;
                case 3:
                    lerFicheiro();
                    break;
                case 4:
                    borrarLibros();
                    break;
                case 5:
                    modificarPrecio();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "opcion incorrecta");

            }
            System.out.println("*******MENU******* \n"
                    + "1)Engade un libro\n"
                    + "2)Visualiza o precio dun libro\n"
                    + "3)Visualiza todos os datos do ficheiro\n"
                    + "4)Borra os libros con 0 unidades\n"
                    + "5)Modifica o precio dun libro determinado\n"
                    + "6)Salir");

            op = Integer.parseInt(JOptionPane.showInputDialog("Escolle unha opción:"));

        }
    }

    public void crearArrayList() {

        String linea;
        String[] lista = new String[4];
        Libro l;

        try {

            sc = new Scanner(new File("libros.txt"));

            while (sc.hasNextLine()) {
                linea = sc.nextLine();
                lista = linea.split(";");

                l = new Libro(lista[0], (lista[1]), Float.parseFloat(lista[2]), Integer.parseInt(lista[3]));
                exemplares.add(l);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("erro1" + ex.getMessage());
        } finally {
            sc.close();
        }

    }

    // engade un novo libro ao noso ficheiro
    public void engadirLibros() throws IOException {

        String nome = JOptionPane.showInputDialog("Introduce titulo del libro");
        String autor = JOptionPane.showInputDialog("Introduce el autor del libro");
        float precio = Float.parseFloat(JOptionPane.showInputDialog("Introduce precio del libro"));
        int unidades = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas unidades hay de ese libro"));

        Libro l = new Libro(nome, autor, precio, unidades);
        exemplares.add(l);

        try {
            ficheiro = new File("libros.txt");
            escribir = new PrintWriter(new FileWriter(ficheiro, true));
            escribir.println(l.visualizar());

        } catch (FileNotFoundException ex) {
            System.out.println("erro de escritura");
        } finally {
            escribir.close();
        }
    }

    //dado o título dun libro visualizar o seu precio
    public void consultarLibro() {

        String titulo = JOptionPane.showInputDialog("Cal é o titulo do libro que desexas consultar?");

        boolean encontrado = false;

        for (Libro elemento : exemplares) {
            if (titulo.equalsIgnoreCase(elemento.getNome())) {
                System.out.println("Precio: " + elemento.getPrecio());
                encontrado = true;

            }
        }
        if (encontrado == false) {
            System.out.println("Libro non atopado");
        }

    }
    //visualiza todos os datos do ficheiro
    public void lerFicheiro() {
        Iterator it = exemplares.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    //borra os libros que teñan 0 unidades
    public void borrarLibros() throws IOException {

        Iterator<Libro> it = exemplares.iterator();
        while (it.hasNext()) {
            if (it.next().getUnidades() == 0) {
                it.remove();

                try {
                    ficheiro = new File("libros.txt");
                    escribir = new PrintWriter(new FileWriter(ficheiro));

                    Iterator it2 = exemplares.iterator();
                    while (it2.hasNext()) {

                        Libro exemplar = (Libro) it2.next();
                        escribir.println(exemplar.visualizar());
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("erro " + ex.getMessage());
                } finally {
                    escribir.close();
                }
            }
        }

    }

    //modifica o precio dun libro determinado
    public void modificarPrecio() throws IOException {

        String titulo = JOptionPane.showInputDialog("A que libro desexas modificar o precio?");
        float nuevoPrecio = Float.parseFloat(JOptionPane.showInputDialog("Que precio desexas asignarlle?"));
        Boolean atopado = false;

        for (Libro elemento : exemplares) {
            if (elemento.getNome().equalsIgnoreCase(titulo)) {
                elemento.setPrecio(nuevoPrecio);
                atopado = true;
            }
        }
        try {
            ficheiro = new File("libros.txt");
            escribir = new PrintWriter(new FileWriter(ficheiro));

            Iterator it2 = exemplares.iterator();
            while (it2.hasNext()) {

                Libro exemplar = (Libro) it2.next();
                escribir.println(exemplar.visualizar());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("erro " + ex.getMessage());
        } finally {
            escribir.close();
        }
        if (atopado == false) {
            System.out.println("Libro non atopado.");
        }
    }

}
//leer do ficheiro que temos e crear outro ficheiro cos titulos de un determinado autor

