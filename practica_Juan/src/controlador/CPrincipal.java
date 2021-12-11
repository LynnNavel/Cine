package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.MDirector;
import modelo.MPelicula;
import vista.VentanaFormulario;

public class CPrincipal implements ActionListener {

	private VentanaFormulario ventana;

	private MPelicula pelicula;
	private MDirector director;

	int pagActualPelicula = 0;
	int pagActualDirector = 0;

	public void iniciar() {

		ventana = new VentanaFormulario("Lil'Paw Cinema", 300, 350);
		ventana.hacerVisible(true);
		
		pelicula = new MPelicula();
		director = new MDirector();

		anadirEventos();

	}

	private void anadirEventos() {

		ventana.getConfirmarInsertarPelicula().addActionListener(this);
		ventana.getConfirmarModificarPelicula().addActionListener(this);
		ventana.getConfirmarBorrarPelicula().addActionListener(this);

		ventana.getRePagPelicula().addActionListener(this);
		ventana.getActualizarPelicula().addActionListener(this);
		ventana.getAvPagPelicula().addActionListener(this);

		ventana.getConfirmarInsertarDirector().addActionListener(this);
		ventana.getConfirmarModificarDirector().addActionListener(this);
		ventana.getConfirmarBorrarDirector().addActionListener(this);

		ventana.getRePagDirector().addActionListener(this);
		ventana.getActualizarDirector().addActionListener(this);
		ventana.getAvPagDirector().addActionListener(this);

		ventana.getSalir().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ev) {

		String resultado;

		if (ev.getSource() == ventana.getSalir()) {

			System.exit(0);

		}

		if (ev.getSource() == ventana.getConfirmarInsertarPelicula()) {
			
			resultado = pelicula.comprobarInsertar(ventana.getCodigoPeliculaInsertar(), ventana.getTituloPeliculaInsertar(),
					ventana.getAnnoPeliculaInsertar(), ventana.getTemaPeliculaInsertar(),
					 ventana.getDirectorPeliculaInsertar(), ventana.getCalificacionPeliculaInsertar());
			
			

				ventana.mostrarMensaje(ventana.MESSAGE_INFORMATION, resultado,
						"Resultado de la operación");

		}

		if (ev.getSource() == ventana.getConfirmarModificarPelicula()) {

			System.out.println(ventana.getCodigoPeliculaModificar());
			
			resultado = pelicula.comprobarModificar(ventana.getCodigoPeliculaModificar(), ventana.getTituloPeliculaModificar(),
					ventana.getAnnoPeliculaModificar(), ventana.getTemaPeliculaModificar(),
					 ventana.getDirectorPeliculaModificar(), ventana.getCalificacionPeliculaModificar());

				ventana.mostrarMensaje(ventana.MESSAGE_INFORMATION, resultado,
						"Resultado de la operacion");

		}

		if (ev.getSource() == ventana.getConfirmarBorrarPelicula()) {

			pelicula.borrar(ventana.getCodigoPeliculaEliminar());

		}

		if (ev.getSource() == ventana.getRePagPelicula() && pagActualPelicula > 1) {

			String[][] arrayPelicula = new String[7][6];

			pagActualPelicula --;
			
			arrayPelicula = pelicula.consultar(pagActualPelicula);

			ventana.setTablaPelicula(arrayPelicula);

		}

		if (ev.getSource() == ventana.getActualizarPelicula()) {

			String[][] arrayPelicula = new String[7][6];

			arrayPelicula = pelicula.consultar(pagActualPelicula);

			ventana.setTablaPelicula(arrayPelicula);

		}
		
		if (ev.getSource() == ventana.getAvPagPelicula()) {

			String[][] arrayPelicula = new String[7][6];
			
			pagActualPelicula ++;

			arrayPelicula = pelicula.consultar(pagActualPelicula);

			ventana.setTablaPelicula(arrayPelicula);

		}
		
		// Eventos de director
		
		if (ev.getSource() == ventana.getConfirmarInsertarDirector()) {

			resultado = director.comprobarInsertar(ventana.getIdDirectorInsertar(), ventana.getNombreDirectorInsertar());

				ventana.mostrarMensaje(ventana.MESSAGE_INFORMATION, "Se ha insertado correctamente",
						"Operacion exitosa");


		}

		if (ev.getSource() == ventana.getConfirmarModificarDirector()) {

			resultado = director.comprobarModificar(ventana.getIdDirectorModificar(), ventana.getNombreDirectorModificar());

				ventana.mostrarMensaje(ventana.MESSAGE_INFORMATION, "Se ha insertado correctamente",
						"Operacion exitosa");

		}

		if (ev.getSource() == ventana.getConfirmarBorrarDirector()) {

			director.borrar(ventana.getIdDirectorEliminar());

		}

		if (ev.getSource() == ventana.getRePagDirector() && pagActualDirector > 1) {

			String[][] arrayDirector= new String[7][2];

			pagActualDirector --;
			
			arrayDirector= director.consultar(pagActualDirector);

			ventana.setTablaDirector(arrayDirector);

		}

		if (ev.getSource() == ventana.getActualizarDirector()) {

			String[][] arrayDirector = new String[7][2];
			
			arrayDirector = director.consultar(pagActualDirector);

			ventana.setTablaDirector(arrayDirector);

		}
		
		if (ev.getSource() == ventana.getAvPagDirector()) {

			String[][] arrayDirector = new String[7][2];

			pagActualDirector ++;
			
			arrayDirector = director.consultar(pagActualDirector);

			ventana.setTablaDirector(arrayDirector);

		}

	}

}
