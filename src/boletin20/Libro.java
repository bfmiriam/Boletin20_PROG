
package boletin20;


public class Libro {
    
    private String nome;
    private String autor;
    private float precio;
    private int unidades;

    public Libro() {
    }

    public Libro(String nome, String autor, float precio, int unidades) {
        this.nome = nome;
        this.autor = autor;
        this.precio = precio;
        this.unidades = unidades;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public float getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        
        return "Nome: "+nome+" Autor: "+autor+" Precio: "+precio+" Unidades: "+unidades;
    }
    public String visualizar(){
        
        return  nome + ";" + autor + ";" + precio + ";" + unidades ;
    }
    
}
