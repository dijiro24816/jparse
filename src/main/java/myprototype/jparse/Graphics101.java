package myprototype.jparse;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

class AlgorithmCanvas extends JComponent {
	private int[] array;
	private int max;

	public AlgorithmCanvas(int[] array) {
		super();
		this.array = array;
		this.max = 0;
		for (int num : array)
			if (num > max)
				max = num;
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		
		int width = getWidth();
		int height = getHeight();
		
		int atomHeight = height / this.max;
		int atomWidth = width / array.length;
		
		for (int i = 0; i < this.array.length; i++)
			graphics2d.drawRect(i * atomWidth, height - atomHeight * this.array[i], atomWidth, atomHeight * this.array[i]);
	}

	
}

class ShapeDrawing extends JComponent {
	private int defaultWidth;
	private int defaultHeight;
	private int width;
	private int height;

	public ShapeDrawing(int width, int height) {
		super();
		
		this.defaultWidth = width;
		this.defaultHeight = height;
		
		this.width = width;
		this.height = height;
		
	}
	
	void reset100() {
		this.width = this.defaultWidth;
		this.height = this.defaultHeight;
	}

	void increase100() {
		this.height += 100;
		this.width += 100;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(100, 150, getWidth() - 100 * 2, getHeight() - 150 * 2);
		repaint();
	}
}

abstract class SortingAlgorithmTracer {
	protected int[] array;
	protected int max;
	
	public int[] getArray() {
		return array;
	}

	public int getMax() {
		return max;
	}

	public SortingAlgorithmTracer(int[] array) {
		super();
		this.array = array;
		for (int num : array)
			if (num > this.max)
				this.max = num;
	}
	
	public abstract void step();
}

class BubbleSortAlgorithmTracer extends SortingAlgorithmTracer {
	private int i;
	private int j;
	
	public BubbleSortAlgorithmTracer(int[] array) {
		super(array);
		this.i = 0;
		this.j = this.array.length - 1;
	}
	
	@Override
	public void step() {
		if (i + 1 > j) {
			this.i = 0;
			this.j--;
		}
		this.i++;
		
		if (this.array[i] < this.array[i - 1]) {
			int tmp = this.array[i];
			this.array[i] = this.array[i - 1];
			this.array[i - 1] = tmp;
		}
	}
}

public class Graphics101 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("My first JFrame");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int[] array = { 1, 3, 2, 5, 7, 1, 3, 2, 5, 7, 1, 3, 2, 5, 7, 1, 3, 2, 5, 7,};
		AlgorithmCanvas algorithmCanvas = new AlgorithmCanvas(array);
		
		frame.add(algorithmCanvas, BorderLayout.CENTER);
		
		
		Button btn = new Button("inc");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				shapeDrawing.increase100();
			}
		});
		
		Button resetBtn = new Button("reset");
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				shapeDrawing.reset100();
			}
		});
		JPanel btnPanel = new JPanel();
		
		btnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		btnPanel.setSize(600, 600);
		btnPanel.add(btn);
		btnPanel.add(resetBtn);
		frame.add(btnPanel, BorderLayout.WEST);

		frame.setVisible(true);
	}
}
