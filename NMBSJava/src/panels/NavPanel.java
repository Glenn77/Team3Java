package panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.LangageHandler;
@SuppressWarnings("serial")
public class NavPanel extends JPanel {
	
	private JButton btnRouteZoek;
	private JButton btnTreinZoek;
	private JButton btnStationZoek;
	private JButton btnVerlorenVoorwerpenZoek;
	private JButton btnbtnVerlorenVoorwerpenVoegToe;
	private JButton btnBiljetKoop;
	private JButton btnAbonnementKoop;
	private JButton btnAbonnementVerlengen;
	private JButton btnPrijzenAanpassen;
	private JButton btnVoegMedewerker;
	public JButton getBtnVoegMedewerker() {
		return btnVoegMedewerker;
	}

	private JButton btnLogout;

	public NavPanel() {
		initialize();
		createComponents();
		addComponents();
	}

	private void initialize() {
		this.setLayout(new GridLayout(11, 1, 5, 5));
	}

	private void createComponents() {

		btnRouteZoek = new JButton();
		LangageHandler.chooseLangageBtn(btnRouteZoek,"zoekRoute");
		btnRouteZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnTreinZoek = new JButton();
		LangageHandler.chooseLangageBtn(btnTreinZoek, "zoekTrein");
		btnTreinZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnStationZoek = new JButton();
		LangageHandler.chooseLangageBtn(btnStationZoek, "zoekStation");
		btnStationZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnVerlorenVoorwerpenZoek = new JButton();
		LangageHandler.chooseLangageBtn(btnVerlorenVoorwerpenZoek, "zoekVoorwerp");
		btnVerlorenVoorwerpenZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnbtnVerlorenVoorwerpenVoegToe = new JButton();
		LangageHandler.chooseLangageBtn(btnbtnVerlorenVoorwerpenVoegToe, "voegVoorwerp");
		btnbtnVerlorenVoorwerpenVoegToe.setHorizontalAlignment(SwingConstants.LEFT);

		btnBiljetKoop = new JButton();
		LangageHandler.chooseLangageBtn(btnBiljetKoop, "koopBiljet");
		btnBiljetKoop.setHorizontalAlignment(SwingConstants.LEFT);
		btnAbonnementKoop = new JButton("Koop abonnement");
		LangageHandler.chooseLangageBtn(btnAbonnementKoop, "koopAbonnement");
		
		btnAbonnementKoop.setHorizontalAlignment(SwingConstants.LEFT);

		btnAbonnementVerlengen = new JButton();
		LangageHandler.chooseLangageBtn(btnAbonnementVerlengen, "verlengAbonnement");
		btnAbonnementVerlengen.setHorizontalAlignment(SwingConstants.LEFT);

		btnPrijzenAanpassen = new JButton();
		LangageHandler.chooseLangageBtn(btnPrijzenAanpassen, "pasPrijzen");
		btnPrijzenAanpassen.setHorizontalAlignment(SwingConstants.LEFT);

		btnVoegMedewerker = new JButton();
		LangageHandler.chooseLangageBtn(btnVoegMedewerker, "voegMedewerker");
		btnVoegMedewerker.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnLogout = new JButton();
		LangageHandler.chooseLangageBtn(btnLogout, "Uitloggen");
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
	}

	private void addComponents() {
		this.add(btnRouteZoek);
		this.add(btnTreinZoek);
		this.add(btnStationZoek);
		this.add(btnBiljetKoop);
		this.add(btnAbonnementKoop);
		this.add(btnAbonnementVerlengen);
		this.add(btnVerlorenVoorwerpenZoek);
		this.add(btnbtnVerlorenVoorwerpenVoegToe);
		this.add(btnPrijzenAanpassen);
		this.add(btnVoegMedewerker);
		this.add(btnLogout);
	}

	public JButton getBtnRouteZoek() {
		return btnRouteZoek;
	}

	public JButton getBtnTreinZoek() {
		return btnTreinZoek;
	}

	public JButton getBtnStationZoek() {
		return btnStationZoek;
	}

	public JButton getBtnVerlorenVoorwerpenZoek() {
		return btnVerlorenVoorwerpenZoek;
	}

	public JButton getBtnbtnVerlorenVoorwerpenVoegToe() {
		return btnbtnVerlorenVoorwerpenVoegToe;
	}

	public JButton getBtnBiljetKoop() {
		return btnBiljetKoop;
	}

	public JButton getBtnAbonnementKoop() {
		return btnAbonnementKoop;
	}

	public JButton getBtnAbonnementVerlengen() {
		return btnAbonnementVerlengen;
	}

	public JButton getBtnPrijzenAanpassen() {
		return btnPrijzenAanpassen;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
}
