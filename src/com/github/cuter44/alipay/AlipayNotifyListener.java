package com.github.cuter44.alipay;

import com.github.cuter44.alipay.resps.NotifyBase;

public interface AlipayNotifyListener
{
    /**
     * 1. 实现这个方法
     * 2. 添加监听器到网关
     * 3. 网关在收到通知时会顺序回调
     * 4. 返回 true 会阻止回调继续向下传递, 并向支付宝发送 success.
     * 注意. 通知是并发的请注意自行维护线程安全
     * 会传入实际的通知类型, 可以通过 instanceof 快速判断
     */
    public abstract boolean handle(NotifyBase notice);
}
