package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MPelicula {

	String error = "";

	Conexion con = new Conexion();;
	ResultSet res;
	PreparedStatement pstm;
	String query;

	public String comprobarInsertar(String p_codigo, String p_titulo, String p_anno, String p_tema, String p_idDirector,
			String p_calificacion) {

		int resultado = 0;
		
		error = "";
		
		if (p_codigo.isEmpty()) {

			error += "No puede dejar el campo de código vacío\n";

		} else if (!isNaN(p_codigo)) {

			error += "El codigo no es un numero\n";

		}

		if (p_titulo.isEmpty()) {

			error += "No puede dejar el campo de títuo vacío\n";

		} else if (p_titulo.length() > 50) {

			error += "El titulo es demasiado largo";

		}

		if (p_tema.isEmpty()) {

			error += "No puede dejar el campo de tema vacío\n";

		} else if (p_tema.length() > 30) {

			error += "El tema es demasiado largo";

		}
		if (p_anno.isEmpty()) {

			error += "No puede dejar el campo de año vacío\n";

		} else if (!isShort(p_anno)) {

			error += "El año no es un numero\n";

		}
		if (p_idDirector.isEmpty()) {

			error += "No puede dejar el campo del id de director vacío\n";

		} else if (!isNaN(p_idDirector)) {

			error += "El id de director no es un numero\n";

		}
		if (p_calificacion.isEmpty()) {

			error += "No puede dejar el campo de calificacion vacío\n";

		} else if (!isFloat(p_calificacion)) {

			error += "La calificacion no es un numero decimal\n";

		}

		if (error.length() < 3) {

			resultado = insertar(Integer.parseInt(p_codigo), p_titulo, p_tema, Short.parseShort(p_anno),
					Integer.parseInt(p_idDirector), Float.parseFloat(p_calificacion));

			switch (resultado) {
			case 0:
				error = "Se ha insertaod con éxito la película";
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

	public String comprobarModificar(String p_codigo, String p_titulo, String p_anno, String p_tema, String p_idDirector,
			String p_calificacion) {
		
int resultado = 0;
		
		error = "";
		
		if (p_codigo.isEmpty()) {

			error += "No puede dejar el campo de código vacío\n";

		} else if (!isNaN(p_codigo)) {

			error += "El codigo no es un numero\n";

		}

		if (p_titulo.isEmpty()) {

			p_titulo = "";

		} else if (p_titulo.length() > 50) {

			error += "El titulo es demasiado largo";

		}

		if (p_tema.isEmpty()) {

			p_tema = "";

		} else if (p_tema.length() > 30) {

			error += "El tema es demasiado largo";

		}
		if (p_anno.isEmpty()) {

			p_anno = "0";

		} else if (!isShort(p_anno)) {

			error += "El año no es un numero\n";

		}
		if (p_idDirector.isEmpty()) {

			p_idDirector = "0";

		} else if (!isNaN(p_idDirector)) {

			error += "El id de director no es un numero\n";

		}
		if (p_calificacion.isEmpty()) {

			p_calificacion = "0";

		} else if (!isFloat(p_calificacion)) {

			error += "La calificacion no es un numero decimal\n";

		}

		if (error.length() < 3) {

			resultado = modificar(Integer.parseInt(p_codigo), p_titulo, p_tema, Short.parseShort(p_anno),
					Integer.parseInt(p_idDirector), Float.parseFloat(p_calificacion));

			switch (resultado) {
			case 0:
				error = "Se ha insertaod con éxito la película";
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

	public boolean isShort(String aux) {
		boolean numero = false;
		try {
			Short.parseShort(aux);
			numero = true;
		} catch (NumberFormatException e) {
			System.err.println(aux + " no es un short");
		}
		return numero;
	}

	public boolean isFloat(String aux) {
		boolean numero = false;
		try {
			Float.parseFloat(aux);
			numero = true;
		} catch (NumberFormatException e) {
			System.err.println(aux + " no es un float");
		}
		return numero;
	}

	// -----------------------------------

	private int insertar(int p_cod_pelicula, String p_titulo, String p_tema, short p_anio, int p_id_director,
			float p_calificacion) {

		// insert into cine.pelicula values (5, 'Java la pelicula', 'Terror', 2021, 5,
		// 0.1);
		con = new Conexion();
		con.abrirConexion();

		query = "insert into cine.pelicula (codigo_pelicula, titulo, tema, anno, id_director, calificacion) values (?, ?, ?, ?, ?, ?)";

		int resultado = 1;

		try {

			pstm = con.getConexion().prepareStatement(query);

			pstm.setInt(1, p_cod_pelicula);
			pstm.setString(2, p_titulo);
			pstm.setString(3, p_tema);
			pstm.setShort(4, p_anio);
			pstm.setInt(5, p_id_director);
			pstm.setFloat(6, p_calificacion);

			pstm.executeUpdate();

			resultado = 0;

		} catch (SQLException sqle) {

			sqle.printStackTrace();

			resultado = 2;

		}

		return resultado;

	}

	public int modificar(int p_cod_pelicula, String p_titulo, String p_tema, short p_anio, int p_id_director,
			float p_calificacion) {

		int ejecucion = 1;
		con.abrirConexion();

		boolean coma = false; // Coloca una coma si hay un 'set = ?' antes
		int contador = 1; // Cuenta cuántos ? tendran que ser convertidos en valores

		query = "update cine.pelicula set ";

		if (!p_titulo.isBlank()) {
			query += "titulo = ? ";
			coma = true;
		}

		if (!p_tema.isBlank()) {
			if (coma) {
				query += ",";
			}
			;
			query += "tema = ?";
			coma = true;
		}

		if (p_anio != 0) {
			if (coma) {
				query += ",";
			}
			;
			query += "anio = ? ";
			coma = true;
		}

		if (p_id_director != 0) {
			if (coma) {
				query += ",";
			}
			;
			query += "id_director = ? ";
			coma = true;
		}

		if (p_calificacion != 0) {
			if (coma) {
				query += ",";
			}
			;
			query += "calificacion = ? ";
		}

		query += "where codigo_pelicula = ?";

		try {

			pstm = con.getConexion().prepareStatement(query);

			if (!p_titulo.isBlank()) {
				pstm.setString(contador, p_titulo);
				contador++;
			}

			if (!p_tema.isBlank()) {
				pstm.setString(contador, p_tema);
				contador++;
			}

			if (p_anio != 0) {
				pstm.setShort(contador, p_anio);
				contador++;
			}

			if (p_id_director != 0) {
				pstm.setInt(contador, p_id_director);
				contador++;
			}

			if (p_calificacion != 0) {
				pstm.setFloat(contador, p_calificacion);
				contador++;
			}

			pstm.setInt(contador, p_cod_pelicula);

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

	public void borrar (String p_cod_pelicula) {

		// insert into cine.pelicula values (5, 'Java la pelicula', 'Terror', 2021, 5,
		// 0.1);
		con = new Conexion();
		con.abrirConexion();

		query = "delete from cine.pelicula where codigo_pelicula = ?";

		try {

			pstm = con.getConexion().prepareStatement(query);

			pstm.setInt(1, Integer.parseInt(p_cod_pelicula));

			pstm.executeUpdate();

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		}

	}

	public String[][] consultar(int pag) {

		con.abrirConexion();
		int offset = 0;
		String[][] registros = new String[7][6];
		int contadorFilas = 0;

		if (pag > 1) {
			offset = (pag - 1) * 7;
		}

		query = "SELECT codigo_pelicula, titulo, tema, anno, id_director, calificacion FROM cine.pelicula limit 7 offset " + offset;

		try {

			pstm = con.getConexion().prepareStatement(query);

//			System.out.println(consulta.toString());

			res = pstm.executeQuery();

			registros = new String[9][7];

			while (res.next()) {

				registros[contadorFilas][0] = res.getString(1);
				registros[contadorFilas][1] = res.getString(2);
				registros[contadorFilas][2] = res.getString(3);
				registros[contadorFilas][3] = res.getString(4);
				registros[contadorFilas][4] = res.getString(5);
				registros[contadorFilas][5] = res.getString(6);

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
