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

public class DlgModifyDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	
	private int centerX;
	private int centerY;
	private int radius;
	private int innerRadius;
	private Color lineColor;
	private Color fillColor;
	
	private boolean correct;


	public DlgModifyDonut(Donut donut) {
		setTitle("Modify Donut");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCenterX = new JLabel("Center X :");
		lblCenterX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);
		txtCenterX.setText("" + donut.getCenter().getX());
		
		JLabel lblCenterY = new JLabel("Center Y :");
		lblCenterY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);
		txtCenterY.setText("" + donut.getCenter().getY());
		
		JLabel lblRadius = new JLabel("Radius :");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		txtRadius.setText("" + donut.getRadius());
		
		JLabel lblInnerRadius = new JLabel("Inner radius :");
		lblInnerRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		txtInnerRadius.setText("" + donut.getInnerRadius());
		
		JLabel lblLineColor = new JLabel("Line color :");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(donut.getLineColor());
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
		btnFillColor.setBackground(donut.getFillColor());
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
						.addComponent(lblCenterX)
						.addComponent(lblCenterY)
						.addComponent(lblRadius)
						.addComponent(lblInnerRadius)
						.addComponent(lblLineColor)
						.addComponent(lblFillColor))
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFillColor)
						.addComponent(btnLineColor)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtCenterX, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
							.addComponent(txtCenterY)
							.addComponent(txtRadius)
							.addComponent(txtInnerRadius)))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterX)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCenterY)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFillColor)
						.addComponent(btnFillColor))
					.addContainerGap(29, Short.MAX_VALUE))
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
							innerRadius = Integer.parseInt(txtInnerRadius.getText());
							lineColor = btnLineColor.getBackground();
							fillColor = btnFillColor.getBackground();
							
							if (centerX <= 0 || centerY <= 0 || radius <= 0 || innerRadius <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates, radius and inner radius must be positive!");
							} else {
						//		donut.moveOn(centerX, centerY);
						//		donut.setRadius(radius);
						//		donut.setInnerRadius(innerRadius);
							//	donut.setLineColor(btnLineColor.getBackground());
							//	donut.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							
							}
							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates, radius and inner radius");
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


	public int getInnerRadius() {
		return innerRadius;
	}


	public Color getLineColor() {
		return lineColor;
	}


	public Color getFillColor() {
		return fillColor;
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


	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}


	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}


	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
