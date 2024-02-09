package org.example;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.constraints.Constraint;


import java.util.stream.IntStream;

public class Overwiev {

    public static void main(String[] args) {

        Model model = new Model("Math model");
        var n = 9;

        IntVar S = model.intVar("S", 1, n, false);
        IntVar M = model.intVar("M", 1, n, false);
        IntVar E = model.intVar("E", 0, n, false);
        IntVar N = model.intVar("N", 0, n, false);
        IntVar D = model.intVar("D", 0, n, false);
        IntVar O = model.intVar("O", 0, n, false);
        IntVar R = model.intVar("R", 0, n, false);
        IntVar Y = model.intVar("Y", 0, n, false);

        model.allDifferent(S, M, E, N, D, O, R, Y).post();



        BoolVar[] r = model.boolVarArray(3);

        D.add(E).eq(Y.add(r[0].mul(10))).post();
        r[0].add(N).add(R).eq(E.add(r[1].mul(10))).post();
        r[1].add(E).add(O).eq(N.add(r[2].mul(10))).post();
        r[2].add(S).add(M).eq(O.add(M.mul(10))).post();


        Solution solution = model.getSolver().findSolution();
        if (solution != null) {
            //System.out.println(solution.toString());
        }

            //model.getSolver().showStatistics();
            if (solution.exists()) {
                System.out.printf("%s = %d\n", S.getName(), S.getValue());
                System.out.printf("%s = %d\n", E.getName(), E.getValue());
                // ...
            }


    }
}