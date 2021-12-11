package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaFormulario extends JFrame implements ActionListener {
	
	public final int MESSAGE_ERROR = JOptionPane.ERROR_MESSAGE;
	public final int MESSAGE_INFORMATION = JOptionPane.INFORMATION_MESSAGE;
	public final int MESSAGE_WARNING = JOptionPane.WARNING_MESSAGE;
	public final int MESSAGE_QUESTION = JOptionPane.QUESTION_MESSAGE;
	public final int MESSAGE = JOptionPane.PLAIN_MESSAGE;

	public final int OPTION_OK = JOptionPane.OK_OPTION;
	public final int OPTION_YES_NO = JOptionPane.YES_NO_OPTION;
	public final int OPTION_YES_NO_CANCEL = JOptionPane.YES_NO_CANCEL_OPTION;
	public final int OPTION_OK_CANCEL = JOptionPane.OK_CANCEL_OPTION;

	JMenuBar barraMenu;
	JMenu menuPelicula, menuDirector;
	JMenuItem itemInsertarPelicula, itemModificarPelicula, itemBorrarPelicula, itemConsultarPelicula;
	JMenuItem itemInsertarDirector, itemModificarDirector, itemBorrarDirector, itemConsultarDirector;
	JMenuItem salir;
	
	GridBagConstraints gc;
	
	private JPanel formularios;
	
	private JPanel panelTitulo;
	private JLabel tituloPanel;

	// Elementos del panel pelicula

	private JPanel panelInsertarPelicula, panelModificarPelicula, panelBorrarPelicula, panelConsultarPelicula,
			panelNavegacionPelicula;
	private JButton confirmarInsertarPelicula, confirmarBorrarPelicula, confirmarModificarPelicula;
	private JScrollPane panelTablaPelicula;

	private JLabel[] labelInsertarPelicula, labelModificarPelicula;
	private JLabel labelBorrarPelicula;

	private JTextField codigoPeliculaTextInsertar, tituloPeliculaTextInsertar, annoPeliculaTextInsertar,
			calificacionPeliculaTextInsertar, temaPeliculaTextInsertar, directorPeliculaTextInsertar;
	private JTextField codigoPeliculaTextModificar, tituloPeliculaTextModificar, annoPeliculaTextModificar,
			calificacionPeliculaTextModificar, temaPeliculaTextModificar, directorPeliculaTextModificar;
	private JTextField codigoPeliculaTextEliminar;

	private JTable tablaPelicula;
	private DefaultTableModel modTablaPelicula;
	private String[] columnasPelicula = { "Codigo de película", "Título de la película", "Año de estreno",
			"Calificacion de edad", "Tema y género", "Director" };
	private JButton rePagPelicula, avPagPelicula, actualizarPelicula;

	// Elementos del panel director

	private JPanel panelInsertarDirector, panelModificarDirector, panelBorrarDirector, panelConsultarDirector,
			panelNavegacionDirector;
	private JButton confirmarInsertarDirector, confirmarModificarDirector, confirmarBorrarDirector;
	private JScrollPane panelTablaDirector;

	private JLabel[] labelInsertarDirector, labelModificarDirector;
	private JLabel labelBorrarDirector;

	private JTextField idDirectorTextInsertar, nombreDirectorTextInsertar;
	private JTextField idDirectorTextModificar, nombreDirectorTextModificar;
	private JTextField idDirectorTextEliminar;

	private JTable tablaDirector;
	private DefaultTableModel modTablaDirector;
	private String[] columnasDirector = { "ID de Director", "Nombre" };
	private JButton rePagDirector, avPagDirector, actualizarDirector;

	public VentanaFormulario(String nombre, int tamanoX, int tamanoY) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(450, 230));

		preparar();
		anadir();

		setTitle(nombre);
		setSize(tamanoX, tamanoY);
		setResizable(true);

	}

	public void hacerVisible(Boolean estado) {
		setVisible(estado);
	}

	private void anadir() {

		// Barra de menús

		menuPelicula.add(itemInsertarPelicula);
		menuPelicula.add(itemModificarPelicula);
		menuPelicula.add(itemBorrarPelicula);
		menuPelicula.add(itemConsultarPelicula);

		menuDirector.add(itemInsertarDirector);
		menuDirector.add(itemModificarDirector);
		menuDirector.add(itemBorrarDirector);
		menuDirector.add(itemConsultarDirector);

		barraMenu.add(menuPelicula);
		barraMenu.add(menuDirector);
		barraMenu.add(salir);

		itemInsertarPelicula.addActionListener(this);
//		itemInsertarPelicula.setMnemonic(KeyEvent.VK_1);
		itemModificarPelicula.addActionListener(this);
//		itemModificarDirector.setMnemonic(KeyEvent.VK_2);
		itemBorrarPelicula.addActionListener(this);
//		itemBorrarPelicula.setMnemonic(KeyEvent.VK_3);
		itemConsultarPelicula.addActionListener(this);
//		itemConsultarPelicula.setMnemonic(KeyEvent.VK_4);

		itemInsertarDirector.addActionListener(this);
		itemConsultarDirector.addActionListener(this);
		itemModificarDirector.addActionListener(this);
		itemBorrarDirector.addActionListener(this);
		
		//Listener que se añade y utiliza en el controlador
//		salir.addActionListener(this);

		// titulo de la ventana pelicula

		panelTitulo.add(tituloPanel);

		// Añadir el formulario de insertar/modificar pelicula a su panel

		gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[0], gc);
		panelModificarPelicula.add(labelModificarPelicula[0], gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(codigoPeliculaTextInsertar, gc);
		panelModificarPelicula.add(codigoPeliculaTextModificar, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[1], gc);
		panelModificarPelicula.add(labelModificarPelicula[1], gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(tituloPeliculaTextInsertar, gc);
		panelModificarPelicula.add(tituloPeliculaTextModificar, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[2], gc);
		panelModificarPelicula.add(labelModificarPelicula[2], gc);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(annoPeliculaTextInsertar, gc);
		panelModificarPelicula.add(annoPeliculaTextModificar, gc);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[3], gc);
		panelModificarPelicula.add(labelModificarPelicula[3], gc);

		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(calificacionPeliculaTextInsertar, gc);
		panelModificarPelicula.add(calificacionPeliculaTextModificar, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[4], gc);
		panelModificarPelicula.add(labelModificarPelicula[4], gc);

		gc.gridx = 1;
		gc.gridy = 4;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(temaPeliculaTextInsertar, gc);
		panelModificarPelicula.add(temaPeliculaTextModificar, gc);

		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridwidth = 1;
		panelInsertarPelicula.add(labelInsertarPelicula[5], gc);
		panelModificarPelicula.add(labelModificarPelicula[5], gc);

		gc.gridx = 1;
		gc.gridy = 5;
		gc.gridwidth = 2;
		panelInsertarPelicula.add(directorPeliculaTextInsertar, gc);
		panelModificarPelicula.add(directorPeliculaTextModificar, gc);

		gc.gridx = 1;
		gc.gridy = 6;
		gc.gridwidth = 3;
		panelInsertarPelicula.add(confirmarInsertarPelicula, gc);
		panelModificarPelicula.add(confirmarModificarPelicula, gc);

		// Estos listener se añaden y utillizan en el controlador
//		confirmarInsertarPelicula.addActionListener(this);
//		confirmarModificarPelicula.addActionListener(this);

		// Añadir borrar pelicula al panel

		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		panelBorrarPelicula.add(labelBorrarPelicula, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 2;
		panelBorrarPelicula.add(codigoPeliculaTextEliminar, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 3;
		panelBorrarPelicula.add(confirmarBorrarPelicula, gc);

		// Listener que se añade y utiliza en el controlador
//		confirmarBorrarPelicula.addActionListener(this);

		// Panel consulta con la tabla de peliculas y sus botones de navegacion

		panelTablaPelicula.setViewportView(tablaPelicula);

		panelNavegacionPelicula.add(rePagPelicula);
		panelNavegacionPelicula.add(actualizarPelicula);
		panelNavegacionPelicula.add(avPagPelicula);

		panelConsultarPelicula.add(panelTablaPelicula, BorderLayout.CENTER);
		panelConsultarPelicula.add(panelNavegacionPelicula, BorderLayout.SOUTH);

		// Listener que se añade y utiliza en el controlador
//		rePagPelicula.addActionListener(this);
//		actualizarPelicula.addActionListener(this);
//		avPagPelicula.addActionListener(this);

		// Añadir los paneles de operaciones de pelicula al panel con CardLayout

		formularios.add(panelInsertarPelicula, "inpeli");
		formularios.add(panelModificarPelicula, "modpeli");
		formularios.add(panelBorrarPelicula, "borpeli");
		formularios.add(panelConsultarPelicula, "conpeli");
		
		// Añadir los elementos de director
		
		gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		panelInsertarDirector.add(labelInsertarDirector[0], gc);
		panelModificarDirector.add(labelModificarDirector[0], gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 2;
		panelInsertarDirector.add(idDirectorTextInsertar, gc);
		panelModificarDirector.add(idDirectorTextModificar, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		panelInsertarDirector.add(labelInsertarDirector[1], gc);
		panelModificarDirector.add(labelModificarDirector[1], gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		panelInsertarDirector.add(nombreDirectorTextInsertar, gc);
		panelModificarDirector.add(nombreDirectorTextModificar, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 3;
		panelInsertarDirector.add(confirmarInsertarDirector, gc);
		panelModificarDirector.add(confirmarModificarDirector, gc);
		
		// Borrar director
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		panelBorrarDirector.add(labelBorrarDirector, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 2;
		panelBorrarDirector.add(idDirectorTextEliminar, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 3;
		panelBorrarDirector.add(confirmarBorrarDirector, gc);
		
		// COnsultar director
		
		panelTablaDirector.setViewportView(tablaDirector);
		
		panelNavegacionDirector.add(rePagDirector);
		panelNavegacionDirector.add(actualizarDirector);
		panelNavegacionDirector.add(avPagDirector);
		
		panelConsultarDirector.add(panelTablaDirector, BorderLayout.CENTER);
		panelConsultarDirector.add(panelNavegacionDirector, BorderLayout.SOUTH);
		
		formularios.add(panelInsertarDirector, "indirector");
		formularios.add(panelModificarDirector, "moddirector");
		formularios.add(panelBorrarDirector, "bordirector");
		formularios.add(panelConsultarDirector, "condirector");

		// Añade todo a la ventana
		
		setJMenuBar(barraMenu);
		add(tituloPanel, BorderLayout.NORTH);
		add(formularios, BorderLayout.CENTER);

	}

	private void preparar() {

		barraMenu = new JMenuBar();
		
		panelTitulo= new JPanel(new FlowLayout(FlowLayout.CENTER)); // Contiene un label orientativo

		menuPelicula = new JMenu("Peliculas");
		itemInsertarPelicula = new JMenuItem("Añadir");
		itemModificarPelicula = new JMenuItem("Modificar");
		itemBorrarPelicula = new JMenuItem("Borrar");
		itemConsultarPelicula = new JMenuItem("Listar");

		menuDirector = new JMenu("Directores");
		itemInsertarDirector = new JMenuItem("Añadir");
		itemModificarDirector = new JMenuItem("Modificar");
		itemBorrarDirector = new JMenuItem("Borrar");
		itemConsultarDirector = new JMenuItem("Listar");

		salir = new JMenuItem("Salir");
		
		// Elementos de pelicula

		formularios = new JPanel(new CardLayout()); // Contiene paneles insertar etc

		panelInsertarPelicula = new JPanel(new GridBagLayout());
		panelModificarPelicula = new JPanel(new GridBagLayout());
		panelBorrarPelicula = new JPanel(new GridBagLayout());
		panelConsultarPelicula = new JPanel(new BorderLayout());
		panelTablaPelicula = new JScrollPane(); // Aqui va el JTable de pelicula

		tituloPanel= new JLabel("Insertar película"); //La operacion que el usuario esta haciendo

		panelNavegacionPelicula = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Contiene botones de navegacion

		confirmarInsertarPelicula = new JButton("Insertar película");
		confirmarModificarPelicula = new JButton("Modificar película");
		confirmarBorrarPelicula = new JButton("Borrar película");

		labelInsertarPelicula = new JLabel[6];
		labelInsertarPelicula[0] = new JLabel("Codigo:");
		labelInsertarPelicula[1] = new JLabel("Titulo:");
		labelInsertarPelicula[2] = new JLabel("Año estreno:");
		labelInsertarPelicula[3] = new JLabel("Calificacion:");
		labelInsertarPelicula[4] = new JLabel("Tema:");
		labelInsertarPelicula[5] = new JLabel("Director:");

		labelModificarPelicula = new JLabel[6];
		labelModificarPelicula[0] = new JLabel("Codigo:");
		labelModificarPelicula[1] = new JLabel("Titulo:");
		labelModificarPelicula[2] = new JLabel("Año estreno:");
		labelModificarPelicula[3] = new JLabel("Calificacion:");
		labelModificarPelicula[4] = new JLabel("Tema:");
		labelModificarPelicula[5] = new JLabel("Director:");

		labelBorrarPelicula = new JLabel("Codigo*:");

		codigoPeliculaTextInsertar = new JTextField(25);
		tituloPeliculaTextInsertar = new JTextField(25);
		annoPeliculaTextInsertar = new JTextField(25);
		calificacionPeliculaTextInsertar = new JTextField(25);
		temaPeliculaTextInsertar = new JTextField(25);
		directorPeliculaTextInsertar = new JTextField(25);

		codigoPeliculaTextModificar = new JTextField(25);
		tituloPeliculaTextModificar = new JTextField(25);
		annoPeliculaTextModificar = new JTextField(25);
		calificacionPeliculaTextModificar = new JTextField(25);
		temaPeliculaTextModificar = new JTextField(25);
		directorPeliculaTextModificar = new JTextField(25);

		codigoPeliculaTextEliminar = new JTextField(25);

		modTablaPelicula = new DefaultTableModel(columnasPelicula, 0);
		tablaPelicula = new JTable(modTablaPelicula);

		rePagPelicula = new JButton("Anterior");
		actualizarPelicula = new JButton("Actualizar");
		avPagPelicula = new JButton("Siguiente");

		// Elementos de director

		panelConsultarDirector = new JPanel(new BorderLayout());
		panelTablaDirector = new JScrollPane(); // Aqui va el JTable de director
		
		panelNavegacionDirector = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panelInsertarDirector = new JPanel(new GridBagLayout());
		panelModificarDirector = new JPanel(new GridBagLayout());
		panelBorrarDirector = new JPanel(new GridBagLayout());

		confirmarInsertarDirector = new JButton("Insertar director");
		confirmarModificarDirector = new JButton("Modificar director");
		confirmarBorrarDirector = new JButton("Borrar director");

		labelInsertarDirector = new JLabel[2];
		labelInsertarDirector[0] = new JLabel("ID del director:");
		labelInsertarDirector[1] = new JLabel("Nombre del director:");

		labelModificarDirector = new JLabel[2];
		labelModificarDirector[0] = new JLabel("ID del director*:");
		labelModificarDirector[1] = new JLabel("Nombre del director:");

		labelBorrarDirector = new JLabel("ID del director*:");

		idDirectorTextInsertar = new JTextField(25);
		nombreDirectorTextInsertar = new JTextField(25);

		idDirectorTextModificar = new JTextField(25);
		nombreDirectorTextModificar = new JTextField(25);

		idDirectorTextEliminar = new JTextField(25);

		modTablaDirector = new DefaultTableModel(columnasDirector, 0);
		tablaDirector = new JTable(modTablaDirector);

		rePagDirector = new JButton("Anterior");
		actualizarDirector = new JButton("Actualizar");
		avPagDirector = new JButton("Siguiente");

	}
	
	public void mostrarMensaje(int tipo, String cuerpo, String titulo) {

		JOptionPane.showMessageDialog(null, cuerpo, titulo, tipo);

	}

	
	/*
	 * La unica funcionalidad de los eventos en la ventana es proporcionar al usuario movilidad
	 * básica. No se realiza ninuna operacion con la información que el usuario inserte, ya que eso
	 * es tarea del controlador
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == itemInsertarPelicula) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "inpeli");

			tituloPanel.setText("Insertar película");

		}

		if (e.getSource() == itemModificarPelicula) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "modpeli");

			tituloPanel.setText("Modificar película");

		}

		if (e.getSource() == itemBorrarPelicula) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "borpeli");

			tituloPanel.setText("Borrar película");

		}

		if (e.getSource() == itemConsultarPelicula) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "conpeli");

			tituloPanel.setText("Listado de películas");

		}
		
		// Director
		
		if (e.getSource() == itemInsertarDirector) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "indirector");

			tituloPanel.setText("Modificar película");

		}

		if (e.getSource() == itemModificarDirector) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "moddirector");

			tituloPanel.setText("Modificar director");

		}

		if (e.getSource() == itemBorrarDirector) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "bordirector");

			tituloPanel.setText("Borrar director");

		}

		if (e.getSource() == itemConsultarDirector) {

			CardLayout formulario = (CardLayout) (formularios.getLayout());

			formulario.show(formularios, "condirector");

			tituloPanel.setText("Listado de directores");

		}

	}

	public JMenuItem getSalir() {
		return salir;
	}
	
	// Getters de botones de navegacion de pelicula

	public JButton getConfirmarInsertarPelicula() {
		return confirmarInsertarPelicula;
	}

	public JButton getConfirmarBorrarPelicula() {
		return confirmarBorrarPelicula;
	}
	
	public JButton getConfirmarModificarPelicula() {
		return confirmarModificarPelicula;
	}
	
	// Getters y setters de los campos de alumno
	
	public String getCodigoPeliculaInsertar() {
		return codigoPeliculaTextInsertar.getText();
	}

	public void setCodigoPeliculaInsertar(String texto) {
		codigoPeliculaTextInsertar.setText(texto);
	}

	public String getTituloPeliculaInsertar() {
		return tituloPeliculaTextInsertar.getText();
	}

	public void setTituloPeliculaInsertar(String texto) {
		tituloPeliculaTextInsertar.setText(texto);
	}

	public String getAnnoPeliculaInsertar() {
		return annoPeliculaTextInsertar.getText();
	}

	public void setAnnoPeliculaTextInsertar(String texto) {
		annoPeliculaTextInsertar.setText(texto);
	}

	public String getCalificacionPeliculaInsertar() {
		return calificacionPeliculaTextInsertar.getText();
	}

	public void setCalificacionPeliculaInsertar(String texto) {
		calificacionPeliculaTextInsertar.setText(texto);
	}

	public String getTemaPeliculaInsertar() {
		return temaPeliculaTextInsertar.getText();
	}

	public void setTemaPeliculaInsertar(String texto) {
		temaPeliculaTextInsertar.setText(texto);
	}

	public String getDirectorPeliculaInsertar() {
		return directorPeliculaTextInsertar.getText();
	}

	public void setDirectorPeliculaInsertar(String texto) {
		directorPeliculaTextInsertar.setText(texto);
	}

	public String getCodigoPeliculaModificar() {
		return codigoPeliculaTextModificar.getText();
	}

	public void setCodigoPeliculaModificar(String texto) {
		codigoPeliculaTextModificar.setText(texto);
	}

	public String getTituloPeliculaModificar() {
		return tituloPeliculaTextModificar.getText();
	}

	public void setTituloPeliculaModificar(String texto) {
		tituloPeliculaTextModificar.setText(texto);
	}

	public String getAnnoPeliculaModificar() {
		return annoPeliculaTextModificar.getText();
	}

	public void setAnnoPeliculaModificar(String texto) {
		annoPeliculaTextModificar.setText(texto);
	}

	public String getCalificacionPeliculaModificar() {
		return calificacionPeliculaTextModificar.getText();
	}

	public void setCalificacionPeliculaModificar(String texto) {
		calificacionPeliculaTextModificar.setText(texto);
	}

	public String getTemaPeliculaModificar() {
		return temaPeliculaTextModificar.getText();
	}

	public void setTemaPeliculaModificar(String texto) {
		temaPeliculaTextModificar.setText(texto);
	}

	public String getDirectorPeliculaModificar() {
		return directorPeliculaTextModificar.getText();
	}

	public void setDirectorPeliculaTextModificar(String texto) {
		directorPeliculaTextModificar.setText(texto);
	}

	public String getCodigoPeliculaEliminar() {
		return codigoPeliculaTextEliminar.getText();
	}

	public void setCodigoPeliculaEliminar(String texto) {
		codigoPeliculaTextEliminar.setText(texto);
	}

	// Getters de botones de navegacion de pelicula
	
	public JButton getRePagPelicula() {
		return rePagPelicula;
	}

	public JButton getAvPagPelicula() {
		return avPagPelicula;
	}

	public JButton getActualizarPelicula() {
		return actualizarPelicula;
	}
	
	// getters de botones de operaciones de Director

	public JButton getConfirmarInsertarDirector() {
		return confirmarInsertarDirector;
	}

	public JButton getConfirmarModificarDirector() {
		return confirmarModificarDirector;
	}

	public JButton getConfirmarBorrarDirector() {
		return confirmarBorrarDirector;
	}
	
	// Getters y setters de los campos de director

	public String getIdDirectorInsertar() {
		return idDirectorTextInsertar.getText();
	}

	public void setIdDirectorInsertar(String texto) {
		idDirectorTextInsertar.setText(texto);
	}

	public String getNombreDirectorInsertar() {
		return nombreDirectorTextInsertar.getText();
	}

	public void setNombreDirectorInsertar(String texto) {
		nombreDirectorTextInsertar.setText(texto);
	}

	public String getIdDirectorModificar() {
		return idDirectorTextModificar.getText();
	}

	public void setIdDirectorModificar(String texto) {
		idDirectorTextModificar.setText(texto);
	}

	public String getNombreDirectorModificar() {
		return nombreDirectorTextModificar.getText();
	}

	public void setNombreDirectorModificar(String texto) {
		nombreDirectorTextModificar.setText(texto);
	}

	public String getIdDirectorEliminar() {
		return idDirectorTextEliminar.getText();
	}

	public void setIdDirectorEliminar(String texto) {
		idDirectorTextEliminar.setText(texto);
	}
	
	// Getters de botones de navegacion director

	public JButton getRePagDirector() {
		return rePagDirector;
	}

	public JButton getAvPagDirector() {
		return avPagDirector;
	}

	public JButton getActualizarDirector() {
		return actualizarDirector;
	}
	
	// Actualizar las tablas
	
	public void setTablaPelicula(String[][] datos) {

		modTablaPelicula.setRowCount(0);

		for (int i = 0; i < datos.length - 2; i++) {

			modTablaPelicula.addRow(datos[i]);

		}

		tablaPelicula.revalidate();

	}
	
	public void setTablaDirector (String[][] datos) {

		modTablaDirector.setRowCount(0);

		for (int i = 0; i < datos.length - 1; i++) {

			modTablaDirector.addRow(datos[i]);

		}

		tablaDirector.revalidate();

	}
	
	
}
