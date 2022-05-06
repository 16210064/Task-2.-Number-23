package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static String convert = "";


    public static void main(String[] args) {
        JButton FromFile = new JButton("Из файла"), FromInput = new JButton("ввести");
        JFrame frame;

        frame = new JFrame();
        frame.setBounds(0,0, 300,200);

        FromFile.setBounds(25,75, 100,30);
        FromInput.setBounds(175, 75, 100, 30);

        JFileChooser fileChooser = new JFileChooser();

        FromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(frame);
                // Если директория выбрана, покажем ее в сообщении

                if (result == JFileChooser.APPROVE_OPTION ){
                    File file = fileChooser.getSelectedFile();
                    try {
                        Scanner scanner = new Scanner(file);
                        String first = scanner.next(), second = scanner.next();
                        LinkedList FirstList = new LinkedList(), SecondList = new LinkedList();
                        Arrays.stream(first.split(",")).map(Integer::parseInt).forEach(FirstList::Add);
                        Arrays.stream(second.split(",")).map(Integer::parseInt).forEach(SecondList::Add);
                        convert = "";
                        FirstList.SortedMerge(SecondList).forEach((q) -> convert += q + ",");
                        JOptionPane.showMessageDialog(frame,
                                "итоговый список: " + convert);
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "файл не найден");
                    }
                }
            }
        });
        FromInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList FirstList = new LinkedList(), SecondList = new LinkedList();
                int c = Integer.parseInt(ShowDialog(frame, "Ввежеите кол-во", "элементов 1 списка"));
                for (int i = 0; i < c; i++){
                    FirstList.Add(
                            Integer.parseInt(ShowDialog(frame, "ввдите число", ""))
                    );
                }
                c = Integer.parseInt(ShowDialog(frame, "Ввежеите кол-во", "элементов 2 списка"));
                for (int i = 0; i < c; i++){
                    SecondList.Add(
                            Integer.parseInt(ShowDialog(frame, "ввдите число", ""))
                    );
                }

                convert = "";
                FirstList.SortedMerge(SecondList).forEach((q) -> convert += q + ",");
                JOptionPane.showMessageDialog(frame,
                        "итоговый список: " + convert);
            }
        });


        frame.setLayout(null);

        frame.add(FromFile);
        frame.add(FromInput);
        frame.setVisible(true);
    }

    public static String ShowDialog(JFrame frame, String title, String text){
        return JOptionPane.showInputDialog(
                frame,
                text,
                title,
                JOptionPane.QUESTION_MESSAGE);
    }
}
