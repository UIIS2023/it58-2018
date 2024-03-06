package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.RemoveShapeCmd;
import command.RemoveShapesCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing_Stevanovic_Jelena_IT58_2018.Circle;
import drawing_Stevanovic_Jelena_IT58_2018.DlgCircle;
import drawing_Stevanovic_Jelena_IT58_2018.DlgDelete;
import drawing_Stevanovic_Jelena_IT58_2018.DlgDonut;
import drawing_Stevanovic_Jelena_IT58_2018.DlgHexagon;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyCircle;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyDonut;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyHexagon;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyLine;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyPoint;
import drawing_Stevanovic_Jelena_IT58_2018.DlgModifyRectangle;
import drawing_Stevanovic_Jelena_IT58_2018.DlgRectangle;
import drawing_Stevanovic_Jelena_IT58_2018.Donut;
import drawing_Stevanovic_Jelena_IT58_2018.HexagonAdapter;
import drawing_Stevanovic_Jelena_IT58_2018.Line;
import drawing_Stevanovic_Jelena_IT58_2018.Point;
import drawing_Stevanovic_Jelena_IT58_2018.Rectangle;
import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import hexagon.Hexagon;
import strategy.DrawStrategy;
import strategy.LogStrategy;
import strategy.Strategy;

public class DrawingController extends Observable {
	
	private DrawingModel model;
	private DrawingFrame frame;
	
	private Shape currentSelected = null;
	private ArrayList<Shape> selected = new ArrayList<>();
	ArrayList<Shape> helpShapes;
	
	private int click = 1;
	private Point startP;
	private Point endP;

	private Command command;

	private RemoveShapeCmd removeCmd;
	private RemoveShapesCmd removeShapesCmd;
	
	private UpdatePointCmd updatePointCmd;
	private UpdateLineCmd updateLineCmd;
	private UpdateCircleCmd updateCircleCmd;
	private UpdateRectangleCmd updateRectangleCmd;
	private UpdateDonutCmd updateDonutCmd;
	private UpdateHexagonCmd updateHexagonCmd;
	
	private BringToFrontCmd bringToFrontCmd;
	private BringToBackCmd bringToBackCmd;
	private ToBackCmd toBackCmd;
	private ToFrontCmd toFrontCmd;
	
	private Strategy strategySave;
	
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {

		this.model = model;
		this.frame = frame;
	}




	public void lineColor(ActionEvent e, Color c) {
		Color temp = JColorChooser.showDialog(null, "Choose a color", c);
		if (temp != null) {
			frame.getBtnLineColor().setBackground(temp);
		}
		
	}




	public void fillColor(ActionEvent e, Color c) {
		Color temp = JColorChooser.showDialog(null, "Choose a color", c);
		if (temp != null) {
			frame.getBtnFillColor().setBackground(temp);
		}
		
	}


