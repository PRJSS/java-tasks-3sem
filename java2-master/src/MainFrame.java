import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
    private static final int WIDTH = 740;
    private static final int HEIGHT = 480;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldM[] = new JTextField[3];
    private JLabel labelImage;
    private ButtonGroup radioButtonsF = new ButtonGroup();
    private ButtonGroup radioButtonsM = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemRB = Box.createHorizontalBox();
    private int formulaId = 1;
    private Double mem[] = new Double[3];
    private int memid;
    private Toolkit kit;

    public Double calculate1(Double x, Double y, Double z) {
        return (Math.sin(Math.PI * y * y) + Math.log(y * y)) / (Math.sin(Math.PI * z * z) + Math.sin(x) + Math.log(z * z) + x * x + Math.pow(Math.E, Math.cos(z * x)));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (x * x) / (Math.log(Math.pow(z, y)) + Math.pow(Math.cos(Math.pow(x, 1 / 3)), 2));
    }

    private void addRadioButtonF(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                Image im;

                if (formulaId == 1)
                    im = kit.getImage("pictures/func1.png").getScaledInstance(600, 100, Image.SCALE_SMOOTH);
                else
                    im = kit.getImage("pictures/func2.png").getScaledInstance(600, 100, Image.SCALE_SMOOTH);


                ImageIcon i = new ImageIcon();
                i.setImage(im);
                labelImage.setIcon(i);
            }
        });
        radioButtonsF.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButtonM(String buttonName, final int mid) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memid = mid - 1;
            }
        });
        radioButtonsM.add(button);
        hboxMemRB.add(button);
    }

    public MainFrame() {
        super("Strange Calculator");
        mem[0] = (double) 0;
        mem[1] = (double) 0;
        mem[2] = (double) 0;
        setSize(WIDTH, HEIGHT);
        kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonF("Formula 1", 1);
        addRadioButtonF("Formula 2", 2);
        radioButtonsF.setSelected(radioButtonsF.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        hboxMemRB.add(Box.createHorizontalGlue());
        addRadioButtonM("M1", 1);
        addRadioButtonM("M2", 2);
        addRadioButtonM("M3", 3);
        radioButtonsM.setSelected(radioButtonsM.getElements().nextElement().getModel(), true);
        hboxMemRB.add(Box.createHorizontalGlue());

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        JLabel labelForM1 = new JLabel("M1:");
        textFieldM[0] = new JTextField("0", 12);
        textFieldM[0].setMaximumSize(textFieldM[0].getPreferredSize());
        JLabel labelForM2 = new JLabel("M1:");
        textFieldM[1] = new JTextField("0", 12);
        textFieldM[1].setMaximumSize(textFieldM[1].getPreferredSize());
        JLabel labelForM3 = new JLabel("M1:");
        textFieldM[2] = new JTextField("0", 12);
        textFieldM[2].setMaximumSize(textFieldM[2].getPreferredSize());

        Box hboxMem = Box.createHorizontalBox();
        hboxMem.add(Box.createHorizontalGlue());
        hboxMem.add(labelForM1);
        hboxMem.add(Box.createHorizontalStrut(10));
        hboxMem.add(textFieldM[0]);
        hboxMem.add(Box.createHorizontalStrut(70));
        hboxMem.add(labelForM2);
        hboxMem.add(Box.createHorizontalStrut(10));
        hboxMem.add(textFieldM[1]);
        hboxMem.add(Box.createHorizontalStrut(70));
        hboxMem.add(labelForM3);
        hboxMem.add(Box.createHorizontalStrut(10));
        hboxMem.add(textFieldM[2]);
        hboxMem.add(Box.createHorizontalStrut(30));

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(80));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(80));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalStrut(60));

        Box hboxImage = Box.createHorizontalBox();
        labelImage = new JLabel("");
        hboxImage.add(labelImage);


        JLabel labelForResult = new JLabel("Result:");
        textFieldResult = new JTextField("0", 12);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "NumberFormatException", "Error 450",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Clear");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mem[memid] = (double) 0;
                textFieldM[memid].setText(mem[memid].toString());
            }
        });
        JButton MP = new JButton("M+");
        MP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                mem[memid] += Double.parseDouble(textFieldResult.getText());
                textFieldM[memid].setText(mem[memid].toString());
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(MP);
        hboxButtonsM.add(Box.createHorizontalStrut(30));
        hboxButtonsM.add(MC);
        hboxButtonsM.add(Box.createHorizontalGlue());
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxImage);

        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxButtonsM);
        contentBox.add(hboxMemRB);
        contentBox.add(hboxMem);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

        Image im = kit.getImage("pictures/func1.png").getScaledInstance(600, 100, Image.SCALE_SMOOTH);;
        ImageIcon icon = new ImageIcon();
        icon.setImage(im);
        labelImage.setIcon(icon);


    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}