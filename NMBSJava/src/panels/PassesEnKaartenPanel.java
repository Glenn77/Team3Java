package panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.*;
import javax.swing.JTextField;
import java.util.Properties;
import org.jdatepicker.impl.*;
import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

public class PassesEnKaartenPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblStartDatum;
	private JLabel lblKlasse;
	private JLabel lblPassType;
	private JLabel lblRes;
	

	private JLabel lblPrint;
	
	private JButton btnPrint;
	
	private JRadioButton rdbEersteKlasse;
	private JRadioButton rdbTweedeKlasse;
	private ButtonGroup grpKlasses;
	
	private JDatePickerImpl dteStartDatum;
	
	private JComboBox cbxPassType;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PassesEnKaartenPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();		
		
		lblTitle = new JLabel("Passes en Kaarten");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblStartDatum = new JLabel("Startdatum: ");
		lblKlasse = new JLabel("Klasse: ");
		rdbEersteKlasse = new JRadioButton("1e Klas");
		rdbEersteKlasse.setMnemonic(1);
		rdbTweedeKlasse = new JRadioButton("2e Klas");
		rdbTweedeKlasse.setMnemonic(2);
		rdbTweedeKlasse.setSelected(true);
		lblPassType = new JLabel("Type pass: ");
		btnPrint = new JButton("PRINT");
		lblPrint = new JLabel("�0");
		lblRes = new JLabel();
		
		grpKlasses = new ButtonGroup();
		grpKlasses.add(rdbEersteKlasse);
		grpKlasses.add(rdbTweedeKlasse);
		
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(new UtilDateModel(), properties);
		dteStartDatum = new JDatePickerImpl(datePanel2, new GUIDateFormat());
		dteStartDatum.getJFormattedTextField().setText(GUIDateFormat.getDate());
		
		String[] passType = {"Go Pass 10", "Key Card", "Rail Pass"};
		cbxPassType = new JComboBox(passType);
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 2;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(lblTitle,c);
		c.gridwidth--;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(lblStartDatum,c);
		c.ipady = 0;
		c.gridx++;
		this.add(dteStartDatum,c);
		c.gridx++;
		this.add(lblPassType,c);
		c.gridx++;
		this.add(cbxPassType,c);
		
		c.gridx = 0;
		c.gridy++;
		this.add(lblKlasse,c);
		c.gridx++;
		c.weightx = 0.5;
		this.add(rdbEersteKlasse,c);
		c.gridx++;
		this.add(rdbTweedeKlasse,c);
		
		c.gridx = 0;
		c.gridy++;
		this.add(btnPrint,c);
		c.gridx++;
		this.add(lblPrint,c);
		c.gridy++;
		c.gridx = 0;
		this.add(lblRes, c);
	}
	
	public JLabel getLblRes() {
		return lblRes;
	}
	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblStartDatum() {
		return lblStartDatum;
	}

	public JLabel getLblKlasse() {
		return lblKlasse;
	}

	public JLabel getLblPassType() {
		return lblPassType;
	}

	public JLabel getLblPrint() {
		return lblPrint;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JRadioButton getRdbEersteKlasse() {
		return rdbEersteKlasse;
	}

	public JRadioButton getRdbTweedeKlasse() {
		return rdbTweedeKlasse;
	}

	public ButtonGroup getGrpKlasses() {
		return grpKlasses;
	}

	public JDatePickerImpl getDteStartDatum() {
		return dteStartDatum;
	}

	public JComboBox getCbxPassType() {
		return cbxPassType;
	}

}
