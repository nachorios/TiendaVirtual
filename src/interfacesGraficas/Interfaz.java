package interfacesGraficas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JMenuItem;

import clases.Categoria.*;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;

public class Interfaz {

	private JFrame frame;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JList categorias;
	private JEditorPane editorPane;
	private JTextField textFieldCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.resize(600, 500);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreaTuProducto = new JLabel("Crea Tu Producto");
		lblCreaTuProducto.setBounds(231, 11, 158, 14);
		frame.getContentPane().add(lblCreaTuProducto);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(113, 56, 86, 20);
		frame.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(57, 59, 56, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(57, 87, 46, 14);
		frame.getContentPane().add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(113, 87, 86, 20);
		frame.getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setBounds(57, 118, 69, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JPanel laminaDeCategorias = new JPanel();
		JPanel laminaDeTexto = new JPanel();
		
		frame.getContentPane().add(laminaDeCategorias);
		frame.getContentPane().add(laminaDeTexto);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(241, 403, 89, 23);
		frame.getContentPane().add(btnCrear);
		btnCrear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Nombre: "+ textFieldNombre.getText()
								  +"\nPrecio: "+ textFieldPrecio.getText()
								  +"\nCantidad: "+ textFieldCantidad.getText()
								  +"\nCategoria: "+ categorias.getSelectedValue()
								  +"\nDescripcion: "+ editorPane.getText()
								  +"\nCaracteristica: ");
			}
			
		});
		
		categorias = new JList(CategoriaType.values());
		JLabel rotulo = new JLabel("Categoria seleccionada: ");
		
		categorias.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CategoriaType categoria = (CategoriaType) categorias.getSelectedValue();
				
				StringBuilder sb = new StringBuilder("Categoria seleccionada: ");
				sb.append(categoria);
				rotulo.setText(sb.toString());
			}
		});
		categorias.setVisibleRowCount(4);
		
		JScrollPane desplazamientoCategorias = new JScrollPane(categorias);
		desplazamientoCategorias.setBounds(123, 118, 109, 83);
		frame.getContentPane().add(desplazamientoCategorias);
		
		rotulo.setBounds(57, 212, 244, 29);
		frame.getContentPane().add(rotulo);
		
		JLabel lblPrecio_1 = new JLabel("Producto ID:");
		lblPrecio_1.setBounds(343, 59, 77, 14);
		frame.getContentPane().add(lblPrecio_1);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(343, 87, 56, 14);
		frame.getContentPane().add(lblCantidad);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(57, 254, 86, 14);
		frame.getContentPane().add(lblDescripcion);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(57, 279, 461, 113);
		frame.getContentPane().add(editorPane);
		
		JScrollPane desplazamientoDescripcion = new JScrollPane(editorPane);
		desplazamientoDescripcion.setBounds(113, 282, 348, 110);
		frame.getContentPane().add(desplazamientoDescripcion);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(409, 84, 86, 20);
		frame.getContentPane().add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
		lblCaracteristicas.setBounds(343, 134, 131, 14);
		frame.getContentPane().add(lblCaracteristicas);
		
		JLabel productoIdLabel = new JLabel("#0001");
		productoIdLabel.setBounds(415, 59, 46, 14);
		frame.getContentPane().add(productoIdLabel);
	}
}
