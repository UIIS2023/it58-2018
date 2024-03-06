package mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnHexagon;
	JToggleButton tglbtnSelect;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	JButton btnLineColor;
	JButton btnFillColor;
	JButton btnModify;
	JButton btnDelete;
	JButton btnBTB;
	JButton btnBTF;
	JButton btnUndo;
	JButton btnRedo;
	JButton btnSaveDraw;
	JButton btnLoadDraw;
	JButton btnSaveTxtLog;
	JButton btnLoadTxtLog;
	JButton btnToBack;
	JButton btnToFront;
	
	JScrollPane scrollPane;
	JList<String> logList;
	
	DefaultListModel<String> commands = new DefaultListModel<>();
	private JButton btnNextLogLine;
	
	public DrawingFrame() {
		
		setTitle("Jelena Stevanovic IT58-2018");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 2500, 2500);
		
		JPanel panel = new JPanel();
		view.setBackground(new Color(255, 255, 255));
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(view, BorderLayout.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 131, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.SOUTH);
		
		logList = new JList<String>(commands);
		logList.setValueIsAdjusting(true);
		logList.setVisibleRowCount(4);
		scrollPane.setViewportView(logList);
		
		JPanel pnlButtons = new JPanel();
		getContentPane().add(pnlButtons, BorderLayout.WEST);
		
		tglbtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		buttonGroup.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		buttonGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		buttonGroup.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		buttonGroup.add(tglbtnHexagon);
		
		btnLineColor = new JButton("Line color");
		btnLineColor.setBackground(Color.BLACK);
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.lineColor(e, btnLineColor.getBackground());
			}
		});
		
		btnFillColor = new JButton("Fill color");
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.fillColor(e, btnFillColor.getBackground());

			}
		});
		
		tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.modify(e);
				
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.delete(e);
				
			}
		});
		
		btnBTB = new JButton("BTB");
		btnBTB.setEnabled(false);
		btnBTB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
				
			}
		});
		
		btnBTF = new JButton("BTF");
		btnBTF.setEnabled(false);
		btnBTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
				
			}
		});
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				
			}
		});
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
				
			}
		});
		
		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
				
			}
		});
		
		btnSaveDraw = new JButton("Save draw");
		btnSaveDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveDraw();
				
			}
		});
		
		btnLoadDraw = new JButton("Load draw");
		btnLoadDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadDraw();
				
			}
		});
		
		btnSaveTxtLog = new JButton("Save text");
		btnSaveTxtLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
				
			}
		});
		
		btnLoadTxtLog = new JButton("Load text");
		btnLoadTxtLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadLog();
				
			}
		});
		
		btnNextLogLine = new JButton("Next log line");
		btnNextLogLine.setEnabled(false);
		btnNextLogLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadNext();
				
			}
		});
		
		
		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons.setHorizontalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
								.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
								.addGroup(gl_pnlButtons.createSequentialGroup()
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
										.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pnlButtons.createSequentialGroup()
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
										.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
										.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
										.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))))
							.addGap(26))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(26, Short.MAX_VALUE))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnBTB, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBTF, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(26, Short.MAX_VALUE))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(26, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_pnlButtons.createSequentialGroup()
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLoadTxtLog, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
								.addComponent(btnSaveTxtLog, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSaveDraw)
								.addComponent(btnNextLogLine))
							.addGap(18))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnLoadDraw)
							.addContainerGap(26, Short.MAX_VALUE))))
		);
		gl_pnlButtons.setVerticalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnPoint)
						.addComponent(tglbtnLine))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnRectangle)
						.addComponent(tglbtnCircle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnDonut)
						.addComponent(tglbtnHexagon))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBTB, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBTF, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
						.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveDraw, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveTxtLog, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadTxtLog, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNextLogLine, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLoadDraw, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(105))
		);
		pnlButtons.setLayout(gl_pnlButtons);
	}

	public JButton getBtnLineColor() {
		return btnLineColor;
	}

	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public void setBtnFillColor(JButton btnFillColor) {
		this.btnFillColor = btnFillColor;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnBTB() {
		return btnBTB;
	}

	public JButton getBtnBTF() {
		return btnBTF;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnSaveDraw() {
		return btnSaveDraw;
	}

	public JButton getBtnLoadDraw() {
		return btnLoadDraw;
	}

	public JButton getBtnSaveTxtLog() {
		return btnSaveTxtLog;
	}

	public JButton getBtnLoadTxtLog() {
		return btnLoadTxtLog;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JList<String> getLogList() {
		return logList;
	}

	public DefaultListModel<String> getCommands() {
		return commands;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnNextLogLine() {
		return btnNextLogLine;
	}

	public void setBtnNextLogLine(JButton btnNextLogLine) {
		this.btnNextLogLine = btnNextLogLine;
	}
}

	

