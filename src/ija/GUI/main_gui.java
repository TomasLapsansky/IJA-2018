package ija.GUI;

import ija.Block.Block;
import ija.Block.Point;
import ija.Port.Connection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class main_gui extends Application {

    public static BorderPane layout;
    public static Pane canvas;

    public static MenuBar topMenu;
    public static TreeView<String> leftMenu;

    public static Map<String, Menu> topMenu_menu;
    public static Map<String, MenuItem> topMenu_items;
    public static Map<String, TreeItem<String>> leftMenu_items;

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        Block.Blocks = new HashMap<>();
        Connection.Connections = new HashMap<>();
        Point.Points = new HashMap<>();

        init_main_gui();

        init_top_menu();

        init_left_menu();

        layout.setTop(topMenu);
        layout.setLeft(leftMenu);
        layout.setCenter(canvas);

        primaryStage.setTitle("Blokove schema");
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void init_main_gui() {
        layout = new BorderPane();
        topMenu = new MenuBar();
        //leftMenu = new TreeView<>();
        canvas = new Pane();

        topMenu_menu = new HashMap<>();
        topMenu_items = new HashMap<>();
        leftMenu_items = new HashMap<>();
    }

    private static void init_top_menu() {

        // File menu
        Menu fileMenu = new Menu("File");
        topMenu_menu.put("File", fileMenu);

        MenuItem newFile = new MenuItem("New");
        topMenu_items.put("File_new", newFile);
        fileMenu.getItems().add(newFile);

        MenuItem openFile = new MenuItem("Open");
        topMenu_items.put("File_open", openFile);
        fileMenu.getItems().add(openFile);

        MenuItem saveFile = new MenuItem("Save");
        topMenu_items.put("File_save", saveFile);
        fileMenu.getItems().add(saveFile);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem settingsFile = new MenuItem("Settings");
        topMenu_items.put("File_settings", settingsFile);
        fileMenu.getItems().add(settingsFile);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem exitFile = new MenuItem("Exit");
        topMenu_items.put("File_exit", exitFile);
        fileMenu.getItems().add(exitFile);

        newFile.setOnAction(e -> handlers.file_new());
        openFile.setOnAction(e -> handlers.file_open());
        saveFile.setOnAction(e -> handlers.file_save());
        settingsFile.setOnAction(e -> handlers.file_settings());
        exitFile.setOnAction(e -> handlers.file_exit());

        settingsFile.setDisable(true);

        // Edit menu
        Menu editMenu = new Menu("Edit");
        topMenu_menu.put("Edit", editMenu);

        MenuItem editPoint = new MenuItem("Point");
        topMenu_items.put("Edit_point", editPoint);
        editMenu.getItems().add(editPoint);

        MenuItem editConnection = new MenuItem("Connection");
        topMenu_items.put("Edit_connection", editConnection);
        editMenu.getItems().add(editConnection);

        // Handlers for EDIT
        editPoint.setOnAction(e -> handlers.edit_point(true, null));
        editConnection.setOnAction(e -> handlers.edit_connection(true, null));

        // Add menu
        Menu addMenu = new Menu("Add");
        topMenu_menu.put("Add", addMenu);

        MenuItem addPoint = new MenuItem("Point");
        topMenu_items.put("Add_point", addPoint);
        addMenu.getItems().add(addPoint);

        Menu addBlock = new Menu("Block");
        addMenu.getItems().add(addBlock);

        MenuItem addAdd = new MenuItem("ADD");
        topMenu_items.put("Add_add", addAdd);
        addBlock.getItems().add(addAdd);

        MenuItem addSub = new MenuItem("SUB");
        topMenu_items.put("Add_sub", addSub);
        addBlock.getItems().add(addSub);

        MenuItem addMul = new MenuItem("MUL");
        topMenu_items.put("Add_mul", addMul);
        addBlock.getItems().add(addMul);

        MenuItem addDiv = new MenuItem("DIV");
        topMenu_items.put("Add_div", addDiv);
        addBlock.getItems().add(addDiv);

        MenuItem addConnection = new MenuItem("Connection");
        topMenu_items.put("Add_connection", addConnection);
        addMenu.getItems().add(addConnection);

        // Handlers for ADD
        addPoint.setOnAction(e -> handlers.add_point());
        addAdd.setOnAction(e -> handlers.add_add());
        addSub.setOnAction(e -> handlers.add_sub());
        addMul.setOnAction(e -> handlers.add_mul());
        addDiv.setOnAction(e -> handlers.add_div());
        addConnection.setOnAction(e -> handlers.add_connection());

        // Delete menu
        Menu deleteMenu = new Menu("Delete");
        topMenu_menu.put("Delete", deleteMenu);

        MenuItem deletePoint = new MenuItem("Point");
        topMenu_items.put("Delete_point", deletePoint);
        deleteMenu.getItems().add(deletePoint);

        MenuItem deleteBlock = new MenuItem("Block");
        topMenu_items.put("Delete_block", deleteBlock);
        deleteMenu.getItems().add(deleteBlock);

        MenuItem deleteConnection = new MenuItem("Connection");
        topMenu_items.put("Delete_connection", deleteConnection);
        deleteMenu.getItems().add(deleteConnection);

        deletePoint.setOnAction(e -> handlers.delete_point());
        deleteBlock.setOnAction(e -> handlers.delete_block());
        deleteConnection.setOnAction(e -> handlers.delete_connection());

        //deletePoint.setDisable(true);

        // Run menu
        Menu runMenu = new Menu("Run");
        topMenu_menu.put("Run", runMenu);

        MenuItem runRun = new MenuItem("Run");
        topMenu_items.put("Run_run", runRun);
        runMenu.getItems().add(runRun);

        MenuItem runCycleDet = new MenuItem("Cycle detection");
        topMenu_items.put("Run_cycledet", runCycleDet);
        runMenu.getItems().add(runCycleDet);

        //topMenu.getMenus().addAll(fileMenu, editMenu, addMenu, deleteMenu, runMenu);

        runCycleDet.setOnAction(e -> handlers.run_cycleDetection());
        runRun.setOnAction(e -> handlers.run_run());

        // Run menu
        Menu debugMenu = new Menu("Debug");
        topMenu_menu.put("Debug", debugMenu);

        MenuItem debugDebug = new MenuItem("Debugging");
        topMenu_items.put("Debug_debug", debugDebug);
        debugMenu.getItems().add(debugDebug);

        debugDebug.setOnAction(e -> handlers.debug());

        debugMenu.setDisable(true);

        topMenu.getMenus().addAll(fileMenu, editMenu, addMenu, deleteMenu, runMenu, debugMenu);

    }

    private static void init_left_menu() {

        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);

        leftMenu = new TreeView<>(root);
        leftMenu.setShowRoot(false);

        leftMenu.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(event.getClickCount() == 2)
                {
                    TreeItem<String> item = leftMenu.getSelectionModel().getSelectedItem();

                    if(item.getParent().getValue().equals("Connections")) {
                        handlers.edit_connection(false, Connection.Connections.get(item.getValue()));
                    } else if(item.getParent().getValue().equals("Points")) {
                        handlers.edit_point(false, Point.Points.get(item.getValue()));
                    }

                }
            }
        });

        leftMenu_items.put("Points", makeBranch("Points", root));
        leftMenu_items.put("Blocks", makeBranch("Blocks", root));
        leftMenu_items.put("Connections", makeBranch("Connections", root));

    }

    public static TreeItem<String> makeBranch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        leftMenu_items.put(name, item);

        return item;
    }

    public static void removeBranch(String name, String parent) {
        TreeItem<String> par = main_gui.leftMenu_items.get(parent);
        par.getChildren().remove(main_gui.leftMenu_items.get(name));
    }
}
