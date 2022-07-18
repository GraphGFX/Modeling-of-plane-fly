
import org.jfree.chart.ChartFactory;
        import org.jfree.chart.ChartPanel;
        import org.jfree.chart.JFreeChart;
        import org.jfree.chart.plot.PlotOrientation;
        import org.jfree.data.xy.XYSeries;
        import org.jfree.data.xy.XYSeriesCollection;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
public class GraphicsAndFrames extends JPanel {
    public static JTable chart;
    public static JTextPane text;
    int roof = 2*30+1;
    public GraphicsAndFrames() { // Кнопки станів демпфера
        button();
        button2();
        button3();
        setLayout(null);
    }
    // chart1 та chart2 створення таблиці та занесення в неї значень
    public void chart1(Calculate p1) {
        int columnNumber = 10;
        String[] columnNames = {
                "T",
                "Y[0]",
                "Y[2]",
                "Y[4]",
                "X[0]",
                "X[2]",
                "X[4]",
                "DND",
                "DED",
                "DNP",
        };
        String[] Time1 = new String[roof];
        String[] massY1 = new String[roof];
        String[] massY3 = new String[roof];
        String[] massY4 = new String[roof];
        String[] massX1 = new String[roof];
        String[] massX3 = new String[roof];
        String[] massX4 = new String[roof];
        String[] massDND = new String[roof];
        String[] massDED = new String[roof];
        String[] massDNP = new String[roof];

        for (int i = 0; i < p1.roof; i++) { // Перетворюємо з double -> String, оскільки таблиця, не може містити різні типи данних, оскільки "шапка"-String
            Time1[i] = (String.valueOf(p1.Time[i]));
            massY1[i] = (String.valueOf(p1.massY1[i])); massY3[i] = (String.valueOf(p1.massY3[i])); massY4[i] = (String.valueOf(p1.massY4[i]));
            massX1[i] = (String.valueOf(p1.massX1[i])); massX3[i] = (String.valueOf(p1.massX3[i])); massX4[i] = (String.valueOf(p1.massX4[i]));
            massDND[i] = (String.valueOf(p1.massDND[i]));
            massDED[i] = (String.valueOf(p1.massDED[i]));
            massDNP[i] = (String.valueOf(p1.massDNP[i]));
        }
        String[][] data = new String[p1.roof][columnNumber];
        for (int i = 0, j = 0; i < p1.roof; i++, j++) {
            data[i][0] = Time1[j];
            data[i][1] = massY1[j]; data[i][2] = massY3[j]; data[i][3] = massY4[j];
            data[i][4] = massX1[j]; data[i][5] = massX3[j]; data[i][6] = massX4[j];
            data[i][7] = massDND[j];
            data[i][8] = massDED[j];
            data[i][9] = massDNP[j];
        }
        chart = new JTable(data, columnNames);
        chart.setBounds(200, 0, 400,400);
    }
    public void chart2(Calculate p) {
        text = new JTextPane();
        text.setText("a_bal= " + p.a_bal +'\n' +
                "cy_bal= " + p.cy_bal +'\n' +
                "a1= " + p.a1 +'\n' +
                "a2= " + p.a2 +'\n' + "a3= " + p.a3 +'\n' +
                "a4= " + p.a4 +'\n' + "a5= " + p.a5 +'\n' +
                "a6= " + p.a6 +'\n' + "a7= " + p.a7 +'\n' +
                "b1= " + p.b1 +'\n' +
                "b2= " + p.b2 +'\n' + "b3= " + p.b3 +'\n' +
                "b4= " + p.b4 +'\n' + "b5= " + p.b5 +'\n' +
                "b6= " + p.b6 +'\n' + "b7= " + p.b7 +'\n' +
                "C6= " + p.C6+ '\n' + "DE= " + p.DE +'\n' +
                "DN= " + p.b6);
        text.setVisible(true);
        text.setBounds(0,0,200,200);
    }

    /**
     * Створення кнопок станів демпфера
     */
    public void button(){
        JButton b1=new JButton();
        b1.setBounds(75,50,250,50);
        b1.setText("Демпфер вимкнено");
        b1.setVisible(true);
        ActionListener actionListenerB1 = new ActionListenerB();
        b1.addActionListener(actionListenerB1);
        add(b1);
    }
    public void button2(){
        JButton b2=new JButton();
        b2.setBounds(325,50,250,50);
        b2.setText("Демпфер увімкнений");
        b2.setVisible(true);
        ActionListener actionListenerB2 = new ActionListenerB2();
        b2.addActionListener(actionListenerB2);
        add(b2);
    }
    public void button3(){
        JButton b3=new JButton();
        b3.setBounds(575,50,250,50);
        b3.setText("Демпфер увімкнений(2)");
        b3.setVisible(true);
        ActionListener actionListenerB3 = new ActionListenerB3();
        b3.addActionListener(actionListenerB3);
        add(b3);
    }

    /**
     * Додаємо функціонал кнопкам станів та для графіку
     */
    public class ActionListenerB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calculate p= new Calculate();
            p.Calc(); p.Calculate1();
            chart1(p);
            chart2(p);
            JFrame frame2 = new JFrame();
            frame2.setLayout(new FlowLayout(FlowLayout.RIGHT));

            frame2.setTitle("Демпфер вимкнено");

