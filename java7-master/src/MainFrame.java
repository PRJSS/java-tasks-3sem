
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class
MainFrame extends JFrame {

    private boolean turn = true;
    private ButtonGroup radioButtons = new ButtonGroup ();
    private static final String FRAME_TITLE = "Клиент мгновенных сообщений";

    private static final int FRAME_MINIMUM_WIDTH = 500;
    private static final int FRAME_MINIMUM_HEIGHT = 500;

    private static final int FROM_FIELD_DEFAULT_COLUMNS = 10;
    private static final int TO_FIELD_DEFAULT_COLUMNS = 20;

    private static final int INCOMING_AREA_DEFAULT_ROWS = 10;
    private static final int OUTGOING_AREA_DEFAULT_ROWS = 5;

    private static final int SMALL_GAP = 5;
    private static final int MEDIUM_GAP = 10;
    private static final int LARGE_GAP = 15;

    private static final int SERVER_PORT = 4567;

    private final JTextField textFieldFrom;
    private final JTextField textFieldTo;

    private final JTextArea textAreaIncoming;
    private final JTextArea textAreaOutgoing;

    String curStringDate;

    public MainFrame() {
        super(FRAME_TITLE);
        setMinimumSize(
                new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
        // Центрирование окна
        final Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                (kit.getScreenSize().height - getHeight()) / 2);

        // Текстовая область для отображения полученных сообщений
        textAreaIncoming = new JTextArea(INCOMING_AREA_DEFAULT_ROWS, 0);
        textAreaIncoming.setEditable(false);

        // Контейнер, обеспечивающий прокрутку текстовой области
        final JScrollPane scrollPaneIncoming =
                new JScrollPane(textAreaIncoming);

        // Подписи полей
        final JLabel labelFrom = new JLabel("Подпись");
        final JLabel labelTo = new JLabel("Получатель");

// Поля ввода имени пользователя и адреса получателя
        textFieldFrom = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);
        textFieldTo = new JTextField(TO_FIELD_DEFAULT_COLUMNS);

        // Текстовая область для ввода сообщения
        textAreaOutgoing = new JTextArea(OUTGOING_AREA_DEFAULT_ROWS, 0);

// Контейнер, обеспечивающий прокрутку текстовой области
        final JScrollPane scrollPaneOutgoing =
                new JScrollPane(textAreaOutgoing);

        // Панель ввода сообщения
        final JPanel messagePanel = new JPanel();
        messagePanel.setBorder(
                BorderFactory.createTitledBorder("Сообщение"));


        // Кнопка отправки сообщения
        final JButton buttonSend = new JButton("Отправить");
        buttonSend.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        ///////////////////////////////////////////////////////////////////////////
        final ButtonGroup myButtons = new ButtonGroup();

        JRadioButton radio1 = new JRadioButton ("Вкл.",true);
        myButtons.add (radio1);
        radio1.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                if(!turn){
                    turn = true;
                    textAreaIncoming.append("Клиент включен" + "\n");
                    buttonSend.setEnabled(true);}
            }
        });

        JRadioButton radio2 = new JRadioButton ("Выкл.",true);
        myButtons.add (radio2);
        radio2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(turn){
                    turn = false;
                    textAreaIncoming.append("Клиент выключен" + "\n");
                    buttonSend.setEnabled(false);}
            }
        });

        // Компоновка элементов панели "Сообщение"
        final GroupLayout layout2 = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout2);

        layout2.setHorizontalGroup(layout2.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout2.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout2.createSequentialGroup()
                                .addComponent(labelFrom)
                                .addGap(SMALL_GAP)
                                .addComponent(textFieldFrom)
                                .addGap(LARGE_GAP)
                                .addComponent(labelTo)
                                .addGap(SMALL_GAP)
                                .addComponent(textFieldTo))
                        .addComponent(scrollPaneOutgoing)
                        .addComponent(radio1)
                        .addComponent(radio2)
                        .addComponent(buttonSend))
                .addContainerGap());
        layout2.setVerticalGroup(layout2.createSequentialGroup()
                .addContainerGap()

                .addGroup(layout2.createParallelGroup(Alignment.BASELINE)
                        .addComponent(labelFrom)
                        .addComponent(textFieldFrom)
                        .addComponent(labelTo)
                        .addComponent(textFieldTo))
                .addGap(MEDIUM_GAP)
                .addComponent(scrollPaneOutgoing)
                .addGap(MEDIUM_GAP)
                .addComponent(buttonSend)
                .addComponent(radio1)
                .addComponent(radio2)
                .addContainerGap());

        // Компоновка элементов фрейма
        final GroupLayout layout1 = new GroupLayout(getContentPane());
        setLayout(layout1);

        layout1.setHorizontalGroup(layout1.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout1.createParallelGroup()
                        .addComponent(scrollPaneIncoming)
                        .addComponent(messagePanel))
                .addContainerGap());
        layout1.setVerticalGroup(layout1.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneIncoming)
                .addGap(MEDIUM_GAP)
                .addComponent(messagePanel)
                .addContainerGap());

        // Создание и запуск потока - обработчика запросов
        new Thread(new Runnable() {


            public void run() {
                try {
//прием сооб
                    final ServerSocket serverSocket =  //Создает серверный сокет, не привязанный к конкретному адресу
                            new ServerSocket(SERVER_PORT);
                    while (!Thread.interrupted()) {
                        final Socket socket = serverSocket.accept();
                        final DataInputStream in = new DataInputStream(
                                socket.getInputStream());

                        // Читаем имя отправителя
                        final String senderName = in.readUTF();

                        // Читаем сообщение
                        final String message = in.readUTF();

                        // Выделяем IP-адрес
                        final String address =
                                ((InetSocketAddress) socket
                                        .getRemoteSocketAddress())
                                        .getAddress()
                                        .getHostAddress();

                        // Закрываем соединение
                        socket.close();
// Выводим сообщение в текстовую область

                        if (turn==true){
                            textAreaIncoming.append(senderName +
                                    " (" + address + "):" + message + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в работе сервера", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }).start();
    }

    private Object newJScrollPane(JTextArea textAreaOutgoing2) {
        // TODO Auto-generated method stub
        return null;
    }

    private void sendMessage() {
        try {
            // Получаем необходимые параметры
            final String senderName = textFieldFrom.getText().trim();
            final String destinationAddress = textFieldTo.getText().trim();
            final String message = textAreaOutgoing.getText().trim();

// Убеждаемся, что поля не пустые
            if (senderName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Введите имя отправителя", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (destinationAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Введите адрес узла-получателя", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        " Введите текст сообщения", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Создаем сокет для соединения
            final Socket socket =  new Socket(destinationAddress, SERVER_PORT);

// Открываем поток вывода данных
            final DataOutputStream out =  new DataOutputStream(socket.getOutputStream());

            // Записываем в потоки
            out.writeUTF(senderName);

            // Записываем в поток сообщение
            out.writeUTF(message);

            // Закрываем сокет
            socket.close();

// Помещаем сообщения в текстовую область вывода
            if (turn==true){
                textAreaIncoming.append("Я -> " + destinationAddress + ": "
                        + message + "\n");
            }

// Очищаем текстовую область ввода сообщения
            textAreaOutgoing.setText("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Не удалось отправить сообщение: узел-адресат не найден",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Не удалось отправить сообщение", "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            //
            public void run() {
                final MainFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}