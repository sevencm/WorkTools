package seven.tools.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Dom4jUtils {
	
	/**
	 * 修改XML属性
	 * @param xmlDocument xml文档
	 * @param selectNodes 选择的节点  "//context"
	 * @param attributeName 属性值
	 * @param attributeValue 属性名称
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	public static void modifyXMLAttribute(Document xmlDocument,String selectNodes,String attributeName, String attributeValue) throws DocumentException{
		List<?> list = xmlDocument.selectNodes(selectNodes);
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()){
			Element ele = (Element) iterator.next();
			Iterator<Attribute> it = ele.attributeIterator();
			while(it.hasNext()){
				Attribute attr =  it.next();
				if(attr.getName().equals(attributeName))
					attr.setValue(attributeValue); 
			}
		}
		
		iterator = list.iterator();
		while(iterator.hasNext()){
			Element ele = (Element) iterator.next();
			Iterator<Attribute> it = ele.attributeIterator();
			while(it.hasNext()){
				Attribute attr =  it.next();
				System.out.println(attr.getName() + "     " + attr.getValue());
			}
		}
	}
	
	/**
	 * 写入文件
	 * @param xmlDocument
	 * @param finlePathAndName src/xxx/xxx.xml路径
	 * @throws IOException
	 */
	public static void writeXml(Document xmlDocument,String finlePathAndName) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		File file = new File(finlePathAndName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
		XMLWriter output = new XMLWriter(new FileWriter(file), format);
		output.write(xmlDocument);
		output.close();
	}
	
	/**
	 * 读取XML
	 * @param inputXmlStream
	 * @return
	 * @throws DocumentException
	 */
	public static Document readXml(InputStream inputXmlStream) throws DocumentException{
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputXmlStream);
		return document;
	}
	
}