            frame2.setBounds(0, 0, 850, 500);
            frame2.add(text);
            JScrollPane p2 = new JScrollPane();
            p2.setViewportView(chart);
            frame2.add(p2);

            JButton b1= new JButton("Графік");
            b1.setSize(new Dimension(200,100));
            ActionListener actionListener4 = new GraphEventOff();
            b1.addActionListener(actionListener4);
            frame2.add(b1);

            frame2.setVisible(true);
        }}
    public class ActionListenerB2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calculate p = new Calculate();
            p.Calc(); p.Calculate2();
            chart1(p);
            chart2(p);
            JFrame frame2 = new JFrame();
            frame2.setLayout(new FlowLayout(FlowLayout.RIGHT));

            frame2.setTitle("Демпфер увімкнений");

            frame2.setBounds(0, 0, 850, 500);
            frame2.add(text);
            JScrollPane p2= new JScrollPane();
            p2.setViewportView(chart);
            frame2.add(p2);
            JButton b1= new JButton("Графік");
            b1.setSize(new Dimension(200,100));
            ActionListener actionListener5 = new GraphEventOn();
            b1.addActionListener(actionListener5);
            b1.setVisible(true);
            frame2.add(b1);
            frame2.setVisible(true);
        }}
    public class ActionListenerB3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calculate p = new Calculate();
            p.Calc(); p.Calculate3();
            chart1(p);
            chart2(p);
            JFrame frame2 = new JFrame();
            frame2.setLayout(new FlowLayout(FlowLayout.RIGHT));

            frame2.setTitle("Демпфер увімкнений(2)");

            frame2.setBounds(0, 0, 850, 500);
            frame2.add(text);
            JScrollPane p2= new JScrollPane();
            p2.setViewportView(chart);
            frame2.add(p2);
            JButton b1= new JButton("Графік");
            b1.setSize(new Dimension(200,100));
            ActionListener actionListener6 = new GraphEventOnSec();
            b1.addActionListener(actionListener6);
            b1.setVisible(true);
            frame2.add(b1);
            frame2.setVisible(true);
        } }

    /**
     * Створюємо графік для кожного зі станів демпфера
     */
    public class GraphEventOff implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double Time[] = new double[roof];
            double ny[] = new double[roof];
            Calculate p5 = new Calculate();
            p5.DT=0.01; // Крок інтегрування
            p5.Calc();
            p5.Calculate1();

            for(int i=0;i<roof;i++){
                Time[i]=p5.Time[i];
                ny[i]=p5.massY3[i];
            }


            XYseries XYseries3 = new XYSeries("Графік Y1(T)");

            for (int i = 0; i < Time.length; i++) { //графік від t та y
                XYseries3.add(Time[i], ny[i]);
            }

            XYSeriesCollection data3 = new XYSeriesCollection(XYseries3);
            JFreeChart chart = ChartFactory.createXYLineChart("Демпфер вимкнено", "T", "Y1", data3, PlotOrientation.VERTICAL, true, true, false);

            ChartPanel chartPanel3 = new ChartPanel(chart);
            chartPanel3.setSize(new java.awt.Dimension(500, 270));
            JFrame z3 = new JFrame();
            z3.setBounds(50,50,500,500);
            z3.add(chartPanel3);
            z3.setVisible(true);
        } }

    public class GraphEventOn implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calculate p6 = new Calculate();
            p6.DT = 0.01; // Крок інтегрування
            p6.Calc();
            p6.Calculate2();
            double Time[] = new double[roof];
            double ny[] = new double[roof];
            for(int i=0;i<roof;i++){
                Time[i]=p6.Time[i];
                ny[i]=p6.massY1[i];
            }
            XYSeries XYseries3 = new XYSeries("Графік Y1(T)");

            for (int i = 0; i < Time.length; i++) { //графік від часу та ny
                XYseries3.add(Time[i], ny[i]);
            }

            XYSeriesCollection data3 = new XYSeriesCollection(XYseries3);
            JFreeChart chart = ChartFactory.createXYLineChart("Демпфер увімкнено", "T", "Y", data3, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel chartPanel3 = new ChartPanel(chart);
            chartPanel3.setSize(new java.awt.Dimension(500, 270));
            JFrame z3 = new JFrame();
            z3.setBounds(50,50,500,500);
            z3.add(chartPanel3);
            z3.setVisible(true);
        } }

    public class GraphEventOnSec implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Calculate p7 = new Calculate();
            p7.DT = 0.01; // Крок інтегрування
            p7.Calc();
            p7.Calculate3();
            double Time[] = new double[roof];
            double ny[] = new double[roof];
            for(int i=0;i<roof;i++){
                Time[i]=p7.Time[i];
                ny[i]=p7.massY1[i];
            }
            XYSeries XYseries3 = new XYSeries("Графік Y(T)");

            for (int i = 0; i < Time.length; i++) { //графік від часу та ny
                XYseries3.add(Time[i], ny[i]);
            }

            XYSeriesCollection data3 = new XYSeriesCollection(XYseries3);
            JFreeChart chart = ChartFactory.createXYLineChart("Демпер увімкнено(2)", "T", "Y", data3, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel chartPanel3 = new ChartPanel(chart);
            chartPanel3.setSize(new java.awt.Dimension(500, 270));
            JFrame z3 = new JFrame();
            z3.setBounds(50,50,500,500);
            z3.add(chartPanel3);
            z3.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }}



