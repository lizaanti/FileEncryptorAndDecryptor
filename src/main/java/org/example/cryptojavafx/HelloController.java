package org.example.cryptojavafx;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    @FXML
    TableView<FileInfo> CryptoTable;
    @FXML
    TextField pathField;

    static char[] russianAlphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя".toCharArray();
    static char[] americanAlphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<FileInfo, String> fileNameColumn = new TableColumn<>("Имя");
        fileNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFileName().toString()));
        fileNameColumn.setPrefWidth(240);

        CryptoTable.getColumns().addAll(fileNameColumn);

        updateList(Paths.get(".", "f"));
    }

    public void updateList(Path path) {
        CryptoTable.getItems().clear();
        try {
            pathField.setText(path.normalize().toAbsolutePath().toString());
            CryptoTable.getItems().addAll(Files.list(path).map(FileInfo::new).toList());
            CryptoTable.sort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public static void saveFileToOutputFolder(File file) {
        try {
            //получаем путь к директории проекта
            Path projectDir = Paths.get("C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\f").toAbsolutePath();
            //получаем имя файла
            String fileName = file.getName();

            //создаем путь к новому файлу в папке
            Path newFilePath = projectDir.resolve(fileName);

            //копируем файл в папку
            Files.copy(file.toPath(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Файл сохранен: " + newFilePath);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        String filePath1 = "C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\f";
        //создаем объект File из пути
        File file1 = new File(filePath1);
        openFile(file1);
    }


    public void btnEncryptionAction(ActionEvent actionEvent) throws IOException {

        //чтение строк файла
        String currentLine;
        String dir1 = "C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\f\\inputFile.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dir1));
        List<String> file = new ArrayList<>();
        // Чтение файла
        while ((currentLine = bufferedReader.readLine()) != null) {
            file.add(currentLine.toLowerCase());
        }
        bufferedReader.close();

        // Пишем в файл
        FileWriter fileWriter = new FileWriter("encryptionFile.txt");

        for (String line : file) {
            StringBuilder res1 = new StringBuilder();

            for (char temp : line.toCharArray()) {
                if (Character.isLetter(temp)) {
                    if (Arrays.binarySearch(russianAlphabet, temp) >= 0) {
                        temp += (char) (HelloApplication.getRotn() % russianAlphabet.length);
                        if (temp > 'я') {
                            temp = (char) ((temp - 'а') % ('я' - 'а' + 1) + 'а');
                        } else if (temp < 'а') {
                            temp = (char) (temp - (('я' - 'а') + 1) + ('я' + 1));
                        }
                    } else if (Arrays.binarySearch(americanAlphabet, temp) >= 0) {
                        temp += (char) (HelloApplication.getRotn() % americanAlphabet.length);
                        if (temp > 'z') {
                            temp = (char) (temp % 'z' + 'a' - 1);
                        } else if (temp < 'a') {
                            temp = (char) (temp + (('z' - 'a') + 1));
                        }
                    }
                }
                res1.append(temp); //добавляем зашифрованный символ к результату
            }

            fileWriter.write(res1.toString() + System.lineSeparator()); //пишем строку в файл
        }
        fileWriter.close();
        File newFile = new File("C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\encryptionFile.txt");
        HelloController.saveFileToOutputFolder(newFile);
    }

    public void btnDecryptionAction(ActionEvent actionEvent) throws IOException {
        String currentLine;
        String dir2 = "C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\encryptionFile.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dir2));
        List<String> file = new ArrayList<>();
        // Чтение файла
        while ((currentLine = bufferedReader.readLine()) != null) {
            file.add(currentLine.toLowerCase());
        }
        bufferedReader.close();

        // Пишем в файл
        FileWriter fileWriter = new FileWriter("decryptionFile.txt");
        for (String line : file) {
            StringBuilder res2 = new StringBuilder();
            //проходимся по каждому символу входной строки
            for (char temp : line.toCharArray()) {
                if (Character.isLetter(temp)) {
                    if (Arrays.binarySearch(russianAlphabet, temp) >= 0) {
                        //находим индекс в русском алфавите
                        int index = Arrays.binarySearch(russianAlphabet, temp);
                        //получаем новый индекс с учетом сдвига
                        int newIndex = (index - HelloApplication.getRotn() + russianAlphabet.length) % russianAlphabet.length;
                        //получаем расшифрованный символ
                        temp = russianAlphabet[newIndex];

                    } else if (Arrays.binarySearch(americanAlphabet, temp) >= 0) {
                        //находим индекс в английском алфавите
                        int index = Arrays.binarySearch(americanAlphabet, temp);
                        //получаем новый индекс с учетом сдвига
                        int newIndex = (index - HelloApplication.getRotn() + americanAlphabet.length) % americanAlphabet.length;
                        //получаем расшифрованный символ
                        temp = americanAlphabet[newIndex];
                    }
                }
                res2.append(temp); //добавляем расшифрованный символ к результату
            }
            fileWriter.write(res2.toString() + System.lineSeparator());
        }
        fileWriter.close();
        File newFile = new File("C:\\Users\\lizaa\\IdeaProjects\\CryptoJAVAFX\\decryptionFile.txt");
        HelloController.saveFileToOutputFolder(newFile);
    }
}