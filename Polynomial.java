import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Polynomial {

    private final static char PLUS_SYMBOL = '+';

    private LinkedList<Term> termList;

    public Polynomial()
    {
        this.termList = new LinkedList<>();
    }

    public Polynomial(Polynomial polynomial)
    {
        this.termList = new LinkedList<>();

        if (polynomial != null)
        {
            for (int i = 0; i < polynomial.getNumTerms(); i++)
            {
                this.termList.add(new Term(polynomial.getTerm(i)));
            }
        }
    }

    public Polynomial(String polynomial) {

        this.termList = new LinkedList<>();

        polynomial = polynomial.replaceAll("x\\^0", "");
        polynomial = polynomial.replaceAll(" ", "");
        polynomial = polynomial.replace("^-", "^1/");
        polynomial = polynomial.replace("-", "+-");
        System.out.println(polynomial);

        if (polynomial.contains(Character.toString(PLUS_SYMBOL))) {
            String[] splitPolynomial = polynomial.split("\\+");

            for (String term : splitPolynomial) {
                term = term.replaceAll("\\^1/", "^-");
                System.out.println(term);
                this.termList.add(new Term(term));
            }
        }
        else {
            this.termList.add(new Term(polynomial));
        }
        this.simplifyTerms();
    }

    public void order() {
        termList.sort(Comparator.comparing(Term::getExponent));
        Collections.reverse(termList);
    }

    public void simplifyTerms() {
        this.order();
        LinkedList<Term> newTermList = new LinkedList<>();
        Term currentTerm = new Term(this.getTerm(0));
        for (int i = 1; i < this.getNumTerms(); i++) {
            if (this.getTerm(i).getExponent() != currentTerm.getExponent()) {
                if (currentTerm.getCoefficient() != 0) {
                    newTermList.add(currentTerm);
                }
                currentTerm = new Term(this.getTerm(i));
            } else {
                currentTerm.add(this.getTerm(i));
            }
        }
        if (currentTerm.getCoefficient() != 0) {
            newTermList.add(currentTerm);
        }
        termList = newTermList;
    }

    public void add(Polynomial polynomial) {
        this.termList.addAll(polynomial.termList);
        this.simplifyTerms();
    }

    public void addTerm(Term term) {
        this.termList.add(term);
        this.simplifyTerms();
    }

    public void clear() {
        termList.clear();
    }

    public int getNumTerms() {
        int count = 0;
        for (Term t : termList) {
            count++;
        }
        return count;
    }

    public Term getTerm(int index) {
        return termList.get(index);
    }


    public String toString() {
        if (termList.isEmpty())
            return "0";
        StringBuilder polynomial = new StringBuilder();
        for (Term t : termList) {
            polynomial.append(t);
        }

        if (polynomial.charAt(0)== PLUS_SYMBOL)
            polynomial = new StringBuilder(polynomial.substring(1));

        return polynomial.toString();
    }

}


