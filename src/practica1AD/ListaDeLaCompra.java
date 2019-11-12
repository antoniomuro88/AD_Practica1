package practica1AD;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaDeLaCompra implements Serializable {

	private static final long serialVersionUID = 6472462555222110171L;
	String nomList;
	ArrayList<ArticuloAComprar> artList;

	public ListaDeLaCompra() {
		nomList = "";
		artList = new ArrayList<ArticuloAComprar>();
	}

	public ListaDeLaCompra(String nom, ArrayList<ArticuloAComprar> ca) {
		nomList = nom;
		artList = ca;
	}

	public ListaDeLaCompra(String nombreLista) {
		nombreLista = nomList;
	}

	public String getNombreLista() {
		return nomList;
	}

	public void setNombreLista(String nombreLista) {
		nomList = nombreLista;
	}

	public void agregarProductoAComprar(ArticuloAComprar articulo) {
		artList.add(articulo);
	}

	public void eliminarArticulo(String descripcionArticulo) {
		artList.remove(descripcionArticulo);
	}

	public ArrayList<ArticuloAComprar> getArticulos() {
		return artList;
	}

}
