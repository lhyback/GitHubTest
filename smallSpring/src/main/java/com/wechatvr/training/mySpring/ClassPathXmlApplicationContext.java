package com.wechatvr.training.mySpring;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;



public class ClassPathXmlApplicationContext implements BeanFactory {
	//Map存各个实例的键值对  <String类名，Object对象实例>
    private Map<String, Object> beans = new HashMap<String, Object>();
    //构造方法
    public ClassPathXmlApplicationContext() throws Exception {
    	    SAXBuilder sb = new SAXBuilder();
    	    //从当前类加载路径取得xml文件的输入流
    	    Document document = sb.build(this.getClass().getClassLoader().getResourceAsStream("my-config.xml"));
    	    System.out.println("读取xml完毕");
    	    Element root = document.getRootElement();
    	    //获取根元素下所有object子元素
    	    List<Element> list = root.getChildren("object");
    	    //遍历所有object元素
    	    for(Element element : list) {
    	    	    //获取id属性的值
    	    	    String id = element.getAttributeValue("id");
    	    	    //获取class属性的值
    	    	    String clazz = element.getAttributeValue("class");
    	    	    
    	    	    
    	    	    System.out.println(id);
    	    	    System.out.println(clazz);
    	    	    Object object = Class.forName(clazz).newInstance();
    	    	    
    	    	    //容器中注入该类
    	    	    beans.put(id, object);
    	    	    //解析每个Object节点的filed子节点
    	    	    List<Element> childrenList = (List<Element>) element.getChildren("filed");
    	    	    for (Element filedElement : childrenList) {
    	    	    		String name = filedElement.getAttributeValue("name");  //这的name相当于属性名（property:name）
    	    	    		String value = filedElement.getAttributeValue("value");  //这的value指属性引用的类名（property:ref）
    	    	    		//取得要注入的实例 -- 这里根据xml animal对应的是Dog实例
    	    	    		Object beanObject = beans.get(value);
    	    	    		
    	    	    		System.out.println("value:" + value);
    	    	    		
    	    	    		//取得相应属性的setter方法名,驼峰式
    	    	    		String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
    	    	    		System.out.println("set方法名：" + methodName);
    	    	    		//利用反射setter完成引用的设值注入(将beanObject注入object)
    	    	    		Method method = object.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
    	    	    		//java中定义的是people.setPet(Animal pet)，而这里的beanObject是Dog,Dog实现的第一个接口就是Animal
    	    	    		method.invoke(object, beanObject);
    	    	    		
			}
    	    	    
    	    	    //调用startup方法放在 解析xml文件的最后, 因为要保证该注入的bean都注入了，invoke时不会空指针报错
    	    	    //获取startup-method属性值
    	    	    String startupMethod = element.getAttributeValue("startup-method");
    	    	    if(startupMethod != null) {
    	    	    	    System.out.println("调用自启方法");
    	    	    	    Method startupMtd = Class.forName(clazz).getMethod(startupMethod);
    	    	    	    startupMtd.invoke(object);
    	    	    }
    	    	    else {
    	    	    	    System.out.println("没有自启方法");
    	    	    }
    	    }
    }
    
    
	public Object getBean(String id) {	
		return beans.get(id);
	}

}
