package seven.tools.mybatis;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import seven.tools.xml.Dom4jUtils;

public class MybatisGenerator {
	
	public static void produceMybatisConfig(String xmlSrcPaht,String generatorName,String projectPath,String modlePak,String xmlPak,String daoPak,String tableName) throws Exception{
		   InputStream in = MybatisGenerator.class.getClassLoader().getResourceAsStream(generatorName);
		   Document document = Dom4jUtils.readXml(in);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.MODEL_SELECTOR, MybatisGeneratorXML.TARGET_PAK, modlePak);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.MODEL_SELECTOR, MybatisGeneratorXML.TARGET_PRO, projectPath);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.SQLMAP_SELECTOR, MybatisGeneratorXML.TARGET_PAK, xmlPak);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.SQLMAP_SELECTOR, MybatisGeneratorXML.TARGET_PRO, projectPath);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.DAO_SELECTOR,  MybatisGeneratorXML.TARGET_PAK, daoPak);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.DAO_SELECTOR, MybatisGeneratorXML.TARGET_PRO, projectPath);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.TABLE_SELECTOR, MybatisGeneratorXML.TABLE_NAME, tableName);
		   Dom4jUtils.writeXml(document, xmlSrcPaht);
	}
	
	public static void produce(String generatorName) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException{
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
//		   File f = new File(MybatisGenerator.class.getResource("").getPath()); //
		   File f = new File("src/seven/tools/mybatis");
		   String finlePathAndName = f.toString() + "\\"+ generatorName;
		   File configFile = new File(finlePathAndName);
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
	}
	
	public static void main(String[] args) throws Exception {
		produceMybatisConfig(
				"src/seven/tools/mybatis/generatorConfig.xml",
				"generatorConfig.xml",
				"C:\\Users\\lianai-teach-006\\workspace_ec\\WorkTools\\src",
				"com.test", "com.test", "com.test", "user_base");
		produce("generatorConfig.xml");
	}
}
