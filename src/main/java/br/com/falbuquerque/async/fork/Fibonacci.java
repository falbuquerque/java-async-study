package br.com.falbuquerque.async.fork;

class Fibonacci {

    Long recursiveSolve(final Long n) {

        if (n == null) {
            return null;
        } 
        
        if (n <= 1) {
            return n;
        }

        return recursiveSolve(n - 1) + recursiveSolve(n - 2);
    }

    Long solve(final Long n) {
        
        if (n == null) {
            return null;
        } 
        
        if (n <= 1) {
            return n;
        }
        
        Long fNLess1 = 1L;
        Long fNLess2 = 0L;
        Long fN = 0L;
        
        for (int i = 2; i <= n; i++) {
            fN = fNLess1 + fNLess2;
            fNLess2 = fNLess1;
            fNLess1 = fN;
        }
        
        return fN;
    }
    
}
