package Lab2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
    @SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
    public class MainFrame extends JFrame
    {
        // Главный метод класса
        public static void main(String[] args)
        {
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        // Размеры окна приложения в виде констант
        private static final int WIDTH = 400;
        private static final int HEIGHT = 320;
        private JTextField textFieldX;
        private JTextField textFieldY;
        private JTextField textFieldZ;
        private JTextField textFieldAdd;
        private JTextField textFieldResult;

        private ButtonGroup radioButtons = new ButtonGroup();
        private ButtonGroup radioMemoryButtons = new ButtonGroup();

        private Box hboxFormulaType = Box.createHorizontalBox();
        private Box hboxMemoryType = Box.createHorizontalBox();

        private Double mem1 = 0.0;
        private Double mem2 = 0.0;
        private Double mem3 = 0.0;
        private int formulaId = 1;
        private int memoryId = 1;

        // Формула №1 для рассчѐта
        public Double calculate1(Double x, Double y, Double z)
        {
            return Math.sqrt(Math.sqrt(Math.log(z) + Math.sin(Math.PI * z * z))) /
                    Math.pow((y * y + Math.exp(Math.cos(x)) + Math.sin(y)), Math.sin(x));
        }

        // Формула №2 для рассчѐта
        public Double calculate2(Double x, Double y, Double z)
        {
            return Math.sqrt(y) * 3 * Math.pow(z, x) / Math.sqrt(1 + y * y * y);
        }

        private void addRadioButton(String buttonName, final int formulaId)
        {
            JRadioButton button = new JRadioButton(buttonName);
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev) {
                    MainFrame.this.formulaId = formulaId;
                }
            });
            radioButtons.add(button);
            hboxFormulaType.add(button);
        }

        private void addMemoryRadioButton(String buttonName, final int memoryId)
        {
            JRadioButton button = new JRadioButton(buttonName);

            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event) {
                    MainFrame.this.memoryId = memoryId;
                }
            });
            radioMemoryButtons.add(button);
            hboxMemoryType.add(button);
        }
        // Конструктор класса
        public MainFrame()
        {
            super("Вычисление формулы");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();

            setLocation((kit.getScreenSize().width - WIDTH) / 2,(kit.getScreenSize().height - HEIGHT) / 2);

            hboxFormulaType.add(Box.createHorizontalGlue());
            addRadioButton("Формула 1", 1);
            addRadioButton("Формула 2", 2);
            radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue());
            hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

// Область с полями ввода для X Y Z
            JLabel labelForX = new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());

            JLabel labelForY = new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());
            JLabel labelForZ = new JLabel("Z:");
            textFieldZ = new JTextField("0", 10);
            textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
            Box hboxVariables = Box.createHorizontalBox();
            hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));

            hboxVariables.add(labelForX);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldX);
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForY);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldY);
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForZ);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldZ);

// Область для вывода результата вычислений
            JLabel labelForResult = new JLabel("Результат:");
            labelForResult = new JLabel("Результат:");
            textFieldResult = new JTextField("0", 15);
            textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
            Box hboxResult = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

// Область для кнопок
            JButton buttonCalc = new JButton("Вычислить");
            buttonCalc.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                    try
                    {
                        Double x = Double.parseDouble(textFieldX.getText());
                        Double y = Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                        Double result;
                        if (formulaId == 1)
                            result = calculate1(x, y, z);
                        else
                            result = calculate2(x, y, z);
                        textFieldResult.setText(result.toString());
                    }
                    catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            JButton buttonReset = new JButton("Очистить поля");
            buttonReset.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                    textFieldX.setText("0");
                    textFieldY.setText("0");
                    textFieldZ.setText("0");
                    textFieldResult.setText("0");
                }
            });

            Box hboxButtons = Box.createHorizontalBox();
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.add(buttonCalc);
            hboxButtons.add(Box.createHorizontalStrut(30));
            hboxButtons.add(buttonReset);
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

// Связать области воедино в компоновке BoxLayout
            Box contentBox = Box.createVerticalBox();
            contentBox.add(Box.createVerticalGlue());
            contentBox.add(hboxFormulaType);
            contentBox.add(hboxVariables);
            contentBox.add(hboxResult);
            contentBox.add(hboxButtons);
            contentBox.add(Box.createVerticalGlue());
            getContentPane().add(contentBox, BorderLayout.CENTER);
        }
    }