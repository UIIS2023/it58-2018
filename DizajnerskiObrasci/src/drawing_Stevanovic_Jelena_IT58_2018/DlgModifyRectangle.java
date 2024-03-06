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

public class DlgModifyRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperLeftX;
	private JTextField txtUpperLeftY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	
	private int upperLeftX;
	private int upperLeftY;
	private int height;
	private int width;
	private Color lineColor;
	private Color fillColor;
	private boolean correct;

	
	public DlgModifyRectangle(Rectangle rectangle) {
		setTitle("Modify Rectangle");
		setModal(true);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblUpperLeftX = new JLabel("Upper left X :");
		lblUpperLeftX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftX = new JTextField();
		txtUpperLeftX.setColumns(10);
		txtUpperLeftX.setText("" + rectangle.getUpperLeftPoint().getX());
		
		JLabel lblUpperLeftY = new JLabel("Upper left Y :");
		lblUpperLeftY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftY = new JTextField();
		txtUpperLeftY.setColumns(10);
		txtUpperLeftY.setText("" + rectangle.getUpperLeftPoint().getY());
		
		JLabel lblHeight = new JLabel("Height :");
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		txtHeight.setText("" + rectangle.getHeight());
		
		JLabel lblWidth = new JLabel("Width :");
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		txtWidth.setText("" + rectangle.getWidth());
		
		JLabel lblLineColor = new JLabel("Line color :");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLineColor.setBackground(rectangle.getLineColor());
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
		btnFillColor.setBackground(rectangle.getFillColor());
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
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblUpperLeftX)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtUpperLeftX))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblUpperLeftY)
									.addComponent(lblHeight))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtUpperLeftY)
									.addComponent(txtHeight, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addComponent(txtWidth)
									.addComponent(btnLineColor)
									.addComponent(btnFillColor))))
						.addComponent(lblWidth)
						.addComponent(lblLineColor)
						.addComponent(lblFillColor))
					.addContainerGap(174, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpperLeftX)
						.addComponent(txtUpperLeftX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUpperLeftY)
						.addComponent(txtUpperLeftY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFillColor)
						.addComponent(btnFillColor))
					.addContainerGap(191, Short.MAX_VALUE))
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
							upperLeftX = Integer.parseInt(txtUpperLeftX.getText());
							upperLeftY = Integer.parseInt(txtUpperLeftY.getText());
							height = Integer.parseInt(txtHeight.getText());
							width = Integer.parseInt(txtWidth.getText());
							lineColor = btnLineColor.getBackground();
							fillColor = btnFillColor.getBackground();
							
							if(upperLeftX <= 0 || upperLeftY <= 0 || height <= 0 || width <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates, height and width must be positive!");
							}else {
							//	rectangle.moveOn(upperLeftX, upperLeftY);
							//	rectangle.setHeight(height);
							//	rectangle.setWidth(width);
							//	rectangle.setLineColor(btnLineColor.getBackground());
							//	rectangle.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Enter coordinates, height and width");
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


	public int getUpperLeftX() {
		return upperLeftX;
	}


	public int getUpperLeftY() {
		return upperLeftY;
	}


	public int getHeight() {
		return height;
	}


	public int getWidth() {
		return width;
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


	public void setUpperLeftX(int upperLeftX) {
		this.upperLeftX = upperLeftX;
	}


	public void setUpperLeftY(int upperLeftY) {
		this.upperLeftY = upperLeftY;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setWidth(int width) {
		this.width = width;
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
