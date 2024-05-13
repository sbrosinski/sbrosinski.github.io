package de.b7i;


import de.b7i.ssg.config.SSGConfig;
import de.b7i.ssg.config.SSGConfigReader;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        SSGConfig config = new SSGConfigReader().read("/Users/stephan/code/ssgen/src/main/resources/site/config.properties");

        Main main = new Main();

        ITemplateEngine templateEngine = main.createTemplateEngine();
        String content = main.parse(Main.class.getResourceAsStream("/site/content/post1.md"));

        IContext ssgContext = new SSGContext(content);


        Path outPath = Paths.get(config.getOutDir(), "index.html");
        FileWriter writer = new FileWriter(outPath.toFile());

        templateEngine.process("index", ssgContext, writer);

    }

    private ITemplateEngine createTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/site/templates/");
        templateResolver.setSuffix(".html");

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }


    private String parse(InputStream is) throws IOException {
        Parser parser = Parser.builder().extensions(List.of(YamlFrontMatterExtension.create())).build();

        Node node = parser.parseReader(new InputStreamReader(is));
        YamlFrontMatterVisitor yamlFrontMatterVisitor = new YamlFrontMatterVisitor();

        node.accept(yamlFrontMatterVisitor);

        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();



        return renderer.render(node);
        //Map<String, List<String>> data = yamlFrontMatterVisitor.getData();
        //System.out.println(data.get("title"));
    }
}