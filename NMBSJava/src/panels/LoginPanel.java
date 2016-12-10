package panels;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	private JLabel lblLangage;
	private JComboBox cmbLangage;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JCheckBox chAdmin;
	private JButton btnLogin;
	private JLabel lblResult;

	public LoginPanel() {
		JPanel content = new JPanel();

		
		JPanel lang = new JPanel();
		String[] langage = {"Nederlands","Fran�ais","English"};
		cmbLangage = new JComboBox(langage);
		lblLangage = new JLabel("Langage:");
		lang.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		lang.add(lblLangage);
		lang.add(cmbLangage);
		
		JPanel usrn = new JPanel();
		lblUsername = new JLabel("Username:");
		txtUsername = new JTextField();
		txtUsername.setColumns(20);
		usrn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		usrn.add(lblUsername);
		usrn.add(txtUsername);

		JPanel usrp = new JPanel();
		lblPassword = new JLabel("Password:");
		txtPassword = new JPasswordField();
		txtPassword.setColumns(20);
		usrp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		usrp.add(lblPassword);
		usrp.add(txtPassword);

		JPanel admin = new JPanel();
		admin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		chAdmin = new JCheckBox("Admin");
		admin.add(chAdmin);

		JPanel login = new JPanel();
		login.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLogin = new JButton("Login");
		login.add(btnLogin);

		lblResult = new JLabel();
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);

		content.setLayout(new GridLayout(8, 1, 5, 5));

		content.add(lang);
		content.add(new JLabel());
		content.add(new JLabel());
		content.add(usrn);
		content.add(usrp);
		content.add(admin);
		content.add(login);
		content.add(lblResult);

		this.add(content);

	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public JCheckBox getChAdmin() {
		return chAdmin;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JLabel getLblResult() {
		return lblResult;
	}

	public JComboBox getCmbLangage() {
		return cmbLangage;
	}

	public JLabel getLblLangage() {
		return lblLangage;
	}
	
	

}