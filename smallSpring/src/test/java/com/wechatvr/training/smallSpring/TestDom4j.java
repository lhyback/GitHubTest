package com.wechatvr.training.smallSpring;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;


public class TestDom4j {
     @Test
     public void readXml() {
    	 	SAXReader reader = new SAXReader();
    	 	try {
    	 		//获取document对象
    	 		Document document = reader.read(new File("/Users/lhy/project/eclipseTraining/smallSpring/resources/my-config.xml"));
    	 		//获取根节点
    	 		Element root = document.getRootElement();
    	 		//获取根节点的迭代器
    	 		Iterator it = root.elementIterator();
    	 		//迭代器遍历,获取根节点中的Object信息
    	 		while (it.hasNext()) {
    	 			System.out.println("===开始遍历某个Object===");
    	 			Element object = (Element) it.next();
    	 			System.out.println("节点名: "+object.getName());
    	 			//获取该Object的所有属性
    	 			List<Attribute> objAttrs = object.attributes();
    	 			//输出该Object的属性
    	 			for (Attribute attr : objAttrs) {
    	 				System.out.println("属性名: " + attr.getName() + "--属性值: " + attr.getValue());
    	 			}
    	 			//解析该Object的子节点
    	 			Iterator<Element> itt = object.elementIterator();
    	 			
    	 			while (itt.hasNext()) {
    	 				Element objectChild = (Element) itt.next();
    	 				System.out.println("子节点名: " + objectChild.getName());
    	 				//element.getStringValue会返回当前节点的子孙节点中所有文本内容连接成的字符串
    	 				//System.out.println("节点名: " + objectChild.getName() + "--节点值: " + objectChild.getStringValue());
    	 			    List<Attribute> objChildAttrs = objectChild.attributes();
    	 			   for (Attribute attr : objChildAttrs) {
       	 				System.out.println("子节点属性名: " + attr.getName() + "--子节点属性值: " + attr.getValue());
       	 			}
    	 			}
    	 			System.out.println("===结束遍历某个Object===" + "\n");
    	 		}
    	 	}
    	 	catch (DocumentException e) {
    	 		e.printStackTrace();
    	 	}
     }
}
