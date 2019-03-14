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
    详见：LoginLoutTest