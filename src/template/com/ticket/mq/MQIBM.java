package com.ticket.mq;

import java.io.IOException;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQIBM {
	private MQQueueManager qMgr;      
    
    private void getConnMQmanager() {      
    	
        MQEnvironment.hostname = "10.0.6.19";// MQ服务器IP      
        MQEnvironment.channel = "SVRCONN.BAIDU";     // 队列管理器对应的服务器连接通道      
        MQEnvironment.CCSID = 1381;            // 字符编码      
        MQEnvironment.port = 1414;             // 队列管理器的端口号      
        try {      
            qMgr = new MQQueueManager("QMDMZ");// 队列管理器名称      
        } catch (MQException e) {      
            e.printStackTrace();      
        }      
    }      
      
    private void closeConnMQmanager() {      
        if (qMgr != null) {      
            try {      
                qMgr.close();      
            } catch (MQException e) {      
                e.printStackTrace();      
            }      
        }      
    }      
      
    public void sendMsg(String msgStr){      
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF|MQC.MQOO_OUTPUT|MQC.MQOO_INQUIRE;      
        MQQueue queue = null;      
        try {      
            //建立Q1通道的连接      
            queue = qMgr.accessQueue("test_d", openOptions, null, null,null);        
            MQMessage msg = new MQMessage();// 要写入队列的消息      
            msg.format = MQC.MQFMT_STRING;      
            msg.characterSet = 1381;      
            msg.writeObject(msgStr); //将消息写入消息对象中      
            MQPutMessageOptions pmo = new MQPutMessageOptions();      
            msg.expiry = -1;    // 设置消息用不过期      
            queue.put(msg, pmo);// 将消息放入队列      
        } catch (MQException e) {      
            e.printStackTrace();      
        } catch (IOException e) {      
            e.printStackTrace();      
        }finally{      
            if(queue!=null){      
                try {      
                    queue.close();      
                } catch (MQException e) {      
                    e.printStackTrace();      
                }      
            }      
        }      
    }      
           
    public void receiveMsg(String queueStr)      
    {      
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF|MQC.MQOO_OUTPUT|MQC.MQOO_INQUIRE;      
        MQQueue queue = null;      
        try {      
            queue = qMgr.accessQueue(queueStr, openOptions, null, null,null);      
                   
            System.out.println("该队列当前的深度为:"+queue.getCurrentDepth());      
            System.out.println("===========================");      
            int depth = queue.getCurrentDepth();      
            //将队列的里的消息读出来      
            while(depth-->0)      
            {      
                MQMessage msg = new MQMessage();// 要读的队列的消息      
                MQGetMessageOptions gmo = new MQGetMessageOptions();      
                queue.get(msg, gmo);      
                System.out.println("消息的大小为："+msg.getDataLength());      
                System.out.println("消息的内容：\n"+msg.readLine());      
                System.out.println("---------------------------");      
            }      
        } catch (MQException e) {      
            e.printStackTrace();      
        } catch (Exception e) {      
            e.printStackTrace();      
        }finally{      
            if(queue!=null){      
                try {      
                    queue.close();      
                } catch (MQException e) {      
                    e.printStackTrace();      
                }      
            }      
        }      
    }      
           
    public static void main(String[] args) {      
    	MQIBM mm = new MQIBM();      
        mm.getConnMQmanager();      
        try {      
//            mm.sendMsg("CHANGJIU COMPANY FIRST TEST MESSAGE TO FJDA");      
//            mm.sendMsg("CHANGJIU COMPANY SECOND TEST MESSAGE TO FJDA");     
        	
//        	while(true){
        		
        		mm.receiveMsg("UMETRIP.KMG.YNBAIDU");
//        		mm.receiveMsg("BAIDU_D_OUT");
        		try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
//        	}
        } catch (Exception e) {      
            e.printStackTrace();      
        }      
        mm.closeConnMQmanager();      
    }   
}
