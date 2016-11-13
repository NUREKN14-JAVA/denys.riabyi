package com.nixsolutions.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel implements ActionListener {

	protected MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField firstNameField;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private Color bgColor;

	public AddPanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);

	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Отмена");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ок");
			okButton.setName("okButton"); 
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			addLabeledField(fieldPanel, "Имя", getFirstNameField());
			addLabeledField(fieldPanel, "Фамилия", getLastNameField());
			addLabeledField(fieldPanel, "Дата рождения", getDateOfBirthField());
		}
		return fieldPanel;
	}

	protected JTextField getDateOfBirthField() {
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");
		}
		return dateOfBirthField;
	}

	protected JTextField getLastNameField() {
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	protected JTextField getFirstNameField() {
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}

	public void actionPerformed(ActionEvent e) {

	}

}