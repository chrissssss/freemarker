package info.balthaus.java;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static info.balthaus.java.OutputUtil.debug;
import static org.junit.Assert.assertEquals;

public class AppTest {

	private static final String KEY1 = "key1";
	private static final String KEY1VAL1 = "k1v1";
	private static final String KEY1VAL2 = "k1v2";
	private static final String KEY1VAL3 = "k1v3";

	private static final String KEY2 = "key2";
	private static final String KEY2VAL1 = KEY1VAL2;

	private static final String JAVA_JAR = "java -jar ";
	private static final String EXPECTED_STRING1 = "001";
	private static final String EXPECTED_STRING2 = JAVA_JAR + EXPECTED_STRING1;
	private static final String LOOKUP = "?seq_index_of(" + KEY2 + ")?string[\"000\"]}";

	private App app;

	private Map<String, Object> getModel() {
		Map<String, Object> result = new HashMap<>();
		result.put(KEY1, Arrays.asList(KEY1VAL1, KEY1VAL2, KEY1VAL3));
		result.put(KEY2, KEY2VAL1);
		return result;
	}

	@Before
	public void setUp() {
		app = new App(getModel());
		System.out.println(getModel());
	}

	@Test
	public void templateUsingDatamodel() throws Exception {
		final String template = "${.data_model[\"" + KEY1 + "\"]" + LOOKUP;
		final String computed = app.compute(template);
		debug(template, computed, EXPECTED_STRING1);
		assertEquals(EXPECTED_STRING1, computed);
	}

	@Test
	public void templateUsingVariable() throws Exception {
		final String template = "${" + App.escape(KEY1) + LOOKUP;
		final String computed = app.compute(template);
		debug(template, computed, EXPECTED_STRING1);
		assertEquals(EXPECTED_STRING1, computed);
	}

	@Test
	public void completeTemplateUsingDatamodel() throws Exception {
		final String template = JAVA_JAR + "${.data_model[\"" + KEY1 + "\"]"+LOOKUP;
		final String computed = app.compute(template);
		debug(template, computed, EXPECTED_STRING2);
		assertEquals(EXPECTED_STRING2, computed);
	}

	@Test
	public void completeTemplateUsingVariable() throws Exception {
		final String template = JAVA_JAR + "${" + App.escape(KEY1) + LOOKUP;
		final String computed = app.compute(template);
		debug(template, computed, EXPECTED_STRING2);
		assertEquals(EXPECTED_STRING2, computed);
	}

	@Test
	public void escapeStringTest() {
		assertEquals("ein\\.test", App.escape("ein.test"));
	}

}
