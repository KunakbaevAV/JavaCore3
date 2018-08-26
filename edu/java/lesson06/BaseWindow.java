package lesson06;

import javax.swing.*;
import java.awt.*;

/**
 * @autor Kunakbaev Artem
 */
class BaseWindow extends JFrame {
    private BaseStudents baseStudents;
    static JTextArea logWindow;

    BaseWindow(BaseStudents baseStudents) throws HeadlessException {
        this.baseStudents = baseStudents;

        setTitle("База студентов");
        setBounds(500, 200, 600, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        addPanelCommands();

        addLogWindow();

        setVisible(true);
    }

    private void addLogWindow() {
        logWindow = new JTextArea();
        logWindow.setLineWrap(true);
        logWindow.setWrapStyleWord(true);
//        logWindow.setEnabled(false);
        logWindow.setText(baseStudents.getLog());
        JScrollPane scroll = new JScrollPane(logWindow);
        add(scroll, BorderLayout.CENTER);
    }

    private void addPanelCommands() {
        JPanel panel = new JPanel(new GridLayout(8, 1));
        JButton btnCreateBD = new JButton("Создать новую базу данных");
        JButton btnInsertStud = new JButton("Добавить нового студента");
        JButton btnUpdateStid = new JButton("Редактировать студента (по id)");
        JButton btnSelectStud = new JButton("Показать студента (по id)");
        JButton btnDeleteStud = new JButton("Удалить студента (по id)");
        JButton btnSelectAll = new JButton("Показать всех студентов");
        JButton btnAddBatch = new JButton("Добавить 1000 студентов");
        JButton btnDeleteAll = new JButton("Удалить базу данных");
        btnCreateBD.addActionListener(e -> {
            baseStudents.createNewBase();
            updateLog();
        });
        btnInsertStud.addActionListener(e -> new StudentWindow(baseStudents, StudentWindow.INSERT));
        btnUpdateStid.addActionListener(e -> new StudentWindow(baseStudents, StudentWindow.UPDATE));
        btnSelectStud.addActionListener(e -> new StudentWindow(baseStudents, StudentWindow.SELECT));
        btnDeleteStud.addActionListener(e -> new StudentWindow(baseStudents, StudentWindow.DELETE));
        btnSelectAll.addActionListener(e -> {
            baseStudents.getAll();
            updateLog();
        });
        btnAddBatch.addActionListener(e -> {
            baseStudents.insertBatch();
            updateLog();
        });
        btnDeleteAll.addActionListener(e -> {
            baseStudents.deleteTable();
            updateLog();
        });
        panel.add(btnCreateBD);
        panel.add(btnInsertStud);
        panel.add(btnUpdateStid);
        panel.add(btnSelectStud);
        panel.add(btnDeleteStud);
        panel.add(btnSelectAll);
        panel.add(btnAddBatch);
        panel.add(btnDeleteAll);
        add(panel, BorderLayout.EAST);
    }

    private void updateLog() {
        logWindow.setText(baseStudents.getLog());
    }
}

class StudentWindow extends JFrame {
    static final int INSERT = 1;
    static final int UPDATE = 2;
    static final int SELECT = 3;
    static final int DELETE = 4;

    private JTextField idText;
    private JTextField nameText;
    private JTextField ratingText;
    private BaseStudents base;
    private Student currentStudent;
    private int mod;

    StudentWindow(BaseStudents base, int mod) throws HeadlessException {
        this.base = base;
        this.mod = mod;
        currentStudent = null;
        setTitle("Добавить нового студента");
        setBounds(550, 250, 200, 150);

        addPannel();

        addButtons();

        setVisible(true);
    }

    private void addButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton ok = new JButton("Ок");
        ok.addActionListener(e -> {
            defaultValues();
            int id = (Integer.parseInt(idText.getText()));
            String name = (nameText.getText());
            int rating = (Integer.parseInt(ratingText.getText()));
            currentStudent = new Student(id, name, rating);

            doCommand(currentStudent);
            BaseWindow.logWindow.setText(base.getLog());

            setVisible(false);
        });
        JButton cancel = new JButton("Отмена");
        cancel.addActionListener(e -> setVisible(false));
        panel.add(cancel);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
    }

    private void defaultValues() {
        if (idText.getText().equals("")) idText.setText("0");
        if (ratingText.getText().equals("")) ratingText.setText("0");
    }

    private void addPannel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel idLabel = new JLabel("id: ");
        JLabel nameLabel = new JLabel("Имя: ");
        JLabel ratiglabel = new JLabel("Балл: ");
        idText = new JTextField();
        nameText = new JTextField();
        ratingText = new JTextField();
        panel.add(idLabel);
        panel.add(idText);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(ratiglabel);
        panel.add(ratingText);
        add(panel, BorderLayout.CENTER);
    }

    private void doCommand(Student student) {
        switch (mod) {
            case 1:
                base.insert(student);
                break;
            case 2:
                base.update(student);
                break;
            case 3:
                int id = Integer.parseInt(idText.getText());
                currentStudent = base.get(id);
                break;
            case 4:
                base.delete(student.getId());
                break;
        }
    }
}
