package org.example;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import org.chocosolver.solver.Solver;



import java.util.List;

/**
 * Trivial example showing how to use Choco Solver
 * to solve the equation system
 * x + y < 5
 * x * y = 4
 * with x in [0,5] and y in {2, 3, 8}
 *
 * @author Charles Prud'homme, Jean-Guillaume Fages
 * @since 9/02/2016
 */
public class Main {
    public static Model model;
    public static IntVar[] vars;
    public static String[] varNames = new String[] {"type", "pdc", "fuel", "skibag", "4-wheel"};
    public static void main(String[] args) {

        fastDiag();

    }

    public static void createVariables() {  // code provided by prof
        // 1 - city , 2 - limo , 3 - combi, 4 - xdrive
        IntVar type = model.intVar(varNames[0], new int[]{1, 2, 3, 4});
        // 0 - nno , 1 - yes
        IntVar pdc = model.intVar(varNames[1], new int[]{0, 1});
        // 1 - 4l, 2 - 6l, 3 - 10l
        IntVar fuel = model.intVar(varNames[2], new int[]{1, 2, 3});
        // Other variables
        // 0 - no, 1 - yes
        IntVar skibag = model.intVar(varNames[3], new int[]{0, 1});
        // 0 - no, 1 - yes
        IntVar wheel_4 = model.intVar(varNames[4], new int[]{0, 1});


        vars = new IntVar[] {type, pdc, fuel, skibag, wheel_4};
    }

    public static void createKB_Approach1() {

        //c1
        model.ifThen(
                model.arithm(vars[4],"=",1),
                model.arithm(vars[0],"=",4)
        );
        //c2
        model.ifThen(
                model.arithm(vars[3],"=",1),
                model.arithm(vars[0],"!=",1)
        );
        //c3
        model.ifThen(
                model.arithm(vars[2],"=",1),
                model.arithm(vars[0],"=",1)
        );
        //c4
        model.ifThen(
                model.arithm(vars[2],"=",2),
                model.arithm(vars[0],"!=",4)
        );

    }



    static void fastDiag() {

        model = new Model("FastDiag");
        createVariables();
        createKB_Approach1();

        //model.arithm(vars[0],"=",3).post();

        model.arithm(vars[2],"=",1).post();

        model.arithm(vars[2],"=",1).post();

        //model.arithm(vars[4],"=",1).post();

        System.out.println(isConsistent(model));





    }

    static boolean isConsistent(Model model)
    {

        Solver solver = model.getSolver();
        return solver.solve();
    }
}