	public void modify(ActionEvent e) {
		if(currentSelected != null) {
			if(currentSelected instanceof Point) {
				DlgModifyPoint dialog = new DlgModifyPoint((Point)currentSelected);
				dialog.setVisible(true);

				if(dialog.isCorrect() == true) {
					Point oldPoint = (Point) currentSelected;
					
					Point newPoint = new Point(dialog.getDialogX(), dialog.getDialogY(), dialog.getColor());
				
	//				System.out.println("Old point: "+oldPoint.toString());
	//				System.out.println("New point: " + newPoint.toString());
					
					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldPoint.toString() + " new point: " + newPoint.toString());

					
					command = new UpdatePointCmd(oldPoint , newPoint);
					addShape(command);

//					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldPoint.toString() + " new point: " + newPoint.toString());

					
				}

			//	addShape(command = new UpdatePointCmd(oldPoint, newPoint));
			//	frame.getCommands().add(frame.getCommands().size(), "Modified point: " + oldPoint.toString() + " new point: " + newPoint.toString());
				
			} else if(currentSelected instanceof Line) {
				DlgModifyLine dialog = new DlgModifyLine((Line)currentSelected);
				dialog.setVisible(true);
				if(dialog.isCorrect() == true) {
					Line oldLine = (Line) currentSelected;
					Line newLine = new Line(new Point(dialog.getStartCoordinateX(), dialog.getStartCoordinateY()), new Point(dialog.getEndCoordinateX(), dialog.getEndCoordinateY()), dialog.getColor());
					
					command = new UpdateLineCmd(oldLine , newLine);
					addShape(command);

					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldLine.toString() + " new line: " + newLine.toString());
			
				}
			} else if(currentSelected instanceof Circle && !(currentSelected instanceof Donut)) {
				DlgModifyCircle dialog = new DlgModifyCircle((Circle)currentSelected);
				dialog.setVisible(true);
				if(dialog.isCorrect() == true) {
					Circle oldCircle = (Circle) currentSelected;
					Circle newCircle = new Circle(new Point(dialog.getCenterX(), dialog.getCenterY()), dialog.getRadius(), dialog.getLineColor(), dialog.getFillColor());
					
					command = new UpdateCircleCmd(oldCircle , newCircle);
					addShape(command);

					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldCircle.toString() + " new circle: " + newCircle.toString());
			
				}

			}else if(currentSelected instanceof Rectangle) {
				
				DlgModifyRectangle dialog = new DlgModifyRectangle((Rectangle) currentSelected);		
				dialog.setVisible(true);
				
				if(dialog.isCorrect() == true) {
				Rectangle oldRectangle = (Rectangle) currentSelected;
				Rectangle newRectangle = new Rectangle(new Point(dialog.getUpperLeftX(), dialog.getUpperLeftY()), dialog.getHeight(), dialog.getWidth(), dialog.getLineColor(), dialog.getFillColor());
				command = new UpdateRectangleCmd(oldRectangle , newRectangle);
				addShape(command);

				frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldRectangle.toString() + " new: " + newRectangle.toString());
			
				}

			} else if(currentSelected instanceof Donut) {
				DlgModifyDonut dialog = new DlgModifyDonut((Donut)currentSelected);
				dialog.setVisible(true);
				if(dialog.isCorrect() == true) {
					Donut oldDonut = (Donut) currentSelected;
					Donut newDonut = new Donut(new Point(dialog.getCenterX(), dialog.getCenterY()), dialog.getRadius(), dialog.getInnerRadius(), dialog.getLineColor(), dialog.getFillColor());
					command = new UpdateDonutCmd(oldDonut , newDonut);
					addShape(command);

					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldDonut.toString() + " new: " + newDonut.toString());
					}

			}else if (currentSelected instanceof HexagonAdapter) {
				DlgModifyHexagon dialog = new DlgModifyHexagon((HexagonAdapter)currentSelected);
				dialog.setVisible(true);
				if(dialog.isCorrect() == true) {
					HexagonAdapter oldHexagon = (HexagonAdapter) currentSelected;
					HexagonAdapter newHexagon = new HexagonAdapter(dialog.getCenterX(), dialog.getCenterY(), dialog.getRadius(), dialog.getLineColor(), dialog.getFillColor());
					command = new UpdateHexagonCmd(oldHexagon , newHexagon);
					addShape(command);

					frame.getCommands().add(frame.getCommands().size(), "Modified: " + oldHexagon.toString() + " new: " + newHexagon.toString());
					}
			}
			redoStack.clear();
			frame.btnRedo.setEnabled(false);
			
			frame.repaint();
	} else {
			JOptionPane.showMessageDialog(null, "First select some shape");
		}
		
	}


	public void delete(ActionEvent e) {
		

		if(currentSelected != null) {
			DlgDelete dialog = new DlgDelete();
			dialog.setVisible(true);
			if(dialog.isCorrect() == true)
			{
		
		// za brisanje vise oblika odjednom		
				
			//	currentSelected.setSelected(false);
			//	helpShapes = new ArrayList<Shape>();
				
				helpShapes = selected;
				
				//Shape helpShape;
				Shape helpShape;
				
		
		//		deleteShapes(new RemoveShapesCmd(helpShapes, this.model));
				
				
				// prva koja je radila
			
		/*		for(int i = 0; i<selected.size(); i++){
					
					helpShape = selected.get(i);
					
					frame.getCommands().add(frame.getCommands().size(), "Deleted: " + selected.get(i));
					
					deleteShape(new RemoveShapeCmd(currentSelected, model, model.getShapes().indexOf(helpShape)));

			//		selected.remove(i);

				} 
	*/

				
				for(int i = selected.size()-1; i >=0; i--){
					
					if(selected.get(0) instanceof HexagonAdapter) {
						
						
						helpShape = selected.get(0).clone();
						
						
						
						frame.getCommands().add(frame.getCommands().size(), "Deleted: " + selected.get(0));
						
						deleteShape(new RemoveShapeCmd(selected.get(0), model, model.getShapes().indexOf(selected.get(0))));

						selected.remove(selected.get(0));
					}else {
					
					helpShape = selected.get(0).clone();
					
					frame.getCommands().add(frame.getCommands().size(), "Deleted: " + helpShape);
					
					deleteShape(new RemoveShapeCmd(helpShape, model, model.getShapes().indexOf(helpShape)));

					selected.remove(helpShape);
					
				}


					

				} 
				
				
				


				
				//brisanje jedan po jedan
				
		/*	for(int i = 0; i<selected.size(); i++){
					
					deleteShape(new RemoveShapeCmd(selected.get(i), this.model));
					
			//	model.getShapes().remove(selected.get(i));
					frame.getCommands().add(frame.getCommands().size(), "Deleted: " + selected.get(i));
				} */
				
				
		//		selected.clear();
			//	currentSelected.setSelected(false);
			//	currentSelected = null;
				
				
				setChanged();
				notifyObservers();
						
	//					frame.repaint();
			}
			
	//		setChanged();
	//		notifyObservers();
			System.out.println(model.getShapes().toString());
			frame.repaint();

			
		}
		else {
			JOptionPane.showMessageDialog(null, "First, select some shape");
		}
		
	//	setChanged();
	//	notifyObservers();
	}




	public void mouseClicked(MouseEvent e) {
		
		
		if (frame.getTglbtnPoint().isSelected()) {
			int x = e.getX();
			int y = e.getY();
			Point point = new Point(x, y, frame.getBtnLineColor().getBackground());
		//	model.add(point);

			addShape(new AddShapeCmd(point, this.model));
			
			frame.getCommands().add(frame.getCommands().size(), "Added: " + point.toString());

		} else if (frame.getTglbtnLine().isSelected()) {	

			if (click == 1) {
				int x = e.getX();
				int y = e.getY();
				startP = new Point(x,y);
				click++;
			} else {
				int x1 = e.getX();
				int y1 = e.getY();
				Line line = new Line(startP, new Point(x1, y1), frame.getBtnLineColor().getBackground());
				click = 1;
				
				addShape(new AddShapeCmd(line, this.model));
				
				frame.getCommands().add(frame.getCommands().size(), "Added: " + line.toString());
			}

		} else if (frame.getTglbtnRectangle().isSelected()) {

			int x = e.getX();
			int y = e.getY();
			DlgRectangle dialog = new DlgRectangle();
			dialog.setVisible(true);
			if(dialog.isCorrect() == true) {
				Rectangle rectangle = new Rectangle(new Point(x, y), dialog.getDlgWidth(), dialog.getDlgHeight(),
						frame.getBtnLineColor().getBackground(), frame.getBtnFillColor().getBackground());

				addShape(new AddShapeCmd(rectangle, this.model));
				
				frame.getCommands().add(frame.getCommands().size(), "Added: " + rectangle.toString());
			}


		} else if (frame.getTglbtnCircle().isSelected()) {

			int x = e.getX();
			int y = e.getY();
			DlgCircle dialog = new DlgCircle();
			dialog.setVisible(true);
			if(dialog.isCorrect() == true) {
				if(dialog.getDlgRadius()<=0) {
					throw new NumberFormatException("Radius has to be a value greater than 0!");
				}
				Circle circle = new Circle(new Point(x, y), dialog.getDlgRadius(), frame.getBtnLineColor().getBackground(),
						frame.getBtnFillColor().getBackground());

				addShape(new AddShapeCmd(circle, this.model));
				
				frame.getCommands().add(frame.getCommands().size(), "Added: " + circle.toString());
			}

		} else if (frame.getTglbtnDonut().isSelected()) {
             int x=e.getX();
             int y=e.getY();
             DlgDonut dialog = new DlgDonut();
             dialog.setVisible(true);
             if(dialog.isCorrect() == true) {
             Donut donut = new Donut(new Point(x,y), dialog.getDlgRadius(), dialog.getDlgInnerRadius(), frame.getBtnLineColor().getBackground(), 
            		 frame.getBtnFillColor().getBackground());
             
             addShape(new AddShapeCmd(donut, this.model));
             
             frame.getCommands().add(frame.getCommands().size(), "Added: " + donut.toString());
             }
             
		}else if (frame.tglbtnHexagon.isSelected()) {
			
			int x = e.getX();
			int y = e.getY();
			DlgHexagon dialog = new DlgHexagon();
			dialog.setVisible(true);
			if (dialog.isCorrect() == true) {
				HexagonAdapter hexagon = new HexagonAdapter(x, y, dialog.getDlgRadius(), frame.getBtnLineColor().getBackground(), frame.getBtnFillColor().getBackground());
				//model.add(hexagon);
				addShape(new AddShapeCmd(hexagon, this.model));
				System.out.println(model.getShapes().toString());
				frame.getCommands().add(frame.getCommands().size(), "Added: " + hexagon.toString());
			}
			
		} else if(frame.tglbtnSelect.isSelected()) {
			int x=e.getX();
			int y=e.getY();
			
			for(int i = model.getShapes().size() -1; i >= 0; i--) {
				if(model.getShapes().get(i).contains(x, y)) {
					
					Shape s = model.getShapes().get(i);
					
					if(model.getShapes().get(i).isSelected())
					{
						frame.getCommands().add(frame.getCommands().size(), "Unselected: " + "X: " +x + " Y: " + y);
						
						model.getShapes().get(i).setSelected(false);
						selected.remove(model.getShapes().get(i));
						
					//	setChanged();
					//	notifyObservers();
					}
					else {
						model.getShapes().get(i).setSelected(true);
						model.getShapes().get(i).draw(frame.getView().getGraphics());
						currentSelected = model.getShapes().get(i);
						
						selected.add(currentSelected);
						
						frame.getCommands().add(frame.getCommands().size(), "Selected: " + "X: " +x + " Y: " + y);
						
						
					//	setChanged();
					//	notifyObservers();
					}
					

					//OVO  ispod je isto dobar kod
					
		/*			model.getShapes().get(i).setSelected(true);
					model.getShapes().get(i).draw(frame.getView().getGraphics());
					currentSelected = model.getShapes().get(i);
					
					selected.add(currentSelected);
					
					frame.getCommands().add(frame.getCommands().size(), "Selected: " + currentSelected);
					*/
		//			break;
					
				}

		
			}
			
		
			
			setChanged();
			notifyObservers();
			checkBtn();
		}
		
		if(currentSelected != null && !frame.tglbtnSelect.isSelected()) {
			for(int i = model.getShapes().size() -1; i >= 0; i--) {
				if(model.getShapes().get(i).isSelected())
				{
					Shape s = model.getShapes().get(i);
					
					
					frame.getCommands().add(frame.getCommands().size(), "Unselected: " + s);
					
					model.getShapes().get(i).setSelected(false);
					selected.remove(model.getShapes().get(i));
					
					setChanged();
					notifyObservers();
					checkBtn();
				}
			}
			currentSelected = null;
		}
		
/*		if(selected.size()!=0 && frame.tglbtnSelect.isSelected()) {
			System.out.println(selected.size());
		for(int i=0; i<selected.size(); i++) {
			System.out.println(selected.get(i));
			if(!selected.get(i).isSelected())
				selected.remove(i);
		}

		}*/
	//	setChanged();
	//	notifyObservers();
		
		
		frame.repaint();
	}

	public void addShape(Command addCmd) {

		addCmd.execute();
		
		undoStack.push(addCmd);
		
		frame.btnUndo.setEnabled(true);
		
		if(!redoStack.isEmpty()) {
			redoStack.clear();
			frame.btnRedo.setEnabled(false);
		}
		
	}
	

	private void deleteShape(RemoveShapeCmd removeCmd) {
		
		removeCmd.execute();
		undoStack.push(removeCmd);
		
	}
	
	//metoda za brisanje vise oblika
	private void deleteShapes(RemoveShapesCmd removeShapesCmd) {
		removeShapesCmd.execute();
		undoStack.push(removeShapesCmd);
	}
	
	public void undo() {
		
		Command cmdUndo = undoStack.pop();

		
		if(cmdUndo instanceof RemoveShapeCmd) {

			while(cmdUndo instanceof RemoveShapeCmd) {
				cmdUndo.unexecute();
				
				redoStack.push(cmdUndo);
	
				frame.getCommands().add(frame.getCommands().size(), "Undo");
				
				cmdUndo = undoStack.pop();
			}
		}else {
			cmdUndo.unexecute();
			
			redoStack.push(cmdUndo);
			
			
			frame.getCommands().add(frame.getCommands().size(), "Undo");
		}
	
		
		
		frame.repaint();
		
		if(!redoStack.isEmpty()) {
			frame.btnRedo.setEnabled(true);
			
		}
		
		if(undoStack.isEmpty()) {
			frame.btnUndo.setEnabled(false);

		}
		checkBtn();

	}


	public void redo() {

			Command cmdRedo = redoStack.pop();
			
			if(cmdRedo instanceof RemoveShapeCmd) {
				while(cmdRedo instanceof RemoveShapeCmd) {
					cmdRedo.execute();
					undoStack.push(cmdRedo);
					frame.getCommands().add(frame.getCommands().size(), "Redo" );

					if(!redoStack.isEmpty()) {
						cmdRedo = redoStack.pop();
					}
					else {
						break;
					}

				}
			}else {
				cmdRedo.execute();
				undoStack.push(cmdRedo);
				frame.getCommands().add(frame.getCommands().size(), "Redo");
			}
			

			frame.repaint();
				
			if(redoStack.isEmpty()) {
				frame.btnRedo.setEnabled(false);

			}
			
			if(!undoStack.isEmpty()) {
				frame.btnUndo.setEnabled(true);

			}
			checkBtn();
			
	}
	
	public void bringToBack() {
		bringToBackCmd = new BringToBackCmd(model, currentSelected);
		bringToBackCmd.execute();
		
		frame.getCommands().add(frame.getCommands().size(), "BTB");
		
		undoStack.push(bringToBackCmd);
		
		redoStack.clear();
		
		setChanged();
		notifyObservers();
		checkBtn();
		
		frame.repaint();
	}
	
	public void bringToFront() {

		bringToFrontCmd = new BringToFrontCmd(model, currentSelected);
		bringToFrontCmd.execute();
		
		frame.getCommands().add(frame.getCommands().size(), "BTF");
		
		undoStack.push(bringToFrontCmd);
		
		redoStack.clear();
		
		setChanged();
		notifyObservers();
		checkBtn();
		
		frame.repaint();
	}
	
	public void toBack() {
		toBackCmd = new ToBackCmd(model, currentSelected);
		toBackCmd.execute();
		
		frame.getCommands().add(frame.getCommands().size(), "To back");
		
		undoStack.push(toBackCmd);
		
		redoStack.clear();
		
		setChanged();
		notifyObservers();
		
		checkBtn();
		
		frame.repaint();
		
	}
	
	public void toFront() {
		toFrontCmd = new ToFrontCmd(model, currentSelected);
		toFrontCmd.execute();
		
		frame.getCommands().add(frame.getCommands().size(), "To front");
		
		undoStack.push(toFrontCmd);
		
		redoStack.clear();
		
		setChanged();
		notifyObservers();
		
		checkBtn();
		
		frame.repaint();
	}
	

	private void checkBtn() {
		
		int flag = (this.model.getShapes().indexOf(currentSelected));
		
		if((this.model.getShapes().indexOf(currentSelected)) == (this.model.getShapes().size()-1)){
			frame.btnBTF.setEnabled(false);
			frame.btnToFront.setEnabled(false);
		}
		
		if (0 == flag ) {
			frame.btnBTB.setEnabled(false);
			frame.btnToBack.setEnabled(false);
		}
		
	}
	
	public void saveLog() {
		strategySave = new LogStrategy(frame);
		strategySave.saveFile();
	}
	
	public void loadLog() {
	//	strategySave = new LogStrategy(frame, model);
	//	strategySave.loadFile();
		
		loadFile();
	}
	
	public void saveDraw() {
		strategySave = new DrawStrategy(model, frame);
		strategySave.saveFile();
	}
	
	public void loadDraw() {
		strategySave = new DrawStrategy(this.model, this.frame);
		strategySave.loadFile();
		
		frame.repaint();
	}
	
	private ArrayList<String> load;
	private int counter = 0;

	public void loadFile() {
		
		model.getShapes().clear();
		frame.getCommands().clear();
		load = new ArrayList<String>();
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showOpenDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave;
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				Scanner scan = new Scanner(fileToSave);
				while(scan.hasNext()) {
					load.add(scan.nextLine());
					
				}

				for(int i = 0; i<load.size(); i++) {
					System.out.println(i + " " + load.get(i));
				}
				
				frame.getBtnNextLogLine().setEnabled(true);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0; i<model.getShapes().size(); i++) {
			model.get(i).setSelected(false);
		}
		
	}
	
	private String row;
	private String temp[];
	
	public void loadNext() {
		
		if(counter < load.size()) {
			
			row = load.get(counter);
			
			if(row.contains("Added")) {
				
				addLoad();
				
			}else if (row.contains("Select")) {
				
				selectLoad();
				
			}
			else if (row.contains("Unselect")) {
				unselectLoad();
				
			} else if (row.contains("Deleted")) {
				deleteLoad();
				
			}else if (row.contains("Modified")) {
				modifyLoad();
			}
			else if (row.contains("Undo")) {
				undoLoad();
				
			}else if (row.contains("Redo")) {
				redoLoad();
				
			}else if (row.contains("BTB")) {
				
				bringTbLoad();
			}else if (row.contains("BTF")) {
				bringTfLoad();
				
			}else if (row.contains("back")) {
				toBackLoad();
				
			}else if (row.contains("front")) {
				toFrontLoad();
			}
			
			counter++;
			
			if(counter == load.size()) {
				frame.getBtnNextLogLine().setEnabled(false);
			}
		}
		
		frame.repaint();
	}
	
	
	public void addLoad() {
		
		if(row.contains("Point")) {
			temp = row.split(" ");	

			int x = Integer.parseInt(temp[3]);
			int y = Integer.parseInt(temp[5]);
			int c = Integer.parseInt(temp[8]);
			
			Point p = new Point(x, y, new Color(c));
			addShape(new AddShapeCmd(p, this.model));
		
			
		}else if (row.contains("Line")) {
			temp = row.split(" ");	
			
			int xStart = Integer.parseInt(temp[4]);
			int yStart = Integer.parseInt(temp[7]);
			int xEnd = Integer.parseInt(temp[10]);
			int yEnd = Integer.parseInt(temp[14]);
			int c = Integer.parseInt(temp[17]);
			
			Line l = new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(c));
			addShape(new AddShapeCmd(l, this.model));
			
		}else if (row.contains("Rectangle")) {
			temp = row.split(" ");		
			
			int x = Integer.parseInt(temp[6]);
			int y = Integer.parseInt(temp[11]);
			int width = Integer.parseInt(temp[14]);
			int height = Integer.parseInt(temp[17]);
			int c = Integer.parseInt(temp[20]);
			int c2 = Integer.parseInt(temp[23]);
			
			Rectangle r = new Rectangle(new Point(x, y), height, width, new Color(c),  new Color(c2));
			addShape(new AddShapeCmd(r, this.model));
			
		}else if (row.contains("Circle")) {
			temp = row.split(" ");	
			
			int x = Integer.parseInt(temp[4]);
			int y = Integer.parseInt(temp[7]);
			int radius = Integer.parseInt(temp[10]);
			int c = Integer.parseInt(temp[13]);
			int c2 = Integer.parseInt(temp[16]);
			
			Circle circle = new Circle(new Point(x, y), radius, new Color(c), new Color(c2));
			addShape(new AddShapeCmd(circle, this.model));
			
		}else if (row.contains("Donut")) {
			temp = row.split(" ");	
			
			int x = Integer.parseInt(temp[4]);
			int y = Integer.parseInt(temp[7]);
			int radius = Integer.parseInt(temp[10]);
			int innerRadius = Integer.parseInt(temp[14]);
			int c = Integer.parseInt(temp[17]);
			int c2 = Integer.parseInt(temp[20]);
			
			Donut d = new Donut(new Point(x, y), radius, innerRadius, new Color(c), new Color(c2));
			addShape(new AddShapeCmd(d, this.model));
		}else if (row.contains("Hexagon")) {
			temp = row.split(" ");	
			
			int x = Integer.parseInt(temp[4]);
			int y = Integer.parseInt(temp[6]);
			int radius = Integer.parseInt(temp[8]);
			int c = Integer.parseInt(temp[10]);
			int c2 = Integer.parseInt(temp[12]);
			
			HexagonAdapter h = new HexagonAdapter(x, y, radius, new Color(c), new Color(c2));
			addShape(new AddShapeCmd(h, this.model));
		}
		
		
		
		frame.repaint();
		
	}
	
	public void selectLoad() {
		
		temp = row.split(" ");
		
		for (Shape shape : model.getShapes()) {
			// 2 i 4 su X i Y koordinata u zavisnoosti od oblika
			if (shape.contains(Integer.parseInt(temp[2]), Integer.parseInt(temp[4]))) {
				if (!shape.isSelected()) {
					currentSelected = shape;
					shape.setSelected(true);
					shape.selected(frame.getView().getGraphics());
					selected.add(shape);
					
					setChanged();
					notifyObservers();
				} 
			}
		}
		
		frame.repaint();
		
	}
	
	public void unselectLoad() {
		
		temp = row.split(" ");
		
		for (Shape shape : model.getShapes()) {
			if (shape.contains(Integer.parseInt(temp[2]), Integer.parseInt(temp[4]))) {
				if (shape.isSelected()) {
					currentSelected=shape;
					shape.setSelected(false);
					shape.selected(frame.getView().getGraphics());
					selected.remove(shape);
					
					setChanged();
					notifyObservers();
				} 
			}
		}
	}
	
	public void deleteLoad() {
		
		Shape helpShape;
		
		for(int i = selected.size()-1; i >=0; i--){

		/*	helpShape = selected.get(0).clone();
			
			deleteShape(new RemoveShapeCmd(helpShape, model, model.getShapes().indexOf(helpShape)));

			selected.remove(helpShape);*/
			
			if(selected.get(0) instanceof HexagonAdapter) {
				
				
				helpShape = selected.get(0).clone();
				
				
				
				frame.getCommands().add(frame.getCommands().size(), "Deleted: " + selected.get(0));
				
				deleteShape(new RemoveShapeCmd(selected.get(0), model, model.getShapes().indexOf(selected.get(0))));

				selected.remove(selected.get(0));
			}else {
			
			helpShape = selected.get(0).clone();
			
			frame.getCommands().add(frame.getCommands().size(), "Deleted: " + helpShape);
			
			deleteShape(new RemoveShapeCmd(helpShape, model, model.getShapes().indexOf(helpShape)));

			selected.remove(helpShape);
			
		}


		} 
		
		setChanged();
		notifyObservers();
	}
	
	public void modifyLoad() {
		
		if(row.contains("Point")){
			temp = row.split(" ");
				
			Point helpPoint = (Point) currentSelected;
			
			Point newPoint = new Point(Integer.parseInt(temp[13]), Integer.parseInt(temp[15]), new Color(Integer.parseInt(temp[18])));
			
			command = new UpdatePointCmd(helpPoint, newPoint);
			addShape(command);
			

		}else if (row.contains("Line")) {
			temp = row.split(" ");
			
			Line helpLine = (Line) currentSelected;
			Line newLine = new Line(new Point(Integer.parseInt(temp[23]), Integer.parseInt(temp[26])), new Point(Integer.parseInt(temp[29]), Integer.parseInt(temp[33])), new Color(Integer.parseInt(temp[36])));
			
			command = new UpdateLineCmd(helpLine, newLine);
			addShape(command);
			

		}else if (row.contains("Rectangle")) {
			temp = row.split(" ");
			
			Rectangle helpRec = (Rectangle) currentSelected;
			Rectangle newRec = new Rectangle(new Point(Integer.parseInt(temp[30]), Integer.parseInt(temp[35])), Integer.parseInt(temp[38]), Integer.parseInt(temp[41]), new Color(Integer.parseInt(temp[44])), new Color(Integer.parseInt(temp[47])));
			
			command = new UpdateRectangleCmd(helpRec, newRec);
			addShape(command);
			
		}else if (row.contains("Circle")) {
			temp = row.split(" ");
			
			Circle helpCircle = (Circle) currentSelected;
			Circle newCircle = new Circle(new Point(Integer.parseInt(temp[22]), Integer.parseInt(temp[25])), Integer.parseInt(temp[28]), new Color(Integer.parseInt(temp[31])), new Color(Integer.parseInt(temp[34])));
			
			command = new UpdateCircleCmd(helpCircle, newCircle);
			addShape(command);

		}else if (row.contains("Donut")) {
			temp = row.split(" ");
			
			Donut helpDonut = (Donut) currentSelected;
			Donut newDonut = new Donut(new Point(Integer.parseInt(temp[25]), Integer.parseInt(temp[28])), Integer.parseInt(temp[31]), Integer.parseInt(temp[35]), new Color(Integer.parseInt(temp[38])), new Color(Integer.parseInt(temp[41])));
			
			command = new UpdateDonutCmd(helpDonut, newDonut);
			addShape(command);
			
	
		}else if (row.contains("Hexagon")) {
			temp = row.split(" ");
			
			HexagonAdapter helpHex = (HexagonAdapter) currentSelected;
			HexagonAdapter newHex = new HexagonAdapter(Integer.parseInt(temp[17]), Integer.parseInt(temp[19]), Integer.parseInt(temp[21]), new Color(Integer.parseInt(temp[23])), new Color(Integer.parseInt(temp[25])));
			
			command = new UpdateHexagonCmd(helpHex, newHex);
			addShape(command);
	
			}
		
		
	}
	
	public void undoLoad() {
		
		Command cmdUndoPop = undoStack.pop();
		redoStack.push(cmdUndoPop);
		
		cmdUndoPop.unexecute();
		setChanged();
		notifyObservers();
	}
	
	public void redoLoad() {
		
		Command cmdRedoPop = redoStack.pop();
		undoStack.push(cmdRedoPop);
		
		cmdRedoPop.execute();
		setChanged();
		notifyObservers();
	}

	public void bringTbLoad() {
		
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				addShape(new BringToBackCmd(model, o));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}
	
	public void bringTfLoad() {
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				addShape(new BringToFrontCmd(model, o));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}
	
	public void toBackLoad() {
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				addShape(new ToBackCmd(model, o));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}
	
	public void toFrontLoad() {
		ListIterator<Shape> it = model.getShapes().listIterator(model.getShapes().size());
		while(it.hasPrevious()) {
			Shape o = (Shape)it.previous();
			if(o.isSelected()) {
				addShape(new ToFrontCmd(model, o));
				setChanged();
				notifyObservers();
				return;
				}
		}
	}
	
	public ArrayList<Shape> getSelected() {
		return selected;
	}

	public Shape getCurrentSelected() {
		return currentSelected;
	}




	public void setCurrentSelected(Shape currentSelected) {
		this.currentSelected = currentSelected;
	}
	

}
