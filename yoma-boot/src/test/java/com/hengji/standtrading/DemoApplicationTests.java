package com.github.yoma;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

    @Test
    public void generateAsciiDocs() throws Exception {
        // 输出Ascii格式
        Swagger2MarkupConfig config =
//            new Swagger2MarkupConfigBuilder().withMarkupLanguage(MarkupLanguage.ASCIIDOC).build();
            new Swagger2MarkupConfigBuilder().withMarkupLanguage(MarkupLanguage.MARKDOWN).build();
        // 输出到单个文件
        // 如果不想分割结果文件，也可以通过替换toFolder(Paths.get("src/docs/asciidoc/generated")
        // 为toFile(Paths.get("src/docs/asciidoc/generated/all"))，
        // 将转换结果输出到一个单一的文件中，这样可以最终生成html的也是单一的
        Swagger2MarkupConverter.from(new URL("http://localhost:8282/v2/api-docs")).withConfig(config).build()
            .toFile(Paths.get("src/docs/asciidoc/generated/all"));
    }

}
