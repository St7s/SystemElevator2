package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class preview extends JFrame {
	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel lblFichierDemandes;
	private JButton btnSelectFile;
	private File selectedFile;
	private JLabel fichierChoisi;
	private JLabel lblNumeroEtageMin;
	private JLabel lblNumeroEtageMax;
	private JTextField txtEtageMin;
	private JTextField txtEtageMax;
	private JButton btnStart;
	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	public preview() {
		//Initialisation du container
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 689, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		

		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(176, 196, 222));
		leftPanel.setBounds(10, 11, 254, 345);
		
		
		rightPanel = new JPanel();
		rightPanel.setBackground(new Color(105, 105, 105));
		rightPanel.setBounds(268, 11, 405, 345);
		
		contentPane.add(leftPanel);
		contentPane.add(rightPanel);

		this.setContentPane(contentPane);

		//Initialize components
		lblFichierDemandes = new JLabel("Fichier demandes :");
		txtEtageMin = new JTextField(2);
		txtEtageMax = new JTextField(2);
		btnStart = new JButton("DEMARER");
		btnSelectFile = new JButton("SELECT");
		lblNumeroEtageMin =  new JLabel("Numero etage min : ");
		fichierChoisi = new JLabel("Aucun fichier choisi...");
		lblNumeroEtageMax = new JLabel("Numero etage min : ");

		
		fichierChoisi.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		GroupLayout gl_panel = new GroupLayout(leftPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNumeroEtageMax, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtEtageMax, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFichierDemandes)
							.addGap(5)
							.addComponent(btnSelectFile))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNumeroEtageMin)
							.addGap(18)
							.addComponent(txtEtageMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(fichierChoisi, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(81)
					.addComponent(btnStart)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblFichierDemandes))
						.addComponent(btnSelectFile))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fichierChoisi)
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumeroEtageMin)
						.addComponent(txtEtageMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumeroEtageMax)
						.addComponent(txtEtageMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addComponent(btnStart)
					.addGap(108))
		);
		leftPanel.setLayout(gl_panel);
		/*
		 * =========================================================== 
		 * Evenements
		 * ===========================================================
		 */
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					fichierChoisi.setText(selectedFile.toString());
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					preview frame = new preview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

