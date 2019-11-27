package com.badboyh2o.axis.client;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class Client {

	
	public static void main(String[] args) {
		try {
			
			// WSDL
			// TCP/IP monitor 8000 8080
			String wsdl = "http://localhost:8080/axis-manual-server/services/HelloService?wsdl";
 
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(wsdl);
			
			// WSDL里面描述的接口名称(要调用的方法)
			// 设置 QName (namespace,method)
			// call.setOperationName("sayHello"); //fail
			call.setOperationName(new QName("http://ws.baboyh2o.com", "sayHello"));
			
			// 接口方法的参数名, 参数类型,参数模式：  IN(输入), OUT(输出) or INOUT(输入输出)
			call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
			// 设置被调用方法的返回值类型
			call.setReturnType(XMLType.XSD_STRING);
			
			//设置方法中参数的值
			Object[] paramValues = new Object[] {"msg from client"};
			
			// 给方法传递参数，并且调用方法
			String result = (String) call.invoke(paramValues);	
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
	
}
