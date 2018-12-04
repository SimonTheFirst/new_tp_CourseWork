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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Date;

import courseWorkClient.common.DataBaseInterface;
import courseWorkClient.common.WrongTimeValues;
import courseWorkClient.common.ApproximationUnit;

import javax.swing.JCheckBox;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel pDep1;
	private JPanel pDep2;
	private JComboBox<String> cmbbxChooseLane;
	private JComboBox<Date> cmbbxChooseDate;
	private JComboBox<Integer> cmbbxChooseTimeFirst;
	private JComboBox<Integer> cmbbxChooseTimeSecond;
	private JComboBox<String> cmbbxChooseTimeOfDay;
	private JComboBox<String> cmbbxChooseWeather;
	private JComboBox<String> cmbbxChooseDependancy;
	private JCheckBox chckbxChooseTime;

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

		pDep1 = new JPanel();
		pDep1.setBackground(Color.GRAY);
		pDep2 = new JPanel();
		pDep2.setBackground(Color.GRAY);

		cmbbxChooseLane = new JComboBox<String>();
		cmbbxChooseDate = new JComboBox<Date>();
		cmbbxChooseTimeFirst = new JComboBox<Integer>();
		cmbbxChooseTimeSecond = new JComboBox<Integer>();
		cmbbxChooseTimeOfDay = new JComboBox<String>();
		cmbbxChooseWeather = new JComboBox<String>();
		cmbbxChooseDependancy = new JComboBox<String>();
		
		fillComboBoxes();

		cmbbxChooseTimeFirst.setEnabled(false);
		cmbbxChooseTimeSecond.setEnabled(false);
		chckbxChooseTime = new JCheckBox("Choose time");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(cmbbxChooseDependancy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
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
							.addComponent(cmbbxChooseWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(pDep2, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pDep1, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
									.addGap(1)))
							.addGap(37))))
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
						.addComponent(cmbbxChooseWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxChooseDependancy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pDep1, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(pDep2, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addGap(47))
		);
		pDep1.setLayout(new BorderLayout(0, 0));
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
				plot();
			}
		});
		
		cmbbxChooseWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot();
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
		
		cmbbxChooseDependancy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot();
			}
		});
	}

	//*Method that fills all combo boxes**
	private void fillComboBoxes()
	{
		DataBaseInterface dbInterface = new DataBaseInterface();
		String sql_lanes = "SELECT DISTINCT name FROM coursework.lane";
		String sql_dates = "SELECT DISTINCT (CONVERT(lane.date, DATE)) FROM coursework.lane ORDER BY CONVERT(lane.date, DATE)";
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
			
			cmbbxChooseDependancy.addItem("Occupancy(time)");
			cmbbxChooseDependancy.addItem("Headway(speed)");		
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
			sql_query = "SELECT " + x + " , " + y + " FROM lane INNER JOIN weather ON CONVERT(lane.date, DATE) = CONVERT(weather.date,DATE) AND hour(lane.date) = hour(weather.date) WHERE name = ? AND CONVERT(lane.date, DATE) = ? ORDER BY " + x;
			if (cmbbxChooseTimeOfDay.getSelectedItem().toString() != "All day")
				sql_query+="AND timeOfDay = ? ";
			if (cmbbxChooseWeather.getSelectedItem().toString() != "Any weather")
				sql_query+="AND precipType = ?";
			if (chckbxChooseTime.isSelected())
			{
				sql_query+= " AND HOUR(lane.date) BETWEEN ? AND ? ";
			}
			if (x == "date")
			{
				sql_query+=" AND MINUTE(date)%10 = 0";
			}
			return sql_query;
		}
		else
		{
			sql_query = "SELECT " + x + " , " + y + " FROM coursework.lane WHERE name = ?";
				if (x == "lane.date")
				{
					sql_query+=" AND MINUTE(date)%10 = 0 ";
				}

				sql_query+= " AND CONVERT(lane.date, DATE) = ?";
				if (chckbxChooseTime.isSelected())
				{
					sql_query+= " AND HOUR(lane.date) BETWEEN ? AND ? ";
				}
				if (x == "date")
				{
					sql_query+=" AND MINUTE(date)%10 = 0 ";
				}
			return sql_query;
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
	
	private JFreeChart drawGraphT(XYDataset dataset, String xAxis, String yAxis)
	{
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
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
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy HH:mm"));

		return chart;
	}

	//*Method that executes sql query and creates dataset from resultset**
	private XYSeries getDataSetXY(String sql, DataBaseInterface dbInterface, boolean f)
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
				if (!f)
				{
					float speed = rs.getFloat(1)*1000/3600;
					series.add(speed, rs.getFloat(2)*speed);
				}
				else
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
	
	private TimeSeries getDataSetT(String sql, DataBaseInterface dbInterface)
	{
		TimeSeries seriesT = new TimeSeries("Dependency");
		byte i = 3;
		try
		{
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
			while (rs.next())
			{
				seriesT.add(new Millisecond(rs.getTimestamp(1)), rs.getFloat(2));
			}
			pstmt.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return seriesT;
	}	
	
	private double countApprPoint(double[] mult, double a)
	{
		return mult[2]*a*a+mult[1]*a+mult[0];
	}
	
	private void plotXY(String x, String y, boolean f)
	{
		DataBaseInterface dbInterface = new DataBaseInterface();
		String sql = buildSqlQuery(x,y);
		XYSeries series = getDataSetXY(sql, dbInterface, f);
		if (!series.isEmpty())
		{
			XYSeriesCollection seriesCollection= new XYSeriesCollection();
			ApproximationUnit au = new ApproximationUnit();
			double[] polinom = au.LeastSquares(series.toArray(),3,series.getItemCount());
			ArrayList<Float> mX = new ArrayList<Float>();
			ArrayList<Float> mY = new ArrayList<Float>();
			for (int i = 0 ; i<series.getItemCount(); i++)
			{
				
				mX.add(series.getX(i).floatValue());
				mY.add(series.getY(i).floatValue());
			}
			
			XYSeries apprser = new XYSeries("approximation");
			
			double minX = series.getMinX(), 
					maxX = series.getMaxX(),
					step = (maxX-minX)/9;
			
			apprser.add(minX, countApprPoint(polinom, minX));
			for(double i = minX + step;i<maxX;i+=step) {
				apprser.add(i, countApprPoint(polinom, i));
			}
			apprser.add(maxX, countApprPoint(polinom, maxX));
			seriesCollection.addSeries(apprser);
			
			seriesCollection.addSeries(series);
			JFreeChart chart = drawGraph(seriesCollection, x, y);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		    chartPanel.setBackground(Color.white);
		    if (!f)
		    {
			    pDep2.removeAll();
			    pDep2.setLayout(new BorderLayout(0, 0));
			    pDep2.add(chartPanel);
			    pDep2.validate();
		    }
		    else
		    {
			    pDep1.removeAll();
			    pDep1.add(chartPanel, BorderLayout.CENTER);
				pDep1.validate();
		    }
		}
		dbInterface.disconnect();
	}
	
	private void plotOT()
	{
		DataBaseInterface dbInterface = new DataBaseInterface();
		String sql = buildSqlQuery("lane.date", "occupancy");
		TimeSeries seriesT = getDataSetT(sql, dbInterface);
		if (!seriesT.isEmpty())
		{
			TimeSeriesCollection seriesCollection= new TimeSeriesCollection();
			
			ApproximationUnit au = new ApproximationUnit();
			XYSeries seriesSub = new XYSeries("sub");
			for (int i = 0, t = 0; i<seriesT.getItemCount(); i++, t+=10)
				seriesSub.add(t, seriesT.getValue(i));
			
			double[] polinom = au.LeastSquares(seriesSub.toArray(),3,seriesSub.getItemCount());
			ArrayList<Float> mX = new ArrayList<Float>();
			ArrayList<Float> mY = new ArrayList<Float>();
			for (int i = 0 ; i<seriesSub.getItemCount(); i++)
			{
				
				mX.add(seriesSub.getX(i).floatValue());
				mY.add(seriesSub.getY(i).floatValue());
			}
			TimeSeries apprser = new TimeSeries ("approximation");
			int minX = (int)seriesSub.getMinX(), 
					maxX = (int)seriesSub.getMaxX(),
					step = (maxX-minX)/10;
			
			
			long uTime = seriesT.getTimePeriod(0).getFirstMillisecond();
			Date time = new Date(uTime+minX*60*1000);
			apprser.add(new Millisecond(time),countApprPoint(polinom, minX));
			for(int i = minX + step;i<maxX;i+=step) 
			{
				apprser.add(new Millisecond(new Date(uTime+i*60*1000)), countApprPoint(polinom, i));
			}
			apprser.add(new Millisecond(new Date(uTime+maxX*60*1000)), countApprPoint(polinom, maxX));
			seriesCollection.addSeries(apprser);
			seriesCollection.addSeries(seriesT);
			JFreeChart chart = drawGraphT(seriesCollection, "time", "occupancy");
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			pDep2.removeAll();
			pDep2.setLayout(new BorderLayout(0, 0));
			pDep2.add(chartPanel);
	    	pDep2.validate();
		}
	    dbInterface.disconnect();
	}
	
	private void plot()
	{
		plotXY("occupancy","volume",true);
		if (cmbbxChooseDependancy.getSelectedItem().toString()=="Occupancy(time)")
			plotOT();
		else
			plotXY("speed","distance", false);
	}
}
