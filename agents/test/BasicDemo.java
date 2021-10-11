package test;

import net.sf.clipsrules.jni.*;

public class BasicDemo {

	public static void main(String args[]) throws Exception {
		Environment clips;

		clips = new Environment();

		clips.eval("(clear)");
		clips.load("/Users/ipepetron/DEVx/clips/clips_jni_051/test/src/test.clp");

		clips.eval("(reset)");
		clips.eval("(facts)");
		clips.run();
	}
}

