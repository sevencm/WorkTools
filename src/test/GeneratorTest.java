package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import seven.tools.mybatis.MybatisGeneratorXML;
import seven.tools.xml.Dom4jUtils;
import seven.tools.xml.Dom4jExample;

public class GeneratorTest {
	public static void main(String[] args) throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException, URISyntaxException, DocumentException {
		new GeneratorTest().run();
	}
	
	public  void  run() throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException, URISyntaxException, DocumentException{
		   List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File f = new File(GeneratorTest.class.getResource("").getPath());
		   String finlePathAndName = f.toString() + "\\generatorConfig.xml";
		   InputStream in = Dom4jExample.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
		   Document document = Dom4jUtils.readXml(in);
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.MODEL_SELECTOR, MybatisGeneratorXML.TARGET_PAK, "com.t");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.MODEL_SELECTOR, MybatisGeneratorXML.TARGET_PRO, "/work");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.SQLMAP_SELECTOR, MybatisGeneratorXML.TARGET_PAK, "com.t");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.SQLMAP_SELECTOR, MybatisGeneratorXML.TARGET_PRO, "/work");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.DAO_SELECTOR,  MybatisGeneratorXML.TARGET_PAK, "com.t");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.DAO_SELECTOR, MybatisGeneratorXML.TARGET_PRO, "/work");
		   Dom4jUtils.modifyXMLAttribute(document, MybatisGeneratorXML.TABLE_SELECTOR, MybatisGeneratorXML.TABLE_NAME, "user_base");
		   Dom4jUtils.writeXml(document, finlePathAndName);
		   
		   File configFile = new File(finlePathAndName);
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
	}
}
