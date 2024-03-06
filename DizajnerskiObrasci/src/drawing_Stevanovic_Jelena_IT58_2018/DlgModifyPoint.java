package drawing_Stevanovic_Jelena_IT58_2018;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModifyPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCordX;
	private JTextField txtCordY;

	private int dialogX;
	private int dialogY;
	private Color color;
	private JButton btnLineColor;
	
	private boolean correct;
	

	public DlgModifyPoint(Point point) {
		setTitle("Modify Point");
		setBounds(100, 100, 379, 246);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCoordinateX = new JLabel("Coordinate X :");
		lblCoordinateX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCordX = new JTextField();
		txtCordX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCordX.setColumns(10);
		txtCordX.setText("" + point.getX());
		
		JLabel lblCoordinateY = new JLabel("Coordinate Y :");
		lblCoordinateY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCordY = new JTextField();
		txtCordY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCordY.setColumns(10);
		txtCordY.setText("" + point.getY());
		
		JLabel lblLineColor = new JLabel("Line color :");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		
		btnLineColor.setBackground(point.getLineColor());
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Pick a color", btnLineColor.getBackground());
				if(temp != null) {
					btnLineColor.setBackground(temp);
				}
			}
		});
	
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateX)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCordX))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateY)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCordY, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblLineColor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLineColor)))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txtCordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txtCordY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton OKButton = new JButton("OK");
				OKButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					//	correct = true;
					//	dispose();
						try {
							dialogX = Integer.parseInt(txtCordX.getText());
							dialogY = Integer.parseInt(txtCordY.getText());
							color = btnLineColor.getBackground();
							
							if(dialogX <= 0 || dialogY <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates must be positive!");
							}else {
							//	point.moveOn(dialogX, dialogY);
							//	point.setLineColor(btnLineColor.getBackground());
								
								correct = true;
								dispose();
							}
					}catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Enter coordinates");
					}
					}});
				OKButton.setActionCommand("OK");
				buttonPane.add(OKButton);
				getRootPane().setDefaultButton(OKButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}


	public boolean isCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	public JButton getBtnLineColor() {
		return btnLineColor;
	}


	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
	}


	public int getDialogX() {
		return dialogX;
	}


	public int getDialogY() {
		return dialogY;
	}


	public void setDialogX(int dialogX) {
		this.dialogX = dialogX;
	}


	public void setDialogY(int dialogY) {
		this.dialogY = dialogY;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	
	}


