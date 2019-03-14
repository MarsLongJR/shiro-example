概念：
一、基础概念知识
    1、身份验证：即证明用户的身份。一般的方式：主体（用户）身份ID（提供身份证、用户名/密码）
        注意：在shiro中需要提供principals(身份)和credentials（证明）给shiro，才能证明。
        即：身份认证=principals+credentials
    2、身份（principals）,即标识主体的属性。可以是任意一个属性：邮箱、用户名等。
        注意：一个用户可以有多个principals,但是主身份（即primary principal是唯一一个）
        一般来说主身份是用户名/密码/手机号
    3、证明（credentials）：即只用主体（用户）知道的安全值。如密码/数字证书等。
    4、最常见的principals和credentials组合就是用户名/密码了。
    5、主体（Subject）
    6、安全实体数据源（Realm）
二、代码
    1、详见：LoginLoutTest
    2、过程：
    2.1、首先通过new IniSecurityManagerFactory并指定一个ini配置文件来创建一个SecurityManager工厂；
    2.2、接着获取SecurityManager并绑定到SecurityUtils，这是一个全局设置，设置一次即可；
    2.3、通过SecurityUtils得到Subject，其会自动绑定到当前线程；
    如果在web环境在请求结束时需要解除绑定；然后获取身份验证的Token，如用户名/密码；
    2.4、调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录；
    2.5、如果身份验证失败请捕获AuthenticationException或其子类，常见的如：
     DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、
     UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、
     IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）
     等，具体请查看其继承关系；对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是
     “用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
    2.6、最后可以调用subject.logout退出，其会自动委托给SecurityManager.logout方法退出。
    3、身份验证步骤：
    1、收集用户身份和凭证，即用户和密码
    2、调用Subject.login进行登录，如果失败将得到相应的AuthenticationException异常，
    根据异常提示用户错误信息；否则登录成功；
    3、最后调用Subject.logout进行退出操作。
三、身份验证流程
    1、首先调用subject.login(token)来进行登录。自动委托给SEM，在调用前必须通过Security.setSecurityManage()
    设置
    2、SEM负责真正的逻辑。它会委托给认证器来认证。
    3、认证器才是真正的认证者。shiro API中核心的身份认证点。此处可以自己定义认证方法。
    4、认证器可以委托相应的认证器策略进行多数据源的验证。默认的模块化数据源验器会调用认证器策略
    进行多数据源认证。
    5、认证器会把对应的通行证传入数据源中，从数据源中获取身份验证信息。如果没有返回或者抛出异常则代表
    身份认证失败。此处可以配置多个数据源，按照相应的顺序和策略进行访问。
四、Realm（安全数据源）
    shiro从Realm中获得安全数据（用户、角色、权限），换句话说SEM要验证用户身份，那么它就需要从安全
    数据源中获取相应的用户进行比较以确定用户身份是否合法。也需要从数据源中获取相应用户的权限和角色进行验证
    用户是否可以操作。换句话说可以把realm看作一个数据源。