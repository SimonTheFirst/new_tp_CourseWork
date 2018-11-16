package courseWorkClient.views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

import courseWorkClient.common.DataBaseInterface;
import courseWorkClient.common.WrongTimeValues;
import courseWorkClient.common.ApproximationUnit;

import javax.swing.JCheckBox;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cmbbxChooseLane;
	private JComboBox<Date> cmbbxChooseDate;
	private JComboBox<Integer> cmbbxChooseTimeFirst;
	private JComboBox<Integer> cmbbxChooseTimeSecond;
	private JPanel pDeps;
	private JPanel pNormalDep;
	private JCheckBox chckbxChooseTime;
	private JComboBox<String> cmbbxChooseTimeOfDay;
	private JComboBox<String> cmbbxChooseWeather;


	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initComponents();
		createEvents();
	}

	//*Initialize components of a frame**
	void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		pDeps = new JPanel();
		pDeps.setBackground(Color.GRAY);
		pNormalDep = new JPanel();
		pNormalDep.setBackground(Color.GRAY);

		cmbbxChooseLane = new JComboBox<String>();
		cmbbxChooseDate = new JComboBox<Date>();
		cmbbxChooseTimeFirst = new JComboBox<Integer>();
		cmbbxChooseTimeSecond = new JComboBox<Integer>();
		cmbbxChooseTimeOfDay = new JComboBox<String>();
		cmbbxChooseWeather = new JComboBox<String>();

		fillComboBoxes();

		cmbbxChooseTimeFirst.setEnabled(false);
		cmbbxChooseTimeSecond.setEnabled(false);
		chckbxChooseTime = new JCheckBox("Choose time");
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pNormalDep, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(cmbbxChooseLane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(cmbbxChooseDate, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbbxChooseTimeFirst, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(cmbbxChooseTimeSecond, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxChooseTime)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmbbxChooseTimeOfDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmbbxChooseWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(pDeps, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
							.addGap(1)))
					.addGap(37))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbbxChooseLane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxChooseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxChooseTimeFirst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxChooseTime)
						.addComponent(cmbbxChooseTimeSecond, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxChooseTimeOfDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxChooseWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pDeps, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(pNormalDep, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		pDeps.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(gl_contentPane);
		
		plot();

	}

	//*Initialize events for a frame**
	void createEvents()
	{
		cmbbxChooseLane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot();
			}
		});
		
		cmbbxChooseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot();
			}
		});
		
		cmbbxChooseTimeOfDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbbxChooseTimeOfDay.getSelectedItem().toString() != "All day"){plot();}
			}
		});
		
		cmbbxChooseWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbbxChooseWeather.getSelectedItem().toString() != "Any weather"){plot();}
			}
		});
		

		chckbxChooseTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbbxChooseTimeFirst.isEnabled() == true)
				{
					cmbbxChooseTimeFirst.setEnabled(false);
					cmbbxChooseTimeSecond.setEnabled(false);
				}
				else
				{
					cmbbxChooseTimeFirst.setEnabled(true);
					cmbbxChooseTimeSecond.setEnabled(true);
				}
			}
		});

	}

	//*Method that fills all combo boxes**
	private void fillComboBoxes()
	{
		DataBaseInterface dbInterface = new DataBaseInterface();
		String sql_lanes = "SELECT DISTINCT name FROM coursework.lane";
		String sql_dates = "SELECT DISTINCT (CONVERT(lane.date, DATE)) FROM coursework.lane ORDER BY lane.date";
		String sql_weather = "SELECT DISTINCT precipType FROM coursework.weather";
		String sql_timeOfDay = "SELECT DISTINCT timeOfDay from coursework.weather ";
		try
		{
			PreparedStatement pstmt = dbInterface.connect().prepareStatement(sql_lanes);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){cmbbxChooseLane.addItem(rs.getString(1));}
			pstmt.close();
			rs.close();

			pstmt = dbInterface.connect().prepareStatement(sql_dates);
			rs = pstmt.executeQuery();
			while(rs.next()){cmbbxChooseDate.addItem(rs.getDate(1));}
			pstmt.close();
			rs.close();

			for (int i = 1; i<=24; i++)
			{
				cmbbxChooseTimeFirst.addItem(i);
				cmbbxChooseTimeSecond.addItem(i);
			}
			
			pstmt = dbInterface.connect().prepareStatement(sql_weather);
			rs = pstmt.executeQuery();
			cmbbxChooseWeather.addItem("Any weather");
			while(rs.next()){cmbbxChooseWeather.addItem(rs.getString(1));}
			pstmt.close();
			rs.close();
			
			pstmt = dbInterface.connect().prepareStatement(sql_timeOfDay);
			rs = pstmt.executeQuery();
			cmbbxChooseTimeOfDay.addItem("All day");
			while(rs.next()){cmbbxChooseTimeOfDay.addItem(rs.getString(1));}
			pstmt.close();
			rs.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		dbInterface.disconnect();
	}

	//*Method that builds sql query**
	private String buildSqlQuery(String x, String y)
	{
		String sql_query;
		if (cmbbxChooseTimeOfDay.getSelectedItem().toString() != "All day" || cmbbxChooseWeather.getSelectedItem().toString() != "Any weather")
		{
			sql_query = "SELECT " + x + " , " + y + " FROM lane INNER JOIN weather ON CONVERT(lane.date, DATE) = CONVERT(weather.date,DATE) AND hour(lane.date) = hour(weather.date) WHERE name = ? AND CONVERT(lane.date, DATE) = ? ";
			if (cmbbxChooseTimeOfDay.getSelectedItem().toString() != "All day")
				sql_query+="AND timeOfDay = ? ";
			if (cmbbxChooseWeather.getSelectedItem().toString() != "Any weather")
				sql_query+="AND precipType = ?";
			if (chckbxChooseTime.isSelected())
			{
				sql_query+= " AND HOUR(lane.date) BETWEEN ? AND ? ";
			}
			return sql_query;
		}
		else
		{
			sql_query = "SELECT " + x + " , " + y + " FROM coursework.lane WHERE name = ?";
			if (cmbbxChooseDate.getSelectedItem().toString() == "All")
				return sql_query;
			else
			{
				sql_query+= " AND CONVERT(lane.date, DATE) = ?";
				if (chckbxChooseTime.isSelected())
				{
					sql_query+= " AND HOUR(lane.date) BETWEEN ? AND ? ";
				}
				return sql_query;
			}
		}
	}
	
	//*Method that plots a graph**
	private JFreeChart drawGraph(XYDataset dataset, String xAxis, String yAxis)
	{
		JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                xAxis,
                yAxis,
                dataset
        );
		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        renderer.setSeriesLinesVisible(1, false);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

		return chart;
	}

	//*Method that executes sql query and creates dataset from resultset**
	private XYSeries getDataSet(String sql, DataBaseInterface dbInterface)
	{
		XYSeries series = new XYSeries("Dependency");
		byte i = 3;
		try
		{
			if ((Integer)cmbbxChooseTimeFirst.getSelectedItem() > (Integer)cmbbxChooseTimeSecond.getSelectedItem())
				throw new WrongTimeValues();
			PreparedStatement pstmt = dbInterface.connect().prepareStatement(sql);
			pstmt.setString(1, cmbbxChooseLane.getSelectedItem().toString());
			pstmt.setString(2, cmbbxChooseDate.getSelectedItem().toString());
			if (cmbbxChooseTimeOfDay.getSelectedItem().toString() != "All day")
			{
				pstmt.setString(i, cmbbxChooseTimeOfDay.getSelectedItem().toString());
				i++;
			}
			if (cmbbxChooseWeather.getSelectedItem().toString() != "Any weather")
			{
				pstmt.setString(i, cmbbxChooseWeather.getSelectedItem().toString());
				i++;
			}
			if (chckbxChooseTime.isSelected()==true)
			{
				pstmt.setString(i, cmbbxChooseTimeFirst.getSelectedItem().toString());
				i++;
				pstmt.setString(i, cmbbxChooseTimeSecond.getSelectedItem().toString());
			}
			ResultSet rs = pstmt.executeQuery();
			boolean hasRows = false;
			while (rs.next())
			{
				hasRows = true;
				series.add(rs.getFloat(1), rs.getFloat(2));
			}
			if(!hasRows)
				JOptionPane.showMessageDialog(null, "No data for conditions you've selected", "Oops", JOptionPane.INFORMATION_MESSAGE);
			pstmt.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

		catch(WrongTimeValues ex)
		{
			JOptionPane.showMessageDialog(null, "You have selected wrong time interval", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		return series;
	}	
	
	private void plot()
	{
		DataBaseInterface dbInterface = new DataBaseInterface();
		String sql = buildSqlQuery("occupancy","volume");
		XYSeries series = getDataSet(sql, dbInterface);
		if (!series.isEmpty())
		{
			XYSeriesCollection seriesCollection= new XYSeriesCollection();
			ApproximationUnit au = new ApproximationUnit();
			double[] polinom = au.LeastSquares(series.toArray(),3,series.getItemCount());
			
			XYSeries apprser = new XYSeries("approximation");
			
			for(double i = series.getMinX();i<=series.getMaxX();i+=500) {
				apprser.add(i, (polinom[2]*i*i+polinom[1]*i)+polinom[0]);
			}
			seriesCollection.addSeries(apprser);
			seriesCollection.addSeries(series);
			
			JFreeChart chart = drawGraph(seriesCollection, "occupancy", "volume");
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		    chartPanel.setBackground(Color.white);
		    pDeps.removeAll();
		    pDeps.add(chartPanel, BorderLayout.CENTER);
			pDeps.validate();
		}
		dbInterface.disconnect();
	}
}