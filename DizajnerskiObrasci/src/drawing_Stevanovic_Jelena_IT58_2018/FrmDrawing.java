package drawing_Stevanovic_Jelena_IT58_2018;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private JToggleButton tglbtnPoint, tglbtnLine, tglbtnRectangle, tglbtnCircle, tglbtnDonut, tglbtnSelect;
	private JButton btnLineColor, btnFillColor, btnModify, btnDelete;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int x;
	private int y;
	private Point point;
	private int click = 1;
	private Line line;
	private Rectangle rectangle;
	private Circle circle;
	private Donut donut;
	private Shape currentSelected = null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setTitle("Jelena Stevanovic IT58-2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tglbtnPoint = new JToggleButton("Point");
		tglbtnLine = new JToggleButton("Line");
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnDonut = new JToggleButton("Donut");

		btnLineColor = new JButton("Line color");
		btnLineColor.setBackground(Color.BLACK);
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose a color", btnLineColor.getBackground());
				if (temp != null) {
					btnLineColor.setBackground(temp);
				}
			}
		});

		btnFillColor = new JButton("Fill color");
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose a color", btnFillColor.getBackground());
				if (temp != null) {
					btnFillColor.setBackground(temp);
				}

			}
		});

		tglbtnSelect = new JToggleButton("Select");
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelected != null) {
					if(currentSelected instanceof Point) {
						DlgModifyPoint dialog = new DlgModifyPoint((Point)currentSelected);
						dialog.setVisible(true);
					} else if(currentSelected instanceof Line) {
						DlgModifyLine dialog = new DlgModifyLine((Line)currentSelected);
						dialog.setVisible(true);
					} else if(currentSelected instanceof Circle && !(currentSelected instanceof Donut)) {
						DlgModifyCircle dialog = new DlgModifyCircle((Circle)currentSelected);
						dialog.setVisible(true);
					}else if(currentSelected instanceof Rectangle) {
						DlgModifyRectangle dialog = new DlgModifyRectangle((Rectangle)currentSelected);
						dialog.setVisible(true);
					} else if(currentSelected instanceof Donut) {
						DlgModifyDonut dialog = new DlgModifyDonut((Donut)currentSelected);
						dialog.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "First select some shape");
				}
				
			}
		});
		
		PnlDrawing panel = new PnlDrawing();
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelected != null) {
					DlgDelete dialog = new DlgDelete();
					dialog.setVisible(true);
					if(dialog.isCorrect() == true)
					{
						panel.getListShape().remove(currentSelected);
						currentSelected.setSelected(false);
						currentSelected = null;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "First, select some shape");
				}
			}
		});
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(currentSelected != null) {
					currentSelected.setSelected(false);
					currentSelected = null;
				}
				
				if (tglbtnPoint.isSelected()) {
					x = e.getX();
					y = e.getY();
					point = new Point(x, y, btnLineColor.getBackground());
					panel.getListShape().add(point);

				} else if (tglbtnLine.isSelected()) {		
					if (click == 1) {
						x = e.getX();
						y = e.getY();
						click++;
					} else {
						int x1 = e.getX();
						int y1 = e.getY();
						line = new Line(new Point(x, y), new Point(x1, y1), btnLineColor.getBackground());
						click = 1;
						panel.getListShape().add(line);
					}

				} else if (tglbtnRectangle.isSelected()) {
					x = e.getX();
					y = e.getY();
					DlgRectangle dialog = new DlgRectangle();
					dialog.setVisible(true);
					if(dialog.isCorrect() == true) {
						rectangle = new Rectangle(new Point(x, y), dialog.getDlgWidth(), dialog.getDlgHeight(),
								btnLineColor.getBackground(), btnFillColor.getBackground());
	
						panel.getListShape().add(rectangle);
					}
					System.out.println(panel.getListShape().size());

				} else if (tglbtnCircle.isSelected()) {
					x = e.getX();
					y = e.getY();
					DlgCircle dialog = new DlgCircle();
					dialog.setVisible(true);
					if(dialog.isCorrect() == true) {
						circle = new Circle(new Point(x, y), dialog.getDlgRadius(), btnLineColor.getBackground(),
								btnFillColor.getBackground());
	
						panel.getListShape().add(circle);
					}
					System.out.println(panel.getListShape().size());

				} else if (tglbtnDonut.isSelected()) {
                     x=e.getX();
                     y=e.getY();
                     DlgDonut dialog = new DlgDonut();
                     dialog.setVisible(true);
                     if(dialog.isCorrect() == true) {
                     donut = new Donut(new Point(x,y), dialog.getDlgRadius(), dialog.getDlgInnerRadius(), btnLineColor.getBackground(), 
                    		 btnFillColor.getBackground());
                     
                     panel.getListShape().add(donut);
                     }
                     System.out.println(panel.getListShape().size());
				} else if(tglbtnSelect.isSelected()) {
					x=e.getX();
					y=e.getY();
					if(currentSelected != null) {
						currentSelected.setSelected(false);
					}
					currentSelected = null;
					for(int i = panel.getListShape().size() - 1; i >= 0; i--) {
						if(panel.getListShape().get(i).contains(x, y)) {
							currentSelected = panel.getListShape().get(i);
							panel.getListShape().get(i).setSelected(true);
							break;
						}
					}
				}

			}
		});
		panel.setBackground(Color.WHITE);

		buttonGroup.add(tglbtnPoint);
		buttonGroup.add(tglbtnLine);
		buttonGroup.add(tglbtnRectangle);
		buttonGroup.add(tglbtnCircle);
		buttonGroup.add(tglbtnDonut);
		buttonGroup.add(tglbtnSelect);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnFillColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLineColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tglbtnSelect, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnLine, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnPoint, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnRectangle, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
					.addGap(10))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
