/*
 * Created by JFormDesigner on Tue Dec 04 18:51:11 KST 2018
 */

package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Kim kang min
 */
public class ServerView extends JFrame {

    static List<JButton> buttonList = new ArrayList<>();
    static ServerController serverController = new ServerController();

    static TableModel rowtableModel = new AbstractTableModel() {
        String headerData[] = {"코드입력","PC번호","고객이름","고객ID"};
        String columnData[] = {"","","",""};
        String headers[] = { "Row #", "Column 1", "Column 2", "Column 3", "Column 4", "Column 5" };

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return 4;
        }

        public String getColumnName(int col) {
            return headers[col];
        }

        public Object getValueAt(int row, int col) {
            if(col==0) {
                return headerData[row];
            }
            return columnData[row];
        }

        public void setValueAt(Object aValue, int row, int col){
            if(col==0){

            }
            else{
                columnData[row]=String.valueOf(aValue);
                fireTableCellUpdated(row, col);
            }
        }
    };

    TableColumnModel columnModel = new DefaultTableColumnModel() {
        boolean first = true;

        public void addColumn(TableColumn tc) {
            if (first) {
                first = false;
                return;
            }
            tc.setMinWidth(79);
            super.addColumn(tc);
        }
    };
    //왼쪽
    TableColumnModel rowHeaderModel = new DefaultTableColumnModel() {
        boolean first = true;

        public void addColumn(TableColumn tc) {
            if (first) {
                tc.setMaxWidth(79);
                super.addColumn(tc);
                first = false;
            }
        }
    };

    static TableModel tableModel = new DefaultTableModel() {
        String header[] = {"선불시간","이용한시간","남은시간"};
        String column[] = {"","",""};
        Vector cache;
        @Override
        public int getRowCount() {
            return 2;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(rowIndex==0){
                return header[columnIndex];
            }
            return column[columnIndex];
        }
        @Override
        public void setValueAt(Object aValue, int row, int col){

            column[row]=String.valueOf(aValue);
            fireTableCellUpdated(col, row);

        }

        @Override
        public void fireTableDataChanged() {
            super.fireTableDataChanged();
        }
    };

    TableModel thirdtableModel = new AbstractTableModel() {
        String headerData[] = {"PC사용금액","유 료 게 임","상 품 금 액","정 액 시 간","선 불 금 액","남은충전금액","충전금액지불",
                "남은마일리지","마일리지지불","할 인 금 액","외 상 금 액","받 을 금 액"};
        String columnData[] = {"","","","","","","","","","","",""};
        String headers[] = { "Row #", "Column 1", "Column 2", "Column 3", "Column 4", "Column 5" };

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return 12;
        }

        public String getColumnName(int col) {
            return headers[col];
        }

        public Object getValueAt(int row, int col) {
            if(col==0) {
                return headerData[row];
            }
            return columnData[row];
        }
    };

    TableColumnModel thirdColumnModel = new DefaultTableColumnModel() {
        boolean first = true;

        public void addColumn(TableColumn tc) {
            if (first) {
                first = false;
                return;
            }
            tc.setMinWidth(78);
            super.addColumn(tc);
        }
    };

    TableColumnModel thirdRowHeaderModel = new DefaultTableColumnModel() {
        boolean first = true;

        public void addColumn(TableColumn tc) {
            if (first) {
                tc.setMaxWidth(78);
                super.addColumn(tc);
                first = false;
            }
        }
    };



    public ServerView() {

        super("PC Count Program");
        initComponents();
    }
    private void initComponents() {
        menuBar1 = new JMenuBar();
        List<JMenu> menuList =new ArrayList<>();



        for(int i=0;i<8;i++){
            menuList.add(new JMenu());
        }
        toolBar1 = new JToolBar();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        panel1 = new JPanel();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        panel2 = new JPanel();
        panel3 = new JPanel();
        toolBar2 = new JToolBar();
        button1 = new JButton();
        button2 = new JButton();
        panel4 = new JPanel();
        panel5 = new JPanel();
        scrollPane3 = new JScrollPane();
        table3 = new JTable();
        panel6 = new JPanel();
        scrollPane4 = new JScrollPane();
        table4 = new JTable();
        panel7 = new JPanel();
        scrollPane5 = new JScrollPane();
        table5 = new JTable();


//오른쪽꺼

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        //======== menuBar1 ========
        {
            JMenu menu1=menuList.get(0);
            //======== menu1 ========
            {
                menu1.setText("\uce74\uc6b4\ud130\uc124\uc815");
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            JMenu menu2=menuList.get(1);
            {
                menu2.setText("\uc190\ub2d8PC\uc124\uc815");
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            JMenu menu3=menuList.get(2);
            {
                menu3.setText("\uc778\uc218\uc778\uacc4/\uc6b4\uc601\uad00\ub9ac");
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            JMenu menu4=menuList.get(3);
            {
                menu4.setText("\uc694\uae08/\uc0c1\ud488\uc124\uc815");
            }
            menuBar1.add(menu4);

            //======== menu5 ========
            JMenu menu5=menuList.get(4);
            {
                menu5.setText("\uae30\ud0c0 \uad00\ub9ac");
            }
            menuBar1.add(menu5);

            //======== menu6 ========
            JMenu menu6=menuList.get(5);
            {
                menu6.setText("ASTsoft");
            }
            menuBar1.add(menu6);

            //======== menu7 ========
            JMenu menu7=menuList.get(6);
            {
                menu7.setText("\ucee4\ubba4\ub2c8\ud2f0");
            }
            menuBar1.add(menu7);

            //======== menu8 ========
            JMenu menu8=menuList.get(7);
            {
                menu8.setText("\ub3c4\uc6c0\ub9d0");
            }
            menuBar1.add(menu8);
        }
        setJMenuBar(menuBar1);

        //======== toolBar1 ========
        {

            //---- button3 ----
            button3.setText("text");
            toolBar1.add(button3);

            //---- button4 ----
            button4.setText("text");
            toolBar1.add(button4);

            //---- button5 ----
            button5.setText("text");
            toolBar1.add(button5);

            //---- button6 ----
            button6.setText("text");
            toolBar1.add(button6);

            //---- button7 ----
            button7.setText("text");
            toolBar1.add(button7);

            //---- button8 ----
            button8.setText("text");
            toolBar1.add(button8);

            //---- button9 ----
            button9.setText("text");
            toolBar1.add(button9);
        }
        contentPane.add(toolBar1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(450, 400));

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new GridLayout(8, 64));

        }
        contentPane.add(panel1, BorderLayout.CENTER);
        JTable firstTable = new JTable(rowtableModel,columnModel);
        JTable firstTableColumn = new JTable(rowtableModel, rowHeaderModel);
        CreateRowTable(firstTable,firstTableColumn);
        for(int i=1;i<5;i++) {
            rowtableModel.setValueAt("", 0, i);
        }

        //크기 지정
        firstTable.setRowHeight(22); //왼쪽열 크기지정
        firstTableColumn.setRowHeight(22); //오른쪽열 크기지정

        JViewport jviewPort = new JViewport();
        jviewPort.setView(firstTableColumn);
        jviewPort.setPreferredSize(firstTableColumn.getMaximumSize());
        firstTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(firstTable);

        scrollPane.setRowHeader(jviewPort);
        scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER,firstTableColumn
                .getTableHeader());

        //일반 테이블 2번째꺼
        JTable secondTable = new JTable(tableModel);
        secondTable.setTableHeader(null);
        JScrollPane scrollPane1 = new JScrollPane(secondTable);

        // 세번떄 테이블

        JTable thirdTable = new JTable(thirdtableModel,thirdColumnModel);
        JTable thirdTableColumn = new JTable(thirdtableModel, thirdRowHeaderModel);
        CreateRowTable(thirdTable,thirdTableColumn);
        JViewport thirdViewPort = new JViewport();
        thirdViewPort.setView(thirdTableColumn);
        thirdViewPort.setPreferredSize(thirdTableColumn.getMaximumSize());
        JScrollPane scrollPane2 = new JScrollPane(thirdTable);
        scrollPane2.setRowHeader(thirdViewPort);
        thirdTable.setRowHeight(21);
        thirdTableColumn.setRowHeight(21);

        tableModel =  secondTable.getModel();

        for(int i=0;i<32;i++){
            buttonList.add(new JButton());
            buttonList.get(i).setText(String.valueOf("PC "+(i+1)));
            final int idx  = i+1 ;
            buttonList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(idx+"버튼 클릭");
                    for (int j = 0; j<tableModel.getRowCount()+1; j++) {
                        tableModel.setValueAt("", j, 1);
                        rowtableModel.setValueAt("",j,1);
                    }
                    serverController.SetTextJtable(idx);

                }
            });
            panel1.add(buttonList.get(i));
        }
        //======== panel2 ========
        {
            panel2.setMaximumSize(new Dimension(150, 600));
            panel2.setPreferredSize(new Dimension(200, 400));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

            //======== panel3 ========
            {
                panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                //======== toolBar2 ========
                {
                    toolBar2.setOrientation(SwingConstants.VERTICAL);

                    //---- button1 ----
                    button1.setText("text");
                    toolBar2.add(button1);

                    //---- button2 ----
                    button2.setText("text");
                    toolBar2.add(button2);
                }
                panel3.add(toolBar2);
            }
            panel2.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
                panel4.setPreferredSize(new Dimension(200, 600));

                //======== panel5 ========
                {
                    panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
                    panel5.setPreferredSize(new Dimension(100, 200));
                    //======== scrollPane3 ========
                    {
                        scrollPane.setViewportView(firstTable);
                    }
                    panel5.add(scrollPane);
                }
                panel4.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
                    panel6.setPreferredSize(new Dimension(100, 60));

                    //======== scrollPane4 ========
                    {
                        scrollPane1.setViewportView(secondTable);
                    }
                    panel6.add(scrollPane1);
                }
                panel4.add(panel6);

                //======== panel7 ========
                {
                    panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
                    panel7.setPreferredSize(new Dimension(100, 580));

                    //======== scrollPane5 ========
                    {
                        scrollPane2.setViewportView(thirdTable);
                    }
                    panel7.add(scrollPane2);
                }
                panel4.add(panel7);
            }
            panel2.add(panel4);
        }
        contentPane.add(panel2, BorderLayout.WEST);
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

      JFrame jframe   =  new ServerView();
        jframe.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the window after the close button is clicked.
                System.out.println("close 눌렀음");
                serverController.ServerClose();
                System.exit(0);
            }
        });


    }
    public void CreateRowTable( JTable Table,JTable TableColumn){

        Table.setTableHeader(null);
        Table.createDefaultColumnsFromModel();
        TableColumn.createDefaultColumnsFromModel();

        Table.setSelectionModel(TableColumn.getSelectionModel());
//배경색지정
        TableColumn.setBackground(Color.cyan);
        TableColumn.setColumnSelectionAllowed(false);
        TableColumn.setCellSelectionEnabled(false);
//가운데 정렬
        DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
        tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcmSchedule =TableColumn.getColumnModel();
        TableColumnModel tcmSchedule2 = Table.getColumnModel();
        for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
            tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
            tcmSchedule2.getColumn(i).setCellRenderer(tScheduleCellRenderer);
        }
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Kim kang min
    private JMenuBar menuBar1;

    private JToolBar toolBar1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JPanel panel1;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JPanel panel2;
    private JPanel panel3;
    private JToolBar toolBar2;
    private JButton button1;
    private JButton button2;
    private JPanel panel4;
    private JPanel panel5;
    private JScrollPane scrollPane3;
    private JTable table3;
    private JPanel panel6;
    private JScrollPane scrollPane4;
    private JTable table4;
    private JPanel panel7;
    private JScrollPane scrollPane5;
    private JTable table5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}