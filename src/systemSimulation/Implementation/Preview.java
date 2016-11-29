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

import systemAscenseur.Interface.ObserverArret;
import systemAscenseur.Interface.ObserverNiveau;
import systemAscenseur.Interface.ObserverSurcharge;

public class Preview extends JFrame implements ObserverSurcharge,ObserverNiveau, ObserverArret{
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

	private static Configurator configurateur;
	private JLabel lblCoeff;
	private JTextField txtCoeff;
	private JLabel lblPoidsMax;
	private JTextField txtPoidsMax;
	private JLabel lblTempsExec;
	private JTextField txtTempsExec;
	private JLabel lblEtat;
	private JLabel lblEtatAscenseur;
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
		lblEtageActuel.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtageActuel.setFont(new Font("Comic Sans MS", lblEtageActuel.getFont().getStyle() | Font.BOLD, 17));
		lblEtageActuel.setForeground(new Color(255, 255, 255));

		lblNiveau = new JLabel("0");
		lblNiveau.setForeground(new Color(255, 215, 0));
		lblNiveau.setFont(new Font("Comic Sans MS", lblNiveau.getFont().getStyle() | Font.BOLD, 24));
		lblNiveau.setHorizontalAlignment(SwingConstants.CENTER);

		lblSurcharge = new JLabel("");
		lblSurcharge.setHorizontalAlignment(SwingConstants.CENTER);
		lblSurcharge.setFont(new Font("Comic Sans MS", lblSurcharge.getFont().getStyle() | Font.BOLD, 22));
		lblSurcharge.setForeground(new Color(255, 0, 0));

		lblEtat = new JLabel(" ETAT ASCENSEUR");
		lblEtat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtat.setFont(new Font("Comic Sans MS", lblEtat.getFont().getStyle() | Font.BOLD, 15));
		lblEtat.setForeground(Color.WHITE);

		lblEtatAscenseur = new JLabel("...");
		lblEtatAscenseur.setFont(new Font("Comic Sans MS", lblEtatAscenseur.getFont().getStyle() | Font.BOLD, 24));
		lblEtatAscenseur.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtatAscenseur.setForeground(new Color(255, 215, 0));

		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblSurcharge, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblEtat, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNiveau, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
												.addComponent(lblEtageActuel, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
								.addGroup(gl_rightPanel.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblEtatAscenseur, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_rightPanel.setVerticalGroup(
				gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
						.addGap(5)
						.addComponent(lblEtageActuel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNiveau)
						.addGap(41)
						.addComponent(lblEtat, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblEtatAscenseur, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
						.addComponent(lblSurcharge, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(103))
				);
		rightPanel.setLayout(gl_rightPanel);

		this.setContentPane(contentPane);

		//Initialize components
		lblFichierDemandes = new JLabel("Selectioner un fichier flow :");
		txtEtageMin = new JTextField("0");
		txtEtageMax = new JTextField("10");
		btnSelectFile = new JButton("CHOISIR");
		lblNumeroEtageMin =  new JLabel("Etage min : ");
		fichierChoisi = new JLabel("Aucun fichier choisi...");
		lblNumeroEtageMax = new JLabel("Etage max : ");

		lblVitesseMoteur = new JLabel("Vitesse Moteur (m/s) : ");

		txtVitesseMoteur = new JTextField("1");

		lblDistanceNiveaux = new JLabel("Distance entre 2 niveaux (m) : ");

		textDistanceNiveaux = new JTextField("3");

		lblCoeff = new JLabel("Coeff de temps :");

		txtCoeff = new JTextField("10");

		lblPoidsMax = new JLabel("Poids Max :");

		txtPoidsMax = new JTextField("5");

		lblTempsExec = new JLabel("Temps exec :");

		txtTempsExec = new JTextField("100000");
		btnStart = new JButton("DEMARER");

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float coef = Float.parseFloat(txtCoeff.getText());
					int distanceNiveaux = Integer.parseInt(textDistanceNiveaux.getText());
					int etageMin = Integer.parseInt(txtEtageMin.getText());
					int etageMax = Integer.parseInt(txtEtageMax.getText());
					int poidsMax = Integer.parseInt(txtPoidsMax.getText());
					long tempsExec = Long.parseLong(txtTempsExec.getText());
					float vitesse = Float.parseFloat(txtVitesseMoteur.getText());
					configurateur = new Configurator(0, coef, tempsExec, vitesse, etageMin, etageMax, poidsMax , distanceNiveaux, selectedFile.getName());
					configurateur.addObserverNiveau(preview);
					configurateur.addObserverArret(preview);
					configurateur.addObserverSurcharge(preview);
					t = new Thread(configurateur, "My Thread");
					t.start();
				} catch (Throwable e1) {e1.printStackTrace();}

			}
		});

		//Positionnement des elements genere automatiquement		
		GroupLayout gl_panel = new GroupLayout(leftPanel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblFichierDemandes)
										.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
										.addComponent(btnSelectFile))
								.addComponent(fichierChoisi, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
																		.addComponent(btnStart)
																		.addComponent(lblTempsExec, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
																.addComponent(lblPoidsMax, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblCoeff, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblDistanceNiveaux, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE))
												.addComponent(lblNumeroEtageMin, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
												.addComponent(lblNumeroEtageMax, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
												.addComponent(lblVitesseMoteur))
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.TRAILING)
														.addComponent(txtVitesseMoteur, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
														.addComponent(txtEtageMax, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
														.addComponent(txtEtageMin, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
												.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textDistanceNiveaux)
														.addComponent(txtCoeff)
														.addComponent(txtPoidsMax)
														.addComponent(txtTempsExec, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))))
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
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textDistanceNiveaux, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDistanceNiveaux))
						.addGap(8)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCoeff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCoeff))
						.addGap(8)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPoidsMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPoidsMax))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTempsExec)
								.addComponent(txtTempsExec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(48)
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
				JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					fichierChoisi.setText(selectedFile.getName());
				}
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
		this.lblEtatAscenseur.setText("");

	}
	@Override
	public void notifierArret(int niveau) {
		this.lblEtatAscenseur.setText("Arret");
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

