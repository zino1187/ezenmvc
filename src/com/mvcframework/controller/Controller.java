package com.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Ŭ���̾�Ʈ�� ��û�� ó���ϴ� ��� ��Ʈ�ѷ����� ���� �ڷ�������
//���� ���� Ŭ����...
public interface Controller {  //�ڷ���+��������
	//�� ��ü�� ��ӹ޴� ���~��Ʈ�ѷ��� �ݵ�� �Ʒ��� �޼�������� ������
	//�� �ϰ�, �ݵ���ڽſ��� �°� �������̵� �ϵ��� ��������!!1
	//��������!!!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public String getResultKey();//�������� �ڿ����� ������� �ʴ´�!!
	public boolean isForward();//������ ���θ� �������� �޼���!!
}








