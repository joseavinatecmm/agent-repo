package test;

import net.sf.clipsrules.jni.*;
import java.util.List;

public class MidDemo {

	public static void main(String noseusa[]) {
		Environment clips = new Environment();
		try {
			clips.build("(deftemplate person (slot name) (slot age))");

			clips.assertString("(person (name \"Fred\") (age 23))");
			clips.assertString("(person (name \"Sally\") (age 323))");
			clips.assertString("(person (name \"Wally\") (age 400))");

			System.out.println("All people:");
			List<FactAddressValue> people = clips.findAllFacts("person");

			for(FactAddressValue p: people) {
				System.out.println("  " + p.getSlotValue("name"));
			}
		}
		catch(Exception e) {}
	} 
}
