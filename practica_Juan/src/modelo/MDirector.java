package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MDirector {

	String error = "";

	Conexion con = new Conexion();
	ResultSet res;
	PreparedStatement pstm;
	String query;

	public String comprobarInsertar(String id_director, String nombre) {

		int resultado = 0;

		error = "";

		if (id_director.isEmpty()) {

			error += "No puede dejar el campo de id vacío\n";

		} else if (!isNaN(id_director)) {

			error += "El id no es un numero\n";

		}

		if (nombre.isEmpty()) {

			error += "No puede dejar el campo de nombre vacío\n";

		} else if (nombre.length() > 35) {

			error += "El nombre es demasiado largo";

		}

		if (error.length() < 3) {

			resultado = insertar(Integer.parseInt(id_director), nombre);

			switch (resultado) {
			case 0:
				error = "Se ha insertaod con éxito el director";
				break;
			case 1:
				error = "No se ha podido insertar los datos en la base de datos";
				break;

			case 2:
				error = "Hubo un error en la insercion de los datos";
				break;

			default:
				break;
			}

		}

		return error;

	}

	public String comprobarModificar(String id_director, String nombre) {

		int resultado = 0;

		error = "";

		if (id_director.isEmpty()) {

			error += "No puede dejar el campo de id vacío\n";

		} else if (!isNaN(id_director)) {

			error += "El ID no es un numero\n";

		}

		if (nombre.isEmpty()) {

			error += "No puede dejar el campo de nombre vacío si es el unico campo\n";

		} else if (nombre.length() > 35) {

			error += "El nombre es demasiado largo";

		}

		if (error.length() < 3) {

			resultado = modificar(Integer.parseInt(id_director), nombre);

			switch (resultado) {
			case 0:
				error = "Se ha insertado con éxito el director";
				break;
			case 1:
				error = "No se ha podido insertar los datos en la base de datos";
				break;

			case 2:
				error = "Hubo un error en la insercion de los datos";
				break;

			default:
				break;
			}

		}

		return error;
	}

	public boolean isNaN(String aux) {
		boolean numero = false;
		try {
			Integer.parseInt(aux);
			numero = true;
		} catch (NumberFormatException e) {
			System.err.println(aux + " no es un número");
		}
		return numero;
	}

	// -----------------------------------

	private int insertar(int id_director, String nombre) {

		// insert into cine.pelicula values (5, 'Java la pelicula', 'Terror', 2021, 5,
		// 0.1);
		con = new Conexion();
		con.abrirConexion();

		query = "insert into cine.director (id_director, nombre) values (?, ?)";

		int resultado = 1;

		try {

			pstm = con.getConexion().prepareStatement(query);

			pstm.setInt(1, id_director);
			pstm.setString(2, nombre);

			pstm.executeUpdate();

			resultado = 0;

		} catch (SQLException sqle) {

			sqle.printStackTrace();

			resultado = 2;

		}

		return resultado;

	}

	public int modificar(int id_director, String nombre) {

		int ejecucion = 1;
		con.abrirConexion();

		boolean coma = false; // Coloca una coma si hay un 'set = ?' antes
		int contador = 1; // Cuenta cuántos ? tendran que ser convertidos en valores

		query = "update cine.director set nombre = ? where id_director = ?";

		try {

			pstm = con.getConexion().prepareStatement(query);

			pstm.setString(1, nombre);
			pstm.setInt(2, id_director);

			System.out.println(pstm.toString());

			pstm.executeUpdate();
			
			ejecucion = 0;

		} catch (SQLException sqle) {
			System.err.print("Hubo un error durante la ejecución de la consulta\n");
			sqle.printStackTrace();
			ejecucion = 2;
		} catch (Exception ex) {
			System.err.print("Hubo un error desconocido durante la ejecución de la consulta\n");
			ex.printStackTrace();
			ejecucion = 2;
		} finally {
			con.cerrarConexion();
		}

		return ejecucion;
	}

	public void borrar(String id_director) {

		// insert into cine.pelicula values (5, 'Java la pelicula', 'Terror', 2021, 5,
		// 0.1);
		con = new Conexion();
		con.abrirConexion();

		query = "delete from cine.director where id_director = ?";

		try {

			pstm = con.getConexion().prepareStatement(query);

			pstm.setInt(1, Integer.parseInt(id_director));

			pstm.executeUpdate();

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		}

	}

	public String[][] consultar(int pag) {

		con.abrirConexion();
		int offset = 0;
		String[][] registros = new String[7][2];
		int contadorFilas = 0;

		if (pag > 1) {
			offset = (pag - 1) * 7;
		}

		query = "SELECT id_director, nombre FROM cine.director limit 7 offset " + offset;

		try {

			pstm = con.getConexion().prepareStatement(query);

//			System.out.println(consulta.toString());

			res = pstm.executeQuery();

			registros = new String[9][2];

			while (res.next()) {

				registros[contadorFilas][0] = res.getString(1);
				registros[contadorFilas][1] = res.getString(2);

				contadorFilas++;

			}

		} catch (SQLException sqle) {
			System.err.print("Hubo un error durante la ejecución de la consulta\n");
			sqle.printStackTrace();
		} catch (Exception ex) {
			System.err.print("Hubo un error desconocido durante la ejecución de la consulta\n");
			ex.printStackTrace();
		} finally {
			con.cerrarConexion();
		}

		return registros;

	}

}
