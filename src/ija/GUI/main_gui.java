package ija.GUI;

import ija.Block.Block;
import ija.Block.Point;
import ija.Port.Connection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Main construction of Graphic user Interface
 * @author Tomas Lapsansky
 * @author Filip Plesko
 */
public class main_gui extends Application {

    private static BorderPane layout;
    public static Pane canvas;

    public static MenuBar topMenu;
    public static TreeView<String> leftMenu;
    public static Map<String, TreeItem<String>> leftMenu_items;

    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts JavaFx
     * @param primaryStage Stage
     */
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

    /**
     * Initialization of main parts of GUI
     */
    private static void init_main_gui() {
        layout = new BorderPane();
        topMenu = new MenuBar();
        canvas = new Pane();

        leftMenu_items = new HashMap<>();
    }

    /**
     * Initialization of Top menu
     */
    private static void init_top_menu() {

        // File menu
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        fileMenu.getItems().add(newFile);

        MenuItem openFile = new MenuItem("Open");
        fileMenu.getItems().add(openFile);

        MenuItem saveFile = new MenuItem("Save");
        fileMenu.getItems().add(saveFile);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem settingsFile = new MenuItem("Settings");
        fileMenu.getItems().add(settingsFile);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem exitFile = new MenuItem("Exit");
        fileMenu.getItems().add(exitFile);

        // Handlers for FILE
        newFile.setOnAction(e -> handlers.file_new());
        openFile.setOnAction(e -> handlers.file_open());
        saveFile.setOnAction(e -> handlers.file_save());
        settingsFile.setOnAction(e -> handlers.file_settings());
        exitFile.setOnAction(e -> handlers.file_exit());

        settingsFile.setDisable(true);

        // Edit menu
        Menu editMenu = new Menu("Edit");

        MenuItem editPoint = new MenuItem("Point");
        editMenu.getItems().add(editPoint);

        MenuItem editConnection = new MenuItem("Connection");
        editMenu.getItems().add(editConnection);

        // Handlers for EDIT
        editPoint.setOnAction(e -> handlers.edit_point(true, null));
        editConnection.setOnAction(e -> handlers.edit_connection(true, null));

        // Add menu
        Menu addMenu = new Menu("Add");

        MenuItem addPoint = new MenuItem("Point");
        addMenu.getItems().add(addPoint);

        Menu addBlock = new Menu("Block");
        addMenu.getItems().add(addBlock);

        MenuItem addAdd = new MenuItem("ADD");
        addBlock.getItems().add(addAdd);

        MenuItem addSub = new MenuItem("SUB");
        addBlock.getItems().add(addSub);

        MenuItem addMul = new MenuItem("MUL");
        addBlock.getItems().add(addMul);

        MenuItem addDiv = new MenuItem("DIV");
        addBlock.getItems().add(addDiv);

        MenuItem addConnection = new MenuItem("Connection");
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

        MenuItem deletePoint = new MenuItem("Point");
        deleteMenu.getItems().add(deletePoint);

        MenuItem deleteBlock = new MenuItem("Block");
        deleteMenu.getItems().add(deleteBlock);

        MenuItem deleteConnection = new MenuItem("Connection");
        deleteMenu.getItems().add(deleteConnection);

        deletePoint.setOnAction(e -> handlers.delete_point());
        deleteBlock.setOnAction(e -> handlers.delete_block());
        deleteConnection.setOnAction(e -> handlers.delete_connection());

        // Run menu
        Menu runMenu = new Menu("Run");

        MenuItem runRun = new MenuItem("Run");
        runMenu.getItems().add(runRun);

        MenuItem runCycleDet = new MenuItem("Cycle detection");
        runMenu.getItems().add(runCycleDet);

        // Handlers for Run
        runCycleDet.setOnAction(e -> handlers.run_cycleDetection());
        runRun.setOnAction(e -> handlers.run_run());

        // Debug menu
        Menu debugMenu = new Menu("Debug");

        MenuItem debugDebug = new MenuItem("Debugging");
        debugMenu.getItems().add(debugDebug);

        debugDebug.setOnAction(e -> handlers.debug());

        debugMenu.setDisable(true);

        topMenu.getMenus().addAll(fileMenu, editMenu, addMenu, deleteMenu, runMenu, debugMenu);

    }

    /**
     * Initialization of left tree view menu
     */
    private static void init_left_menu() {

        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);

        leftMenu = new TreeView<>(root);
        leftMenu.setShowRoot(false);

        // Double click left menu edit checker
        leftMenu.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2)
            {
                TreeItem<String> item = leftMenu.getSelectionModel().getSelectedItem();

                if(item.getParent().getValue().equals("Connections")) {
                    handlers.edit_connection(false, Connection.Connections.get(item.getValue()));
                } else if(item.getParent().getValue().equals("Points")) {
                    handlers.edit_point(false, Point.Points.get(item.getValue()));
                }

            }
        });

        leftMenu_items.put("Points", makeBranch("Points", root));
        leftMenu_items.put("Blocks", makeBranch("Blocks", root));
        leftMenu_items.put("Connections", makeBranch("Connections", root));

    }

    /**
     * Adding to left tree view menu
     * @param name Name of leaf
     * @param parent Name of parent node
     * @return Reference to leaf
     */
    public static TreeItem<String> makeBranch(String name, TreeItem<String> parent) {

        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);

        leftMenu_items.put(name, item);

        return item;
    }

    /**
     * Removing item from left tree view menu
     * @param name Name of leaf
     * @param parent Name of parent node
     */
    public static void removeBranch(String name, String parent) {
        TreeItem<String> par = main_gui.leftMenu_items.get(parent);
        par.getChildren().remove(main_gui.leftMenu_items.get(name));
    }
}
