# Log4jPatch
通过Java Agent 免重启修复Log4j漏洞
## 注意
该方式**在重启服务后会失效**，所以只能作为漏洞的缓解方案
## 原理
 通过Java Agent检测Hook `JndiLookup#lookup`，当执行到该函数直接返回
 ![image](https://user-images.githubusercontent.com/32150850/146130504-9b4d61a2-a4a0-482c-81bd-67e84002f07b.png)

## 使用
通过java自带的`jps`命令找到服务的进程ID，一般Tomcat的进程名为` Bootstrap`。

![image](https://user-images.githubusercontent.com/32150850/146130537-c1bc0c7f-0de5-4eb6-890d-7416f20314e1.png)
运行 java -jar Log4j-Agent.jar 进程ID  添加Agent，注入成功会返回Patch Success
![image](https://user-images.githubusercontent.com/32150850/146130673-eefec3a3-1405-4de7-a241-519aff3c452c.png)
修复成功后再去触发，会返回Warning JNDI
