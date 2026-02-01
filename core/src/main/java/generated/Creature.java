// Generated code produced by taboratory.
// This is here to generate an import statement for PartialParser .
// This is here to generate an import statement for StringJunction .
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
import com.github.tommyettinger.ds.LongList;
import com.github.tommyettinger.ds.ObjectList;
import com.github.tommyettinger.ds.ObjectObjectOrderedMap;
import com.github.tommyettinger.ds.StringJunction;
import com.github.tommyettinger.ds.support.util.PartialParser;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Creature {
  public static final String[] __headerLine;

  public static final String[] __defaults;

  static {
    __headerLine = new String[]{"name:s^", "graphic:s", "toughness:i", "agility:i", "persona:i", "expertise:i", "maneuvers:s[, ]", "close:s", "closeGraphic:s", "closeEffects:s[, ]", "far:s", "farGraphic:s", "farEffects:s[, ]", "buffEffects:s[, ]", "debuffEffects:s[, ]", "description:s", "tags:s[, ]", "pronoun:s", "writing:s", "offenseSayings:s[||]", "hurtSayings:s[||]", "inspireSayings:s[||]", "cautionSayings:s[||]"};
    __defaults = new String[__headerLine.length];
    Arrays.fill(__defaults, "");
  }

  public String name;

  public String graphic;

  public int toughness;

  public int agility;

  public int persona;

  public int expertise;

  public ObjectList<String> maneuvers;

  public String close;

  public String closeGraphic;

  public ObjectList<String> closeEffects;

  public String far;

  public String farGraphic;

  public ObjectList<String> farEffects;

  public ObjectList<String> buffEffects;

  public ObjectList<String> debuffEffects;

  public String description;

  public ObjectList<String> tags;

  public String pronoun;

  public String writing;

  public ObjectList<String> offenseSayings;

  public ObjectList<String> hurtSayings;

  public ObjectList<String> inspireSayings;

  public ObjectList<String> cautionSayings;

  public long __code;

  public Creature() {
    this(__defaults);
  }

  public Creature(String name, String graphic, int toughness, int agility, int persona,
      int expertise, ObjectList<String> maneuvers, String close, String closeGraphic,
      ObjectList<String> closeEffects, String far, String farGraphic, ObjectList<String> farEffects,
      ObjectList<String> buffEffects, ObjectList<String> debuffEffects, String description,
      ObjectList<String> tags, String pronoun, String writing, ObjectList<String> offenseSayings,
      ObjectList<String> hurtSayings, ObjectList<String> inspireSayings,
      ObjectList<String> cautionSayings, long __code) {
    this.name = name;
    this.graphic = graphic;
    this.toughness = toughness;
    this.agility = agility;
    this.persona = persona;
    this.expertise = expertise;
    this.maneuvers = maneuvers;
    this.close = close;
    this.closeGraphic = closeGraphic;
    this.closeEffects = closeEffects;
    this.far = far;
    this.farGraphic = farGraphic;
    this.farEffects = farEffects;
    this.buffEffects = buffEffects;
    this.debuffEffects = debuffEffects;
    this.description = description;
    this.tags = tags;
    this.pronoun = pronoun;
    this.writing = writing;
    this.offenseSayings = offenseSayings;
    this.hurtSayings = hurtSayings;
    this.inspireSayings = inspireSayings;
    this.cautionSayings = cautionSayings;
    this.__code = __code;
  }

  public Creature(String[] fields) {
    this.name = fields[0];
    this.graphic = fields[1];
    this.toughness = Base.BASE10.readInt(fields[2]);
    this.agility = Base.BASE10.readInt(fields[3]);
    this.persona = Base.BASE10.readInt(fields[4]);
    this.expertise = Base.BASE10.readInt(fields[5]);
    this.maneuvers = ObjectList.parse(fields[6], ", ", PartialParser.DEFAULT_STRING);
    this.close = fields[7];
    this.closeGraphic = fields[8];
    this.closeEffects = ObjectList.parse(fields[9], ", ", PartialParser.DEFAULT_STRING);
    this.far = fields[10];
    this.farGraphic = fields[11];
    this.farEffects = ObjectList.parse(fields[12], ", ", PartialParser.DEFAULT_STRING);
    this.buffEffects = ObjectList.parse(fields[13], ", ", PartialParser.DEFAULT_STRING);
    this.debuffEffects = ObjectList.parse(fields[14], ", ", PartialParser.DEFAULT_STRING);
    this.description = fields[15];
    this.tags = ObjectList.parse(fields[16], ", ", PartialParser.DEFAULT_STRING);
    this.pronoun = fields[17];
    this.writing = fields[18];
    this.offenseSayings = ObjectList.parse(fields[19], "||", PartialParser.DEFAULT_STRING);
    this.hurtSayings = ObjectList.parse(fields[20], "||", PartialParser.DEFAULT_STRING);
    this.inspireSayings = ObjectList.parse(fields[21], "||", PartialParser.DEFAULT_STRING);
    this.cautionSayings = ObjectList.parse(fields[22], "||", PartialParser.DEFAULT_STRING);
    this.__code = Hasher.stringArrayHashBulk64.hash64(11111111L, fields);
  }

  public static ObjectObjectOrderedMap<String, Creature> parseAll(List<String> lines) {
    if (lines == null || lines.isEmpty()) return new ObjectObjectOrderedMap<String, Creature>();
    String firstLine = lines.get(0);
    String[] header = TextTools.split(firstLine, "\t");
    if (!Arrays.deepEquals(__headerLine, header)) {
      throw new IllegalArgumentException("Header lines do not match! Expected:\n" +
        TextTools.join("\t", __headerLine) + "\nbut got:\n" + firstLine);
    }
    int numLines = lines.size();
    ObjectObjectOrderedMap<String, Creature> all = new ObjectObjectOrderedMap<String, Creature>(numLines);
    for (int i = 1; i < numLines; i++) {
      String current = lines.get(i);
      Creature parsed = new Creature(TextTools.split(current, "\t"));
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
    Creature other = (Creature) o;
    if (!Objects.equals(name, other.name)) return false;
    if (!Objects.equals(graphic, other.graphic)) return false;
    if (toughness != other.toughness) return false;
    if (agility != other.agility) return false;
    if (persona != other.persona) return false;
    if (expertise != other.expertise) return false;
    if (!Objects.equals(maneuvers, other.maneuvers)) return false;
    if (!Objects.equals(close, other.close)) return false;
    if (!Objects.equals(closeGraphic, other.closeGraphic)) return false;
    if (!Objects.equals(closeEffects, other.closeEffects)) return false;
    if (!Objects.equals(far, other.far)) return false;
    if (!Objects.equals(farGraphic, other.farGraphic)) return false;
    if (!Objects.equals(farEffects, other.farEffects)) return false;
    if (!Objects.equals(buffEffects, other.buffEffects)) return false;
    if (!Objects.equals(debuffEffects, other.debuffEffects)) return false;
    if (!Objects.equals(description, other.description)) return false;
    if (!Objects.equals(tags, other.tags)) return false;
    if (!Objects.equals(pronoun, other.pronoun)) return false;
    if (!Objects.equals(writing, other.writing)) return false;
    if (!Objects.equals(offenseSayings, other.offenseSayings)) return false;
    if (!Objects.equals(hurtSayings, other.hurtSayings)) return false;
    if (!Objects.equals(inspireSayings, other.inspireSayings)) return false;
    if (!Objects.equals(cautionSayings, other.cautionSayings)) return false;
    return true;
  }
}
