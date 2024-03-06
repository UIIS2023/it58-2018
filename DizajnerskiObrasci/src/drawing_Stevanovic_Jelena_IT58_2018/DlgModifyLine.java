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

public class DlgModifyLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	
	private int startCoordinateX;
	private int startCoordinateY;
	private int endCoordinateX;
	private int endCoordinateY;

	private boolean correct;
	private Color color;

	public DlgModifyLine(Line line) {
		setTitle("Modify Line");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblStartPointX = new JLabel("Start point X :");
		lblStartPointX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtStartPointX = new JTextField();
		txtStartPointX.setColumns(10);
		txtStartPointX.setText("" + line.getStartPoint().getX());
		
		JLabel lblStartPointY = new JLabel("Start point Y :");
		lblStartPointY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtStartPointY = new JTextField();
		txtStartPointY.setColumns(10);
		txtStartPointY.setText("" + line.getStartPoint().getY());
		
		JLabel lblEndPointX = new JLabel("End point X :");
		lblEndPointX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtEndPointX = new JTextField();
		txtEndPointX.setColumns(10);
		txtEndPointX.setText("" + line.getEndPoint().getX());
		
		JLabel lblEndPointY = new JLabel("End point Y :");
		lblEndPointY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtEndPointY = new JTextField();
		txtEndPointY.setColumns(10);
		txtEndPointY.setText("" + line.getEndPoint().getY());
		
		JLabel lblLineColor = new JLabel("Line color :");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(line.getLineColor());
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblStartPointX)
									.addGap(18)
									.addComponent(txtStartPointX, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStartPointY)
										.addComponent(lblEndPointX)
										.addComponent(lblEndPointY))
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEndPointY, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
										.addComponent(txtEndPointX, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
										.addComponent(txtStartPointY, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
										.addComponent(btnLineColor))))
							.addGap(127))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblLineColor)
							.addContainerGap(324, Short.MAX_VALUE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointX)
						.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointY)
						.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointX)
						.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointY)
						.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addContainerGap(67, Short.MAX_VALUE))
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
							startCoordinateX = Integer.parseInt(txtStartPointX.getText());
							startCoordinateY = Integer.parseInt(txtStartPointY.getText());
							endCoordinateX = Integer.parseInt(txtEndPointX.getText());
							endCoordinateY = Integer.parseInt(txtEndPointY.getText());
							color = btnLineColor.getBackground();
							
							if (startCoordinateX <= 0 || startCoordinateY <= 0 || endCoordinateX <= 0 || endCoordinateY <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates must be positive!");
							} else {
						//		line.getStartPoint().setX(startCoordinateX);
							//	line.getStartPoint().setY(startCoordinateY);
							//	line.getEndPoint().setX(endCoordinateX);
							//	line.getEndPoint().setY(endCoordinateY);
							//	line.setLineColor(btnLineColor.getBackground());
								correct = true;
								dispose();
							}
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter coordinates of start and end point");
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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getStartCoordinateX() {
		return startCoordinateX;
	}

	public int getStartCoordinateY() {
		return startCoordinateY;
	}

	public int getEndCoordinateX() {
		return endCoordinateX;
	}

	public int getEndCoordinateY() {
		return endCoordinateY;
	}

	public void setStartCoordinateX(int startCoordinateX) {
		this.startCoordinateX = startCoordinateX;
	}

	public void setStartCoordinateY(int startCoordinateY) {
		this.startCoordinateY = startCoordinateY;
	}

	public void setEndCoordinateX(int endCoordinateX) {
		this.endCoordinateX = endCoordinateX;
	}

	public void setEndCoordinateY(int endCoordinateY) {
		this.endCoordinateY = endCoordinateY;
	}

}
