package systemSimulation.Implementation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;

public class Preview extends JFrame implements ObserverSurcharge,ObserverNiveau{
	/*
	 * =========================================================== 
	 * Attributs
	 * ===========================================================
	 */
	private static Preview preview;
	private static Thread t;
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
	private JLabel lblEtageActuel;
	private JLabel lblNiveau;
	private JLabel lblSurcharge;
	private JLabel lblVitesseMoteur;
	private JTextField txtVitesseMoteur;
	private JLabel lblDistanceNiveaux;
	private JTextField textDistanceNiveaux;
	private static JLabel time;

	private static Configurator configurateur;
	/*
	 * =========================================================== 
	 * Constructeur
	 * ===========================================================
	 */
	public Preview() {
		setResizable(false);
		//Initialisation du container
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 689, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);



		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(176, 196, 222));
		leftPanel.setBounds(10, 11, 332, 345);

		rightPanel = new JPanel();
		rightPanel.setBackground(new Color(105, 105, 105));
		rightPanel.setBounds(352, 11, 311, 345);

		contentPane.add(leftPanel);
		contentPane.add(rightPanel);

		lblEtageActuel = new JLabel("ETAGE ACTUEL");
		lblEtageActuel.setFont(new Font("Comic Sans MS", lblEtageActuel.getFont().getStyle() | Font.BOLD, 17));
		lblEtageActuel.setForeground(new Color(255, 255, 255));

		lblNiveau = new JLabel("0");
		lblNiveau.setForeground(new Color(255, 215, 0));
		lblNiveau.setFont(new Font("Comic Sans MS", lblNiveau.getFont().getStyle() | Font.BOLD, 18));
		lblNiveau.setHorizontalAlignment(SwingConstants.CENTER);

		lblSurcharge = new JLabel("");
		lblSurcharge.setHorizontalAlignment(SwingConstants.CENTER);
		lblSurcharge.setFont(new Font("Comic Sans MS", lblSurcharge.getFont().getStyle() | Font.BOLD, 22));
		lblSurcharge.setForeground(new Color(255, 0, 0));

		time = new JLabel("0");
		time.setFont(new Font("Comic Sans MS", time.getFont().getStyle() & ~Font.BOLD, 15));
		time.setForeground(new Color(0, 255, 127));

		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addGap(111)
										.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNiveau, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblEtageActuel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblSurcharge, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(time, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_rightPanel.setVerticalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addGap(5)
						.addComponent(lblEtageActuel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNiveau)
						.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
						.addComponent(lblSurcharge, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(59)
						.addComponent(time, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		rightPanel.setLayout(gl_rightPanel);

		this.setContentPane(contentPane);

		//Initialize components
		lblFichierDemandes = new JLabel("Selectioner un fichier flow :");
		txtEtageMin = new JTextField("0");
		txtEtageMax = new JTextField("10");
		btnStart = new JButton("DEMARER");	
		btnSelectFile = new JButton("CHOISIR");
		lblNumeroEtageMin =  new JLabel("Etage min : ");
		fichierChoisi = new JLabel("Aucun fichier choisi...");
		lblNumeroEtageMax = new JLabel("Etage max : ");

		lblVitesseMoteur = new JLabel("Vitesse Moteur (m/s) : ");

		txtVitesseMoteur = new JTextField("1");

		lblDistanceNiveaux = new JLabel("Distance entre 2 niveaux (m) : ");

		textDistanceNiveaux = new JTextField("3");

		//Positionnement des elements genere automatiquement		
		GroupLayout gl_panel = new GroupLayout(leftPanel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(lblFichierDemandes)
														.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
														.addComponent(btnSelectFile))
												.addComponent(fichierChoisi, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNumeroEtageMin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNumeroEtageMax, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
												.addComponent(lblVitesseMoteur))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(txtEtageMax, 43, 43, 43)
														.addComponent(txtEtageMin, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
												.addComponent(txtVitesseMoteur, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblDistanceNiveaux, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
										.addComponent(textDistanceNiveaux, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(122)
										.addComponent(btnStart)))
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFichierDemandes)
								.addComponent(btnSelectFile))
						.addGap(18)
						.addComponent(fichierChoisi)
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEtageMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumeroEtageMin))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumeroEtageMax)
								.addComponent(txtEtageMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtVitesseMoteur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVitesseMoteur))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(textDistanceNiveaux, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDistanceNiveaux))
						.addGap(135)
						.addComponent(btnStart)
						.addContainerGap())
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

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					configurateur = new Configurator(0, 10, 100000, 1, 0, 10, 5, 3, "users");
					configurateur.addObserverNiveau(preview);

					t = new Thread(configurateur, "My Thread");
					t.start();
				} catch (Throwable e1) {e1.printStackTrace();}
				
			}
		});
	}

	/*
	 * =========================================================== 
	 * Methodes
	 * ===========================================================
	 */
	@Override
	public void notifierNiveau(int niveau) {
		this.lblNiveau.setText(niveau+ "");
	}


	@Override
	public void notifierSurcharge() {
		this.lblSurcharge.setText("SURCHARGE !!!!");
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) throws Throwable{
		preview = new Preview();
		preview.setVisible(true);

		
	}
}

