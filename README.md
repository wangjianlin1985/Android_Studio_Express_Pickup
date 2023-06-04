# Android_Studio_Express_Pickup
安卓Android快递代拿跑腿APP设计可升级Studio毕业源码案例设计
## 开发环境: Myclipse/Eclipse/Idea都可以(服务器端) + Eclipse/AndroidStudio都可以(手机客户端) + mysql数据库
## 系统客户端和服务器端架构技术: 界面层，业务逻辑层，数据层3层分离技术，MVC设计思想！
## 服务器和客户端数据通信格式： XML格式(用于传输查询的记录集)和json格式(用于传输单个的对象信息)
## 人人快递APP主要分为发件和接件两大模块，主要具有如下功能：

a、手机号码注册，在线智能寄件;

b、在线申请快递员资格，审核通过便可同步接收身边的快件订单;

c、根据自己的行程，自主选择周边快件订单，及时取件，随程携带;

d、全程投保，在线订单实时跟踪;

e、在线电子签收、电子结算，数据信息历史保存;

f、费用及违禁寄品查询。

  快递代拿APP需要用户注册账号，注册登陆后可以添加个人基本信息。添加的基本信息中需要注明用户是哪个学校哪个班级等等。之后用户可以进行发布快递代拿信息和查询并选择接受快递代拿任务。当快递交到收件人手上时，点击确认进行付费。
## 实体ER属性如下：
用户: 用户名,登录密码,用户类型,姓名,性别,出生日期,用户照片,联系电话,邮箱,家庭地址,认证文件,审核状态,注册时间

快递代拿: 订单id,代拿任务,物流公司,运单号码,收货人,收货电话,收货备注,送达地址,代拿报酬,代拿状态,任务发布人,发布时间

物流公司: 公司id,公司名称

订单状态: 订单状态id,订单状态名称

代拿订单: 订单id,代拿的快递,接任务人,接单时间,订单状态,实时动态,用户评价

新闻公告: 公告id,标题,公告内容,发布时间 
