package gui;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ConfigGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String fileName;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigGUI frame = new ConfigGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfigGUI() {
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		panel.setBounds(10, 11, 254, 345);
		contentPane.add(panel);
		
		JLabel lblFichierDemandes = new JLabel("Fichier demandes :");
		
		JButton btnSelect = new JButton("SELECT");
		
		 btnSelect.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          fileName = selectedFile.getName();
		          System.out.println(fileName);
		        }
		      }
		    });
		
		JLabel lblNombreEtages =  new JLabel("Nombre des étages :");
		
		JTextField txtNombreEtages = new JTextField(2);
		
		
		JButton btnStart = new JButton("START");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(45)
							.addComponent(lblFichierDemandes)
							.addGap(5)
							.addComponent(btnSelect))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNombreEtages)
							.addGap(18)
							.addComponent(txtNombreEtages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(94)
							.addComponent(btnStart)))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblFichierDemandes))
						.addComponent(btnSelect))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreEtages)
						.addComponent(txtNombreEtages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnStart)
					.addGap(247))
		);
		panel.setLayout(gl_panel);
		
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(268, 11, 405, 345);
		contentPane.add(panel_1);
		
		JLabel lblEtageActuel = new JLabel("Etage actuel : ");
		panel_1.add(lblEtageActuel);
		
		JLabel lblNewLabel = new JLabel("Etage");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.CYAN);
		panel_1.add(lblNewLabel);
	}
}
