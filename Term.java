import java.util.Objects;

public class Term implements Comparable, Cloneable {
    private final static char VARIABLE_SYMBOL = 'x';
    private final static char EXPONENT_SYMBOL = '^';
    private final static char PLUS_SYMBOL = '+';
    private final static char MINUS_SYMBOL = '-';

    private int mCoefficient;
    private int mExponent;

    public Term(){
        this.setAll(1, 1);
    }

    public Term(int coefficient, int exponent) {
        this.setAll(coefficient, exponent);
    }

    public Term(Term other) { //copy constructor
        if (other == null)
        {
            throw new NullPointerException();
        }
        else {
            this.setAll(other.getCoefficient(), other.getExponent());
        }
    }

    public Term(String term)
    {
        int coefficient, exponent;

        if (!term.isEmpty()) {

            if (term.contains(Character.toString(VARIABLE_SYMBOL))) {
                String[] splitTerm = term.split(Character.toString(VARIABLE_SYMBOL));

                coefficient = parseCoefficientString(splitTerm[0]);
                if (splitTerm.length == 2) {
                    exponent = parseExponentString(splitTerm[1]);
                } else
                    exponent = 1;
            } else {
                coefficient = parseCoefficientString(term);
                exponent = 0;
            }
        }
        else {
            coefficient = exponent = 0;
        }

        setAll(coefficient, exponent);

    }

    public int getCoefficient() {
        return mCoefficient;
    }

    public void setCoefficient(int coefficient) {
        mCoefficient = coefficient;
    }

    public int getExponent() {
        return mExponent;
    }

    public void setExponent(int exponent) {
        mExponent = exponent;
    }

    public void setAll(int coefficient, int exponent) {
        mCoefficient = coefficient;
        mExponent = exponent;
    }

    private int parseCoefficientString(String coefficient) {
        int c;

        if (coefficient.equals(Character.toString(PLUS_SYMBOL)) || coefficient.isEmpty()) {
            c = 1;
        }
        else if (coefficient.equals(Character.toString(MINUS_SYMBOL))) {
            c = -1;
        }
        else
            c = Integer.parseInt(coefficient);

        return c;
    }

    private int parseExponentString(String exponent) {
        int e;
        exponent = exponent.replaceAll("\\^", "");
        exponent = exponent.replaceAll("\\+", "");
        e = Integer.parseInt(exponent);

        return e;
    }

    public void add(Term newTerm) {
        if (this.getExponent() == newTerm.getExponent()) {
            int c = this.getCoefficient() + newTerm.getCoefficient();
            this.setCoefficient(c);
        }
    }

    @Override
    public String toString() {
        if (mCoefficient == 0) {
            return "";
        }
        else if (mExponent == 0) {
            return (mCoefficient > 0 ? "+" : "") + String.valueOf(mCoefficient);
        }
        String coeffSign = mCoefficient < 0 ? "-" : "+";
        String coefficient = String.valueOf(Math.abs(mCoefficient) != 1 ? Math.abs(mCoefficient) : "");
        String current_term = coeffSign + coefficient + "x";
        String exponent = mExponent != 1 ? EXPONENT_SYMBOL + String.valueOf(mExponent) : "";
        current_term += exponent;
        return current_term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return mCoefficient == term.mCoefficient && mExponent == term.mExponent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCoefficient, mExponent);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Term other))
            throw new ClassCastException();

        int compareCoefficient = mCoefficient - other.mCoefficient;
        if (compareCoefficient != 0)
            return compareCoefficient;

        return mExponent - other.mExponent;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }




}
