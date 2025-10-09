// Generated code produced by taboratory.
// This is here to generate an import statement for PartialParser .
// This is here to generate an import statement for Junction .
// This is here to generate an import statement for Hasher .
// This is here to generate an import statement for IntList .
// This is here to generate an import statement for LongList .
// This is here to generate an import statement for FloatList .
// This is here to generate an import statement for DoubleList .
// This is here to generate an import statement for CharList .
// This is here to generate an import statement for BooleanList .
// This is here to generate an import statement for ObjectList .
package generated;

import com.github.tommyettinger.digital.Base;
import com.github.tommyettinger.digital.Hasher;
import com.github.tommyettinger.digital.TextTools;
import com.github.tommyettinger.ds.BooleanList;
import com.github.tommyettinger.ds.CharList;
import com.github.tommyettinger.ds.DoubleList;
import com.github.tommyettinger.ds.FloatList;
import com.github.tommyettinger.ds.IntList;
import com.github.tommyettinger.ds.Junction;
import com.github.tommyettinger.ds.LongList;
import com.github.tommyettinger.ds.ObjectList;
import com.github.tommyettinger.ds.ObjectObjectOrderedMap;
import com.github.tommyettinger.ds.support.util.PartialParser;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNullByDefault;

@NotNullByDefault
public class Maneuver {
  public static final String[] __headerLine;

  public static final String[] __defaults;

  static {
    __headerLine = new String[]{"name:s^", "rank:i", "efficiency:i", "power:i", "type:s", "status:s", "statusGrade:i", "before:b", "affects:s", "recipient:s", "action:s", "amount", "condition:s", "description:s"};
    __defaults = new String[__headerLine.length];
    Arrays.fill(__defaults, "");
  }

  public String name;

  public int rank;

  public int efficiency;

  public int power;

  public String type;

  public String status;

  public int statusGrade;

  public boolean before;

  public String affects;

  public String recipient;

  public String action;

  public String amount;

  public String condition;

  public String description;

  public long __code;

  public Maneuver() {
    this(__defaults);
  }

  public Maneuver(String name, int rank, int efficiency, int power, String type, String status,
      int statusGrade, boolean before, String affects, String recipient, String action,
      String amount, String condition, String description, long __code) {
    this.name = name;
    this.rank = rank;
    this.efficiency = efficiency;
    this.power = power;
    this.type = type;
    this.status = status;
    this.statusGrade = statusGrade;
    this.before = before;
    this.affects = affects;
    this.recipient = recipient;
    this.action = action;
    this.amount = amount;
    this.condition = condition;
    this.description = description;
    this.__code = __code;
  }

  public Maneuver(String[] fields) {
    this.name = fields[0];
    this.rank = Base.BASE10.readInt(fields[1]);
    this.efficiency = Base.BASE10.readInt(fields[2]);
    this.power = Base.BASE10.readInt(fields[3]);
    this.type = fields[4];
    this.status = fields[5];
    this.statusGrade = Base.BASE10.readInt(fields[6]);
    this.before = !fields[7].isEmpty() && fields[7].charAt(0) == 't';
    this.affects = fields[8];
    this.recipient = fields[9];
    this.action = fields[10];
    this.amount = fields[11];
    this.condition = fields[12];
    this.description = fields[13];
    this.__code = Hasher.stringArrayHashBulk64.hash64(11111111L, fields);
  }

  public static ObjectObjectOrderedMap<String, Maneuver> parseAll(List<String> lines) {
    if (lines == null || lines.isEmpty()) return new ObjectObjectOrderedMap<String, Maneuver>();
    String firstLine = lines.get(0);
    String[] header = TextTools.split(firstLine, "\t");
    if (!Arrays.deepEquals(__headerLine, header)) {
      throw new IllegalArgumentException("Header lines do not match! Expected:\n" +
        TextTools.join("\t", __headerLine) + "\nbut got:\n" + firstLine);
    }
    int numLines = lines.size();
    ObjectObjectOrderedMap<String, Maneuver> all = new ObjectObjectOrderedMap<String, Maneuver>(numLines);
    for (int i = 1; i < numLines; i++) {
      String current = lines.get(i);
      Maneuver parsed = new Maneuver(TextTools.split(current, "\t"));
      all.put(parsed.key(), parsed);
    }
    return all;
  }

  public String key() {
    return name;
  }

  public long longHashCode() {
    return __code;
  }

  public int hashCode() {
    return (int)__code;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Maneuver other = (Maneuver) o;
    if (!Objects.equals(name, other.name)) return false;
    if (rank != other.rank) return false;
    if (efficiency != other.efficiency) return false;
    if (power != other.power) return false;
    if (!Objects.equals(type, other.type)) return false;
    if (!Objects.equals(status, other.status)) return false;
    if (statusGrade != other.statusGrade) return false;
    if (before != other.before) return false;
    if (!Objects.equals(affects, other.affects)) return false;
    if (!Objects.equals(recipient, other.recipient)) return false;
    if (!Objects.equals(action, other.action)) return false;
    if (!Objects.equals(amount, other.amount)) return false;
    if (!Objects.equals(condition, other.condition)) return false;
    if (!Objects.equals(description, other.description)) return false;
    return true;
  }
}
