/**
 * The CourseNameComparator contains a compare method to compare the name of two Course
 * objects. Used to determine the order in which courses are printed out.
 *
 * @author Alexander Leong
 */
package LMS;
import java.util.Comparator;

public class CourseNameComparator implements Comparator {
    /**
     * Overrides the Comparator compare method. Takes in two generic objects and casts them to
     * Courses. Compares the course name of the left object to the course name of the right object. Returns
     * an integer value to indicate their order.
     * @param left
     *      The first course to be compared.
     * @param right
     *      The second course to be compared.
     * @return
     *      Returns -1 if the department / course number of the left object precedes that of the right object.
     *      Returns 0 if the department and course number are exactly the same.
     *      Returns 1 if the department / course number of the right object precedes that of the left object.
     */
    public int compare(Object left, Object right) {
        Course a1 = (Course) left;
        Course b1 = (Course) right;
        if (a1.getDepartment().equals(b1.getDepartment()) &&
                a1.getNumber() == b1.getNumber()) {
            return 0;
        } else {
            if (a1.getDepartment().compareTo(b1.getDepartment()) < 0) {
                return -1;
            }
            if (a1.getDepartment().equals(b1.getDepartment()) &&
                    a1.getNumber() < b1.getNumber()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
