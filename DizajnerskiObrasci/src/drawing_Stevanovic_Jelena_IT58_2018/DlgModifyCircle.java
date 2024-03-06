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

public class DlgModifyCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	
	private int centerX;
	private int centerY;
	private int radius;
	private boolean correct;
	private Color lineColor;
	private Color fillColor;

	
	public DlgModifyCircle(Circle circle) {
		setTitle("Modify Circle");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCenterX = new JLabel("Center X :");
		lblCenterX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);
		txtCenterX.setText("" + circle.getCenter().getX());
		
		JLabel lblCenterY = new JLabel("Center Y :");
		lblCenterY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);
		txtCenterY.setText("" + circle.getCenter().getY());
		
		JLabel lblRadius = new JLabel("Radius :");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		txtRadius.setText("" + circle.getRadius());
		
		JLabel lblLineColor = new JLabel("Line color :");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(circle.getLineColor());
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Pick a color", btnLineColor.getBackground());
				if(temp != null) {
					btnLineColor.setBackground(temp);
				}
			}
		});
		
		
		JLabel lblFillColor = new JLabel("Fill color :");
		lblFillColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnFillColor = new JButton("Modify color");
		btnFillColor.setBackground(circle.getFillColor());
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Pick a color", btnFillColor.getBackground());
				if(temp != null) {
					btnFillColor.setBackground(temp);
				}
			}
		});
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCenterX)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCenterY)
								.addComponent(lblRadius))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRadius, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(txtCenterY, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
								.addComponent(lblFillColor)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFillColor))
							.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
								.addComponent(lblLineColor)
								.addGap(27)
								.addComponent(btnLineColor))))
					.addGap(149))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterX)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCenterY)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFillColor)
						.addComponent(btnFillColor))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							centerX = Integer.parseInt(txtCenterX.getText());
							centerY = Integer.parseInt(txtCenterY.getText());
							radius = Integer.parseInt(txtRadius.getText());
							lineColor = btnLineColor.getBackground();
							fillColor = btnFillColor.getBackground();
							
							if (centerX <= 0 || centerY <= 0 || radius <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates and radius must be positive!");
							} else {
							//	circle.moveOn(centerX, centerY);
								//circle.setRadius(radius);
							//	circle.setLineColor(btnLineColor.getBackground());
							//	circle.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates and radius");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
	}


	public int getCenterX() {
		return centerX;
	}


	public int getCenterY() {
		return centerY;
	}


	public int getRadius() {
		return radius;
	}


	public boolean isCorrect() {
		return correct;
	}



	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}


	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	public Color getLineColor() {
		return lineColor;
	}


	public Color getFillColor() {
		return fillColor;
	}


	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}


	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}




}
