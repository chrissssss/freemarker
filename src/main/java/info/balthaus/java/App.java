package info.balthaus.java;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

class App {

	private final Map<String, Object> model;

	public App(Map<String, Object> model) {
		this.model = model;
	}

	public String compute(final String pTemplate) throws Exception {
		final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		final DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
		final DefaultObjectWrapper defaultObjectWrapper = builder.build();
		cfg.setObjectWrapper(defaultObjectWrapper);
		Template t = new Template("templateName", new StringReader(pTemplate), cfg);
		Writer out = new StringWriter();
		t.process(model, out);
		final String transformedTemplate = out.toString();
		return transformedTemplate;
	}

	public static String escape(String s) {
		return s.replace(".", "\\.");
	}
}
