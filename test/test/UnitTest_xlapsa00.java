package test;

import ija.Block.*;
import ija.Port.*;

import org.junit.*;

public class UnitTest_xlapsa00 {

    private Block ADD;
    private Block SUB;
    private Block MUL;
    private Block DIV;

    @Before
    public void setUp() {

        ADD = new ADD_Block("scitanie");
        SUB = new SUB_Block("Odcitanie");
        MUL = new MUL_Block("NASOBENIE");
        DIV = new DIV_Block("d e l e n i e");

    }

    @Test
    public void Simple_Block_name_test() {

        Assert.assertEquals("ADD name", "scitanie", ADD.getName());
        Assert.assertEquals("ADD name", "Odcitanie", SUB.getName());
        Assert.assertEquals("ADD name", "NASOBENIE", MUL.getName());
        Assert.assertEquals("ADD name", "d e l e n i e", DIV.getName());

        DIV.Rename("delenie");

        Assert.assertEquals("Block rename", "delenie", DIV.getName());

    }

    @Test
    public void Simple_Block_addIO_test() {

        ADD.AddInput("A", 2.0);
        ADD.AddInput("B", 4);

        ADD.AddOutput("X");
        ADD.AddOutput("Y");

        Assert.assertEquals("Add Input test 01", 2, ADD.getInput("A").getValue(), 0.01);
        Assert.assertEquals("Add Input test 02", 4, ADD.getInput("B").getValue(), 0.01);
        Assert.assertNull("Non-existing Input", ADD.getInput("C"));

        Assert.assertEquals("Add Output test 01", "X", ADD.getOutput("X").getName());
        Assert.assertEquals("Add Output test 02", "Y", ADD.getOutput("Y").getName());
        Assert.assertNull("Non-existing Output", ADD.getOutput("Z"));

    }

    @Test
    public void Simple_Block_removeIO_test() {

        ADD.RemoveInput("A");
        ADD.RemoveInput("B");

        ADD.RemoveOutput("X");
        ADD.RemoveOutput("Y");

        Assert.assertNull("Remove Input A test", ADD.getInput("A"));
        Assert.assertNull("Remove Input B test", ADD.getInput("B"));

        Assert.assertNull("Remove Output X test", ADD.getOutput("X"));
        Assert.assertNull("Remove Output Y test", ADD.getOutput("Y"));

    }

    private void Fill_IO_Default(Block obj) {

        obj.AddInput("A", 10);
        obj.AddInput("B", 2);

        obj.AddOutput("X");
        obj.AddOutput("Y");

    }

    private void Clean_IO_Default(Block obj) {

        obj.RemoveInput("A");
        obj.RemoveInput("B");

        obj.RemoveOutput("X");
        obj.RemoveOutput("Y");

    }

    @Test
    public void Simple_ADD_Block_result_test() {

        Fill_IO_Default(ADD);

        ADD.result();

        Assert.assertEquals("ADD result test 01", 12, ADD.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("ADD result test 02", 12, ADD.getOutput("Y").getValue(), 0.01);

        ADD.getInput("A").setValue(11);
        ADD.result();

        Assert.assertEquals("ADD result test 03", 13, ADD.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("ADD result test 04", 13, ADD.getOutput("Y").getValue(), 0.01);

        Clean_IO_Default(ADD);
    }

    @Test
    public void Simple_SUB_Block_result_test() {

        Fill_IO_Default(SUB);

        SUB.result();

        Assert.assertEquals("SUB result test 01", 8, SUB.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("SUB result test 02", 8, SUB.getOutput("Y").getValue(), 0.01);

        SUB.getInput("A").setValue(11);
        SUB.result();

        Assert.assertEquals("SUB result test 03", 9, SUB.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("SUB result test 04", 9, SUB.getOutput("Y").getValue(), 0.01);

        Clean_IO_Default(SUB);
    }

    @Test
    public void Simple_MUL_Block_result_test() {

        Fill_IO_Default(MUL);

        MUL.result();

        Assert.assertEquals("MUL result test 01", 20, MUL.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("MUL result test 02", 20, MUL.getOutput("Y").getValue(), 0.01);

        MUL.getInput("A").setValue(11);
        MUL.result();

        Assert.assertEquals("MUL result test 03", 22, MUL.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("MUL result test 04", 22, MUL.getOutput("Y").getValue(), 0.01);

        Clean_IO_Default(MUL);
    }

    @Test
    public void Simple_DIV_Block_result_test() {

        Fill_IO_Default(DIV);

        DIV.result();

        Assert.assertEquals("DIV result test 01", 5, DIV.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("DIV result test 02", 5, DIV.getOutput("Y").getValue(), 0.01);

        DIV.getInput("A").setValue(11);
        DIV.result();

        Assert.assertEquals("DIV result test 03", 5.5, DIV.getOutput("X").getValue(), 0.01);
        Assert.assertEquals("DIV result test 04", 5.5, DIV.getOutput("Y").getValue(), 0.01);

        Clean_IO_Default(DIV);
    }

    @Test
    public void Simple_Connection_test() {

        Fill_IO_Default(ADD);
        Fill_IO_Default(SUB);

        // Have to create new connection
        Connection ADD_SUB_A = new Connection(ADD.getOutput("X"), SUB.getInput("A"));

        Assert.assertNotEquals("Connection before send test", ADD.getOutput("X").getValue(), SUB.getInput("A").getValue(), 0.01);

        ADD.result();

        Assert.assertEquals("Connection sending test", ADD.getOutput("X").getValue(), SUB.getInput("A").getValue(), 0.01);

    }
}
