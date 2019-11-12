package practica1AD;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) {
		ArrayList<ListaDeLaCompra> arrayListCom = new ArrayList<ListaDeLaCompra>();
		arrayListCom = (persistencia());
		outputFichero(arrayListCom);
		mostrarResultado();
	}

	private static void mostrarResultado() {
		ArrayList<ListaDeLaCompra> arrayListCom = persistenciap();
		for (int i = 0; i < arrayListCom.size(); i++) {
			String nombreLista = arrayListCom.get(i).getNombreLista();
			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("CONTENIDO DE LA LISTA '" + nombreLista + "': ");

			ArrayList<ArticuloAComprar> articulosLista = arrayListCom.get(i).getArticulos();
			for (int z = 0; z < articulosLista.size(); z++) {
				System.out.println("-------------------------------");
				System.out.println("(" + (z + 1) + ") -Descripción: " + articulosLista.get(z).getDescripcion());
				System.out.println("(" + (z + 1) + ") -Unidad de medida: " + articulosLista.get(z).getUnidad());
				System.out.println("(" + (z + 1) + ") -Cantidad: " + articulosLista.get(z).getCantidad());
			}
		}
	}

	private static ArrayList<ListaDeLaCompra> persistenciap() {
		ArrayList<ListaDeLaCompra> arrayCargarListas = new ArrayList<ListaDeLaCompra>();
		try {
			ObjectInputStream leerFicheroLista = new ObjectInputStream(new FileInputStream("listas.dat"));
			arrayCargarListas = ((ArrayList<ListaDeLaCompra>) leerFicheroLista.readObject());
			leerFicheroLista.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return arrayCargarListas;
	}

	private static void outputFichero(ArrayList<ListaDeLaCompra> arrayLista) {
		try {
			ObjectOutputStream escribiendoFicheroArticulos = new ObjectOutputStream(new FileOutputStream("listas.dat"));
			escribiendoFicheroArticulos.writeObject(arrayLista);
			escribiendoFicheroArticulos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static ArrayList<ListaDeLaCompra> persistencia() {
		Boolean parar = false;
		Boolean pararLista = false;
		int i, nl;
		nl = 1;
		ArrayList<ListaDeLaCompra> listas = new ArrayList<ListaDeLaCompra>();
		sc = new Scanner(System.in);

		while (pararLista == false) {

			System.out.println("Creación de listas");
			System.out.println("-------------------------------");
			System.out.println("Escriba el nombre de la lista (" + nl + ")");
			String listaNombre = sc.nextLine();
			System.out.println("		*añadiendo lista con nombre: " + listaNombre + "*");
			ArrayList<ArticuloAComprar> articulos = new ArrayList<ArticuloAComprar>();
			i = 1;
			parar = false;
			while (parar == false) {
				System.out.println("Escriba el nombre del artículo (" + i + ")");
				String articuloNombre = sc.nextLine();
				System.out.println("		*añadiendo artículo con nombre: " + articuloNombre + "*");

				System.out.println("Escriba la unidad de medida del artículo (" + i + ")");
				String articuloUnidad = sc.nextLine();
				System.out.println("		*añadiendo unidad de medida: " + articuloUnidad + "*");

				System.out.println("Escriba la cantidad del artículo (" + i + ")");
				int articuloCantidad = sc.nextInt();
				sc.nextLine();
				System.out.println("		*añadiendo cantidad de artículos: " + articuloCantidad + "*");

				articulos.add(new ArticuloAComprar(articuloNombre, articuloCantidad, articuloUnidad));
				i++;
				Boolean seguir = false;

				while (seguir == false) {
					System.out.println("¿Quiere crear más artículos? S/N");
					char respuesta = sc.nextLine().charAt(0);

					switch (respuesta) {
					case 'S':
						seguir = true;
						break;

					case 's':
						seguir = true;
						break;

					case 'N':
						seguir = true;
						parar = true;
						break;

					case 'n':
						seguir = true;
						parar = true;
						break;

					default:
						System.out.println("Escriba 'S' para Sí y 'N' para NO");
						break;
					}
				}
			}
			listas.add(new ListaDeLaCompra(listaNombre, articulos));
			nl++;
			Boolean seguirlista = false;
			while (seguirlista == false) {
				System.out.println("¿Quiere crear otra lista? S/N");
				char res = sc.nextLine().charAt(0);
				switch (res) {
				case 'S':
					seguirlista = true;
					break;

				case 's':
					seguirlista = true;
					break;

				case 'N':
					seguirlista = true;
					pararLista = true;
					break;

				case 'n':
					seguirlista = true;
					pararLista = true;
					break;

				default:
					System.out.println("Escriba 'S' para Sí y 'N' para NO");
					break;
				}
			}
		}

		return listas;
	}
}