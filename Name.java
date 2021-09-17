public class Name {
    private String first;
    private String last;

    public Name() {
        this("","");
    }

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Name(Name origName) {
        this.first = origName.first;
        this.last = origName.last;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Name) {
            Name other = (Name) obj;
            return this.first.equals(other.first) && this.last.equals(other.last);
        }
        return false;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }

    public static Name read(java.util.Scanner sc) {
        if (sc.hasNext()) {
            Name newName = new Name(sc.next(), sc.next());
            return newName;
        }
        return null;
    }
}
