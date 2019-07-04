# unix-web-helper
## 简介
>&nbsp;&nbsp;&nbsp;&nbsp;unix-web-helper是一个拥有鉴权、shell命令执行、文件读写功能的Unix/Linux Web操作终端。 
## 程序运行
>&nbsp;&nbsp;&nbsp;&nbsp;直接使用命令 java -jar unix-web-helper-1.0-SNAPSHOT.jar 即可
## 鉴权
>&nbsp;&nbsp;&nbsp;&nbsp;用户输入密码（初始密码abc123保存在SecurityService类中），验证通过后才能进行下面操作。
![image](https://github.com/haoshen/unix-web-helper/blob/master/pic/passwd_verify.jpg)
## 执行命令
>&nbsp;&nbsp;&nbsp;&nbsp;输入命令(可以用$号代替项目根目录，使用命令"echo $"可以查看根目录的值)，点击回车后会显示执行结果（Exit Code表示执行结果返回码，默认为-100，一般为0表示正常，非0表示错误）
![image](https://github.com/haoshen/unix-web-helper/blob/master/pic/test_cmd.jpg)
## 文件编辑
>&nbsp;&nbsp;&nbsp;&nbsp;输入文件路径（同样可以使用$号代替项目根目录），点击加载后下方会显示文件内容。编辑完成后点击更新即可保存文件。
![image](https://github.com/haoshen/unix-web-helper/blob/master/pic/text_edit.jpg)
## More  
>&nbsp;&nbsp;&nbsp;&nbsp;如果您对本项目感兴趣，欢迎与我联系

